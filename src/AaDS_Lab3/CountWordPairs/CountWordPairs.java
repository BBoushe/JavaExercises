package AaDS_Lab3.CountWordPairs;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CountWordPairs {

    //TODO: implement function
    public static int countWordPairs(String[] words) { // n^2 solution
        int n = words.length;
        int result = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && (words[i].charAt(0) == words[j].charAt(0)))
                    result++;
            }
        }
        return result / 2;

    }

    public static int countWordPairsFaster(String[] words) { // this is n implementation without changing the String[] words
        int n = words.length;
        int[] reachedLetter = new int[122];
        int result = 0;

        for (int i = 0; i < n; i++) {
            reachedLetter[(int) words[i].charAt(0)]++;
        }

        for (int count : reachedLetter) {
            result += (count * (count - 1)) / 2;
        }
        return result;
    }

    public static int countWordPairsHashMap(String[] words) { // hashmap solution
        Map<Character, Integer> frequencyMap = new HashMap<>();

        for (String word : words) {
            if (!word.isEmpty()) {
                char firstChar = Character.toLowerCase(word.charAt(0));
                frequencyMap.put(firstChar, frequencyMap.getOrDefault(firstChar, 0) + 1);
            }
        }

        int pairCount = 0;
        for (int frequency : frequencyMap.values()) {
            if (frequency > 1) {
                pairCount += frequency * (frequency - 1) / 2;
            }
        }
        frequencyMap.clear();
        return pairCount;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();

        String words[] = new String[N];

        for (int i = 0; i < N; i++) {
            words[i] = input.next();
        }

        System.out.println(countWordPairs(words));
        System.out.println(countWordPairsFaster(words));
        System.out.println(countWordPairsHashMap(words));

    }
}
