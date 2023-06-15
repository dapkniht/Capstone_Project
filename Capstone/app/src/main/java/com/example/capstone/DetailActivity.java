package com.example.capstone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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
    TextView fruitName, latinName, desc, nutrient;
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
                        nutrient.setText(response.body().getData().getDescription());
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
}