package com.example.cadastrolivro;

import androidx.annotation.NonNull;

public class SerieFilmeModel {
    public long id;
    public String nome, genero, tipo;
    public double nota;

    @NonNull
    @Override
    public String toString() {
        return id + "-" + nome + "-" + nota;
    }
}