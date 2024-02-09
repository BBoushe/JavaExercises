package AaDS_Lab7.RoutingHashJava;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class MapEntry<K extends Comparable<K>, E> implements Comparable<K> {

    K key;
    E value;

    public MapEntry(K key, E val) {
        this.key = key;
        this.value = val;
    }

    public int compareTo(K that) {
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
    private SLLNode<MapEntry<K, E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
        buckets = (SLLNode<MapEntry<K, E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public SLLNode<MapEntry<K, E>> search(K targetKey) {
        int b = hash(targetKey);
        for (SLLNode<MapEntry<K, E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (targetKey.equals(((MapEntry<K, E>) curr.element).key))
                return curr;
        }
        return null;
    }

    public void insert(K key, E val) {
        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
        int b = hash(key);
        for (SLLNode<MapEntry<K, E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
                curr.element = newEntry;
                return;
            }
        }
        buckets[b] = new SLLNode<MapEntry<K, E>>(newEntry, buckets[b]);
    }


    public void delete(K key) {
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


public class RoutingHashJava {
    public static boolean isValidRouteRange(String originalIP, String comparedIP) {
        int allowedValue = Integer.parseInt(originalIP.substring(originalIP.lastIndexOf('.') +1));
        int comparedValue = Integer.parseInt(comparedIP.substring(comparedIP.lastIndexOf('.') + 1));

        int calc = allowedValue - comparedValue;

        if ((calc > 0 || calc <= 254))
            return true;
        else return false;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        CBHT<String, String[]> hash = new CBHT<>(128);

        for (int i = 0; i < N; i++) { // building the table
            String input = br.readLine();
            String[] ips = br.readLine().split(",");
            hash.insert(input, ips);
        }

        int M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) { //internal check
            String staticIp = br.readLine();
            String ip = br.readLine();

            SLLNode<MapEntry<String, String[]>> node = hash.search(staticIp);

            if (node == null) {
                System.out.println("ne postoi");
                continue;
            }

            String[] oldIps = node.element.value;
            boolean passed = false;

            for(int j = 0; j < oldIps.length; j++){
                String temp = oldIps[j].substring(0, oldIps[j].lastIndexOf('.'));

                if(temp.equals(ip.substring(0, ip.lastIndexOf('.')))){
                    if(isValidRouteRange(oldIps[j], ip))
                        passed= true;
                    else passed = false;
                }
            }

            System.out.println(passed ? "postoi" : "ne postoi");
        }

    }
}