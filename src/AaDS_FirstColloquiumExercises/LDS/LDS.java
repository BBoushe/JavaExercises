package AaDS_FirstColloquiumExercises.LDS;

// Find the longest decreasing sequence in an array. The numbers in the sequence don't need to be on consecutive indices in the array.
import java.util.Scanner;

public class LDS {
    private static int najdolgaOpagackaSekvenca(int[] a) {
        int n = a.length;
        int[] lds = new int[n];
        int max = 1;

        for(int i = 0; i < n; i++){
            lds[i] = 1;
        }

        for(int i = 1; i < n; i++){
            for(int j = 0; j < i; j++){
                if(a[i] < a[j] && lds[i] < lds[j] + 1)
                    lds[i] = lds[j] + 1;
            }
        }

        for(int i = 0; i < n; i++){
            if(lds[i] > max)
                max = lds[i];
        }

        return max;
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int n = stdin.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = stdin.nextInt();
        }
        System.out.println(najdolgaOpagackaSekvenca(a));
    }
}
