package com.github.geje1017.regToDEA.main.gui;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    private final Controller controller;

    private final JPanel inputPanel = new JPanel(new FlowLayout());
    private final JPanel resultPanel = new JPanel(new BorderLayout());
    private final JTextField textField = new JTextField("(DEA|NEA) , sind , cool , (!)+", 20);
    private final JButton button = new JButton("Convert");

    public Frame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Set the size of the window to 80% of the maximum screen size
        int frameWidth = (int) (screenSize.width * 0.8);
        int frameHeight = (int) (screenSize.height * 0.8);
        this.setSize(frameWidth, frameHeight);

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        this.controller = new Controller(this);

        // Input Panel
        inputPanel.add(new JLabel("Regular Expression:"));
        inputPanel.add(textField);
        inputPanel.add(button);

        // Result Panel
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(resultPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.add(inputPanel, BorderLayout.PAGE_START);
        this.add(scrollPane, BorderLayout.CENTER);

        this.button.addActionListener(this.controller);

        this.setVisible(true);
    }

    public String getInputText() {
        return textField.getText();
    }

    public void clearResults() {
        resultPanel.removeAll();
        resultPanel.revalidate();
        resultPanel.repaint();
    }

    public void addResult(String text) {

        JTextArea textArea = new JTextArea(text + "\n");
        textArea.setPreferredSize(new Dimension(this.getWidth()/2, textArea.getPreferredSize().height));
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);

        JPanel centeredPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centeredPanel.add(textArea);

        resultPanel.add(centeredPanel);
        resultPanel.revalidate();
        resultPanel.repaint();
    }

}
