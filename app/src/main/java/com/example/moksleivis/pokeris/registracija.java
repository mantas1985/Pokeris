package com.example.moksleivis.pokeris;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class registracija extends AppCompatActivity {

    private EditText mUsernameView;
    private EditText mPasswordView;
    private EditText mEmailView;

    private    String username;
    private    String password;
    private    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registracija);

        mUsernameView = (EditText) findViewById(R.id.VartotojoVardas);
        mPasswordView = (EditText) findViewById(R.id.Slaptazodis);
        mEmailView = (EditText) findViewById(R.id.pastas);

        mUsernameView.setError(null);
        mPasswordView.setError(null);
        mEmailView.setError(null);

        Button mygtukasPatvirtinti = (Button) findViewById(R.id.registracija);
        mygtukasPatvirtinti.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                tikrintiRegistracija();

            }
        });

    }
    private boolean isValid(String credentials) {
        if (credentials.isEmpty())
        {
            return false;
        }
        return true;
    }

    private  void tikrintiRegistracija()
    {
        boolean cancel = false;
        View focusView = null;

        username =mUsernameView.getText().toString();
        password =mPasswordView.getText().toString();
        email =mEmailView.getText().toString();

        if (!isValid(username)) {
            mUsernameView.setError(getString(R.string.error_invalid_username));
            focusView = mUsernameView;
            cancel = true;
        }
        if (!isValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_Elpastas));
            focusView = mEmailView;
            cancel = true;
        }
        if (!isValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }
        if (cancel) {
        focusView.requestFocus();
    }else {
        Intent intent = new Intent(registracija.this, MainActivity.class);
        startActivity(intent);
    }
    }

}


