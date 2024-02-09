package AaDS_FirstColloquiumExercises.ExpressionEvaluator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class ExpressionEvaluator {

    static int applyOperator(char op, int a, int b) {
        switch (op) {
            case '+':
                return a + b;
            case '*':
                return a * b;
        }
        return 0;
    }

    static int precedence(char op) {
        if(op == '*')
            return 2;
        else return 1;
    }

    public static int evaluateExpression(String expression) {
        Stack<Character> operators = new Stack<>();
        Stack<Integer> operands = new Stack<>();
        int n = expression.length();
        int temp = 1;

        for (int i = 0; i < n; i++) {
            char token = expression.charAt(i);

            if (Character.isDigit(token)) {
                operands.push(token - '0');
            } else {
                if (!operators.isEmpty() && precedence(token) != 1) {
                    temp *= operands.pop();
                }
                operators.push(token);
            }
        }

        while (!operators.isEmpty()) {
           operands.push(applyOperator(operators.pop(), operands.pop(), operands.pop()));
        }

        return operands.pop();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(evaluateExpression(input.readLine()));
    }

}