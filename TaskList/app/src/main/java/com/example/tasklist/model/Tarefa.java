package com.example.tasklist.model;

import java.io.Serializable;

public class Tarefa implements Serializable {

    Long id;
    String tarefa;

    public Tarefa() {

    }
    public Tarefa(String tarefa) {
        this.tarefa = tarefa;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTarefa() {
        return tarefa;
    }

    public void setTarefa(String tarefa) {
        this.tarefa = tarefa;
    }
}
