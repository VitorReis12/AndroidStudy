package com.example.firebaseapp.model;

public class Produto {

    private String descricao;

    private String Marca;
    private Double preco;


    public Produto() {
    }

    public Produto(String descricao, String marca, Double preco) {
        this.descricao = descricao;
        Marca = marca;
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
