package AaDS_Lab4.ArithmeticExpression;

import jdk.jshell.EvalException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class ArithmeticExpression {

    // funkcija za presmetuvanje na izrazot pocnuvajki
    // od indeks l, zavrsuvajki vo indeks r

    static int presmetaj(char chars[], int l, int r) {
        Stack<Integer> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();


        for (int i = l; i <= r; i++) {
            if (Character.isDigit(chars[i])) { // if it's a number push it to the operands stack
                operands.push(chars[i] - '0');
            } else if (chars[i] == '(') {
                operators.push(chars[i]);
            } else if (chars[i] == ')') { // so basically when we've encountered ) then we do the operations until we hit a ( meaning we've tackled one (A + B) block
                while (!operators.isEmpty() && operators.peek() != '(') {
                    applyOperator(operators, operands);
                }
                operators.pop(); // pop the '(' to get rid of the (A +B) block
            } else {
                // it's an operator
                while (!operators.isEmpty() && precedence(chars[i]) <= precedence(operators.peek())) {
                    applyOperator(operators, operands);
                }
                operators.push(chars[i]);
            }
        }

        // this is to clean up the remaining operators so the outer most ()
        while (!operators.isEmpty()) {
            applyOperator(operators, operands);
        }

        return operands.pop();
    }

    static void applyOperator(Stack<Character> operators, Stack<Integer> operands) {
        int right = operands.pop();
        int left = operands.pop();
        char op = operators.pop();

        switch (op) {
            case '+':
                operands.push(left + right);
                break;
            case '-':
                operands.push(left - right);
                break;
        }
    }

    static int precedence(char operator) {
        if (operator == '+' || operator == '-')
            return 1;
        else return -1;
    }

    static int presmetajRekurzivno(char c[], int l, int r) {
        if(l == r) {
            return toInt(c[l]);
        }

        int p = 0, idx = -1;
        for(int i = l; i < r; i++) {
            if(c[i] == '(') p++;
            else if(c[i] == ')') p--;
            if((c[i] == '+' || c[i] == '-') && p == 0) idx = i;
        }

        if(idx == -1) return presmetaj(c, l + 1, r - 1);

        if(c[idx] == '+')
            return presmetaj(c, l, idx - 1) + presmetaj(c, idx + 1, r);
        else if(c[idx] == '-')
            return presmetaj(c, l, idx - 1) - presmetaj(c, idx + 1, r);

        return 0;
    }

    static int toInt(char c) {
        return Integer.parseInt(String.valueOf(c));
    }


    public static void main(String[] args) throws Exception {
        int i, j, k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = null;
        expression = br.readLine();

        char exp[] = expression.toCharArray();

        int rez = presmetaj(exp, 0, exp.length - 1);
        System.out.println(rez);

        br.close();
    }

}
