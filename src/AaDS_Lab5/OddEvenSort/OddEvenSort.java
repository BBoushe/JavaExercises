package AaDS_Lab5.OddEvenSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class OddEvenSort {

    static void oddEvenSort(int a[], int n)
    {
        List<Integer> oddNumbers = new ArrayList<>();
        List<Integer> evenNumber = new ArrayList<>();

        for(int num : a){
            if(num%2==1) oddNumbers.add(num);
            else evenNumber.add(num);
        }

        Collections.sort(oddNumbers);
        Collections.sort(evenNumber, Collections.reverseOrder());

        oddNumbers.addAll(evenNumber);

        int i =0;
        for(int num : oddNumbers)
            a[i++] = num;
    }

    public static void main(String[] args) throws IOException{
        int i;
        BufferedReader stdin = new BufferedReader( new InputStreamReader(System.in));
        String s = stdin.readLine();
        int n = Integer.parseInt(s);

        s = stdin.readLine();
        String [] pom = s.split(" ");
        int [] a = new int[n];
        for(i=0;i<n;i++)
            a[i]=Integer.parseInt(pom[i]);
        oddEvenSort(a,n);
        for(i=0;i<n-1;i++)
            System.out.print(a[i]+" ");
        System.out.print(a[i]);
    }
}