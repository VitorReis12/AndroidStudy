package com.example.tasklist.helper;

import com.example.tasklist.model.Tarefa;

import java.util.List;

public interface ITarefaDAO {

    public boolean salvar(Tarefa tarefa);

    public boolean atualizar(Tarefa tarefa);

    public boolean deletar(Tarefa tarefa);

    public List<Tarefa> listar();


}
