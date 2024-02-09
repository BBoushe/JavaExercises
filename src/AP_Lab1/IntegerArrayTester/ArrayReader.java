package AP_Lab1.IntegerArrayTester;

import java.io.InputStream;
import java.util.Scanner;

public class ArrayReader {
    public static IntegerArray readIntegerArray(InputStream in) {
        Scanner scanner = new Scanner(in);
        int n = scanner.nextInt();
        int arr[] = new int[n];
        int i = 0;

        while(scanner.hasNext()){
            arr[i++] = scanner.nextInt();
        }
        return new IntegerArray(arr);

    }
}
