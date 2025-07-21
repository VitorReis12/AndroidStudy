package com.example.tasklist.activitys;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tasklist.R;
import com.example.tasklist.helper.TarefaDAO;
import com.example.tasklist.model.Tarefa;
import com.google.android.material.textfield.TextInputEditText;

public class AdicionarTarefa extends AppCompatActivity {

    TextInputEditText textInputEditText;
    Toolbar toolbar;
    Tarefa tarefaAtual;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adicionar_tarefa);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        textInputEditText = findViewById(R.id.InputTarefa);

        tarefaAtual = (Tarefa) getIntent().getSerializableExtra("tarefaSelecionada");

        if (tarefaAtual != null) {
            textInputEditText.setText(tarefaAtual.getTarefa());
        }


        toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_adicionar_tarefa, menu);

        return super.onCreateOptionsMenu(menu);


    }

    @SuppressLint("NonConstantResourceId")

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemSalvar) {

            String task = textInputEditText.getText().toString();
            TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());

            if (tarefaAtual != null) {

                if (!task.isEmpty()) {
                    Tarefa tarefa = new Tarefa(task);
                    tarefa.setId(tarefaAtual.getId());

                    if (tarefaDAO.atualizar(tarefa)) {
                        finish();
                        Toast.makeText(this, "Tarefa Atualizada com sucesso!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Erro ao Atualizar Tarefa!", Toast.LENGTH_SHORT).show();
                    }
                }
            } else {

                if (!task.isEmpty()) {
                    Tarefa tarefa = new Tarefa(task);

                    if (tarefaDAO.salvar(tarefa)) {
                        finish();
                        Toast.makeText(this, "Tarefa salva com sucesso!", Toast.LENGTH_SHORT).show();

                    } else {

                        Toast.makeText(this, "Erro ao Salvar Tarefa!", Toast.LENGTH_SHORT).show();


                    }


                }
            }


        }
        return super.onOptionsItemSelected(item);
    }
}