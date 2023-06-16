package com.example.capstone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.capstone.API.ApiConfig;
import com.example.capstone.API.Data;
import com.example.capstone.API.DetailResponse;
import com.example.capstone.Preferences.SharedPreference;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    TextView fruitName, latinName, desc, nutrient, calcium, vitaminc;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        fruitName = findViewById(R.id.txt_detail_name);
        latinName = findViewById(R.id.txt_detail_latin_name);
        desc = findViewById(R.id.txt_detail_desc);
        nutrient = findViewById(R.id.txt_detail_nutrient_table);
        img = findViewById(R.id.img_detail);
        calcium = findViewById(R.id.txt_detail_nutrient_table_calcium);
        vitaminc = findViewById(R.id.txt_detail_nutrient_table_vitaminc);

        getData(getIntent().getStringExtra("id"));
    }

    private void getData(String id) {
        SharedPreference sharedPreference = new SharedPreference(this);
        Call<DetailResponse> client = ApiConfig.getApiService().getDescription(sharedPreference.getKeyToken(),id);
        client.enqueue(new Callback<DetailResponse>() {
            @Override
            public void onResponse(Call<DetailResponse> call, Response<DetailResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        fruitName.setText(response.body().getData().getName());
                        latinName.setText(response.body().getData().getScientificName());
                        desc.setText(response.body().getData().getDescription());
                        Glide.with(getApplicationContext()).
                                load(response.body().getData().getImage()).
                                into(img);
                        nutrient.setText(response.body().getData().getPotassium());
                        calcium.setText(response.body().getData().getCalcium());
                        vitaminc.setText(response.body().getData().getVitaminC());
                    }
                } else {
                    if (response.body() != null) {
                        Log.e(TAG, "onFailure: ");
                    }
                }
            }

            @Override
            public void onFailure(Call<DetailResponse> call, Throwable t) {

            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_item1) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            return true;
        }else if (item.getItemId() == R.id.action_item2) {
            Intent intent = new Intent(getApplicationContext(), PredictActivity.class);
            startActivity(intent);
            return true;
        }else if (item.getItemId() == R.id.action_item3) {
            Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
            startActivity(intent);
            return true;
        }else if (item.getItemId() == R.id.action_item4) {
            SharedPreference sharedPreference = new SharedPreference(this);
            sharedPreference.removeKeyToken();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            return true;
        }else {
            return true;
        }
    }
}