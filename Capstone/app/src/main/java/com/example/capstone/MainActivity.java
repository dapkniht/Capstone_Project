package com.example.capstone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.capstone.API.ApiConfig;
import com.example.capstone.API.DataItem;
import com.example.capstone.API.FruitResponse;
import com.example.capstone.Adapter.HomeAdapter;
import com.example.capstone.Preferences.SharedPreference;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recv_home);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        recyclerView.addItemDecoration(itemDecoration);

        getData();

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        if (searchManager != null) {
            SearchView searchView = (SearchView) findViewById(R.id.searchview);
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    getDataSearch(s);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    return false;
                }
            });
        }

    }

    private void getData() {
        SharedPreference sharedPreference = new SharedPreference(this);
        Call<FruitResponse> client = ApiConfig.getApiService().getAllFruit(sharedPreference.getKeyToken());
        client.enqueue(new Callback<FruitResponse>() {
            @Override
            public void onResponse(Call<FruitResponse> call, Response<FruitResponse> response) {
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
            public void onFailure(Call<FruitResponse> call, Throwable t) {

            }
        });
    }

    private void getDataSearch(String name) {
        SharedPreference sharedPreference = new SharedPreference(this);
        Call<FruitResponse> client = ApiConfig.getApiService().getAllFruitByName(sharedPreference.getKeyToken(),name);
        client.enqueue(new Callback<FruitResponse>() {
            @Override
            public void onResponse(Call<FruitResponse> call, Response<FruitResponse> response) {
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
            public void onFailure(Call<FruitResponse> call, Throwable t) {
            }
        });
    }

    private void setFruitData(List<DataItem> data) {
        ArrayList<DataItem> listItem = new ArrayList<DataItem>();
        for (DataItem item : data) {
            DataItem data1 = new DataItem();
            data1.setId(item.getId());
            data1.setName(item.getName());
            data1.setImage(item.getImage());
            listItem.add(data1);
        }
        HomeAdapter adapter = new HomeAdapter(listItem);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickCallback(data1 -> {
            Intent intentToDetail = new Intent(MainActivity.this, DetailActivity.class);
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