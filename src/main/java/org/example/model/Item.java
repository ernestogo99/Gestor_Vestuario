package org.example.model;

import org.example.enums.Conservacao;

import java.util.UUID;

public abstract class Item {
    protected String id;
    protected String cor;
    protected String tamanho;
    protected String lojaDeOrigem;
    protected Conservacao conservacao;
    protected int numeroDeUsos;

    public Item( String cor, String tamanho, String lojaDeOrigem, Conservacao conservacao) {
        this.id = UUID.randomUUID().toString();
        this.cor = cor;
        this.tamanho = tamanho;
        this.lojaDeOrigem = lojaDeOrigem;
        this.conservacao = conservacao;
    }


    public void registrarUso(){
        this.numeroDeUsos++;
    }

    public int getNumeroDeUsos(){
        return this.numeroDeUsos;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getTamanho() {
        return this.tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getLojaDeOrigem() {
        return this.lojaDeOrigem;
    }

    public void setLojaDeOrigem(String lojaDeOrigem) {
        this.lojaDeOrigem = lojaDeOrigem;
    }

    public Conservacao getConservacao() {
        return this.conservacao;
    }

    public void setConservacao(Conservacao conservacao) {
        this.conservacao = conservacao;
    }
}
