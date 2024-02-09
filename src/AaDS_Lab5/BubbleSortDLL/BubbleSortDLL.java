package AaDS_Lab5.BubbleSortDLL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Scanner;

public class BubbleSortDLL {

    public static void bubbleSort(LinkedList<Integer> list) {
        int n = list.size();
        int temp1, temp2;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n-1-i; j++){
                if((list.get(j)) > (list.get(j+1))){
                    int temp = list.get(j+1);
                    list.add(j+1, list.get(j));
                    list.remove(j);
                    list.add(j, temp);
                    list.remove(j+2);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        LinkedList<Integer> list = new LinkedList<>();

        for(int i =0; i < n; i++){
            list.add(input.nextInt());
        }

        bubbleSort(list);

        for(int num : list){
            System.out.print(num + " ");
        }

    }

}