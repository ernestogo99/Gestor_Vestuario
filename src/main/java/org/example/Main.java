package org.example;

import org.example.view.MenuGUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MenuGUI main=new MenuGUI();
                main.show();
            }
        });

    }
}
