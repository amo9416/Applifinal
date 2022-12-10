package com.example.test_code_api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InscriptionActivity extends AppCompatActivity {

    private Button mbutton;
    private TextView mTextView;
    private EditText mLogin;
    private EditText mpassword;

    private static final String SHARED_PREF_USER_INFO = "SHARED_PREF_USER_INFO";
    private static final String SHARED_PREF_USER_INFO_ID = "SHARED_PREF_USER_INFO_ID";
    private static final String SHARED_PREF_USER_INFO_PWD = "SHARED_PREF_USER_INFO_PWD";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        mbutton = findViewById(R.id.button);
        mTextView = findViewById(R.id.textView);
        mLogin = findViewById(R.id.login);
        mpassword = findViewById(R.id.password);

        String id = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).getString(SHARED_PREF_USER_INFO_ID, null);
        String pwd = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).getString(SHARED_PREF_USER_INFO_PWD, null);

        mLogin.setText(id);
        mpassword.setText(pwd);

        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE)
                        .edit()
                        .putString(SHARED_PREF_USER_INFO_ID, mLogin.getText().toString())
                        .putString(SHARED_PREF_USER_INFO_PWD, mpassword.getText().toString())
                        .apply();
            }
        });
    }
}