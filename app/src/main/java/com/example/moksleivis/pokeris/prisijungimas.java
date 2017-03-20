package com.example.moksleivis.pokeris;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class prisijungimas extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prisijungimo_dizainas);
    }
    public void naujas_irasas2(View v){
        Intent intent = new Intent (prisijungimas.this,naujas_irasas.class);
        startActivity(intent);
    }
    public void paieska2(View v){
        Intent intent = new Intent (prisijungimas.this,paieska.class);
        startActivity(intent);
    }
}