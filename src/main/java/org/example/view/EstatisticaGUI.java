package org.example.view;

import org.example.controller.EstatiticasController;
import org.example.interfaces.IEmprestavel;
import org.example.interfaces.ILavavel;
import org.example.model.Item;
import org.example.model.Look;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EstatisticaGUI {
    private JFrame frame;
    private JPanel panel;
    private JLabel lblTitulo;
    private EstatiticasController estatiticasController;

    public EstatisticaGUI(){
        this.estatiticasController=new EstatiticasController();
        this.initialize();
    }


    private void initialize(){
        frame = new JFrame();
        frame.setTitle("Estatísticas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLayout(new BorderLayout(10, 10));

        lblTitulo = new JLabel("Estatísticas", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(lblTitulo, BorderLayout.NORTH);

        panel = new JPanel(new GridLayout(5, 2, 20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 200, 30, 200));

        adicionarBotoes();

        frame.add(panel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
    }


    private void adicionarBotoes() {
        adicionarBotao("Ver itens mais usados", this::listarItensMaisUsados);
        adicionarBotao("Ver itens menos usados", this::listarItensMenosUsados);
        adicionarBotao("Ver itens mais lavados", this::listarItensMaisLavados);
        adicionarBotao("Ver itens menos lavados", this::listarItensMenosLavados);
        adicionarBotao("Ver looks mais usados", this::listarLooksMaisUsados);
        adicionarBotao("Ver itens emprestados", this::listarItensEmprestados);
        adicionarBotao("Ver qtd de utilizações de um item", this::verQTDdeUtilizacoesDoItem);
        adicionarBotao("Ver qtd de utilizações de um look", this::verQTDdeUtilizacoesDoLook);
        adicionarBotao("Voltar", this::voltar);
    }

    private void adicionarBotao(String texto,  Runnable acao) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Arial", Font.PLAIN, 16));
        botao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    acao.run();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(botao);
    }


    private void voltar() {
        frame.dispose();
        MenuGUI menu = new MenuGUI();
        menu.show();
    }

    public void show(){
        frame.setVisible(true);
    }

    private void listarItensMaisUsados(){
        try {
            List<Item> itens = estatiticasController.listarItensMaisUsados();
            if (itens.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Nenhum item cadastrado.");
            } else {
                String[] colunas = {"ID", "Tipo", "Cor", "Tamanho", "Loja", "Conservação","Número de usos"};

                String[][] dados = new String[itens.size()][colunas.length];
                for (int i = 0; i < itens.size(); i++) {
                    Item item = itens.get(i);
                    dados[i][0] = item.getId();
                    dados[i][1] = item.getClass().getSimpleName();
                    dados[i][2] = item.getCor();
                    dados[i][3] = item.getTamanho();
                    dados[i][4] = item.getLojaDeOrigem();
                    dados[i][5] = item.getConservacao().toString();
                    dados[i][6] = String.valueOf(item.getNumeroDeUsos());
                }

                JTable tabela = new JTable(dados, colunas);
                JScrollPane scrollPane = new JScrollPane(tabela);
                scrollPane.setPreferredSize(new Dimension(600, 300));

                JOptionPane.showMessageDialog(frame, scrollPane, "Itens mais usados", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao listar itens: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarItensMenosUsados(){
        try {
            List<Item> itens = estatiticasController.listarItensMenosUsados();
            if (itens.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Nenhum item cadastrado.");
            } else {
                String[] colunas = {"ID", "Tipo", "Cor", "Tamanho", "Loja", "Conservação","Número de usos"};

                String[][] dados = new String[itens.size()][colunas.length];
                for (int i = 0; i < itens.size(); i++) {
                    Item item = itens.get(i);
                    dados[i][0] = item.getId();
                    dados[i][1] = item.getClass().getSimpleName();
                    dados[i][2] = item.getCor();
                    dados[i][3] = item.getTamanho();
                    dados[i][4] = item.getLojaDeOrigem();
                    dados[i][5] = item.getConservacao().toString();
                    dados[i][6] = String.valueOf(item.getNumeroDeUsos());
                }

                JTable tabela = new JTable(dados, colunas);
                JScrollPane scrollPane = new JScrollPane(tabela);
                scrollPane.setPreferredSize(new Dimension(600, 300));

                JOptionPane.showMessageDialog(frame, scrollPane, "Itens menos utilizados", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao listar itens: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarItensMaisLavados(){
        try {
            List<ILavavel> itens = estatiticasController.listarItensMaisLavados();
            if (itens.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Nenhum item cadastrado.");
            } else {
                String[] colunas = {"Tipo","Quantidade de lavagens"};

                String[][] dados = new String[itens.size()][colunas.length];
                for (int i = 0; i < itens.size(); i++) {
                    ILavavel item = itens.get(i);
                    dados[i][0] = item.getClass().getSimpleName();
                    dados[i][1] = String.valueOf(item.getQuantidadeDeLavagens());
                }

                JTable tabela = new JTable(dados, colunas);
                JScrollPane scrollPane = new JScrollPane(tabela);
                scrollPane.setPreferredSize(new Dimension(600, 300));

                JOptionPane.showMessageDialog(frame, scrollPane, "Itens mais lavados", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao listar itens: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void listarItensMenosLavados(){
        try {
            List<ILavavel> itens = estatiticasController.listarItensMenosLavados();
            if (itens.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Nenhum item cadastrado.");
            } else {
                String[] colunas = {"Tipo","Quantidade de lavagens"};

                String[][] dados = new String[itens.size()][colunas.length];
                for (int i = 0; i < itens.size(); i++) {
                    ILavavel item = itens.get(i);
                    dados[i][0] = item.getClass().getSimpleName();
                    dados[i][1] = String.valueOf(item.getQuantidadeDeLavagens());
                }

                JTable tabela = new JTable(dados, colunas);
                JScrollPane scrollPane = new JScrollPane(tabela);
                scrollPane.setPreferredSize(new Dimension(600, 300));

                JOptionPane.showMessageDialog(frame, scrollPane, "Itens menos lavados", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao listar itens: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void listarLooksMaisUsados(){
        try {
            List<Look> looks = estatiticasController.listarLooksMaisUsados();
            if (looks.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Nenhum item cadastrado.");
            } else {
                String[] colunas = {"Id","Tipo","Número de usos"};

                String[][] dados = new String[looks.size()][colunas.length];
                for (int i = 0; i < looks.size(); i++) {
                    Look look = looks.get(i);
                    dados[i][0] = look.getId();
                    dados[i][1] = look.getClass().getSimpleName();
                    dados[i][2] = String.valueOf(look.getNumeroDeUsos());
                }

                JTable tabela = new JTable(dados, colunas);
                JScrollPane scrollPane = new JScrollPane(tabela);
                scrollPane.setPreferredSize(new Dimension(600, 300));

                JOptionPane.showMessageDialog(frame, scrollPane, "Looks mais usados", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao listar itens: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarItensEmprestados(){
        try {
            List<IEmprestavel> itens = estatiticasController.listarItensEmprestados();
            if (itens.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Nenhum item cadastrado.");
            } else {
                String[] colunas = {"Tipo","ID"};

                String[][] dados = new String[itens.size()][colunas.length];
                for (int i = 0; i < itens.size(); i++) {
                    IEmprestavel item = itens.get(i);
                    if (item instanceof Item itemReal) {
                        dados[i][0] = itemReal.getClass().getSimpleName();
                        dados[i][1] = itemReal.getId();
                    }
                }

                JTable tabela = new JTable(dados, colunas);
                JScrollPane scrollPane = new JScrollPane(tabela);
                scrollPane.setPreferredSize(new Dimension(600, 300));

                JOptionPane.showMessageDialog(frame, scrollPane, "Itens emprestados", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao listar itens: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void verQTDdeUtilizacoesDoItem(){
        try{
            String id = JOptionPane.showInputDialog(frame, "ID do item ");
            int quantidade=this.estatiticasController.qtdDeUtilizacoesDoItem(id);
            JOptionPane.showMessageDialog(frame, "Quantidade de utilizações do item: " + quantidade);
        }  catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao obter quantidade de utilizações do item: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void verQTDdeUtilizacoesDoLook() {
        try {
            String id = JOptionPane.showInputDialog(frame, "ID do look ");
            int quantidade = this.estatiticasController.qtdDeUtilizacoesDoLook(id);
            JOptionPane.showMessageDialog(frame, "Quantidade de utilizações do look: " + quantidade);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao obter quantidade de utilizações do look: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
