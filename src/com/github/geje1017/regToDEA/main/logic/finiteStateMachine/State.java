package com.github.geje1017.regToDEA.main.logic.finiteStateMachine;

/**
 * Represents a state in an EA.
 */
public class State {

    private String name; // The name of the state

    private static int uniqueName = 0;

    /**
     * Constructs a state of an EA with a generated name.
     */
    public State() {
        this.name = generateName();
    }

    /**
     * Generates a unique name for the state.
     *
     * @return The generated name.
     */
    private String generateName() {
        return "q" + uniqueName++;
    }

    public void fuseName(State state) {
        this.name += state.getName();
    }

    // Getter and setter methods

    public String getName() {
        return name;
    }
    public static void resetUniqueName() {
        uniqueName = 0;
    }

    @Override
    public String toString() {
        return this.name;
    }
}