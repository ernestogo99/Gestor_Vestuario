    package org.example.view;

    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;

    public class MenuGUI {
        private JFrame frame;
        private JPanel panel;
        private JButton btnItens;
        private JButton btnLooks;
        private JButton btnLavagens;
        private JButton btnEmprestimos;
        private JButton btnEstatisticas;
        private JButton btnSair;
        private JLabel lblTitulo;


        public MenuGUI(){
            this.initialize();
        }

        private void initialize(){
            frame=new JFrame();
            frame.setTitle("Gestor Vestuário");

            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(800,500);
            frame.setLayout(new BorderLayout(10, 10));

            lblTitulo = new JLabel("Menu Principal", SwingConstants.CENTER);
            lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
            frame.add(lblTitulo, BorderLayout.NORTH);

            panel = new JPanel(new GridLayout(6,1,10,10));
            panel.setBorder(BorderFactory.createEmptyBorder(30, 200, 30, 200));
            btnItens = new JButton("Operações com Itens");
            btnLooks = new JButton("Operações com Looks");
            btnLavagens = new JButton("Registrar Lavagens");
            btnEstatisticas = new JButton("Ver Estatísticas");
            btnSair = new JButton("Sair");

            panel.add(btnItens);
            panel.add(btnLooks);
            panel.add(btnLavagens);
            panel.add(btnEstatisticas);
            panel.add(btnSair);

            btnItens.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ItemGUI itemGUI = new ItemGUI();
                    itemGUI.show();
                    frame.dispose();
                }
            });

            btnEstatisticas.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    EstatisticaGUI estatisticaGUI = new EstatisticaGUI();
                    estatisticaGUI.show();
                    frame.dispose();
                }
            });

            btnLavagens.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    LavagemGUI lavagemGUI= new LavagemGUI();
                    lavagemGUI.show();
                    frame.dispose();
                }
            });

            frame.add(panel);

            btnLooks.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    LookGUI lookGUI=new LookGUI();
                    lookGUI.show();
                    frame.dispose();
                }
            });

        }

        public void show(){
            frame.setVisible(true);
        }
    }
