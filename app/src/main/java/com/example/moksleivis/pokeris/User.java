package com.example.moksleivis.pokeris;

/**
 * Created by moksleivis on 2017-04-06.
 */

public class User {

    private String vardas;
    private String pastas;
    private String password;



    public User(String vardas, String pastas, String password) {
        this.vardas = vardas;
        this.pastas = pastas;
        this.password = password;
    }

    public String getVardas() {
        return vardas;
    }

    public void setVardas(String vardas) {
        this.vardas = vardas;
    }

    public String getPastas() {
        return pastas;
    }

    public void setPastas(String pastas) {
        this.pastas = pastas;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
