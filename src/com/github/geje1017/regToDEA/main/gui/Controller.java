package com.github.geje1017.regToDEA.main.gui;

import com.github.geje1017.regToDEA.main.logic.postfix.ExpressionEvaluator;
import com.github.geje1017.regToDEA.main.logic.finiteStateMachine.FiniteStateMachineImplementation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controller implements ActionListener {

    private Frame frame;
    private ExpressionEvaluator evaluator;

    public Controller(Frame frame) {
        this.frame = frame;
        this.evaluator = new ExpressionEvaluator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Vorhandene Ergebnisse löschen
        frame.clearResults();

        String inputText = frame.getInputText();
        ArrayList<FiniteStateMachineImplementation> minimizedFsmList = evaluator.evaluateExpression(inputText);

        for (FiniteStateMachineImplementation fsm : minimizedFsmList) {
            frame.addResult(fsm.toString());
        }
    }
}
