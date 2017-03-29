package com.example.moksleivis.pokeris;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class naujas_irasasActivity extends AppCompatActivity {

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

            //find the radio button by returned ID
            patvirtinimas[0] = (RadioButton) findViewById(selectedId);

            StringBuffer gerimas = new StringBuffer();
            gerimas.append("Viskis")
                    .append(gerimasViskis.isChecked());

            gerimas.append("Alus")
                    .append(gerimasAlus.isChecked());

            gerimas.append("CocaCola")
                    .append(gerimasCocaCola.isChecked());

            gerimas.append("Sultys")
                    .append(gerimasSultys.isChecked());


            Toast.makeText(naujas_irasasActivity.this,
                    String.valueOf(tipai.getSelectedItem()) + "\n" +
                            gerimas.toString() +
                            patvirtinimas[0].getText(), Toast.LENGTH_SHORT).show();


        }//onclick
    });//saugoti

    }//oncreate
}//class

