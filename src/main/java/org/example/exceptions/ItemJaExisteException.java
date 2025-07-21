package org.example.exceptions;

public class ItemJaExisteException extends RuntimeException {
    public ItemJaExisteException(String id) {
        super("Item com Id: " + id + "já existe");
    }
}
