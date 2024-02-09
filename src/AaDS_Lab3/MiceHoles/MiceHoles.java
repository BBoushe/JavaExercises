package AaDS_Lab3.MiceHoles;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class MiceHoles {

    //TODO: implement function
    public static int minTime(int mice[], int holes[]){
        int max = 0;
        int n = holes.length;

        Arrays.sort(mice);
        Arrays.sort(holes);

        //after sorting we find the maximum absolute value between the mice and the holes
        // this time is the maximum time that it could take for the mouse to reach its hole (all mice move at the same time)

        for(int i = 0; i < n; i++){
            int distance = Math.abs(mice[i] - holes[i]);

            if(distance > max)
                max = distance;
        }

        return max;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String line = input.nextLine();
        String parts[] = line.split(" ");
        int mice[] = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            mice[i] = Integer.parseInt(parts[i]);
        }

        line = input.nextLine();
        parts = line.split(" ");
        int holes[] = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            holes[i] = Integer.parseInt(parts[i]);
        }

        System.out.println(minTime(mice, holes));
    }
}
