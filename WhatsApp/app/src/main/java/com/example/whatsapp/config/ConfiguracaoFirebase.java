package com.example.whatsapp.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfiguracaoFirebase {

    private static DatabaseReference databaseReference;
    private static FirebaseAuth auth;

    public static DatabaseReference getReference(){

        if (databaseReference == null){
            databaseReference = FirebaseDatabase.getInstance().getReference();
            return databaseReference;
        }
        return databaseReference;
    }

    public static FirebaseAuth getAuth(){
        if (auth == null){
            auth = FirebaseAuth.getInstance();
            return auth;
        }
        return auth;

    }


}
