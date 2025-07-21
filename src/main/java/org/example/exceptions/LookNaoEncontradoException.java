package org.example.exceptions;

public class LookNaoEncontradoException extends RuntimeException {
    public LookNaoEncontradoException(String id) {
        super("Look com id +" + id + "não encontrado");
    }
    public LookNaoEncontradoException(){
        super("Look não encontrado");
    }
}
