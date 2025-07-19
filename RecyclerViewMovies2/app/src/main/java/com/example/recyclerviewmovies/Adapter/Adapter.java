package com.example.recyclerviewmovies.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewmovies.Model.Filme;
import com.example.recyclerviewmovies.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    List<Filme> filmeList;

    public Adapter(List<Filme> filmeList) {
        this.filmeList = filmeList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_movies,parent,false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Filme filme = filmeList.get(position);
        String titulo = filme.getTitulo().toString();
        int ano = filme.getAno();
        int capa = filme.getCapa();

        holder.textViewTitulo.setText(titulo);
        holder.textViewAno.setText(String.valueOf(ano));
        holder.imageViewCapa.setImageResource(capa);

    }

    @Override
    public int getItemCount() {
        return this.filmeList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitulo;
        TextView textViewAno;
        ImageView imageViewCapa;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitulo = itemView.findViewById(R.id.textViewTitulo);
            textViewAno = itemView.findViewById(R.id.textViewAno);
            imageViewCapa = itemView.findViewById(R.id.imageViewCapa);

        }
    }

}
