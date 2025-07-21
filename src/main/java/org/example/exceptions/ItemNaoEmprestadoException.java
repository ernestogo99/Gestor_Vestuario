package org.example.exceptions;

public class ItemNaoEmprestadoException extends RuntimeException {
    public ItemNaoEmprestadoException() {
        super("O item não está emprestado");
    }
}
