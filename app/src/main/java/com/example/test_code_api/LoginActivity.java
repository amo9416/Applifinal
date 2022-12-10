package com.example.test_code_api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText mUsername;
    private EditText mPassword;
    private Button mBtnConnexion;

    String sharedPrefId;
    String sharedPrefPwd;

    private static final String SHARED_PREF_USER_INFO = "SHARED_PREF_USER_INFO";
    private static final String SHARED_PREF_USER_INFO_ID = "SHARED_PREF_USER_INFO_ID";
    private static final String SHARED_PREF_USER_INFO_PWD = "SHARED_PREF_USER_INFO_PWD";
    private boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mBtnConnexion = findViewById(R.id.buttonConnexion);
        mPassword = findViewById(R.id.passwordConnexion);
        mUsername = findViewById(R.id.usernameConnexion);


        sharedPrefId = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).getString(SHARED_PREF_USER_INFO_ID, null);
        sharedPrefPwd = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).getString(SHARED_PREF_USER_INFO_PWD, null);


        mBtnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isAllFieldsChecked = checkAccessInPreferences();
                if (isAllFieldsChecked) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public boolean checkAccessInPreferences() {
        if (!mUsername.getText().toString().equals(sharedPrefId)) {
            mUsername.setError("username or password are not correct");
            return false;
        }
        if (!mPassword.getText().toString().equals(sharedPrefPwd)) {
            mPassword.setError("username or password are not correct");
            return false;
        }
        return true;
    }
}