package com.example.moksleivis.pokeris;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class naujas_irasasActivity extends AppCompatActivity {

    private EditText Naujas_vardas;
    private static final String REGISTER_URL = "http://mantas.byethost31.com/Mobile/newEntry.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.naujas_irasas);

        final Spinner tipai = (Spinner) findViewById(R.id.tipaiSelect);
        List<String> list = new ArrayList<String>();
        list.add(getResources().getString(R.string.tipas_Mokamas));
        list.add(getResources().getString(R.string.Tipas_Nemokamas));
        list.add(getResources().getString(R.string.tipas_Turnyras));
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipai.setAdapter(dataAdapter);

     Naujas_vardas = (EditText) findViewById(R.id.naujas_Vardas);
        Naujas_vardas.setError(null);

    final CheckBox gerimasViskis = (CheckBox) findViewById(R.id.gerimasViskis);
    final CheckBox gerimasAlus = (CheckBox) findViewById(R.id.gerimasAlus);
    final CheckBox gerimasCocaCola = (CheckBox) findViewById(R.id.gerimasCocaCola);
    final CheckBox gerimasSultys = (CheckBox) findViewById(R.id.gerimasSultys);

    final RadioGroup patvirtinimai = (RadioGroup) findViewById(R.id.patvirtinimaiRadioGroup);
    final RadioButton[] patvirtinimas = new RadioButton[1];

    Button saugoti = (Button) findViewById(R.id.submitButton);
    saugoti.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            //get selected radio button from RadioGroup
            int selectedId = patvirtinimai.getCheckedRadioButtonId();

            boolean cancel = false;
            View focusView = null;

            String vardas =Naujas_vardas.getText().toString();

            if (!isValid(vardas)) {
                Naujas_vardas.setError(getString(R.string.error_invalid_zaidejoNikas));
                focusView = Naujas_vardas;
                cancel = true;
            }


            //find the radio button by returned ID ...
            patvirtinimas[0] = (RadioButton) findViewById(selectedId);

            StringBuffer gerimai = new StringBuffer();

            if (gerimasViskis.isChecked()) {
                gerimai.append(getResources().getString(R.string.gerimas_Viskis))
                        .append(" ");
            }
            if(gerimasAlus.isChecked()) {
                gerimai.append(getResources().getString(R.string.gerimas_Alus))
                        .append(" ");
            }
            if(gerimasCocaCola.isChecked()) {
                gerimai.append(getResources().getString(R.string.gerimas_CocaCola))
                        .append(" ");
            }
            if(gerimasSultys.isChecked()) {
                gerimai.append(getResources().getString(R.string.gerimas_Sultys))
                        .append(" ");

            }
            if (cancel) {
                focusView.requestFocus();

            } else {
                //String vardas, String patvirtinimas, String tipas, String gerimas
                // sukuriamas vartotojo iraso objektas, pagal susikurta klase
                Pokeris poker = new Pokeris(vardas, patvirtinimas[0].getText().toString(),
                        String.valueOf(tipai.getSelectedItem()),gerimai.toString());
                register(poker.getVardas(),poker.getPatvirtinimas(),poker.getTipas(),poker.getGerimas());
                Toast.makeText(naujas_irasasActivity.this,
                                poker.getVardas() +"\n" +
                                poker.getPatvirtinimas() + "\n" +
                                poker.getTipas() + "\n" +
                                poker.getGerimas(), Toast.LENGTH_LONG).show() ;
            }

        }//onclick
    });//saugoti

    }//oncreate
    private boolean isValid(String credentials){

        final String CREDENTIALS_PATTERN = "^([0-9a-zA-Z]{3,15})+$";
        Pattern pattern = Pattern.compile(CREDENTIALS_PATTERN);

        Matcher matcher = pattern.matcher(credentials);
        return matcher.matches();
    }

    private void register(String vardas, String patvirtinimas, String tipas , String gerimas) {
        class NewEntry extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            DB database = new DB();
            Loginas naujas = new Loginas(getApplicationContext());


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(naujas_irasasActivity.this, "Please Wait",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {
            //pirmas stringas raktas antras reiksme
                HashMap<String, String> data = new HashMap<String,String>();
                data.put("vardas",params[0]);
                data.put("patvirtinimas",params[1]);
                data.put("tipas",params[2]);
                data.put("gerimas",params[3]);
                data.put("username",naujas.getUsername());

                String result = database.sendPostRequest(REGISTER_URL,data);

                return  result;
            }
        }

        NewEntry ru = new NewEntry();
        ru.execute(vardas,patvirtinimas,tipas,gerimas);
    }

}//class

