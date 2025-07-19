package com.example.minhasanotacoes;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.minhasanotacoes.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private AnotacaoPreferencias anotacaoPreferencias;
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private EditText editAnotacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        editAnotacao = findViewById(R.id.editTextAnotacao);
        anotacaoPreferencias = new AnotacaoPreferencias(getApplicationContext());

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String anotacao = editAnotacao.getText().toString();
                if (anotacao.equals("")){
                    Snackbar.make(view, "Preencha a Anotação", Snackbar.LENGTH_LONG)
                            .show();
                }else {
                    anotacaoPreferencias.salvarAnotacao(anotacao);
                    Snackbar.make(view, "Anotação salva com sucesso!", Snackbar.LENGTH_LONG)
                            .show();
                }

            }
        });
        String anotacao = anotacaoPreferencias.recuperarAnotacao();
        if (anotacao != null && !anotacao.equals("")){
            editAnotacao.setText(anotacao);
        }
    }



    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}