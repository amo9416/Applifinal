package com.example.test_code_api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InscriptionActivity extends AppCompatActivity {

    private Button mbutton;
    private TextView mTextView;
    private EditText mLogin;
    private EditText mpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        mbutton = findViewById(R.id.button);
        mTextView = findViewById(R.id.textView);
        mLogin = findViewById(R.id.login);
        mpassword = findViewById(R.id.password);

    }
}