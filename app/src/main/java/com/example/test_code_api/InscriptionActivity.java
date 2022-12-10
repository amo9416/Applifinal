package com.example.test_code_api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    private EditText mPassword;
    private boolean isAllFieldsChecked = false;

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
        mPassword = findViewById(R.id.password);

        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isAllFieldsChecked = CheckAllFields();
                if (isAllFieldsChecked) {
                    getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE)
                            .edit()
                            .putString(SHARED_PREF_USER_INFO_ID, mLogin.getText().toString())
                            .putString(SHARED_PREF_USER_INFO_PWD, mPassword.getText().toString())
                            .apply();
                    Intent intent = new Intent(InscriptionActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean CheckAllFields() {
        if (mLogin.length() == 0) {
            mLogin.setError("This field is required");
            return false;
        }

        if (mPassword.length() == 0) {
            mPassword.setError("Password is required");
            return false;
        } else if (mPassword.length() < 8) {
            mPassword.setError("Password must be minimum 8 characters");
            return false;
        }
        return true;
    }
}