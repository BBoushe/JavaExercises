package AaDS_Lab5.ShakerSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ShakerSort { // the first is an implementation with arrays regular in one for
    //    public static String toString(int[] a) {
    //        if (a == null)
    //            return "null";
    //        int iMax = a.length - 1;
    //        if (iMax == -1)
    //            return "[]";
    //
    //        StringBuilder b = new StringBuilder();
    //        for (int i = 0; ; i++) {
    //            b.append(a[i]);
    //            if (i == iMax)
    //                return b.toString();
    //            b.append(" ");
    //        }
    //    }
    //
    //    static void swap(int a[], int x, int y) {
    //        int temp = a[x];
    //        a[x] = a[y];
    //        a[y] = temp;
    //    }
    //
    //    static void shakerSort(int a[], int n) {
    //
    //        for (int i = 0; i < n; i++) {
    //            if(i > n-1-i) break;
    //
    //            int localMinPos = i;
    //            int localMaxPos = n - 1 - i;
    //            int localMin = a[i];
    //            int localMax = a[n - 1 - i];
    //            for (int j = i; j < n - i; j++) {
    //                if (a[j] >= localMax) {
    //                    localMax = a[j];
    //                    localMaxPos = j;
    //                }
    //                if (a[j] <= localMin) {
    //                    localMin = a[j];
    //                    localMinPos = j;
    //                }
    //            }
    //            swap(a, i, localMinPos);
    //            System.out.println(toString(a));
    //            swap(a, n - 1 - i, localMaxPos);
    //            System.out.println(toString(a));
    //        }
    //    }

    // this is an implementation with linked lists
    //    public static String toString(List<Integer> list) {
    //        StringBuilder b = new StringBuilder();
    //
    //        for (int num : list) {
    //            b.append(num + " ");
    //        }
    //
    //        return b.toString();
    //    }
    //
    //    public static void shakerSort(int a[], int n) {
    //        LinkedList<Integer> lista = new LinkedList<>();
    //
    //        for (int num : a) {
    //            lista.add(num);
    //        }
    //
    //        for (int i = 0; i < n; i++) {
    //            if(i > n-1-i) break;
    //
    //            int localMinPos = i;
    //            int localMaxPos = n - 1 - i;
    //            int localMin = lista.get(i);
    //            int localMax = lista.get(n - 1 - i);
    //
    //
    //            for (int j = i; j < n - i; j++) {
    //                if (lista.get(j) <= localMin) {
    //                    localMin = lista.get(j);
    //                    localMinPos = j;
    //                }
    //            }
    //            lista.remove(localMinPos);
    //            lista.add(i, localMin);
    //            System.out.println(toString(lista));
    //
    //            for (int j = i; j < n - i; j++) {
    //                if (lista.get(j) >= localMax) {
    //                    localMax = lista.get(j);
    //                    localMaxPos = j;
    //                }
    //            }
    //            lista.remove(localMaxPos);
    //            lista.add(n - 1 - i, localMax);
    //            System.out.println(toString(lista));
    //        }
    //    }

    public static String toString(int a[]) {
        StringBuilder b = new StringBuilder();

        for (int num : a) {
            b.append(num + " ");
        }

        return b.toString();
    }

    static void swap(int a[], int x, int y) {
        int temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }

    public static void shakerSort(int a[], int n) {
        if (n == 1) {
            System.out.println(toString(a));
            System.out.println(toString(a));
            return;
        }

        boolean swapped;

        for (int i = 0; true; ) {
            swapped = false;
            for (int j = n - 1 - i; j > 0; j--) {
                if (a[j] < a[j - 1]) {
                    swap(a, j, j - 1);
                    swapped = true;
                }
            }
            System.out.println(toString(a));

            i++;

            for (int j = i; j < n - 1; j++) {
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                    swapped = true;
                }
            }
            System.out.println(toString(a));

            if (!swapped) {
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int i;
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s = stdin.readLine();
        int n = Integer.parseInt(s);

        s = stdin.readLine();
        String[] pom = s.split(" ");
        int[] a = new int[n];
        for (i = 0; i < n; i++)
            a[i] = Integer.parseInt(pom[i]);
        shakerSort(a, n);
    }
}
