package AaDS_ExamExercises.SecondColloq.Dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.stream.IntStream;

class MapEntry<K extends Comparable<K>, E> implements Comparable<K> {

    // Each MapEntry object is a pair consisting of a key (a Comparable
    // object) and a value (an arbitrary object).
    K key;
    E value;

    public MapEntry(K key, E val) {
        this.key = key;
        this.value = val;
    }

    public int compareTo(K that) {
        // Compare this map entry to that map entry.
        @SuppressWarnings("unchecked")
        MapEntry<K, E> other = (MapEntry<K, E>) that;
        return this.key.compareTo(other.key);
    }

    public String toString() {
        return "<" + key + "," + value + ">";
    }
}

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class CBHT<K extends Comparable<K>, E> {

    // An object of class CBHT is a closed-bucket hash table, containing
    // entries of class MapEntry.
    private SLLNode<MapEntry<K, E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
        // Construct an empty CBHT with m buckets.
        buckets = (SLLNode<MapEntry<K, E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        String hashable = key.toString();
        return Math.abs(hashable.hashCode()) % 1000;
    }

    public SLLNode<MapEntry<K, E>> search(K targetKey) {
        // Find which if any node of this CBHT contains an entry whose key is
        // equal
        // to targetKey. Return a link to that node (or null if there is none).
        int b = hash(targetKey);
        for (SLLNode<MapEntry<K, E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (targetKey.equals(((MapEntry<K, E>) curr.element).key))
                return curr;
        }
        return null;
    }

    public void insert(K key, E val) {        // Insert the entry <key, val> into this CBHT.
        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
        int b = hash(key);
        for (SLLNode<MapEntry<K, E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
                // Make newEntry replace the existing entry ...
                curr.element = newEntry;
                return;
            }
        }
        // Insert newEntry at the front of the 1WLL in bucket b ...
        buckets[b] = new SLLNode<MapEntry<K, E>>(newEntry, buckets[b]);
    }

    public void delete(K key) {
        // Delete the entry (if any) whose key is equal to key from this CBHT.
        int b = hash(key);
        for (SLLNode<MapEntry<K, E>> pred = null, curr = buckets[b]; curr != null; pred = curr, curr = curr.succ) {
            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
                if (pred == null)
                    buckets[b] = curr.succ;
                else
                    pred.succ = curr.succ;
                return;
            }
        }
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";
            for (SLLNode<MapEntry<K, E>> curr = buckets[i]; curr != null; curr = curr.succ) {
                temp += curr.element.toString() + " ";
            }
            temp += "\n";
        }
        return temp;
    }

}


public class KumanovskiDijalekt {
    public static int makePrime(int number) {
        if (number > 1 && IntStream.range(2, (int) Math.sqrt(number)).noneMatch(n -> (number % n == 0)))
            return number;

        return makePrime(number + 1);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(
                System.in));
        int N = Integer.parseInt(br.readLine());

        String rechnik[] = new String[N];
        for (int i = 0; i < N; i++) {
            rechnik[i] = br.readLine();
        }

        String tekst = br.readLine();

        int loadFactor = makePrime((int) (N / 0.67));
        CBHT<String, String> table = new CBHT<>(1000);

        for (String definicija : rechnik) {
            String[] par = definicija.split(" ");
            table.insert(par[0], par[1]);
        }

        String[] toTranslate = tekst.split(" ");
        int sentenceLen = toTranslate.length;
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < sentenceLen; i++) {
            String original = toTranslate[i];
            String changed = toTranslate[i].replaceAll("[,.?!]", "").toLowerCase();

            SLLNode<MapEntry<String, String>> word = table.search(changed);

            if (word != null) {
                changed = word.element.value;
                int len = changed.length();
                // check if the character was either or with a capital letter or had any symbol because of stripping earlier.
                if (Character.isUpperCase(original.charAt(0))) {
                    changed = changed.substring(0, 1).toUpperCase() + changed.substring(1);
                }
                if (original.matches(".*[,.!?].*")) {
                    changed = changed.substring(0, len) + String.valueOf(original.charAt(original.length() - 1));
                }

                result.append(changed).append(" ");
            } else {
                result.append(original).append(" ");
            }
        }

        System.out.println(result.toString());
    }
}

