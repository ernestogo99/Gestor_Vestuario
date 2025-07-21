package org.example.model;

import org.example.enums.Conservacao;


public class Acessorio extends Item
{
    public Acessorio(String cor, String tamanho, String lojaDeOrigem, Conservacao conservacao) {
        super(cor, tamanho, lojaDeOrigem, conservacao);
    }
}
