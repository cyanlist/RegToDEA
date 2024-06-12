package com.github.geje1017.regToDEA.main.postfix;

import com.github.geje1017.regToDEA.main.finiteStateMachine.FiniteStateMachineImplementation;
import com.github.geje1017.regToDEA.main.finiteStateMachine.FiniteStateMachineOperation;

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
     * @return The result of the evaluation.
     */
    public static FiniteStateMachineImplementation evaluateExpression(String expression) {
        String postfixExpression = ExpressionConverter.infixToPostfix(expression);
        System.out.println("Postfix Expression: " +  postfixExpression);
        return evaluatePostfixExpression(postfixExpression);
    }

    /**
     * Evaluates a postfix expression.
     *
     * @param postfixExpression The postfix expression to be evaluated.
     * @return The result of the evaluation.
     */
    private static FiniteStateMachineImplementation evaluatePostfixExpression(String postfixExpression) {
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
                stack.push(FiniteStateMachineOperation.convertInputCharacter(operandBuilder.toString()));
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
                        // Kleene Closure operation
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

                stack.peek().printDefinition();

                operand1 = stack.pop();
                stack.push(FiniteStateMachineOperation.toDeterministicFsm(operand1));

                operand1 = stack.pop();
                stack.push(FiniteStateMachineOperation.minimize(operand1));

            }
        }
        // Result of the evaluation is on the top of the stack
        return stack.pop();
    }

    /**
     * Checks if the given character is an operand.
     *
     * @param character The character to be checked.
     * @return True if the character is an operand, false otherwise.
     */
    private static boolean isOperand(char character) {
        return !isOperator(character) && character != '(' && character != ')' && character != ' ';
    }

    /**
     * Checks if the given character is an operator.
     *
     * @param character The character to be checked.
     * @return True if the character is an operator, false otherwise.
     */
    private static boolean isOperator(char character) {
        return character == '*' || character == '|' || character == '+' || character == ',';
    }
}
