package org.example.exceptions;

public class LavagemNaoEncontradaException extends RuntimeException {
    public LavagemNaoEncontradaException(String id) {
        super("Lavagem com id:" + id + "não encontrada");
    }
}
