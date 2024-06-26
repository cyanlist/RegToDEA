package com.github.geje1017.regToDEA.main.logic.postfix;

import com.github.geje1017.regToDEA.main.logic.finiteStateMachine.FiniteStateMachineImplementation;
import com.github.geje1017.regToDEA.main.logic.finiteStateMachine.FiniteStateMachineOperation;
import com.github.geje1017.regToDEA.main.logic.finiteStateMachine.State;

import java.util.ArrayList;
import java.util.Stack;

/**
 * This class provides methods to evaluate infix expressions.
 * An infix expression is first converted to postfix notation and then evaluated.
 */
public class ExpressionEvaluator {

    /**
     * Evaluates an infix expression.
     *
     * @param expression The infix expression to be evaluated.
     * @return The list of FSMs resulting from the evaluation.
     */
    public ArrayList<FiniteStateMachineImplementation> evaluateExpression(String expression) {
        ArrayList<FiniteStateMachineImplementation> fsmList = new ArrayList<>();
        ArrayList<FiniteStateMachineImplementation> minimizedFsmList = new ArrayList<>();

        String postfixExpression = ExpressionConverter.infixToPostfix(expression);
        System.out.println("Postfix Expression: " + postfixExpression);

        State.resetUniqueName();
        evaluatePostfixExpression(postfixExpression, fsmList, minimizedFsmList);

        ArrayList<FiniteStateMachineImplementation> combinedList = new ArrayList<>();
        combinedList.addAll(fsmList); // Füge alle Elemente von list1 hinzu
        combinedList.addAll(minimizedFsmList);

        return combinedList;
    }

    /**
     * Evaluates a postfix expression.
     *
     * @param postfixExpression  The postfix expression to be evaluated.
     * @param fsmList            List to store FSMs resulting from the evaluation.
     * @param minimizedFsmList   List to store minimized FSMs resulting from the evaluation.
     */
    private void evaluatePostfixExpression(String postfixExpression, ArrayList<FiniteStateMachineImplementation> fsmList, ArrayList<FiniteStateMachineImplementation> minimizedFsmList) {
        Stack<FiniteStateMachineImplementation> stack = new Stack<>();

        for (int i = 0; i < postfixExpression.length(); i++) {
            char currentChar = postfixExpression.charAt(i);

            if (currentChar == ' ') {
                continue; // Skip whitespace
            }

            if (isOperand(currentChar)) {
                // Extract operands from the expression
                StringBuilder operandBuilder = new StringBuilder();
                while (i < postfixExpression.length() && isOperand(postfixExpression.charAt(i))) {
                    operandBuilder.append(postfixExpression.charAt(i++));
                }
                // Push the operand onto the stack
                FiniteStateMachineImplementation fsm = FiniteStateMachineOperation.convertInputCharacter(operandBuilder.toString());
                stack.push(fsm);
                fsmList.add(fsm);
                i--; // Move back one step to correct the index
            } else {
                // Evaluate operator
                FiniteStateMachineImplementation operand1, operand2;

                switch (currentChar) {
                    case '*':
                        // Kleene Closure operation
                        operand1 = stack.pop();
                        stack.push(FiniteStateMachineOperation.applyKleeneClosure(operand1));
                        break;
                    case '+':
                        // Positive Closure operation
                        operand1 = stack.pop();
                        stack.push(FiniteStateMachineOperation.applyPositiveClosure(operand1));
                        break;
                    case ',':
                        // Concatenation operation
                        operand2 = stack.pop();
                        operand1 = stack.pop();
                        stack.push(FiniteStateMachineOperation.concatenate(operand1, operand2));
                        break;
                    case '|':
                        // Alternation operation
                        operand2 = stack.pop();
                        operand1 = stack.pop();
                        stack.push(FiniteStateMachineOperation.alternate(operand1, operand2));
                        break;
                    default:
                        // Default case, should not occur in well-formed expressions
                        System.out.println("Invalid character: " + currentChar);
                }

                operand1 = stack.peek();
                minimizedFsmList.add(operand1);

                // operand1 = stack.pop();
                // stack.push(FiniteStateMachineOperation.toDeterministicFsm(operand1));

                // operand1 = stack.pop();
                // FiniteStateMachineImplementation minimizedFsm = FiniteStateMachineOperation.minimize(operand1);
                // stack.push(minimizedFsm);
            }
        }
    }

    /**
     * Checks if the given character is an operand.
     *
     * @param character The character to be checked.
     * @return True if the character is an operand, false otherwise.
     */
    private boolean isOperand(char character) {
        return !isOperator(character) && character != '(' && character != ')' && character != ' ';
    }

    /**
     * Checks if the given character is an operator.
     *
     * @param character The character to be checked.
     * @return True if the character is an operator, false otherwise.
     */
    private boolean isOperator(char character) {
        return character == '*' || character == '|' || character == '+' || character == ',';
    }
}
