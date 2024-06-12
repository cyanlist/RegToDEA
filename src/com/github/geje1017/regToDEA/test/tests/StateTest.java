package com.github.geje1017.regToDEA.test.tests;

import static org.junit.jupiter.api.Assertions.*;

import com.github.geje1017.regToDEA.main.finiteStateMachine.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StateTest {

    private State state;

    @BeforeEach
    public void setUp() {
        state = new State();
    }

    @Test
    public void testGetName() {
        assertEquals("q0", state.getName(), "Checking if the state name is set correctly");
    }

    @Test
    public void testSetName() {
        state.setName("NewTestState");
        assertEquals("NewTestState", state.getName(), "Checking if the state name can be updated");
    }

    @Test
    public void testSetBlankName() {
        state.setName("");
        assertEquals("q0", state.getName(), "Checking if setting a blank name results in null");
    }

    @Test
    public void testSetNullName() {
        state.setName(null);
        assertEquals("q0", state.getName(), "Checking if setting a null name results in null");
    }


}

