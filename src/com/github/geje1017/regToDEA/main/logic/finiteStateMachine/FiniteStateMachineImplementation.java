package com.github.geje1017.regToDEA.main.logic.finiteStateMachine;

import com.github.geje1017.regToDEA.main.logic.CustomHashSet;

import java.util.ArrayList;

public class FiniteStateMachineImplementation {

    /*
     * List of transitions in the FSM.
     */
    protected ArrayList<Transition> transitions;    // Inhabits all transitions
    protected ArrayList<State> startStates;         // Inhabits all startStates
    protected ArrayList<State> finalStates;         // Inhabits all finalStates
    protected String expression = "";

    /**
     * Constructs an FSM.
     */
    protected FiniteStateMachineImplementation() {
        transitions = new ArrayList<>();
        startStates = new ArrayList<>();
        finalStates = new ArrayList<>();
        expression = "";
    }

    public void addTransition(State sourceState, String inputSymbol, State targetState) {
        transitions.add(new Transition(sourceState, inputSymbol, targetState));
    }

    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        // Expression
        res.append("Current evaluated expression: ").append(expression);
        // States (Q)
        res.append("\nM=(Q,∑,δ,S,F)");
        CustomHashSet<State> uniqueStates = new CustomHashSet<>();
        for (Transition transition : transitions) {
            if (transition.getSourceState() != null) {
                uniqueStates.add(transition.getSourceState());
            }
            uniqueStates.add(transition.getTargetState());
        }
        res.append("\nQ=").append(uniqueStates);

        // Input symbols (Σ)
        CustomHashSet<String> inputSymbols = new CustomHashSet<>();
        for (Transition transition : transitions) {
            inputSymbols.add(transition.getInputSymbol());
        }
        res.append("\n∑=").append(inputSymbols);

        // Transition (δ: Q x ∑ --> Q)
        res.append("\nδ: Q x ∑ --> Q: {");
        for (Transition transition : transitions) {
            res.append("\n\t").append(transition);
        }
        res.append("}");

        // Start states
        res.append("\nS=").append(startStates);

        // Final states
        res.append("\nF=").append(finalStates);

        return res.toString();
    }

    // Getter and setter methods

    public void addStartState(State state) {
        this.startStates.add(state);
    }
    public State getStartState() {
        return this.startStates.get(0);
    }
    public void addFinalState(State state) {
        this.finalStates.add(state);
    }
    public ArrayList<State> getFinalStates() {
        return this.finalStates;
    }
    public void addAllTransitions(ArrayList<Transition> transitions) {
        this.transitions.addAll(transitions);
    }
    public String getExpression() {
        return this.expression;
    }
    public void setExpression(String expression) {
        this.expression = expression;
    }
}
