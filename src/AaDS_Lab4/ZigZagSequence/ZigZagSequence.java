package AaDS_Lab4.ZigZagSequence;

import javax.swing.plaf.IconUIResource;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ZigZagSequence {

    static int najdiNajdolgaCikCak(int a[]) {
        boolean isPositive = false;
        boolean isNegative = false;
        int n = a.length;
        int length = 0;
        int max = 0;

        if (a[0] > 0) {
            isPositive = true;
            length++;
        } else if (a[0] < 0) {
            isNegative = true;
            length++;
        }

        for (int i = 1; i < n; i++) {
            if (isPositive && a[i] > 0) {
                length = 1;
            } else if (isNegative && a[i] < 0) {
                length = 1;
            } else if (a[i] == 0) {
                isPositive = false;
                isNegative = false;
                length = 0;
            } else {
                if (a[i] > 0) {
                    isPositive = true;
                    isNegative = false;
                } else if (a[i] < 0) {
                    isNegative = true;
                    isPositive = false;
                }
                length++;
            }

            if(length > max)
                max = length;
        }

        return max;
    }

    public static void main(String[] args) throws Exception {
        int i, j, k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int a[] = new int[N];
        for (i = 0; i < N; i++)
            a[i] = Integer.parseInt(br.readLine());

        int rez = najdiNajdolgaCikCak(a);
        System.out.println(rez);

        br.close();

    }

}

