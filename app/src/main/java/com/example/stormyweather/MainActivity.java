package com.example.stormyweather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String Api = "61ae7a3c662bbfba12a6a4c1bb349f43";
        double lat = 23.7660;
        double lon = 90.3586;
        String url = "api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&appid="+Api;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            if(response.isSuccessful()){
                Log.v(TAG, ""+response.body().string());
            }
        } catch (IOException e) {
            Log.e(TAG, "IOException caught !!");
            e.printStackTrace();
        }

    }
}