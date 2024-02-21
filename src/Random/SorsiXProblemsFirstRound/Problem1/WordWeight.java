package Random.SorsiXProblemsFirstRound.Problem1;

import java.io.InputStreamReader;
import java.util.*;
import java.util.Map.Entry;

public class WordWeight {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        final String input = scanner.nextLine();
        printLargestWordSum(input);
    }

    static void printLargestWordSum(String line) {
        line = line.toLowerCase();
        String[] zborovi = line.split(" ");
        String najtezhok = "";
        int maxWeight = 0;

        for (String zbor : zborovi) {
            Map<Character, Integer> bukvi = new HashMap<>();

            for (char ch : zbor.toCharArray()) {
                bukvi.put(ch, bukvi.getOrDefault(ch, 0) + 1); // stavi 0 ili x+1 vo mapata vo zavisnost dali vishe postoi
            }

            int weight = bukvi.values().stream()
                    .mapToInt(count -> count * count).sum();
            // soberi gi site vrednosti na bukvi vo format a*a

            if (weight > maxWeight) {
                maxWeight = weight;
                najtezhok = zbor;
            }
        }

        System.out.println(najtezhok + "=" + maxWeight);
    }
}