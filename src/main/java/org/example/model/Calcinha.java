package org.example.model;

import org.example.enums.Conservacao;
import org.example.interfaces.ILavavel;

public class Calcinha extends Item implements ILavavel {
    private int quantidadeDeLavagens;

    public Calcinha(String cor, String tamanho, String lojaDeOrigem, Conservacao conservacao) {
        super(cor, tamanho, lojaDeOrigem, conservacao);
    }

    @Override
    public void registrarLavagem() {
        this.quantidadeDeLavagens++;
    }

    public int getQuantidadeDeLavagens(){
        return this.quantidadeDeLavagens;
    }


    @Override
    public String toString() {
        return "Calcinha{" +
                "quantidadeDeLavagens=" + quantidadeDeLavagens +
                ", id='" + id + '\'' +
                ", cor='" + cor + '\'' +
                ", tamanho='" + tamanho + '\'' +
                ", lojaDeOrigem='" + lojaDeOrigem + '\'' +
                ", conservacao=" + conservacao +
                ", numeroDeUsos=" + numeroDeUsos +
                '}';
    }
}
