package AP_Lab2.DoubleMatrixTester;

import java.io.InputStream;
import java.util.Scanner;

public class MatrixReader {
    public static DoubleMatrix read(InputStream input) throws InsufficientElementsException {
        Scanner sc = new Scanner(input);
        int m = sc.nextInt();
        int n = sc.nextInt();
        double[] array = new double[m*n];

        int i = 0;
        while(sc.hasNextDouble()){
            array[i++] = sc.nextDouble();
        }

        return new DoubleMatrix(array, m, n);
    }
}
