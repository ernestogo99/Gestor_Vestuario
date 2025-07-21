package org.example.exceptions;

public class ItemNaoEncontradoException extends RuntimeException {
    public ItemNaoEncontradoException(String id) {
        super("Item com ID " + id + " não foi encontrado.");
    }
    public ItemNaoEncontradoException(){
        super("Item não encontrado");
    }
}
