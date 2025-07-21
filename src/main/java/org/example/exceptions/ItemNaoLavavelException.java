package org.example.exceptions;

public class ItemNaoLavavelException extends RuntimeException {
    public ItemNaoLavavelException() {
        super("Esse item não é lavável");
    }
}
