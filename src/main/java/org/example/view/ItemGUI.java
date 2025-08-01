package org.example.view;

import org.example.controller.ItemController;
import org.example.enums.Conservacao;
import org.example.model.Item;

import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class ItemGUI {
    private JFrame frame;
    private JPanel panel;
    private JLabel lblTitulo;
    private ItemController controller;

    public ItemGUI() {
        this.controller = new ItemController();
        initialize();
    }

    public void initialize() {
        frame = new JFrame();
        frame.setTitle("Items");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLayout(new BorderLayout(10, 10));

        lblTitulo = new JLabel("Gerenciar Itens", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(lblTitulo, BorderLayout.NORTH);

        panel = new JPanel(new GridLayout(5, 2, 20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 200, 30, 200));

        adicionarBotoes();

        frame.add(panel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);

    }

    public void show(){
        frame.setVisible(true);
    }

    private void adicionarBotoes() {
        adicionarBotao("Criar Item", new Runnable() {
            public void run() {
                criarItem();
            }
        });

        adicionarBotao("Listar Itens", new Runnable() {
            public void run() {
                listarItens();
            }
        });

        adicionarBotao("Buscar Item por ID", new Runnable() {
            public void run() {
                buscarItem();
            }
        });

        adicionarBotao("Editar Item", new Runnable() {
            public void run() {
                editarItem();
            }
        });

        adicionarBotao("Remover Item", new Runnable() {
            public void run() {
                removerItem();
            }
        });

        adicionarBotao("Ver Quantidade de Itens", new Runnable() {
            public void run() {
                verQuantidadeItens();
            }
        });

        adicionarBotao("Registrar Empréstimo", new Runnable() {
            public void run() {
                registrarEmprestimo();
            }
        });

        adicionarBotao("Registrar Devolução", new Runnable() {
            public void run() {
                registrarDevolucao();
            }
        });

        adicionarBotao("Dias desde Empréstimo", new Runnable() {
            public void run() {
                diasDesdeEmprestimo();
            }
        });

        adicionarBotao("Voltar", new Runnable() {
            public void run() {
                voltar();
            }
        });
    }


    private void adicionarBotao(String texto,  Runnable acao) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Arial", Font.PLAIN, 16));
        botao.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
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

    private void criarItem() {
        try {
            String[] tipos = {"Camisa", "Calça","Calcinha","Casaco","Cueca","Pulseira","Relogio","Saia"};
            String tipoSelecionado = (String) JOptionPane.showInputDialog(
                    frame,
                    "Escolha o tipo de item:",
                    "Tipo de Item",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    tipos,
                    tipos[0]
            );

            Item novoItem = ItemFactory.criarItem(tipoSelecionado);
            if (novoItem != null) {
                controller.criarItem(novoItem);
                JOptionPane.showMessageDialog(frame, tipoSelecionado + " criada com sucesso!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void buscarItem() {
        String id = JOptionPane.showInputDialog(frame, "ID do item:");
        if (id == null || id.trim().isEmpty()) return;

        try {
            Item item = controller.buscarItem(id);
            if (item == null) {
                JOptionPane.showMessageDialog(frame, "Item não encontrado.");
                return;
            }

            JPanel painel = new JPanel();
            painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
            painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            painel.add(new JLabel("ID: " + item.getId()));
            painel.add(new JLabel("Tipo: " + item.getClass().getSimpleName()));
            painel.add(new JLabel("Cor: " + item.getCor()));
            painel.add(new JLabel("Tamanho: " + item.getTamanho()));
            painel.add(new JLabel("Loja de Origem: " + item.getLojaDeOrigem()));
            painel.add(new JLabel("Estado de Conservação: " + item.getConservacao()));
            painel.add(new JLabel("Número de Usos: " + item.getNumeroDeUsos()));

            JOptionPane.showMessageDialog(frame, painel, "Detalhes do Item", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarItens() {
        try {
            List<Item> itens = controller.listarItens();
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

                JOptionPane.showMessageDialog(frame, scrollPane, "Itens Cadastrados", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao listar itens: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }



    private void verQuantidadeItens() {
        try {
            int quantidade = controller.exibirQuantidadeDeItens();
            JOptionPane.showMessageDialog(frame, "Quantidade total de itens: " + quantidade);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao obter quantidade de itens: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void editarItem() {
        try {
            String id = JOptionPane.showInputDialog(frame, "ID do item a editar:");
            String cor = JOptionPane.showInputDialog(frame, "Nova cor:");
            String tamanho = JOptionPane.showInputDialog(frame, "Novo tamanho:");
            String loja = JOptionPane.showInputDialog(frame, "Nova loja de origem:");

            Conservacao conservacao = (Conservacao) JOptionPane.showInputDialog(
                    frame,
                    "Nova conservação:",
                    "Escolha",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    Conservacao.values(),
                    Conservacao.BOA
            );

            controller.editarItem(id, cor, tamanho, loja, conservacao);
            JOptionPane.showMessageDialog(frame, "Item editado com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao editar item: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void removerItem() {
        try {
            String id = JOptionPane.showInputDialog(frame, "ID do item a remover:");
            controller.removerItem(id);
            JOptionPane.showMessageDialog(frame, "Item removido com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao remover item: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void registrarEmprestimo() {
        try {
            String id = JOptionPane.showInputDialog(frame, "ID do item para emprestar:");
            controller.registarEmprestimo(id);
            JOptionPane.showMessageDialog(frame, "Empréstimo registrado com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao registrar empréstimo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void registrarDevolucao() {
        try {
            String id = JOptionPane.showInputDialog(frame, "ID do item para devolução:");
            controller.devolverItem(id);
            JOptionPane.showMessageDialog(frame, "Item devolvido com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao registrar devolução: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void diasDesdeEmprestimo() {
        try {
            String id = JOptionPane.showInputDialog(frame, "ID do item:");
            long dias = controller.diasDesdeOEmprestimo(id);
            JOptionPane.showMessageDialog(frame, "Dias desde o empréstimo: " + dias);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao calcular dias: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
