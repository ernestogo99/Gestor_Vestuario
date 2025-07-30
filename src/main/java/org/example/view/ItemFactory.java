package org.example.view;

import org.example.enums.Conservacao;
import org.example.model.*;

import javax.swing.*;

public class ItemFactory {
    public static Item criarItem(String tipo,String cor,String tamanho,String loja, Conservacao conservacao) {

        if (tipo.equals("Camisa")) {
            return new Camisa(cor, tamanho, loja, conservacao);
        } else if (tipo.equals("Calça")) {
            return new Calca(cor, tamanho, loja, conservacao);
        } else if (tipo.equals("Saia")) {
            return new Saia(cor, tamanho, loja, conservacao);
        } else if (tipo.equals("Calcinha")) {
            return new Calcinha(cor, tamanho, loja, conservacao);
        } else if (tipo.equals("Cueca")) {
            return new Cueca(cor, tamanho, loja, conservacao);
        } else if (tipo.equals("Casaco")) {
            return new Casaco(cor, tamanho, loja, conservacao);
        } else if (tipo.equals("Pulseira")) {
            return new Pulseira(cor, tamanho, loja, conservacao);
        } else if (tipo.equals("Relogio")) {
            return new Relogio(cor, tamanho, loja, conservacao);
        } else {
            JOptionPane.showMessageDialog(null, "Tipo de item desconhecido: " + tipo);
            return null;
        }
    }
}
