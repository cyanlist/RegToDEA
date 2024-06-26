package com.github.geje1017.regToDEA.main.logic.finiteStateMachine;

// Todo: Add exception system
public class FiniteStateMachineOperation {

    /**
     * Converts the input symbol to an FSM.
     *
     * @param inputSymbol The input symbol to be converted.
     * @return The converted FSM.
     */
    public static FiniteStateMachineImplementation convertInputCharacter(String inputSymbol) {

        FiniteStateMachineImplementation convertedFSM = new FiniteStateMachineImplementation();

        // Todo: Implement universal method to validate input
        if (inputSymbol != null && !inputSymbol.isBlank()) {
            switch (inputSymbol.trim()) {
                case "e":
                    convertEmptySymbol(convertedFSM);
                    break;
                case "0/":
                    convertZeroSymbol(convertedFSM);
                    break;
                default:
                    convertRegularSymbol(convertedFSM, inputSymbol);
                    break;
            }
        }
        return convertedFSM;
    }

    /**
     * Handles the case when the input symbol is "e".
     *
     * @param fsm The FSM to be modified.
     */
    private static void convertEmptySymbol(FiniteStateMachineImplementation fsm) {

        State startEndState = new State();

        fsm.addStartState(startEndState);
        fsm.addFinalState(startEndState);

        fsm.addTransition(startEndState, null, startEndState);

        if (fsm.getExpression().isEmpty()) {
            fsm.setExpression("ε");
        }
    }

    /**
     * Handles the case when the input symbol is "0/".
     *
     * @param fsm The FSM to be modified.
     */
    private static void convertZeroSymbol(FiniteStateMachineImplementation fsm) {

        State startState = new State();
        fsm.addStartState(startState);

        fsm.addTransition(startState, null, null);

        if (fsm.getExpression().isEmpty()) {
            fsm.setExpression("∅");
        }
    }

    /**
     * Handles the case when the input symbol is a regular symbol.
     *
     * @param fsm The FSM to be modified.
     * @param inputSymbol The input symbol.
     */
    private static void convertRegularSymbol(FiniteStateMachineImplementation fsm, String inputSymbol) {

        State startState = new State();
        State finalState = new State();

        fsm.addStartState(startState);
        fsm.addFinalState(finalState);

        fsm.addTransition(startState, inputSymbol, finalState);

        fsm.setExpression(inputSymbol);
    }

    /**
     * Concatenates two deterministic FSMs.
     *
     * @param fsm1 The first deterministic FSM.
     * @param fsm2 The second deterministic FSM.
     * @return The concatenated FSM.
     */
    public static FiniteStateMachineImplementation concatenate(FiniteStateMachineImplementation fsm1, FiniteStateMachineImplementation fsm2) {

        FiniteStateMachineImplementation concatenatedFsm = new FiniteStateMachineImplementation();

        concatenatedFsm.transitions.addAll(fsm1.transitions);
        concatenatedFsm.startStates.addAll(fsm1.startStates);

        // Replace the start state of fsm2 with all corresponding final states in fsm1
        for (Transition fsm2Transition : fsm2.transitions) {
            State sourceState = fsm2Transition.getSourceState();
            State targetState = fsm2Transition.getTargetState();

            if (sourceState.equals(fsm2.getStartState())) {
                for (State fsm1FinalState : fsm1.finalStates) {
                    concatenatedFsm.addTransition(
                            fsm1FinalState,
                            fsm2Transition.getInputSymbol(),
                            targetState.equals(fsm2.getStartState()) ? fsm1FinalState : targetState
                    );
                }
            } else {
                concatenatedFsm.transitions.add(fsm2Transition);
            }
        }

        concatenatedFsm.finalStates.addAll(fsm2.finalStates);
        concatenatedFsm.setExpression(fsm1.getExpression() + "," + fsm2.getExpression());
        return concatenatedFsm;
    }

    /**
     * Creates an alternation of two deterministic FSMs.
     *
     * @param fsm1 The first deterministic FSM.
     * @param fsm2 The second deterministic FSM.
     * @return The alternated FSM.
     */
    public static FiniteStateMachineImplementation alternate(FiniteStateMachineImplementation fsm1, FiniteStateMachineImplementation fsm2) {

        FiniteStateMachineImplementation alternatedFsm = new FiniteStateMachineImplementation();

        State fsm1StartState = fsm1.getStartState();

        alternatedFsm.addAllTransitions(fsm1.transitions);
        alternatedFsm.startStates.addAll(fsm1.startStates);
        alternatedFsm.finalStates.addAll(fsm1.finalStates);

        // Replace the start states of fsm1 and fsm2 with a new shared start state
        for (Transition fsm2Transition : fsm2.transitions) {
            State tempSourceState = fsm2Transition.getSourceState();
            State tempTargetState = fsm2Transition.getTargetState();
            if (fsm2Transition.getSourceState().equals(fsm2.getStartState())) {
                tempSourceState = fsm1StartState;
            }
            if (fsm2Transition.getTargetState().equals(fsm2.getStartState())) {
                tempTargetState = fsm1StartState;
            }
            alternatedFsm.addTransition(tempSourceState, fsm2Transition.getInputSymbol(), tempTargetState);
        }

        alternatedFsm.finalStates.addAll(fsm2.finalStates);
        alternatedFsm.setExpression(fsm1.getExpression() + "|" + fsm2.getExpression());
        return alternatedFsm;
    }

    /**
     * Applies positive closure to a deterministic FSM.
     * Positive closure means that the FSM can repeat its entire sequence of states one or more times.
     *
     * @param fsm The deterministic FSM to apply positive closure to.
     * @return The FSM after applying positive closure.
     */
    public static FiniteStateMachineImplementation applyPositiveClosure(FiniteStateMachineImplementation fsm) {

        FiniteStateMachineImplementation positiveClosureFsm = new FiniteStateMachineImplementation();

        State startState = fsm.getStartState();

        positiveClosureFsm.transitions.addAll(fsm.transitions);
        positiveClosureFsm.startStates.addAll(fsm.startStates);
        positiveClosureFsm.finalStates.addAll(fsm.finalStates);
        positiveClosureFsm.setExpression(fsm.getExpression());

        // Create a feedback loop for each state that transitions to a final state, linking it back to the start state to enable repetition
        for (Transition newTransitions : fsm.transitions) {
            for (State finalState : fsm.finalStates) {
                if (newTransitions.getTargetState().equals(finalState)) {
                    positiveClosureFsm.addTransition(newTransitions.getSourceState(), newTransitions.getInputSymbol(), startState);
                }
            }
        }

        positiveClosureFsm.setExpression("(" + positiveClosureFsm.getExpression() + ")+");
        return positiveClosureFsm;
    }

    /**
     * Applies Kleene closure to a deterministic FSM.
     *
     * @param fsm The deterministic FSM to apply Kleene closure to.
     * @return The FSM after applying Kleene closure.
     */
    public static FiniteStateMachineImplementation applyKleeneClosure(FiniteStateMachineImplementation fsm) {

        // Extend positiveClosure()
        FiniteStateMachineImplementation kleeneClosureFsm = applyPositiveClosure(fsm);
        convertEmptySymbol(kleeneClosureFsm);

        kleeneClosureFsm.setExpression(kleeneClosureFsm.getExpression() + "*");
        return kleeneClosureFsm;

    }

    /**
     * Minimize a FSM.
     *
     * @param fsm The deterministic FSM to minimize.
     * @return The minimized DEA.
     */
    public static FiniteStateMachineImplementation minimize(FiniteStateMachineImplementation fsm) {
        //Todo: Implement logic
        System.out.print("\nTODO: Minimized FSM: " + fsm.getExpression() + "\n");
        return fsm;
    }

    /**
     * Validates whether the FSM is deterministic.
     * If it is not, the method will convert it into a valid DFSM.
     *
     * @param fsm The FSM to convert into a deterministic FSM.
     * @return The validated or converted DFSM.
     */
    public static FiniteStateMachineImplementation toDeterministicFsm(FiniteStateMachineImplementation fsm) {
        //Todo: Implement logic
        System.out.print("\nTODO: Deterministic FSM: " + fsm.getExpression());
        return fsm;
    }

}
