package com.example.moksleivis.pokeris;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class prisijungimas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prisijungimo_dizainas);

        Button mygtukasIrasas = (Button) findViewById(R.id.irasas);
        mygtukasIrasas.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                naujas_irasas2();
            }
        });

        Button mygtukasPaieska = (Button) findViewById(R.id.paieska);
        mygtukasPaieska.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                paieska2();
            }
        });
    }
    public void naujas_irasas2(){
        Intent intent = new Intent (prisijungimas.this,naujas_irasasActivity.class);
        startActivity(intent);
    }
    public void paieska2(){
        Intent intent = new Intent (prisijungimas.this,paieska.class);
        startActivity(intent);
    }
}