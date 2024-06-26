package com.github.geje1017.regToDEA.main.gui;

import com.github.geje1017.regToDEA.main.logic.postfix.ExpressionEvaluator;
import java.awt.event.ActionListener;

public abstract class Controller implements ActionListener {

    protected Frame frame;
    protected ExpressionEvaluator evaluator;

    public Controller(Frame frame) {
        this.frame = frame;
        this.evaluator = new ExpressionEvaluator();
    }
}
