package com.example.recyclerviewmovies.Activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewmovies.Adapter.Adapter;
import com.example.recyclerviewmovies.ClickListener;
import com.example.recyclerviewmovies.Model.Filme;
import com.example.recyclerviewmovies.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Filme> filmeList;
    RecyclerView recyclerViewMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        filmeList = new ArrayList<>();

        CriarFilmes();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        Adapter adapter = new Adapter(filmeList);
        recyclerViewMovies = findViewById(R.id.recyclerViewMovies);
        recyclerViewMovies.setHasFixedSize(true);
        recyclerViewMovies.setLayoutManager(layoutManager);
        recyclerViewMovies.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        recyclerViewMovies.setAdapter(adapter);

        recyclerViewMovies.addOnItemTouchListener(
                new ClickListener(

                        getApplicationContext(),
                        recyclerViewMovies,

                        new ClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Filme filme = filmeList.get(position);
                                String titulo = filme.getTitulo();
                                Toast.makeText(getApplicationContext(), "Item Precionado: " + titulo, Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                Filme filme = filmeList.get(position);
                                String titulo = filme.getTitulo();
                                int ano = filme.getAno();
                                Toast.makeText(getApplicationContext(), "Clique Longo: " + titulo + " de " + ano, Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        })
        );

    }

    public void CriarFilmes() {
        Filme filme = new Filme("The Mentalist", R.drawable.mentalist_capa, 2008);
        filmeList.add(filme);
        filme = new Filme("Psych - Agentes Especiais", R.drawable.psych_capa, 2006);
        filmeList.add(filme);
        filme = new Filme("Monk - Um Detetive Diferente", R.drawable.monk_capa, 2002);
        filmeList.add(filme);
        filme = new Filme("Suits", R.drawable.suits_capa, 2011);
        filmeList.add(filme);
        filme = new Filme("Shrek", R.drawable.shrek_capa, 2001);
        filmeList.add(filme);
    }


}