package com.canozgan.rickandmorty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import com.canozgan.rickandmorty.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {
    ActivitySplashBinding binding;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        View view =binding.getRoot();
        setContentView(view);
        sharedPreferences = this.getSharedPreferences("com.canozgan.rickandmorty", Context.MODE_PRIVATE);
        boolean isFirst =sharedPreferences.getBoolean("isFirst",true);
        if(isFirst){
            binding.textView.setText("Welcome!");
            sharedPreferences.edit().putBoolean("isFirst",false).apply();
        }
        else{
            binding.textView.setText("Hello!");
        }
        new CountDownTimer(5000,1000){
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent intentToMainActivity = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intentToMainActivity);
                finish();
            }
        }.start();
    }
}