package AaDS_Lab4.SumOfAbsoluteDifferences;

//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.Arrays;
//import java.util.StringTokenizer;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//public class SumOfAbsoluteDifferences {
//
//    private static int abs(int a, int b) {
//        return Math.abs(a - b);
//    }
//
//    private static int absSum(int row[]) {
//        int sum = 0;
//        int n = row.length;
//
//        for (int i = 0; i < n; i++) {
//            if (i == n - 1) {
//                sum += abs(row[i], row[0]);
//            } else sum += abs(row[i], row[i + 1]);
//        }
//
//        return sum;
//    }
//    static void solve(int numbers[], int N, int K) {
//        int[][] kValues = new int[N - K + 1][K];
//        int kPosition = 0;
//        int localMax;
//        int max = 0;
//        int kIthPostion = 0;
//
//        for (int i = 0; i < N - K + 1; i++) {
//            kPosition = i;
//            for (int j = 0; j < K; j++) {
//                kValues[i][j] = numbers[kPosition++];
//            }
//
//            localMax = absSum(kValues[i]);
//
//            if (localMax > max) {
//                max = localMax;
//                kIthPostion = i;
//            }
//
//        }
//        for (int i = 0; i < N - K + 1; i++) {
//            for (int j = 0; j < K; j++)
//                System.out.print(kValues[i][j] + " ");
//            System.out.println("The sum of this row is: " + absSum(kValues[i]));
//        }
//
//        System.out.print("The row with the max abs value is: ");
//        for (int i = 0; i < K; i++) {
//            System.out.print(kValues[kIthPostion][i] + " ");
//        }
//    }

//    static void solve(int numbers[], int N, int K) {
//        int[][] absValues = new int[N][N];
//        int[] newArray = new int[K];
//        int xPos = 0, yPos = 0;
//        int localMax = 0;
//        int counter = 0;
//
//        for(int i =0; i < N; i++){
//            for(int j =0; j < N; j++){
//                absValues[i][j] = abs(numbers[i], numbers[j]);
//            }
//        }
//            for (int i = 0; i < N; i++) {
//                for (int j = 0; j < N; j++) {
//                    if (i <= j) {
//                        if (localMax < absValues[i][j]) {
//                            localMax = absValues[i][j];
//                            xPos = i;
//                            yPos = j;
//                        }
//                    }
//                }
//                localMax = 0;
//                i = yPos -1;
//                newArray[counter++] = numbers[xPos];
//                if(counter == K) break;
//            }
//
//        String matrix = Arrays.stream(absValues)
//                .map(row -> Arrays.stream(row)
//                        .mapToObj(Integer::toString)
//                        .collect(Collectors.joining(" |")))
//                .collect(Collectors.joining("\n"));
//
//        System.out.println(matrix);
//        System.out.println(Arrays.toString(newArray));
//        System.out.println(absSum(newArray));
//    }

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SumOfAbsoluteDifferences {
    static int solve(int numbers[], int N, int K) {
        int[][] mat = new int[N][K];

        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 1; j < K; j++) {
                for (int k = 0; k < i; k++) {
                    mat[i][j] = Math.max(mat[i][j], mat[k][j - 1] + Math.abs(numbers[i] - numbers[k]));
                }
                max = Math.max(mat[i][j], max);
            }
        }

        return max;
    }

    public static void main(String[] args) throws Exception {
        int i, j, k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int numbers[] = new int[N];

        st = new StringTokenizer(br.readLine());
        for (i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int res = solve(numbers, N, K);

        System.out.println(res);

        br.close();
    }

}
