package org.example.exceptions;

public class ItemNaoEmprestavelException extends RuntimeException {
    public ItemNaoEmprestavelException() {
        super("Esse item não pode ser emprestado");
    }
}
