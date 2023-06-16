package com.example.capstone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.capstone.API.ApiConfig;
import com.example.capstone.API.LoginRequest;
import com.example.capstone.API.LoginResponse;
import com.example.capstone.Preferences.SharedPreference;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();
    private TextView btnToRegister;
    private EditText username, password;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        btnToRegister = findViewById(R.id.btn_login_register);
        username = findViewById(R.id.edt_login_username);
        password = findViewById(R.id.edt_login_password);
        button = findViewById(R.id.btn_login);
        SharedPreference pref = new SharedPreference(getApplicationContext());

        if (!pref.getKeyToken().isEmpty()) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }

        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.setCookie("https://ready2eat-backend-kkszfyhisa-et.a.run.app", "token");

        btnToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RequestBody email = RequestBody.create(MediaType.parse("application/json"), username.getText().toString());
                RequestBody pass = RequestBody.create(MediaType.parse("application/json"), password.getText().toString());

                LoginRequest request = new LoginRequest(username.getText().toString(),password.getText().toString());
                Call<LoginResponse> client = ApiConfig.getApiService().getLogin(request);
                client.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getMessage().equalsIgnoreCase("Success")) {
                                pref.setKeyToken("Bearer " + response.body().getData().getAccessToken());
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            if (response.body() != null) {
                                Log.e(TAG, "onFailure: ");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {

                    }
                });
            }
        });
    }
}