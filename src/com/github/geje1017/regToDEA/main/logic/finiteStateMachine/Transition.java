package com.github.geje1017.regToDEA.main.logic.finiteStateMachine;

/**
 * Represents a transition between two states in an EA.
 */
public class Transition {

    private State sourceState; // The source state of the transition
    private String inputSymbol; // The input symbol triggering the transition
    private State targetState; // The target state of the transition

    /**
     * Constructs a transition with the given source state, input symbol, and target state.
     *
     * @param sourceState The source state of the transition.
     * @param inputSymbol The input symbol triggering the transition.
     * @param targetState The target state of the transition.
     */
    public Transition(State sourceState, String inputSymbol, State targetState) {
        // Todo: Implement universal method to validate input
        this.sourceState = sourceState;
        this.inputSymbol = inputSymbol;
        this.targetState = targetState;
    }

    /**
     * Gets the source state of the transition.
     *
     * @return The source state of the transition.
     */
    public State getSourceState() {
        return sourceState;
    }

    /**
     * Sets the source state of the transition.
     *
     * @param sourceState The source state of the transition.
     */
    public void setSourceState(State sourceState) {
        this.sourceState = sourceState;
    }

    /**
     * Gets the input symbol triggering the transition.
     *
     * @return The input symbol triggering the transition.
     */
    public String getInputSymbol() {
        return inputSymbol;
    }

    /**
     * Sets the input symbol triggering the transition.
     *
     * @param inputSymbol The input symbol triggering the transition.
     */
    public void setInputSymbol(String inputSymbol) {
        this.inputSymbol = inputSymbol;
    }

    /**
     * Gets the target state of the transition.
     *
     * @return The target state of the transition.
     */
    public State getTargetState() {
        return targetState;
    }

    /**
     * Sets the target state of the transition.
     *
     * @param targetState The target state of the transition.
     */
    public void setTargetState(State targetState) {
        this.targetState = targetState;
    }

    /**
     * Returns a string representation of the transition.
     *
     * @return A string representing the transition in the format:
     * </p>δ(sourceState,inputSymbol)=targetState.
     */
    @Override
    public String toString() {
        return "δ(" + sourceState + "," + inputSymbol + ")=" + targetState;
    }
}
