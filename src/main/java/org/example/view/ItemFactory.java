package org.example.view;

import org.example.enums.Conservacao;
import org.example.model.*;

import javax.swing.*;

public class ItemFactory {
    public static Item criarItem(String tipo) {
        String cor = JOptionPane.showInputDialog("Cor:");
        String tamanho = JOptionPane.showInputDialog("Tamanho:");
        String loja = JOptionPane.showInputDialog("Loja de Origem:");

        Conservacao conservacao = (Conservacao) JOptionPane.showInputDialog(
                null,
                "Conservação:",
                "Escolha",
                JOptionPane.QUESTION_MESSAGE,
                null,
                Conservacao.values(),
                Conservacao.EXCELENTE
        );

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
