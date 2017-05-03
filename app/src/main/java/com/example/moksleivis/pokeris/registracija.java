package com.example.moksleivis.pokeris;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class registracija extends AppCompatActivity {

    private EditText mUsernameView;
    private EditText mPasswordView;
    private EditText mEmailView;

    private    String username;
    private    String password;
    private    String email;
    private static final String REGISTER_URL = "http://mantas.byethost31.com/Mobile/register.php";
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
        if (!isEmailValid(email)) {
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

          //String vardas, String pastas, String password
            User vartotojas = new User (username,email,password);
            registerUser(vartotojas.getVardas(), vartotojas.getPassword(), vartotojas.getPastas());
            Toast.makeText(registracija.this,
                            vartotojas.getVardas() +"\n" +
                            vartotojas.getPastas() + "\n" +
                            vartotojas.getPassword() + "\n"
                             ,Toast.LENGTH_LONG).show() ;
            Intent intent = new Intent(registracija.this, MainActivity.class);
            startActivity(intent);
    }
    }
    private void registerUser(String username, String password, String email) {
        String urlSuffix = "?username="+username+"&password="+password+"&email="+email;
        class RegisterUser extends AsyncTask<String, Void, String> {

            ProgressDialog loading;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(registracija.this, "Prašome palaukti",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {
                String s = params[0];
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(REGISTER_URL+s);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();

                    // byethost naudoja antibot sistema, todel reikia kiekvienam rankutėmis suvesti cookie turinį,
                    // kuris pas kiekviena bus skirtingas. kaip tai padaryti zemiau nuoroda
                    // http://stackoverflow.com/questions/31912000/byethost-server-passing-html-values-checking-your-browser-with-json-string
                    con.setRequestProperty("Cookie", "__test=7a4d917e220fbf9a55cab3046bd1a3b7; expires=2038 m. sausio 1 d., penktadienis 01:55:55; path=/");
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String result;

                    result = bufferedReader.readLine();

                    return result;
                }catch(Exception e){
                    return null;
                }
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(urlSuffix);
    }
    public boolean isEmailValid(String email) {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if(matcher.matches())
            return true;
        else
            return false;
    }
}


