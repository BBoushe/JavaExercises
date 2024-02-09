// Find the longest decreasing sequence in an array. The numbers in the sequence don't need to be on consecutive indices in the array.

package AaDS_ExamExercises.FirstColloq.LargestDecreasingSequence;

import java.util.Arrays;
import java.util.Scanner;

public class LDS {
    private static int najdolgaOpagackaSekvenca(int[] a) {
        int maxLength = 1;
        int n = a.length;

        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for(int i = 1; i < n; i++){
            for (int j = 0; j < i; j++){
                if(a[j] > a[i] && dp[j] + 1 > dp[i]){
                    dp[i] = dp[j] + 1;
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }

        return maxLength;
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
