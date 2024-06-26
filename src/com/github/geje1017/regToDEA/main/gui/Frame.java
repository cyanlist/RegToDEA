package com.github.geje1017.regToDEA.main.gui;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    private static final String DEFAULT_REGEX = "(DEA|Expressions) , sind , cool , (!)+";

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final JPanel inputPanel = new JPanel(new FlowLayout());
    private final JPanel resultPanel = new JPanel(new BorderLayout());
    private final JTextField textField = new JTextField(DEFAULT_REGEX);
    private final JButton convertButton = new JButton("Convert");

    public Frame() {
        initializeFrame();
        initializeInputPanel();
        initializeResultPanel();

        this.convertButton.addActionListener(new ConvertButtonController(this));
        this.setVisible(true);
    }

    private void initializeFrame() {
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int frameWidth = (int) (screenSize.width * 0.8);
        int frameHeight = (int) (screenSize.height * 0.8);
        this.setSize(frameWidth, frameHeight);

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
    }

    private void initializeInputPanel() {
        Dimension tempDimension = new Dimension(screenSize.width/2, textField.getPreferredSize().height);
        textField.setPreferredSize(tempDimension);
        inputPanel.add(new JLabel("Regular Expression:"));
        inputPanel.add(textField);
        inputPanel.add(convertButton);
        this.add(inputPanel, BorderLayout.PAGE_START);
    }

    private void initializeResultPanel() {
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(resultPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(16);
        verticalScrollBar.setBlockIncrement(50);

        this.add(scrollPane, BorderLayout.CENTER);
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
        JTextArea textArea = createTextArea(text);
        JPanel centeredPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centeredPanel.add(textArea);

        resultPanel.add(centeredPanel);
        resultPanel.revalidate();
        resultPanel.repaint();
    }

    private JTextArea createTextArea(String text) {
        JTextArea textArea = new JTextArea(text + "\n");
        Dimension tempDimension = new Dimension(screenSize.width/2, textArea.getPreferredSize().height);
        textArea.setPreferredSize(tempDimension);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        return textArea;
    }
}
