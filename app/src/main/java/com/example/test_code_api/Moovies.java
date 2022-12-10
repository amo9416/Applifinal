package com.example.test_code_api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.test_code_api.ui.main.MooviesFragment;

public class Moovies extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moovies);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MooviesFragment.newInstance())
                    .commitNow();
        }
    }
}