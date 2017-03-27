package com.example.moksleivis.pokeris;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class naujas_irasas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.naujas_irasas);

        final Spinner tipai =(Spinner) findViewById(R.id.tipaiSelect);
        List<String> list= new ArrayList<String>();
        list.add(getResources().getString(R.string.tipas_Mokamas));
        list.add(getResources().getString(R.string.Tipas_Nemokamas));
        list.add(getResources().getString(R.string.tipas_Turnyras));
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String> (this,
                android.R.layout.simple_spinner_item);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipai.setAdapter(dataAdapter);
    }


}
