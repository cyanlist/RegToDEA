package com.github.geje1017.regToDEA.main.logic.postfix;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * This class provides methods to convert infix expressions to postfix notation.
 */
public class ExpressionConverter {

    // Enum for operator priorities
    private enum OperatorPriority {
        LOW, MEDIUM, HIGH
    }

    // Constants for operators and their priorities
    private static final Map<Character, OperatorPriority> OPERATOR_PRIORITY_MAP = new HashMap<>();
    static {
        OPERATOR_PRIORITY_MAP.put('|', OperatorPriority.LOW);
        OPERATOR_PRIORITY_MAP.put(',', OperatorPriority.LOW);
        OPERATOR_PRIORITY_MAP.put('*', OperatorPriority.MEDIUM);
        OPERATOR_PRIORITY_MAP.put('+', OperatorPriority.MEDIUM);
    }

    private static final char LEFT_PARENTHESIS = '(';
    private static final char RIGHT_PARENTHESIS = ')';

    /**
     * Converts an infix expression to a postfix expression.
     *
     * @param infix The infix expression to be converted.
     * @return The resulting postfix expression.
     */
    public static String infixToPostfix(String infix) {
        if (!validate(infix)) {
            throw new IllegalArgumentException("Invalid infix expression");
        }

        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);

            // Skip if the character is whitespace
            if (c == ' ') {
                continue;
            }

            // Check if character is an operand
            if (isOperand(c)) {
                processOperand(infix, i, postfix);
                while (i + 1 < infix.length() && isOperand(infix.charAt(i + 1))) {
                    i++; // Skip the remaining characters of the operand
                }
            } else if (c == LEFT_PARENTHESIS) {
                stack.push(c);
            } else if (c == RIGHT_PARENTHESIS) {
                processRightParenthesis(stack, postfix);
            } else { // Operator found
                processOperator(c, stack, postfix);
            }
        }

        // Remove any remaining operators from the stack and append them to the postfix expression
        while (!stack.isEmpty()) {
            postfix.append(stack.pop()).append(' ');
        }

        return postfix.toString().trim();
    }

    /**
     * Validates an infix expression.
     *
     * @param expression The infix expression to be validated.
     * @return True if the expression is valid, false otherwise.
     */
    private static boolean validate(String expression) {
        // Todo: Implement method
        return true;
    }

    /**
     * Processes an operand in the infix expression.
     *
     * @param infix   The infix expression.
     * @param index   The index of the current character.
     * @param postfix The StringBuilder to store the resulting postfix expression.
     */
    private static void processOperand(String infix, int index, StringBuilder postfix) {
        StringBuilder operand = new StringBuilder();
        operand.append(infix.charAt(index));
        while (index + 1 < infix.length() && isOperand(infix.charAt(index + 1))) {
            operand.append(infix.charAt(++index));
        }
        postfix.append(operand.toString().trim()).append(' ');
    }

    /**
     * Processes the right parenthesis in the infix expression.
     *
     * @param stack   The stack containing operators.
     * @param postfix The StringBuilder to store the resulting postfix expression.
     */
    private static void processRightParenthesis(Stack<Character> stack, StringBuilder postfix) {
        while (!stack.isEmpty() && stack.peek() != LEFT_PARENTHESIS) {
            postfix.append(stack.pop()).append(' ');
        }
        stack.pop(); // remove the open '('
    }

    /**
     * Processes an operator in the infix expression.
     *
     * @param operator The operator to be processed.
     * @param stack    The stack containing operators.
     * @param postfix  The StringBuilder to store the resulting postfix expression.
     */
    private static void processOperator(char operator, Stack<Character> stack, StringBuilder postfix) {
        while (!stack.isEmpty() && getOperatorPriority(stack.peek()) >= getOperatorPriority(operator) && stack.peek() != LEFT_PARENTHESIS) {
            postfix.append(stack.pop()).append(' ');
        }
        stack.push(operator);
    }

    /**
     * Retrieves the priority of an operator.
     *
     * @param operator The operator.
     * @return The priority of the operator.
     */
    private static int getOperatorPriority(char operator) {
        return OPERATOR_PRIORITY_MAP.getOrDefault(operator, OperatorPriority.HIGH).ordinal();
    }

    /**
     * Checks if a character is an operand.
     *
     * @param character The character to be checked.
     * @return True if the character is an operand, false otherwise.
     */
    private static boolean isOperand(char character) {
        // Check if character is not an operator or parenthesis
        return !isOperator(character) && character != LEFT_PARENTHESIS && character != RIGHT_PARENTHESIS;
    }

    /**
     * Checks if a character is an operator.
     *
     * @param character The character to be checked.
     * @return True if the character is an operator, false otherwise.
     */
    private static boolean isOperator(char character) {
        return OPERATOR_PRIORITY_MAP.containsKey(character);
    }
}
