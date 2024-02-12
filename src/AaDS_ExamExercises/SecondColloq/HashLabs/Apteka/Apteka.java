package AaDS_ExamExercises.SecondColloq.HashLabs.Apteka;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.stream.IntStream;

class Lek {
    String ime;
    int pozLista;
    int cena;
    int kolicina;

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public int getPozLista() {
        return pozLista;
    }

    public Lek(String ime, int pozLista, int cena, int kolicina) {
        this.ime = ime.toUpperCase();
        this.pozLista = pozLista;
        this.cena = cena;
        this.kolicina = kolicina;
    }

    @Override
    public String toString() {
        if (pozLista == 1) return ime + "\n" + "POZ\n" + cena + "\n" + kolicina;
        else return ime + "\n" + "NEG\n" + cena + "\n" + kolicina;
    }
}

class LekKluch {
    String ime;

    public LekKluch(String ime) {
        this.ime = ime.toUpperCase();
    }

    @Override
    public int hashCode() {
        String name = ime.toUpperCase();
        if (name.length() < 3) return 0;
        return (29 * (29 * (name.charAt(0)) + name.charAt(1)) + name.charAt(3)) % 102780;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LekKluch lekKluch = (LekKluch) o;
        return Objects.equals(ime, lekKluch.ime);
    }
}

public class Apteka {

    public static int makePrime(int number) {
        if (number > 1 && IntStream.rangeClosed(2, (int) Math.sqrt(number)).noneMatch(n -> (number % n == 0))) {
            return number;
        }

        return makePrime(number + 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int loadFactor = makePrime((int) (N / 0.5));
        CBHT<LekKluch, Lek> hashTable = new CBHT<>(102780);

        for (int i = 0; i < N; i++) {
            String input = in.readLine();
            String[] parts = input.split(" ");
            LekKluch key = new LekKluch(parts[0]);
            Lek medicine = new Lek(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));

            hashTable.insert(key, medicine);
        }

        while (true) {
            String input = in.readLine().toUpperCase();
            if (input.equals("KRAJ")) break;
            int quantity = Integer.parseInt(in.readLine());

            boolean exists = false;
            for (SLLNode<MapEntry<LekKluch, Lek>> curr = hashTable.getBucket(new LekKluch(input).hashCode()); curr != null; curr = curr.succ) {
                if (curr.element.key.ime.compareTo(input) == 0) {
                    Lek targetLek = curr.element.value;

                    System.out.println(targetLek.getIme());

                    int list = targetLek.getPozLista();
                    if (list == 1) {
                        System.out.println("POZ");
                    } else {
                        System.out.println("NEG");
                    }

                    System.out.println(targetLek.getCena());
                    System.out.println(targetLek.getKolicina());

                    if (quantity > targetLek.getKolicina())
                        System.out.println("Nema dovolno lekovi");
                    else {
                        System.out.println("Napravena naracka");
                        curr.element.value.setKolicina(targetLek.getKolicina() - quantity);
                    }

                    exists = true;
                }
            }
            if (!exists) System.out.println("Nema takov lek");
        }
    }
}

class CBHT<K, E> {
    private SLLNode<MapEntry<K, E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
        buckets = (SLLNode<MapEntry<K, E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        return key.hashCode();
    }

    // probajte da ja reshite ovaa zadacha bez koristenje na ovoj metod
    // try to solve this task without using this method

    // public SLLNode<MapEntry<K,E>> search(K targetKey) {
    //     int b = hash(targetKey);
    //     for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
    //         if (targetKey.equals(curr.element.key))     return curr;
    //     }
    //     return null;
    // }

    public void insert(K key, E val) {
        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
        int b = hash(key);
        for (SLLNode<MapEntry<K, E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (key.equals(curr.element.key)) {
                curr.element = newEntry;
                return;
            }
        }
        buckets[b] = new SLLNode<MapEntry<K, E>>(newEntry, buckets[b]);
    }

    public void delete(K key) {
        int b = hash(key);
        for (SLLNode<MapEntry<K, E>> pred = null, curr = buckets[b]; curr != null; pred = curr, curr = curr.succ) {
            if (key.equals(curr.element.key)) {
                if (pred == null) buckets[b] = curr.succ;
                else pred.succ = curr.succ;
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

    public SLLNode<MapEntry<K, E>> getBucket(int n) {
        return buckets[n];
    }

}

class MapEntry<K, E> {
    K key;
    E value;

    public MapEntry(K key, E val) {
        this.key = key;
        this.value = val;
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

