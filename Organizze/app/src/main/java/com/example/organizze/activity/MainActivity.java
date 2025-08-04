package com.example.organizze.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;

import com.example.organizze.R;
import com.example.organizze.config.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;
public class MainActivity extends IntroActivity {

    private FirebaseAuth auntenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        verificarUsuarioLogado();

        setButtonBackVisible(false);
        setButtonNextVisible(false);

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_1)
                .build()
        );

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_2)
                .build()
        );

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_3)
                .build()
        );

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_4)
                .build()
        );

        addSlide(new FragmentSlide.Builder()
                .fragment(R.layout.intro_cadastro)
                .background(android.R.color.white)
                .canGoForward(false)
                .build()
        );


    }

    public void btEntrar(View view){

        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    public void btCadastrar(View view){

        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);

    }


    public void verificarUsuarioLogado(){

        auntenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        if (auntenticacao.getCurrentUser() != null) {
            abrirTelaPrincipal();
        }

    }

    public void abrirTelaPrincipal(){


        startActivity(new Intent(this, PrincipalActivity.class));


    }


    @Override
    protected void onStart() {
        super.onStart();

        verificarUsuarioLogado();

    }
}