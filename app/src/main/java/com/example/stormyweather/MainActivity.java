package com.example.stormyweather;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.telephony.AvailableNetworkInfo;
import android.util.Log;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
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
        String url = "https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&appid=" + Api;

        if (isNetworkAvailable()) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {

                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    try {
                        //Response response = call.execute();
                        if (response.isSuccessful()) {
                            Log.v(TAG, "" + response.body().string());
                        } else {
                            DialoagueAboutError();
                        }
                    } catch (IOException e) {
                        Log.e(TAG, "IOException caught !!");
                        e.printStackTrace();
                    }
                }
            });
            Log.d(TAG, "Main Code is running");
        }
    }

    //@RequiresApi(api = Build.VERSION_CODES.M)
    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        //Network networkInfo = manager.getActiveNetwork();
        NetworkInfo networkInfo1 = manager.getActiveNetworkInfo();
        Boolean isAvailabe = false;
        if(networkInfo1 != null && networkInfo1.isConnected()){
            isAvailabe = true;
        }else{
            Toast.makeText(this, "Sorry Network is not Available..please Connect to Internet. ", Toast.LENGTH_SHORT).show();
        }
        return isAvailabe;
    }

    private void DialoagueAboutError() {
        AlertDialogueFragment dialogueFragment = new AlertDialogueFragment();
        //dialogueFragment.show(getFragmentManager(), "error dialogue");
        dialogueFragment.show(getSupportFragmentManager(), "error");
    }
}