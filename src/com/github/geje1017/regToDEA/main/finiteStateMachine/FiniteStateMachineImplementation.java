package com.github.geje1017.regToDEA.main.finiteStateMachine;

import java.util.ArrayList;
import java.util.HashSet;

public class FiniteStateMachineImplementation implements FiniteStateMachineInterface {

    /*
     * List of transitions in the FSM.
     */
    protected ArrayList<Transition> transitions;    // Inhabits all transitions
    protected ArrayList<State> startStates;         // Inhabits all startStates
    protected ArrayList<State> finalStates;         // Inhabits all finalStates
    protected String expression = "";

    /**
     * Constructs a FSM.
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

    public void printDefinition() {

        // Expression
        System.out.println(" " + expression);
        // States (Q)
        HashSet<State> uniqueStates = new HashSet<>();
        for (Transition transition : transitions) {
            if (transition.getSourceState() != null) {
                uniqueStates.add(transition.getSourceState());
            }
            uniqueStates.add(transition.getTargetState());
        }
        System.out.println("Q=" + uniqueStates);

        // Input symbols (Σ)
        HashSet<String> inputSymbols = new HashSet<>();
        for (Transition transition : transitions) {
            inputSymbols.add(transition.getInputSymbol());
        }
        System.out.println("∑=" + inputSymbols);

        // Transition (δ: Q x ∑ --> Q)
        System.out.println("δ: Q x ∑ --> Q: [");
        for (Transition transition : transitions) {
            System.out.println("\t" + transition);
        }
        System.out.println("]");

        // Start states
        System.out.println("Startzustände=" + startStates);

        // Final states
        System.out.println("F=" + finalStates);
    }

    @Override
    public String toString() {
        return this.expression;
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

    public String getExpression() {
        return this.expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
