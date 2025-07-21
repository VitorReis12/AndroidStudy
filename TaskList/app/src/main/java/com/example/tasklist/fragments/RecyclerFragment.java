package com.example.tasklist.fragments;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tasklist.activitys.AdicionarTarefa;
import com.example.tasklist.helper.ClickListener;
import com.example.tasklist.R;
import com.example.tasklist.adapter.Adapter;
import com.example.tasklist.helper.DBHelper;
import com.example.tasklist.helper.TarefaDAO;
import com.example.tasklist.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class RecyclerFragment extends Fragment {

    private RecyclerView recyclerViewTarefas;
    private List<Tarefa> tarefaList;
    private Tarefa tarefaAtual;

    public RecyclerFragment() {

    }

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_recycler, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        recyclerViewTarefas = view.findViewById(R.id.recyclerViewMyTask);



        recyclerViewTarefas.addOnItemTouchListener(new ClickListener(getContext(), recyclerViewTarefas, new ClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                tarefaAtual = tarefaList.get(position);
                Intent intent = new Intent(getContext(), AdicionarTarefa.class);
                intent.putExtra("tarefaSelecionada", tarefaAtual);
                startActivity(intent);

            }

            @Override
            public void onLongItemClick(View view, int position) {
                tarefaAtual = tarefaList.get(position);

                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle("Confimar Exclusão");
                dialog.setMessage("Deseja excluir a Tarefa: '" + tarefaAtual.getTarefa() +"' ?");

                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TarefaDAO tarefaDAO = new TarefaDAO(getContext());
                        if (tarefaDAO.deletar(tarefaAtual)){
                            carregarTarefas();
                            Toast.makeText(getContext(), "Tarefa Deletada!", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getContext(), "Erro ao Deletar Tarefa!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.setNegativeButton("Não",null);
                dialog.create();
                dialog.show();

            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }));

    }

    @Override
    public void onStart() {
        super.onStart();
        carregarTarefas();

    }



    public void carregarTarefas(){
        TarefaDAO tarefaDAO = new TarefaDAO(getContext());
        tarefaList = tarefaDAO.listar();

        recyclerViewTarefas.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewTarefas.setHasFixedSize(true);
        recyclerViewTarefas.addItemDecoration(
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerViewTarefas.setAdapter(new Adapter(tarefaList));
    }

}
