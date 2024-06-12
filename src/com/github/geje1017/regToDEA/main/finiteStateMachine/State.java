package com.github.geje1017.regToDEA.main.finiteStateMachine;

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

    /**
     * Gets the name of the state.
     *
     * @return The name of the state.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the state.
     * Trims leading and trailing whitespace.
     *
     * @param name The name of the state.
     * @throws IllegalArgumentException if the name is blank or null.
     */
    public void setName(String name) {
        // Todo: Implement universal method to validate input
        if (name.isBlank()) {
            throw new IllegalArgumentException("Invalid name for State");
        }
        this.name = name.trim();
    }

    /**
     * Returns the name of the state as its string representation.
     *
     * @return The name of the state.
     */
    @Override
    public String toString() {
        return this.name;
    }
}