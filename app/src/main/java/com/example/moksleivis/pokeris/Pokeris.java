package com.example.moksleivis.pokeris;

/**
 * Created by moksleivis on 2017-04-06.
 */

public class Pokeris {
    private String vardas;
    private String patvirtinimas;
    private String tipas;
    private String gerimas;

    public Pokeris(String vardas, String patvirtinimas, String tipas, String gerimas) {
        this.vardas = vardas;
        this.patvirtinimas = patvirtinimas;
        this.tipas = tipas;
        this.gerimas = gerimas;
    }

    public String getVardas() {
        return vardas;
    }

    public void setVardas(String vardas) {
        this.vardas = vardas;
    }

    public String getPatvirtinimas() {
        return patvirtinimas;
    }

    public void setPatvirtinimas(String patvirtinimas) {
        this.patvirtinimas = patvirtinimas;
    }

    public String getTipas() {
        return tipas;
    }

    public void setTipas(String tipas) {
        this.tipas = tipas;
    }

    public String getGerimas() {
        return gerimas;
    }

    public void setGerimas(String gerimas) {
        this.gerimas = gerimas;
    }
}
