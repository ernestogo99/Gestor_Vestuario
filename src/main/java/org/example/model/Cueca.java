package org.example.model;

import org.example.enums.Conservacao;
import org.example.interfaces.ILavavel;

public class Cueca extends Item implements ILavavel {
    private int quantidadeDeLavagens;
    public Cueca(String cor, String tamanho, String lojaDeOrigem, Conservacao conservacao) {
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
        return "Cueca{" +
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
