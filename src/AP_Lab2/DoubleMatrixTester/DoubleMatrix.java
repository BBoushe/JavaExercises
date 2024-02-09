package AP_Lab2.DoubleMatrixTester;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public final class DoubleMatrix {

    private final double[] array;
    private int m;
    private int n;

    private final double[][] matrix;

    DoubleMatrix(double[] array, int m, int n) throws InsufficientElementsException {
        if (array.length < m * n) throw new InsufficientElementsException();

        this.array = Arrays.copyOfRange(array, array.length - m * n, array.length); // this takes care of immutability and also takes care of check if less elements
        this.m = m;
        this.n = n;

        matrix = new double[m][n];
        int begin = 0;
        for(int i = m - 1; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                matrix[i][j] = array[array.length - 1 - begin];
                begin++;
            }
        }

    }

    public String getDimensions() {
        return "[" + this.m + " x " + this.n + "]";
    }

    public int rows() {
        return m;
    }

    public int columns() {
        return n;
    }

    public double maxElementAtRow(int row) throws InvalidRowNumberException {
        double max;
        if (row < 1 || row > m) throw new InvalidRowNumberException();
        else {
            max = matrix[row][0];
            for (int i = 0; i < n; i++) {
                if (matrix[row][i] > max)
                    max = matrix[row][i];
            }
        }
        return max;
    }

    public double maxElementAtColumn(int column) throws InvalidColumnNumberException {
        double max;
        if (column < 1 || column > n) {
            throw new InvalidColumnNumberException();
        } else {
            max = matrix[0][column];
            for (int i = 0; i < m; i++) {
                if (matrix[column][i] > max)
                    max = matrix[column][i];
            }
        }
        return max;
    }

    public double sum() {
        return Arrays.stream(matrix).flatMapToDouble(Arrays::stream).sum();
    }

    public double[] toSortedArray() {
        double[] flatArray = new double[m * n];

        flatArray = Arrays.stream(matrix).flatMapToDouble(Arrays::stream).toArray();
        Arrays.sort(flatArray);

        return flatArray;
    }

    @Override
    public String toString() {
        return Arrays.stream(matrix)
                .map(row -> Arrays.stream(row)
                        .mapToObj(number -> String.format("%2.f", number))
                        .collect(Collectors.joining("\t")))
                .collect(Collectors.joining("\n"));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoubleMatrix that = (DoubleMatrix) o;
        return m == that.m && n == that.n && Arrays.deepEquals(matrix, that.matrix);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(m, n);
        result = 31 * result + Arrays.deepHashCode(matrix);
        return result;
    }
}
