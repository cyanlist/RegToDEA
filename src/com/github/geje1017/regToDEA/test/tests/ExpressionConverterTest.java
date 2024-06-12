package com.github.geje1017.regToDEA.test.tests;

import com.github.geje1017.regToDEA.main.postfix.ExpressionConverter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExpressionConverterTest {

    @Test
    void test_concatenation() {
        String infixExpression = "a,b";
        String expectedPostfix = "a b ,";
        String actualPostfix = ExpressionConverter.infixToPostfix(infixExpression);
        assertEquals(expectedPostfix, actualPostfix);
    }

    @Test
    void test_alternation() {
        String infixExpression = "a|b";
        String expectedPostfix = "a b |";
        String actualPostfix = ExpressionConverter.infixToPostfix(infixExpression);
        assertEquals(expectedPostfix, actualPostfix);
    }

    @Test
    void test_kleeneClosure() {
        String infixExpression = "a*";
        String expectedPostfix = "a *";
        String actualPostfix = ExpressionConverter.infixToPostfix(infixExpression);
        assertEquals(expectedPostfix, actualPostfix);
    }

    @Test
    void test_positiveClosure() {
        String infixExpression = "a+";
        String expectedPostfix = "a +";
        String actualPostfix = ExpressionConverter.infixToPostfix(infixExpression);
        assertEquals(expectedPostfix, actualPostfix);
    }

    @Test
    void test_empty_expression() {
        String infixExpression = "";
        String expectedPostfix = "";
        String actualPostfix = ExpressionConverter.infixToPostfix(infixExpression);
        assertEquals(expectedPostfix, actualPostfix);
    }

    @Test
    void test_parenthesis() {
        String infixExpression = "(a)";
        String expectedPostfix = "a";
        String actualPostfix = ExpressionConverter.infixToPostfix(infixExpression);
        assertEquals(expectedPostfix, actualPostfix);
    }

    @Test
    void test_extra_space() {
        String infixExpression = "a        |        b";
        String expectedPostfix = "a b |";
        String actualPostfix = ExpressionConverter.infixToPostfix(infixExpression);
        assertEquals(expectedPostfix, actualPostfix);
    }

    @Test
    void test_invalid_input() {
        String infixExpression = "a ,";
        String expectedPostfix = "";
        String actualPostfix = ExpressionConverter.infixToPostfix(infixExpression);
        assertEquals(expectedPostfix, actualPostfix);
    }

    @Test
    void test_basicTest() {
        String infixExpression = "2|(a,b)*";
        String expectedPostfix = "2 a b , * |";
        String actualPostfix = ExpressionConverter.infixToPostfix(infixExpression);
        assertEquals(expectedPostfix, actualPostfix);
    }
}

