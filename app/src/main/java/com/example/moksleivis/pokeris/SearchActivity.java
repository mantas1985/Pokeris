package com.example.moksleivis.pokeris;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    public static final String EXTRA_STORAGE_POKERIS_ID = "com.example.moksleivis.pokeris.STORAGE_POKERIS_ID";

    private List<Pokeris> pokerisList;
    private ViewGroup pokerisGroup;

    private View searchStatusView;
    private View searchFormView;

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        this.searchStatusView = findViewById(R.id.search_status);
        this.searchFormView = findViewById(R.id.search_form);

        this.searchView = (SearchView) findViewById(R.id.search_view);
        this.searchView.setVisibility(View.GONE);

        Button mygtukasIrasas = (Button) findViewById(R.id.irasas);
        mygtukasIrasas.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                naujas_irasas2();
            }
        });

        Button searchBtn = (Button) findViewById(R.id.search_button);
        searchBtn.setText(getString(R.string.search_saved_pokerio_zaideju));

        /*searchBtn.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.font_size_extra_small));
        searchBtn.setTextColor(getResources().getColor(R.color.text_hint));*/

        searchBtn.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                v.setVisibility(View.GONE);
                SearchActivity.this.searchView.setVisibility(View.VISIBLE);
                SearchActivity.this.searchView.onActionViewExpanded();
            }
        });

        AutoCompleteTextView searchText = (AutoCompleteTextView) this.searchView.findViewById(
                this.searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null));
       /* searchText.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                getResources().getDimensionPixelSize(R.dimen.font_size_extra_small));*/

        this.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String location) {

                SearchActivity.this.searchView.clearFocus();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String location) {

                searchPokeriuZaideju(location);

                return false;
            }
        });

    }

    private void updatePokeriuZaidejus() {

        this.pokerisGroup = (ViewGroup) findViewById(R.id.saved_pokeriu_zaidejai_container);
        this.pokerisGroup.removeAllViews();
// TODO : padaryti call i DB
       /* try {
            SearchActivity.this.pokerisList = KITM.getStorage().getAllStudents();
        } catch (StorageIOException e) {
            e.printStackTrace();
        }*/
        SearchActivity.this.pokerisList = new ArrayList<Pokeris>();

        Pokeris zaidejas = new Pokeris("Mantas","Taip","Mokamas","Sultys");

        SearchActivity.this.pokerisList.add(zaidejas);


        for (Pokeris pokeris : SearchActivity.this.pokerisList) {
            final View locationItem = getLayoutInflater().inflate(
                    R.layout.pokeris_item, SearchActivity.this.pokerisGroup, false);
            final TextView title = (TextView) locationItem.findViewById(R.id.title);
            title.setText(pokeris.getVardas());
            // TODO: papildyti klase gauti ID is DB
            /*final Integer pokerisStorageId = pokeris.getStorageId();*/
            title.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SearchActivity.this, naujas_irasasActivity.class);
                    /*intent.putExtra(SearchActivity.EXTRA_STORAGE_POKERIS_ID, pokerisStorageId);*/
                    startActivity(intent);

                }
            });
            this.pokerisGroup.addView(locationItem);
        }
    }

    private void searchPokeriuZaideju(String searchKey) {
        //showProgress(true);
        updatePokeriuZaidejus();
    }

    public void naujas_irasas2(){
        Intent intent = new Intent (SearchActivity.this,naujas_irasasActivity.class);
        startActivity(intent);
    }

}

