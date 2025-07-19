package com.example.recyclerviewmovies.Model;

import android.widget.ImageView;

public class Filme {

    String Titulo;

    int Capa;
    int ano;

    public Filme(String titulo, int capa, int ano) {
        Titulo = titulo;
        Capa = capa;
        this.ano = ano;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public int getCapa() {
        return Capa;
    }

    public void setCapa(int capa) {
        Capa = capa;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
}
