package com.github.geje1017.regToDEA.main.finiteStateMachine;

import java.util.ArrayList;

public interface FiniteStateMachineInterface {

    /**
     * Adds a transition to the automaton.
     *
     * @param sourceState The source state of the transition.
     * @param inputSymbol The input symbol triggering the transition.
     * @param targetState The target state of the transition.
     */
    void addTransition(State sourceState, String inputSymbol, State targetState);

    /**
     * Prints the definition of the automaton.
     * <pre>
     * - Expression
     * - States (Q)
     * - Input alphabet (Σ)
     * - Transition functions (δ: Q x Σ --> Q)
     * - Start states
     * - Final states (F)
     * </pre>
     */
    void printDefinition();

    // Getter and setter methods

    void addStartState(State state);
    State getStartState();
    void addFinalState(State state);
    ArrayList<State> getFinalStates();
    String getExpression();
    void setExpression(String expression);
}
