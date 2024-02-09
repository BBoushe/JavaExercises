package AaDS_Lab1.ReverseWord;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ReverseWord {


    // solution using streams
//    public static void printReversed(String word) {
//        String reverseWord = IntStream.range(0, word.length())
//                .mapToObj(i -> word.charAt(word.length() - 1 - i))
//                .map(ch -> Character.toString(ch))
//                .collect(Collectors.joining());
//
//        System.out.println(reverseWord);
//    }

    public static void printReversed(String word){
        char[] chars = word.toCharArray();
        int n = word.length();
        StringBuilder newWord = new StringBuilder();

        for(int i = n-1; i >= 0; i--){
            newWord.append(chars[i]);
        }
        System.out.println(newWord);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();

        for(int i = 0; i < n; i++){
            printReversed(scanner.nextLine());
        }
        scanner.close();
    }
}