package com.example.moksleivis.pokeris;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private EditText mUsernameView;
    private EditText mPasswordView;

    private String username;
    private String password;
    private Loginas objektas;

    private CheckBox rememberMeCheckBox;

    private static final String REGISTER_URL = "http://mantas.byethost31.com/Mobile/LoginConfirmation.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUsernameView = (EditText) findViewById(R.id.prisijungimoVardas);
        mPasswordView = (EditText) findViewById(R.id.slaptazodis);
        rememberMeCheckBox = (CheckBox) findViewById(R.id.remember_me);
        objektas = new Loginas(getApplicationContext());
        rememberMeCheckBox.setChecked(objektas.isRemembered());

        if (objektas.isRemembered()) {
            mUsernameView.setText(objektas.getUsername(), TextView.BufferType.EDITABLE);
            mPasswordView.setText(objektas.getPassword(), TextView.BufferType.EDITABLE);
        } else {

            mUsernameView.setText("", TextView.BufferType.EDITABLE);
            mPasswordView.setText("", TextView.BufferType.EDITABLE);
        }
        mUsernameView.setError(null);
        mPasswordView.setError(null);
//kazkas
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

        username = mUsernameView.getText().toString();
        password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Toast.makeText(MainActivity.this,username + "" + password,
        // Toast.LENGTH_SHORT).show();

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
            if (rememberMeCheckBox.isChecked()) {
                objektas.setUsername(username);
                objektas.setPassword(password);
                objektas.setRemembered(true);

            } else {
                objektas.setUsername(username);
                objektas.setPassword(password);
                objektas.setRemembered(false);

            }
           /* */
            autenifikacija(objektas);
        }
    }

    public void registracija2() {
        Intent intent = new Intent(MainActivity.this, registracija.class);
        startActivity(intent);
    }

    private boolean isValid(String credentials) {

        final String CREDENTIALS_PATTERN = "^([0-9a-zA-Z]{3,15})+$";
        Pattern pattern = Pattern.compile(CREDENTIALS_PATTERN);

        Matcher matcher = pattern.matcher(credentials);
        return matcher.matches();
    }

    private void autenifikacija(final Loginas objektas){

    class NewEntry extends AsyncTask<String, Void, String> {
        ProgressDialog loading;
        DB database = new DB();


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = ProgressDialog.show(MainActivity.this, "Please Wait", null, true, true);
        }

        @Override
        protected void onPostExecute(String s) {
          if(s.equals("203")){
              Toast.makeText(getApplicationContext(),"Vartotojo vardas arba slaptazodis neteisingas",Toast.LENGTH_LONG).show();
          }  else{
              Intent intent = new Intent(MainActivity.this, SearchActivity.class);
              startActivity(intent);
          }
            super.onPostExecute(s);
            loading.dismiss();
            /*Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();*/
        }

        @Override
        protected String doInBackground(String... params) {
            //pirmas stringas raktas antras reiksme
            HashMap<String, String> data = new HashMap<String, String>();
            data.put("username", params[0]);
            data.put("password", params[1]);


            String result = database.sendPostRequest(REGISTER_URL, data);

            return result;
        }
    }

    NewEntry ru = new NewEntry();
        ru.execute(objektas.getUsername(),objektas.getPassword());
}
}
