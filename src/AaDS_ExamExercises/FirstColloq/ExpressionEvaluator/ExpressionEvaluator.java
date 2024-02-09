package AaDS_ExamExercises.FirstColloq.ExpressionEvaluator;


import java.util.NoSuchElementException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


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
    private E[] elems; //elems[0...depth-1] se negovite elementi.
    private int depth; //depth e dlabochinata na stekot

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

    public int size() {
        // Ja vrakja dolzinata na stack-ot.
        return depth;
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

public class ExpressionEvaluator {

    static int doOperation(int a, int b, char c) {
        switch (c) {
            case '+':
                return a + b;
            case '*':
                return a * b;
            default:
                return 0;
        }
    }

    static int precedence(char c) {
        if (c == '+')
            return 1;
        else if (c == '*')
            return 2;

        return 0;
    }

    public static int evaluateExpression(String expression) {
        int n = expression.length();
        ArrayStack<Integer> operands = new ArrayStack<>(n);
        ArrayStack<Character> operators  = new ArrayStack<>(n);

        for(int i = 0; i<n; i++){
            char ch = expression.charAt(i);

            if(Character.isDigit(ch)){
                StringBuilder num = new StringBuilder(String.valueOf(ch));
                while(i+1 < n && Character.isDigit(expression.charAt(i+1))){
                    num.append(expression.charAt(++i));
                }
                operands.push(Integer.valueOf(num.toString()));
            } else if(ch == '+' || ch == '*'){
                while(!operators.isEmpty() && precedence(ch) <= precedence(operators.peek())){
                    char c = operators.pop();
                    int a = operands.pop();
                    int b = operands.pop();
                    operands.push(doOperation(a,b,c));
                }
                operators.push(ch);
            }
        }

        while(!operators.isEmpty()){
            char c = operators.pop();
            int a = operands.pop();
            int b = operands.pop();
            operands.push(doOperation(a,b,c));
        }

        return operands.pop();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(evaluateExpression(input.readLine()));
    }
}
