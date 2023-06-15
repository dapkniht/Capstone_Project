package com.example.capstone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.capstone.API.ApiConfig;
import com.example.capstone.API.PredictResponse;
import com.example.capstone.Preferences.SharedPreference;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.FileOutputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PredictActivity extends AppCompatActivity {

    private static final String TAG = PredictActivity.class.getSimpleName();
    int img_gallery;
    private ImageView imageView;
    private Button photoButton, galleryButton, uploadButton;
    private TextView txtPredict, txtName;
    File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predict);

        imageView = findViewById(R.id.img_predict);
        photoButton = findViewById(R.id.btn_camera);
        galleryButton = findViewById(R.id.btn_gallery);
        uploadButton = findViewById(R.id.btn_predict);
        txtPredict = findViewById(R.id.txt_prog_predict);
        txtName = findViewById(R.id.txt_product_name);

        if (ContextCompat.checkSelfPermission(PredictActivity.this, android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(PredictActivity.this, new String[]{
                    Manifest.permission.CAMERA
            }, 100);
        }

        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 100);
            }
        });

        galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent, "Select Picture"), img_gallery);
            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                predictFruit();
            }
        });
    }

    private File bitmapToFile(Bitmap bitmap) {
        File file1 = new File(getCacheDir(), "temp_img.jpg");
        try {
            FileOutputStream outputStream = new FileOutputStream(file1);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 5, outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file1;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
            Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            file = bitmapToFile(bitmap);
        }

        if (resultCode == RESULT_OK) {
            if (requestCode == img_gallery) {
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    imageView.setImageURI(selectedImageUri);
                    Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                    file = bitmapToFile(bitmap);
                }
            }
        }
    }

    void predictFruit() {
        if (file != null) {
            File getFile = file;
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), getFile);

            MultipartBody.Part body = MultipartBody.Part.createFormData(
                    "image",
                    getFile.getName(),
                    requestBody
            );

            SharedPreference sharedPreference = new SharedPreference(this);
            Call<PredictResponse> client = ApiConfig.getApiService().getPredict(sharedPreference.getKeyToken(),body);
            client.enqueue(new Callback<PredictResponse>() {
                @Override
                public void onResponse(Call<PredictResponse> call, Response<PredictResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {

                            txtPredict.setText(response.body().getData().getPredict());
                            txtPredict.setBackgroundResource(R.drawable.bg_predict_false);
                            txtName.setText(response.body().getData().getFruit());
                            Toast.makeText(PredictActivity.this, "Prediksi Berhasil", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(PredictActivity.this, "Prediksi Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<PredictResponse> call, Throwable t) {
                    Log.e(TAG, "onFailure: " + t.getMessage());
                }
            });
        } else {

        }
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