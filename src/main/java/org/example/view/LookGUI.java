package org.example.view;

import org.example.controller.ItemController;
import org.example.controller.LookController;
import org.example.model.Acessorio;
import org.example.model.Item;
import org.example.model.Look;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LookGUI {
    private JFrame frame;
    private JPanel panel;
    private JLabel lblTitulo;
    private LookController controller;
    private ItemController itemController;

    public LookGUI() {
        this.controller = new LookController();
        this.itemController = new ItemController();
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Looks");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLayout(new BorderLayout(10, 10));

        lblTitulo = new JLabel("Gerenciar Looks", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(lblTitulo, BorderLayout.NORTH);

        panel = new JPanel(new GridLayout(6, 5, 20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 200, 30, 200));

        adicionarBotoes();

        frame.add(panel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
    }

    public void show(){
        frame.setVisible(true);
    }

    private void adicionarBotoes() {
        adicionarBotao("Criar look", this::criarLook);
        adicionarBotao("Listar looks", this::listarLooks);
        adicionarBotao("Remover look", this::removerLook);
        adicionarBotao("Buscar look", this::buscarLook);
        adicionarBotao("Registrar uso", this::registrarUso);
        adicionarBotao("Adicionar acessório", this::adicionarAcessorioAoLook);
        adicionarBotao("Remover acessório", this::removerAcessorioDoLook);
        adicionarBotao("Trocar parte de cima", this::modificarParteDeCima);
        adicionarBotao("Trocar parte de baixo", this::modificarParteDeBaixo);
        adicionarBotao("Trocar parte intima", this::modificarParteIntima);
        adicionarBotao("Trocar tudo", this::modificarLook);
        adicionarBotao("Trocar partes de cima e de baixo", this::modificarLookCimaBaixo);
        adicionarBotao("Voltar", this::voltar);
    }

    private void voltar(){
        MenuGUI menuGUI=new MenuGUI();
        menuGUI.show();
        frame.dispose();
    }

    private void adicionarBotao(String texto, Runnable acao){
        JButton botao=new JButton(texto);
        botao.setFont(new Font("Arial", Font.PLAIN, 16));
        botao.addActionListener(new ActionListener() {
            @Override
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

    private void registrarUso() {
        try {
            String id = JOptionPane.showInputDialog(frame, "ID do Look para registrar o uso:");
            if (id == null || id.trim().isEmpty()) return;

            controller.registrarUso(id);
            JOptionPane.showMessageDialog(frame, "Look registrado com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao registrar look: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void criarLook() {
        try {

            List<Item> partesDecima = itemController.listarPartesDecima();
            List<Item> partesDebaixo = itemController.listarPartesDebaixo();
            List<Item> partesIntimas = itemController.listarPartesIntimas();
            List<Acessorio> acessorios = itemController.listarAcessorios();

            if (partesDecima.isEmpty() || partesDebaixo.isEmpty() || partesIntimas.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "É necessário ter pelo menos uma peça de cada tipo (cima, baixo, íntima) para criar um look.");
                return;
            }

            JPanel painel = new JPanel(new GridLayout(0, 2, 10, 10));
            panel.setBorder(BorderFactory.createEmptyBorder(30, 200, 30, 200));


            painel.add(new JLabel("Selecione parte de cima:"));
            JComboBox<Item> cbParteCima = new JComboBox<>(new DefaultComboBoxModel<>(partesDecima.toArray(new Item[0])));
            cbParteCima.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                              boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof Item) {
                        Item item = (Item) value;
                        setText("Tipo: " + item.getClass().getSimpleName() + " | Cor: " + item.getCor() + " | Tamanho: " + item.getTamanho());
                    }
                    return this;
                }
            });
            cbParteCima.setPreferredSize(new Dimension(200,30));
            painel.add(cbParteCima);


            painel.add(new JLabel("Selecione parte de baixo:"));
            JComboBox<Item> cbParteBaixo = new JComboBox<>(new DefaultComboBoxModel<>(partesDebaixo.toArray(new Item[0])));
            cbParteBaixo.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                              boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof Item) {
                        Item item = (Item) value;
                        setText("Tipo: " + item.getClass().getSimpleName() + " | Cor: " + item.getCor() + " | Tamanho: " + item.getTamanho());
                    }
                    return this;
                }
            });
            cbParteBaixo.setPreferredSize(new Dimension(200,30));
            painel.add(cbParteBaixo);



            painel.add(new JLabel("Selecione parte íntima:"));
            JComboBox<Item> cbParteIntima = new JComboBox<>(new DefaultComboBoxModel<>(partesIntimas.toArray(new Item[0])));
            cbParteIntima.setPreferredSize(new Dimension(200,30));
            cbParteIntima.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                              boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof Item) {
                        Item item = (Item) value;
                        setText("Tipo: " + item.getClass().getSimpleName() + " | Cor: " + item.getCor() + " | Tamanho: " + item.getTamanho());
                    }
                    return this;
                }
            });
            painel.add(cbParteIntima);


            painel.add(new JLabel("Selecione os acessórios (opcional):"));
            JList<Acessorio> listAcessorios = new JList<>(acessorios.toArray(new Acessorio[0]));
            listAcessorios.setVisibleRowCount(4);
            listAcessorios.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            listAcessorios.setCellRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                              boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof Acessorio a) {
                        setText("ID: " + a.getId() + " | Tipo: " + a.getClass().getSimpleName());
                    }
                    return this;
                }
            });
            JScrollPane scrollAcessorios = new JScrollPane(listAcessorios);
            painel.add(scrollAcessorios);


            int res = JOptionPane.showConfirmDialog(frame, painel, "Criar Look", JOptionPane.OK_CANCEL_OPTION);
            if (res != JOptionPane.OK_OPTION) return;


            Item parteCima = (Item) cbParteCima.getSelectedItem();
            Item parteBaixo = (Item) cbParteBaixo.getSelectedItem();
            Item parteIntima = (Item) cbParteIntima.getSelectedItem();
            List<Acessorio> acessoriosSelecionados = listAcessorios.getSelectedValuesList();

            Look look = new Look(parteCima, parteBaixo, parteIntima, acessoriosSelecionados);
            controller.criarLook(look);

            JOptionPane.showMessageDialog(frame, "Look criado com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao criar look: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void listarLooks() {
        try {
            List<Look> looks = controller.listarLooks();
            if (looks.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Nenhum look cadastrado.");
            } else {
                String[] colunas = {"ID", "Parte de Cima", "Parte de Baixo","Parte Íntima","Acessórios"};

                String[][] dados = new String[looks.size()][colunas.length];

                for (int i = 0; i < looks.size(); i++) {
                    Look look = looks.get(i);

                    Item cima = look.getParteDecima();
                    Item baixo = look.getParteDebaixo();
                    Item intima = look.getParteIntima();
                    List<Acessorio> acessorios = look.getAcessorios();

                    dados[i][0] = look.getId();
                    dados[i][1] = cima != null ? cima.getClass().getSimpleName() + "-" + cima.getCor() : "";
                    dados[i][2] = baixo != null ? baixo.getClass().getSimpleName() + "-" + baixo.getCor() : "";
                    dados[i][3] = intima != null ? intima.getClass().getSimpleName() + "-" + intima.getCor() : "";


                    if (acessorios != null && !acessorios.isEmpty()) {
                        StringBuilder acessoriosTexto = new StringBuilder();
                        for (Acessorio a : acessorios) {
                            acessoriosTexto.append(a.getClass().getSimpleName());
                        }
                        dados[i][4] = acessoriosTexto.toString();
                    } else {
                        dados[i][4] = "";
                    }
                }

                JTable tabela = new JTable(dados, colunas);
                JScrollPane scrollPane = new JScrollPane(tabela);
                scrollPane.setPreferredSize(new Dimension(800, 300));

                JOptionPane.showMessageDialog(frame, scrollPane, "Looks Cadastrados", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao listar looks: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarLook() {
        String id = JOptionPane.showInputDialog(frame, "ID do item:");
        if (id == null || id.trim().isEmpty()) return;

        try {
            Look look = controller.obterLookPorId(id);
            if (look == null) {
                JOptionPane.showMessageDialog(frame, "Look não encontrado.");
                return;
            }

            JPanel painel = new JPanel();
            painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
            painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            Item cima =look.getParteDecima();
            Item baixo=look.getParteDebaixo();
            Item intima=look.getParteIntima();
            List<Acessorio> acessorios = look.getAcessorios();
            painel.add(new JLabel("ID: " + look.getId()));
            painel.add(new JLabel("Parte de cima: " + cima.getClass().getSimpleName() + "-" + cima.getCor()));
            painel.add(new JLabel("Parte de baixo: " + baixo.getClass().getSimpleName() + "-" + baixo.getCor()));
            painel.add(new JLabel("Parte íntima: " + intima.getClass().getSimpleName() + "-" + intima.getCor()));
            if (acessorios != null && !acessorios.isEmpty()) {
                painel.add(Box.createVerticalStrut(10));
                painel.add(new JLabel("Acessórios:"));
                for (Acessorio a : acessorios) {
                    painel.add(new JLabel( a.getClass().getSimpleName() + " - " + a.getCor()));
                }
            }

            JOptionPane.showMessageDialog(frame, painel, "Detalhes do Item", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removerLook() {
        try {
            String id = JOptionPane.showInputDialog(frame, "ID do Look a remover:");
            controller.excluirLook(id);
            JOptionPane.showMessageDialog(frame, "Look removido com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao remover Look: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void adicionarAcessorioAoLook() {
        try {
            String lookId = JOptionPane.showInputDialog(frame, "ID do look:");
            if (lookId == null || lookId.trim().isEmpty()) return;

            List<Acessorio> acessoriosDisponiveis = itemController.listarAcessorios();
            if (acessoriosDisponiveis.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Nenhum acessório disponível.");
                return;
            }

            DefaultComboBoxModel<Acessorio> model = new DefaultComboBoxModel<>();
            for (Acessorio a : acessoriosDisponiveis) {
                model.addElement(a);
            }

            JComboBox<Acessorio> comboBox = new JComboBox<>(model);
            comboBox.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                              boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof Acessorio) {
                        Acessorio a = (Acessorio) value;
                        setText("Tipo: " + a.getClass().getSimpleName() + " | Cor: " + a.getCor());
                    }
                    return this;
                }
            });

            int result = JOptionPane.showConfirmDialog(frame, comboBox, "Selecione um acessório", JOptionPane.OK_CANCEL_OPTION);
            if (result != JOptionPane.OK_OPTION) return;

            Acessorio acessorioSelecionado = (Acessorio) comboBox.getSelectedItem();
            if (acessorioSelecionado == null) {
                JOptionPane.showMessageDialog(frame, "Nenhum acessório selecionado.");
                return;
            }

            controller.adicionarAcessorio(lookId, acessorioSelecionado);
            JOptionPane.showMessageDialog(frame, "Acessório adicionado com sucesso ao look!");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao adicionar acessório: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void removerAcessorioDoLook() {
        try {
            String lookId = JOptionPane.showInputDialog(frame, "ID do look:");
            if (lookId == null || lookId.trim().isEmpty()) return;

            Look look = controller.obterLookPorId(lookId);
            List<Acessorio> acessorios = look.getAcessorios();

            if (acessorios == null || acessorios.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Este look não possui acessórios.");
                return;
            }

            // Criação do modelo e combo box
            DefaultComboBoxModel<Acessorio> model = new DefaultComboBoxModel<>();
            for (Acessorio a : acessorios) {
                model.addElement(a);
            }

            JComboBox<Acessorio> comboBox = new JComboBox<>(model);
            comboBox.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                              boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof Acessorio a) {
                        setText(a.getClass().getSimpleName() + " (" + a.getCor() + ")");
                    }
                    return this;
                }
            });

            int result = JOptionPane.showConfirmDialog(frame, comboBox, "Selecione o acessório a remover", JOptionPane.OK_CANCEL_OPTION);
            if (result != JOptionPane.OK_OPTION) return;

            Acessorio selecionado = (Acessorio) comboBox.getSelectedItem();
            if (selecionado == null) return;

            controller.removerAcessorio(lookId, selecionado.getId());
            JOptionPane.showMessageDialog(frame, "Acessório removido com sucesso do look!");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao remover acessório: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void modificarParteDeCima() {
        try {
            String lookId = JOptionPane.showInputDialog(frame, "ID do look:");
            if (lookId == null || lookId.trim().isEmpty()) return;

            List<Item> itens = itemController.listarPartesDecima();
            if (itens.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Nenhum item disponível para parte de cima.");
                return;
            }

            JComboBox<Item> comboBox = new JComboBox<>(itens.toArray(new Item[0]));
            comboBox.setRenderer(new DefaultListCellRenderer() {
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof Item item) {
                        setText(item.getClass().getSimpleName() + " - " + item.getCor());
                    }
                    return this;
                }
            });

            int result = JOptionPane.showConfirmDialog(frame, comboBox, "Selecione nova parte de cima", JOptionPane.OK_CANCEL_OPTION);
            if (result != JOptionPane.OK_OPTION) return;

            Item selecionado = (Item) comboBox.getSelectedItem();
            controller.modificarParteDeCima(lookId, selecionado);
            JOptionPane.showMessageDialog(frame, "Parte de cima modificada com sucesso!");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao modificar parte de cima: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modificarParteDeBaixo() {
        try {
            String lookId = JOptionPane.showInputDialog(frame, "ID do look:");
            if (lookId == null || lookId.trim().isEmpty()) return;

            List<Item> itens = itemController.listarPartesDebaixo();
            if (itens.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Nenhum item disponível para parte de baixo.");
                return;
            }

            JComboBox<Item> comboBox = new JComboBox<>(itens.toArray(new Item[0]));
            comboBox.setRenderer(new DefaultListCellRenderer() {
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof Item item) {
                        setText(item.getClass().getSimpleName() + " - " + item.getCor());
                    }
                    return this;
                }
            });

            int result = JOptionPane.showConfirmDialog(frame, comboBox, "Selecione nova parte de baixo", JOptionPane.OK_CANCEL_OPTION);
            if (result != JOptionPane.OK_OPTION) return;

            Item selecionado = (Item) comboBox.getSelectedItem();
            controller.modificarParteDeBaixo(lookId, selecionado);
            JOptionPane.showMessageDialog(frame, "Parte de baixo modificada com sucesso!");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao modificar parte de baixo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void modificarParteIntima() {
        try {
            String lookId = JOptionPane.showInputDialog(frame, "ID do look:");
            if (lookId == null || lookId.trim().isEmpty()) return;

            List<Item> itens = itemController.listarPartesIntimas();
            if (itens.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Nenhum item disponível para parte íntima.");
                return;
            }

            JComboBox<Item> comboBox = new JComboBox<>(itens.toArray(new Item[0]));
            comboBox.setRenderer(new DefaultListCellRenderer() {
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof Item item) {
                        setText(item.getClass().getSimpleName() + " - " + item.getCor());
                    }
                    return this;
                }
            });

            int result = JOptionPane.showConfirmDialog(frame, comboBox, "Selecione nova parte íntima", JOptionPane.OK_CANCEL_OPTION);
            if (result != JOptionPane.OK_OPTION) return;

            Item selecionado = (Item) comboBox.getSelectedItem();
            controller.modificarParteIntima(lookId, selecionado);
            JOptionPane.showMessageDialog(frame, "Parte íntima modificada com sucesso!");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao modificar parte íntima: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modificarLook() {
        try {
            String idLook = JOptionPane.showInputDialog(frame, "Informe o ID do look a modificar:");
            if (idLook == null || idLook.trim().isEmpty()) return;

            List<Item> partesDecima = itemController.listarPartesDecima();
            List<Item> partesDebaixo = itemController.listarPartesDebaixo();
            List<Item> partesIntimas = itemController.listarPartesIntimas();

            if (partesDecima.isEmpty() || partesDebaixo.isEmpty() || partesIntimas.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "É necessário ter pelo menos uma peça de cada tipo (cima, baixo, íntima) para modificar o look.");
                return;
            }

            JPanel painel = new JPanel(new GridLayout(0, 2, 10, 10));
            painel.setBorder(BorderFactory.createEmptyBorder(30, 200, 30, 200));


            painel.add(new JLabel("Selecione nova parte de cima:"));
            JComboBox<Item> cbParteCima = new JComboBox<>(new DefaultComboBoxModel<>(partesDecima.toArray(new Item[0])));
            cbParteCima.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                              boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof Item item) {
                        setText("Tipo: " + item.getClass().getSimpleName() + " | Cor: " + item.getCor() + " | Tamanho: " + item.getTamanho());
                    }
                    return this;
                }
            });
            painel.add(cbParteCima);
            painel.add(new JLabel("Selecione nova parte de baixo:"));
            JComboBox<Item> cbParteBaixo = new JComboBox<>(new DefaultComboBoxModel<>(partesDebaixo.toArray(new Item[0])));
            cbParteBaixo.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                              boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof Item item) {
                        setText("Tipo: " + item.getClass().getSimpleName() + " | Cor: " + item.getCor() + " | Tamanho: " + item.getTamanho());
                    }
                    return this;
                }
            });
            painel.add(cbParteBaixo);


            painel.add(new JLabel("Selecione nova parte íntima:"));
            JComboBox<Item> cbParteIntima = new JComboBox<>(new DefaultComboBoxModel<>(partesIntimas.toArray(new Item[0])));
            cbParteIntima.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                              boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof Item item) {
                        setText("Tipo: " + item.getClass().getSimpleName() + " | Cor: " + item.getCor() + " | Tamanho: " + item.getTamanho());
                    }
                    return this;
                }
            });
            painel.add(cbParteIntima);

            int res = JOptionPane.showConfirmDialog(frame, painel, "Modificar Look", JOptionPane.OK_CANCEL_OPTION);
            if (res != JOptionPane.OK_OPTION) return;

            Item parteCima = (Item) cbParteCima.getSelectedItem();
            Item parteBaixo = (Item) cbParteBaixo.getSelectedItem();
            Item parteIntima = (Item) cbParteIntima.getSelectedItem();

            controller.modificarLook(idLook, parteCima, parteBaixo, parteIntima);
            JOptionPane.showMessageDialog(frame, "Look modificado com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao modificar look: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }




    private void modificarLookCimaBaixo() {
        String id = JOptionPane.showInputDialog(frame, "ID do Look a ser modificado:");
        if (id == null || id.trim().isEmpty()) return;

        try {
            Look lookExistente = controller.obterLookPorId(id);
            if (lookExistente == null) {
                JOptionPane.showMessageDialog(frame, "Look não encontrado.");
                return;
            }

            List<Item> partesDecima = itemController.listarPartesDecima();
            List<Item> partesDebaixo = itemController.listarPartesDebaixo();

            if (partesDecima.isEmpty() || partesDebaixo.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "É necessário ter pelo menos uma peça de cada tipo (cima e baixo) para modificar o look.");
                return;
            }

            JPanel painel = new JPanel(new GridLayout(0, 2, 10, 10));
            painel.setBorder(BorderFactory.createEmptyBorder(30, 200, 30, 200));

            painel.add(new JLabel("Selecione nova parte de cima:"));
            JComboBox<Item> cbParteCima = new JComboBox<>(new DefaultComboBoxModel<>(partesDecima.toArray(new Item[0])));
            cbParteCima.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                              boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof Item item) {
                        setText("Tipo: " + item.getClass().getSimpleName() + " | Cor: " + item.getCor() + " | Tamanho: " + item.getTamanho());
                    }
                    return this;
                }
            });
            painel.add(cbParteCima);

            painel.add(new JLabel("Selecione nova parte de baixo:"));
            JComboBox<Item> cbParteBaixo = new JComboBox<>(new DefaultComboBoxModel<>(partesDebaixo.toArray(new Item[0])));
            cbParteBaixo.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                              boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof Item item) {
                        setText("Tipo: " + item.getClass().getSimpleName() + " | Cor: " + item.getCor() + " | Tamanho: " + item.getTamanho());
                    }
                    return this;
                }
            });
            painel.add(cbParteBaixo);

            int res = JOptionPane.showConfirmDialog(frame, painel, "Modificar Look", JOptionPane.OK_CANCEL_OPTION);
            if (res != JOptionPane.OK_OPTION) return;

            Item novaParteCima = (Item) cbParteCima.getSelectedItem();
            Item novaParteBaixo = (Item) cbParteBaixo.getSelectedItem();

            controller.modificarLook(id, novaParteCima, novaParteBaixo);

            JOptionPane.showMessageDialog(frame, "Look modificado com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao modificar look: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }









}
