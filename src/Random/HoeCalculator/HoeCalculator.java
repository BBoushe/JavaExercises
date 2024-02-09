package Random.HoeCalculator;

import java.util.Random;
import java.util.Scanner;

public class HoeCalculator {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random rand = new Random();
        String name = in.nextLine();

        while(true){

            int bitches = (rand.nextInt()%10) & Integer.MAX_VALUE;

            System.out.println(name + " will have " + bitches + " bitches in his life");

            if(bitches == 0)
            {
                System.out.println("And this is the truth ;("); return;}

            else System.out.println("BUT THIS IS A LIE!!!");
        }
    }
}
