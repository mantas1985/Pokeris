package com.example.moksleivis.pokeris;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    public void prisijungimas2(){
        Intent intent = new Intent (MainActivity.this, prisijungimas.class);
        startActivity(intent);
    }

    public void registracija2(){
        Intent intent = new Intent (MainActivity.this, registracija.class);
        startActivity(intent);
    }
}
