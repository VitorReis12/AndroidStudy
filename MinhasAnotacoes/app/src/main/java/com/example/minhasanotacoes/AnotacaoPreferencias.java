package com.example.minhasanotacoes;

import android.content.Context;
import android.content.SharedPreferences;

public class AnotacaoPreferencias {

    private Context c;
    private SharedPreferences preferences;
    private final String NOME_ARQUIVO = "anotacao.preferences";
    private final String CHAVE_NOME = "nome";
    SharedPreferences.Editor editor;
    public AnotacaoPreferencias(Context c) {
        this.c = c;

        preferences = c.getSharedPreferences(NOME_ARQUIVO,0);

        editor = preferences.edit();

    }



    public void salvarAnotacao(String string){
        editor.putString(CHAVE_NOME, string);
        editor.commit();

    }

    public String recuperarAnotacao(){
        String anotacao = preferences.getString(CHAVE_NOME,null);
        return anotacao;
    }

}
