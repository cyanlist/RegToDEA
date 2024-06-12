package com.github.geje1017.regToDEA.main.gui;

import com.github.geje1017.regToDEA.main.postfix.ExpressionEvaluator;

import java.util.Scanner;

public class StringInputReader {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Reads a string input from the console.
     *
     * @return The string input from the console.
     */
    protected static String readStringInput() {
        System.out.println("\n------------------------------------------------");
        System.out.println("Welcome to regToDEA-Converter! It converts a regular String to a Deterministic State Machine!");

        System.out.println("Valid operations are:");
        System.out.println("\tConcatenation: a,b");
        System.out.println("\tAlternation: a|b");
        System.out.println("\tKleene Closure: a*");
        System.out.println("\tBrackets: ( ... )");
        System.out.println("\tε: e");
        System.out.println("\t∅: 0/");

        System.out.println("Examples are:");
        System.out.println("\tHello,World");
        System.out.println("\tHello,(World|Kitty)");
        // System.out.println("\t(DSTM,are,cool)*"); // Todo: Bug with ,* combination

        System.out.print("Enter here a valid regular expression to evaluate (type 'exit' to quit): ");
        return scanner.nextLine();
    }

    public static void read() {
        while (true) {
            String input = readStringInput();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            else {
                ExpressionEvaluator.evaluateExpression(input);
            }
        }
        scanner.close();
    }

}

