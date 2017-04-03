package com.example.moksleivis.pokeris;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.R.string.cancel;

public class MainActivity extends AppCompatActivity {
    private EditText mUsernameView;
    private EditText mPasswordView;

    private    String username;
    private    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUsernameView = (EditText) findViewById(R.id.prisijungimoVardas);
        mPasswordView = (EditText) findViewById(R.id.slaptazodis);
        mUsernameView.setError(null);
        mPasswordView.setError(null);

        Button mygtukasPrisijungimas = (Button) findViewById(R.id.prisijungti);
        mygtukasPrisijungimas.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                prisijungimas2();

            }
        });


        Button mygtukasRegistracija = (Button) findViewById(R.id.registracija);
        mygtukasRegistracija.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                registracija2();
            }
        });

    }
    public void prisijungimas2() {

        username =mUsernameView.getText().toString();
        password =mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        Toast.makeText(MainActivity.this,username + "" + password,
                Toast.LENGTH_SHORT).show();

        if (!isValid(username)) {
            mUsernameView.setError(getString(R.string.error_invalid_username));
            focusView = mUsernameView;
            cancel = true;
        }
        if (!isValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        } else {
            Intent intent = new Intent(MainActivity.this, prisijungimas.class);
            startActivity(intent);
        }
    }
    public void registracija2(){
        Intent intent = new Intent (MainActivity.this, registracija.class);
        startActivity(intent);
    }

    private boolean isValid(String credentials){

        final String CREDENTIALS_PATTERN = "^([0-9a-z]{3,15})+$";
        Pattern pattern = Pattern.compile(CREDENTIALS_PATTERN);

        Matcher matcher = pattern.matcher(credentials);
        return matcher.matches();
    }
}
