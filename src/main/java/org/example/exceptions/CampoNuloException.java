package org.example.exceptions;

public class CampoNuloException extends RuntimeException {
  public CampoNuloException() {
    super("Este campo não pode ser nulo");
  }
}
