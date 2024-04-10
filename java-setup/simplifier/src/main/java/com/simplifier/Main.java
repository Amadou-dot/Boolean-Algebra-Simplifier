package com.simplifier;
// A program that takes less complex boolean expressions and simplifies them.

public class Main {
    public static void main(String[] args) {
        String result = SimplifyExpression("BA+(AA+CA)");
        System.out.println("Result: " + result);
    }

    /**
     * Simplifies a boolean algebra expression using the absorption laws.
     * 
     * @param expression the boolean algebra expression to be simplified
     * @return the simplified boolean algebra expression
     */
    public static String Absorb(String expression) {
        // Absorption Law 1: A+AB is equivalent to A
        expression = expression.replaceAll("([A-Z])\\+\\1[A-Z]", "$1");
        // Absorption Law 2: AB+A is equivalent to A
        expression = expression.replaceAll("([A-Z])([A-Z])\\+\\1", "$1");
        // Absorption Law 3: BA+A is equivalent to A
        expression = expression.replaceAll("([A-Z])([A-Z])\\+\\2", "$2");
        // Absorption Law 4: A+BA is equivalent to A
        expression = expression.replaceAll("([A-Z])\\+[A-Z]\\1", "$1");
        System.out.println("Apply Absorption law (A+AB = A): " + expression);
        return expression;
    }

    /**
     * Applies the Idempotent rule to simplify a boolean algebra expression.
     * 
     * @param expression the boolean algebra expression to be simplified
     * @return the simplified expression after applying the Idempotent rule
     */
    public static String Idempotent(String expression) {
        expression = expression.replaceAll("([A-Z])\\1+", "$1");
        System.out.println("Apply Idempotent law (AA = A): " + expression);
        return expression;
    }

    /**
     * Distributes the given expression by applying the distributive property of
     * boolean algebra.
     * 
     * @param expression the expression to be distributed
     * @return the distributed expression
     */
    public static String Distribute(String expression) {
        String oldExpression;
        do {
            oldExpression = expression;
            expression = expression.replaceAll("([A-Z])\\(([A-Z]+)\\+([A-Z]+)\\)", "$1$2+$1$3");
        } while (!expression.equals(oldExpression));
        expression = expression.replaceAll("([A-Z])([A-Z])([A-Z])", "$1$2+$1$3");
        // remove parentheses
        expression = expression.replaceAll("\\(", "").replaceAll("\\)", "");
        System.out.println("Apply Distribution: " + expression);
        return expression;
    }

    public static String SimplifyExpression(String expression) {
        String oldExpression = expression;
        while (!expression.equals(oldExpression)) {
            expression = Idempotent(expression);
            expression = Distribute(expression);
            expression = Absorb(expression);
            oldExpression = expression;
        }

        return expression;
    }

    public static void printHelp() {
        System.out.println("This program simplifies boolean expressions.");
        System.out.println("Use '+' for an OR gate. e.g. A+B means A OR B");
        System.out.println("Characters next to each other represent an AND gate. e.g. AB means A AND B");
        System.out.println("Use brackets to group expressions. e.g. A(B+C) means A AND (B OR C)");
    }
}