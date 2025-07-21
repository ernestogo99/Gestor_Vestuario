package org.example.interfaces;

import org.example.exceptions.ItemNaoEmprestadoException;

import java.time.LocalDate;

public interface IEmprestavel {
    void registrarEmprestimo() ;
    long quantidadeDeDiasDesdeOEmprestimo();
    void registrarDevolucao();
    boolean isEstaEmprestado();
}
