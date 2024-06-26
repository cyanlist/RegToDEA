package com.github.geje1017.regToDEA.main.gui;

import com.github.geje1017.regToDEA.main.logic.finiteStateMachine.FiniteStateMachineImplementation;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class ConvertButtonController extends Controller{

    public ConvertButtonController(Frame frame) {
        super(frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.clearResults();

        String inputText = frame.getInputText();
        ArrayList<FiniteStateMachineImplementation> minimizedFsmList = evaluator.evaluateExpression(inputText);

        for (FiniteStateMachineImplementation fsm : minimizedFsmList) {
            frame.addResult(fsm.toString());
        }
    }
}
