package org.example.enums;

public enum Conservacao {
    EXCELENTE("Excelente"),
    BOA("Boa"),
    REGULAR("Regular"),
    RUIM("Ruim");

    private final String descricao;

    Conservacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
