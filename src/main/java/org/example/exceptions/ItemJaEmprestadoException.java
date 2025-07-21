package org.example.exceptions;

public class ItemJaEmprestadoException extends RuntimeException {

    public ItemJaEmprestadoException() {
        super("O item já está emprestado");
    }
}
