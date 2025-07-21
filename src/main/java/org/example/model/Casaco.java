package org.example.model;

import org.example.enums.Conservacao;
import org.example.exceptions.ItemJaEmprestadoException;
import org.example.exceptions.ItemNaoEmprestadoException;
import org.example.interfaces.IEmprestavel;
import org.example.interfaces.ILavavel;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Casaco extends  Item implements ILavavel, IEmprestavel {
    private int quantidadeDeLavagens;
    private boolean estaEmprestado;
    private String dataEmprestimo;
    private String dataDevolucao;

    public Casaco(String cor, String tamanho, String lojaDeOrigem, Conservacao conservacao) {
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
    public void registrarEmprestimo() {
        if(this.estaEmprestado){
            throw  new ItemJaEmprestadoException();
        }
        this.estaEmprestado=true;
        this.dataEmprestimo= LocalDate.now().toString();

    }

    @Override
    public long quantidadeDeDiasDesdeOEmprestimo() {
        if (!this.estaEmprestado || this.dataEmprestimo == null) {
            throw new ItemNaoEmprestadoException();
        }
        LocalDate dataEmprestimoLD = LocalDate.parse(this.dataEmprestimo);
        return ChronoUnit.DAYS.between(dataEmprestimoLD, LocalDate.now());
    }

    @Override
    public void registrarDevolucao() {
        if(!this.estaEmprestado){
            throw  new ItemNaoEmprestadoException();
        }
        this.dataDevolucao=LocalDate.now().toString();
        this.estaEmprestado=false;
    }

    public boolean isEstaEmprestado() {
        return this.estaEmprestado;
    }


    public String getDataEmprestimo() {
        return dataEmprestimo;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void setQuantidadeDeLavagens(int quantidadeDeLavagens) {
        this.quantidadeDeLavagens = quantidadeDeLavagens;
    }

    public void setEstaEmprestado(boolean estaEmprestado) {
        this.estaEmprestado = estaEmprestado;
    }

    public void setDataEmprestimo(String dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
}
