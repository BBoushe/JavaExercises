package AaDS_Lab6.PostFixEvaluation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

interface Stack<E> {

    // Elementi na stekot se objekti od proizvolen tip.

    // Metodi za pristap:

    public boolean isEmpty();
    // Vrakja true ako i samo ako stekot e prazen.

    public E peek();
    // Go vrakja elementot na vrvot od stekot.

    // Metodi za transformacija:

    public void clear();
    // Go prazni stekot.

    public void push(E x);
    // Go dodava x na vrvot na stekot.

    public E pop();
    // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
}

class ArrayStack<E> implements Stack<E> {
    private E[] elems;
    private int depth;

    @SuppressWarnings("unchecked")
    public ArrayStack(int maxDepth) {
        // Konstrukcija na nov, prazen stek.
        elems = (E[]) new Object[maxDepth];
        depth = 0;
    }


    public boolean isEmpty() {
        // Vrakja true ako i samo ako stekot e prazen.
        return (depth == 0);
    }


    public E peek() {
        // Go vrakja elementot na vrvot od stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        return elems[depth - 1];
    }


    public void clear() {
        // Go prazni stekot.
        for (int i = 0; i < depth; i++) elems[i] = null;
        depth = 0;
    }


    public void push(E x) {
        // Go dodava x na vrvot na stekot.
        elems[depth++] = x;
    }


    public E pop() {
        // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        E topmost = elems[--depth];
        elems[depth] = null;
        return topmost;
    }
}

public class PostFixEvaluation {
    static int applyOperation(ArrayStack<Integer> stack, char ch) throws Exception {
        int right = stack.pop();
        int left = stack.pop();

        switch (ch) {
            case '/':
                return left / right;
            case '*':
                return left * right;
            case '+':
                return left + right;
            case '-':
                return left - right;
            default:
                throw new Exception("Something went wrong!");
        }
    }

    static int evaluatePostfix(char[] izraz, int n) throws Exception {
        ArrayStack<Integer> operands = new ArrayStack<>(izraz.length);
        ArrayStack<Character> operators = new ArrayStack<>(izraz.length);

        for (int i = 0; i < n; i++) {
            if (izraz[i] == '/' || izraz[i] == '*' || izraz[i] == '+' || izraz[i] == '-') { // if it's a operator sign get the top most on the stack and do the operation;
                int temp = applyOperation(operands, izraz[i]);
                operands.push(temp);
                i++; // we need to skip the ' ' after the operator
            } else { // build the number and add it to the operands stack
                StringBuilder operand = new StringBuilder();
                while (izraz[i] != ' ') {
                    operand.append(izraz[i]);
                    i++;
                }
                operands.push(Integer.parseInt(operand.toString()));
            }
        }
        return operands.pop();

    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        char exp[] = expression.toCharArray();

        int rez = evaluatePostfix(exp, exp.length);
        System.out.println(rez);

        br.close();

    }

}