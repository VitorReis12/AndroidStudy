package com.example.tasklist.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tasklist.R;
import com.example.tasklist.model.Tarefa;

import org.w3c.dom.Text;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    List<Tarefa> tarefaList;

    public Adapter(List<Tarefa> tarefaList) {
        this.tarefaList = tarefaList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_task,parent,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Tarefa tarefa = tarefaList.get(position);
        holder.textViewTarefa.setText(tarefa.getTarefa());

    }

    @Override
    public int getItemCount() {
        return tarefaList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView textViewTarefa;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTarefa = itemView.findViewById(R.id.textViewTarefas);


        }
    }


}
