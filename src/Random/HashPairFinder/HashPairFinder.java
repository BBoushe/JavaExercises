package Random.HashPairFinder;

import java.util.*;

public class HashPairFinder {
    public static int factorial(int n) {
        if (n <= 1)
            return 1;
        else
            return n * factorial(n - 1);
    }

    public static int combinationsFormula(int n) {
        return (factorial(n) / (factorial(n - 2) * 2)); // choose 2 items from a set of N items
    }

    public static int pairFinder(String words[]) {
        int N = words.length;
        int size = (int) (N / 0.625);
        int total = 0;

        HashMap<Integer, ArrayList<String>> table = new HashMap<>(size);

        for (int i = 0; i < N; i++) {
            int key = words[i].length();
            if (table.containsKey(key)) {
                table.get(key).add(words[i]);
            } else {
                table.put(key, new ArrayList<>());
                table.get(key).add(words[i]);
            }
        }

        for (ArrayList<String> lists : table.values()){
            total += combinationsFormula(lists.size());
        }

        return total;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int N = in.nextInt();
        in.nextLine();
        String[] words = new String[N];

        for (int i = 0; i < N; i++) {
            words[i] = in.nextLine();
        }

        System.out.println(pairFinder(words));
    }
}
