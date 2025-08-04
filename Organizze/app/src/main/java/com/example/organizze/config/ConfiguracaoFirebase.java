package com.example.organizze.config;

import com.google.firebase.auth.FirebaseAuth;

public class ConfiguracaoFirebase {


    public static FirebaseAuth autenticacao;


    public static FirebaseAuth getFirebaseAutenticacao(){


        autenticacao = FirebaseAuth.getInstance();

        return autenticacao;
    }


}
