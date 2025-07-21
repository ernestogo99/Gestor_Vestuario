package org.example.view;

import org.example.controller.ItemController;
import org.example.controller.LavagemController;
import org.example.interfaces.ILavavel;
import org.example.model.Item;
import org.example.model.Lavagem;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LavagemGUI {
    private JFrame frame;
    private JPanel panel;
    private JLabel lblTitulo;
    private LavagemController controller;
    private ItemController itemController;


    public LavagemGUI() {
        this.controller = new LavagemController();
        this.itemController=new ItemController();
        initialize();
    }

    public void initialize() {
        frame = new JFrame();
        frame.setTitle("Lavagens");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLayout(new BorderLayout(10, 10));

        lblTitulo = new JLabel("Gerenciar Lavagens", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(lblTitulo, BorderLayout.NORTH);

        panel = new JPanel(new GridLayout(7, 1, 20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 200, 30, 200));

        adicionarBotoes();

        frame.add(panel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
    }

    public void show() {
        frame.setVisible(true);
    }

    private void adicionarBotoes() {
        adicionarBotao("Criar Lavagem", this::criarLavagem);
        adicionarBotao("Listar Lavagens", this::listarLavagens);
        adicionarBotao("Buscar Lavagem por ID", this::buscarLavagem);
        adicionarBotao("Adicionar Item à Lavagem", this::adicionarItemNaLavagem);
        adicionarBotao("Registrar Lavagem", this::registrarLavagem);
        adicionarBotao("Remover Item da Lavagem", this::removerItemDaLavagem);
        adicionarBotao("Voltar", this::voltar);
    }

    private void adicionarBotao(String texto,  Runnable acao) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Arial", Font.PLAIN, 16));
        botao.addActionListener(new java.awt.event.ActionListener() {
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

    private void criarLavagem() {
        try {
            Lavagem lavagem = new Lavagem();
            controller.criarLavagem(lavagem);
            JOptionPane.showMessageDialog(frame, "Lavagem criada com sucesso! ID: " + lavagem.getId());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao criar lavagem: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarLavagens() {
        try {
            List<Lavagem> lavagens = controller.listarLavagens();
            if (lavagens.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Nenhuma lavagem cadastrada.");
                return;
            }

            String[] colunas = {"ID", "Quantidade de Itens"};
            String[][] dados = new String[lavagens.size()][colunas.length];

            for (int i = 0; i < lavagens.size(); i++) {
                Lavagem l = lavagens.get(i);
                dados[i][0] = l.getId();
                dados[i][1] = String.valueOf(l.quantidadeDeitems());
            }

            JTable tabela = new JTable(dados, colunas);
            JScrollPane scrollPane = new JScrollPane(tabela);
            scrollPane.setPreferredSize(new Dimension(600, 300));

            JOptionPane.showMessageDialog(frame, scrollPane, "Lavagens Cadastradas", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao listar lavagens: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarLavagem() {
        String id = JOptionPane.showInputDialog(frame, "ID da lavagem:");
        if (id == null || id.trim().isEmpty()) return;

        try {
            Lavagem lavagem = controller.obterPorId(id);
            if (lavagem == null) {
                JOptionPane.showMessageDialog(frame, "Lavagem não encontrada.");
                return;
            }

            JPanel painel = new JPanel();
            painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
            painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            painel.add(new JLabel("ID: " + lavagem.getId()));
            painel.add(new JLabel("Quantidade de Itens: " + lavagem.quantidadeDeitems()));

            painel.add(new JLabel("Itens Laváveis:"));
            for (ILavavel item : lavagem.getItems()) {
                painel.add(new JLabel(" - " + item.getClass().getSimpleName()));
            }

            JOptionPane.showMessageDialog(frame, painel, "Detalhes da Lavagem", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao buscar lavagem: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void adicionarItemNaLavagem() {
        try {
            String idLavagem = JOptionPane.showInputDialog(frame, "ID da lavagem:");
            if (idLavagem == null || idLavagem.trim().isEmpty()) return;

            List<ILavavel> lavaveis = itemController.listarLavaveis();
            if (lavaveis.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Nenhum item lavável disponível.");
                return;
            }


            DefaultComboBoxModel<ILavavel> model = new DefaultComboBoxModel<>();
            for (ILavavel item : lavaveis) {
                model.addElement(item);
            }

            JComboBox<ILavavel> comboBox = new JComboBox<>(model);
            comboBox.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                              boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof Item) {
                        Item item = (Item) value;
                        setText("ID: " + item.getId() + " | Cor: " + item.getCor() + " | Tamanho: " + item.getTamanho());
                    }
                    return this;
                }
            });

            int result = JOptionPane.showConfirmDialog(frame, comboBox, "Selecione um item lavável", JOptionPane.OK_CANCEL_OPTION);
            if (result != JOptionPane.OK_OPTION) return;

            ILavavel itemSelecionado = (ILavavel) comboBox.getSelectedItem();
            if (itemSelecionado == null) {
                JOptionPane.showMessageDialog(frame, "Nenhum item selecionado.");
                return;
            }

            controller.adicionarItemNaLavagem(idLavagem, itemSelecionado);
            JOptionPane.showMessageDialog(frame, "Item adicionado com sucesso à lavagem!");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao adicionar item: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void registrarLavagem() {
        try {
            String id = JOptionPane.showInputDialog(frame, "ID da lavagem para registrar lavagem:");
            if (id == null || id.trim().isEmpty()) return;

            controller.registrarLavagem(id);
            JOptionPane.showMessageDialog(frame, "Lavagem registrada com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao registrar lavagem: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removerItemDaLavagem() {
        try {
            String idLavagem = JOptionPane.showInputDialog(frame, "ID da lavagem:");
            if (idLavagem == null || idLavagem.trim().isEmpty()) return;

            Lavagem lavagem=this.controller.obterPorId(idLavagem);
            List<ILavavel> itensNaLavagem =lavagem.getItems();
            if (itensNaLavagem == null || itensNaLavagem.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Nenhum item encontrado nesta lavagem.");
                return;
            }


            String[] opcoes = new String[itensNaLavagem.size()];
            for (int i = 0; i < itensNaLavagem.size(); i++) {
                ILavavel item = itensNaLavagem.get(i);
                if(item instanceof Item item1 )
                opcoes[i] = item1.getId() + " - " + item.getClass().getSimpleName();
            }

            String escolha = (String) JOptionPane.showInputDialog(
                    frame,
                    "Selecione o item a remover da lavagem:",
                    "Remover Item",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
            );

            if (escolha == null) return;


            String idItem = escolha.split(" - ")[0];

            controller.removerItemDaLavagem(idLavagem, idItem);
            JOptionPane.showMessageDialog(frame, "Item removido da lavagem com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao remover item: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

}
