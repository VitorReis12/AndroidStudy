package com.example.tasklist.helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tasklist.model.Tarefa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TarefaDAO implements ITarefaDAO {

    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public TarefaDAO(Context context) {

        DBHelper dbHelper = new DBHelper(context);
        escreve = dbHelper.getWritableDatabase();
        le = dbHelper.getReadableDatabase();

    }

    @Override
    public boolean salvar(Tarefa tarefa) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", tarefa.getTarefa());

        try {

            escreve.insert(DBHelper.TABELA_TAREFAS, null, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean atualizar(Tarefa tarefa) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("nome", tarefa.getTarefa());

            String[] args = {tarefa.getId().toString()};

            escreve.update(DBHelper.TABELA_TAREFAS, contentValues, "id=?", args);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean deletar(Tarefa tarefa) {
        try {

            String[] args = {tarefa.getId().toString(), tarefa.getTarefa()};
            escreve.delete(DBHelper.TABELA_TAREFAS,"id=? AND nome=?", args);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Tarefa> listar() {

        List<Tarefa> tarefaList = new ArrayList<>();
        String sql = "SELECT * FROM " + DBHelper.TABELA_TAREFAS;

        Cursor cursor = le.rawQuery(sql, null);

        while (cursor.moveToNext()) {

            Tarefa tarefa = new Tarefa();
            @SuppressLint("Range") Long id = cursor.getLong(cursor.getColumnIndex("id"));
            @SuppressLint("Range") String nomeTarefa = cursor.getString(cursor.getColumnIndex("nome"));
            tarefa.setId(id);
            tarefa.setTarefa(nomeTarefa);
            tarefaList.add(tarefa);


        }

        return tarefaList;
    }
}
