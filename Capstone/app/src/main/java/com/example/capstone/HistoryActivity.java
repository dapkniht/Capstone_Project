package com.example.capstone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.capstone.API.ApiConfig;
import com.example.capstone.API.DataItem;
import com.example.capstone.API.History;
import com.example.capstone.API.HistoryResponse;
import com.example.capstone.Adapter.HistoryAdapter;
import com.example.capstone.Adapter.HomeAdapter;
import com.example.capstone.Preferences.SharedPreference;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity {
    private static final String TAG = HistoryActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.recv_history);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        recyclerView.addItemDecoration(itemDecoration);

        getData();
    }

    private void getData() {
        SharedPreference sharedPreference = new SharedPreference(this);
        Call<HistoryResponse> client = ApiConfig.getApiService().getHistory(sharedPreference.getKeyToken());

        client.enqueue(new Callback<HistoryResponse>() {
            @Override
            public void onResponse(Call<HistoryResponse> call, Response<HistoryResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        setFruitData(response.body().getData());
                    }
                } else {
                    if (response.body() != null) {
                        Log.e(TAG, "onFailure: ");
                    }
                }
            }

            @Override
            public void onFailure(Call<HistoryResponse> call, Throwable t) {

            }
        });
    }

    private void setFruitData(List<History> data) {
        ArrayList<History> listItem = new ArrayList<History>();
        for (History item : data) {
            History data1 = new History();
            data1.setId(item.getId());
            data1.setName(item.getName());
            data1.setImage(item.getImage());
            data1.setPredict(item.getPredict());
            listItem.add(data1);
        }
        HistoryAdapter adapter = new HistoryAdapter(listItem);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickCallback(data1 -> {
            Intent intentToDetail = new Intent(HistoryActivity.this, DetailActivity.class);
            intentToDetail.putExtra("id", data1.getId());
            startActivity(intentToDetail);
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