package com.github.geje1017.regToDEA.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CollapsiblePanelExample extends JFrame {

    private JPanel mainPanel;
    private JPanel collapsiblePanel;
    private JToggleButton toggleButton;

    public CollapsiblePanelExample() {
        setTitle("Einklappbares Panel Beispiel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout());
        collapsiblePanel = new JPanel();
        collapsiblePanel.setLayout(new BoxLayout(collapsiblePanel, BoxLayout.Y_AXIS));
        collapsiblePanel.setBorder(BorderFactory.createEtchedBorder());

        // Beispielinhalt für das einklappbare Panel
        for (int i = 0; i < 10; i++) {
            collapsiblePanel.add(new JLabel("Eintrag " + (i + 1)));
        }

        JScrollPane scrollPane = new JScrollPane(collapsiblePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        toggleButton = new JToggleButton("Einklappen");
        toggleButton.addActionListener(e -> {
            if (toggleButton.isSelected()) {
                // Einklappen
                mainPanel.remove(scrollPane);
            } else {
                // Ausklappen
                mainPanel.add(scrollPane, BorderLayout.CENTER);
            }
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        mainPanel.add(toggleButton, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CollapsiblePanelExample();
            }
        });
    }
}

