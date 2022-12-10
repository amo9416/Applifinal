package com.example.test_code_api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegistrationActivity extends AppCompatActivity {

    private Button buttonInscription;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        buttonInscription = findViewById(R.id.buttonInscription);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inscriptionIntent = new Intent(RegistrationActivity.this, InscriptionActivity.class);
                startActivity(inscriptionIntent);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(loginIntent);
            }
        });

    }
}