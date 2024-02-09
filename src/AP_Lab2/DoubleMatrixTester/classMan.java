//package AP_Lab2.DoubleMatrixTester;
//
//import java.util.Arrays;
//import java.util.Comparator;
//import java.util.stream.Collectors;
//import java.io.InputStream;
//import java.util.Scanner;
//import java.io.ByteArrayInputStream;
//import java.text.DecimalFormat;
//import java.util.Objects;
//
//
//// this is a class for how the problem works on the courses system. There are some mistakes on my part, but it's mostly how courses handles the code execution it's not like that on the machine
//
//class InsufficientElementsException extends Exception {
//    InsufficientElementsException() {
//        super("Insufficient number of elements");
//    }
//}
//
//class InvalidRowNumberException extends Exception {
//    InvalidRowNumberException() {
//        super("Invalid row number");
//    }
//}
//
//class InvalidColumnNumberException extends Exception {
//    InvalidColumnNumberException() {
//        super("Invalid column number");
//    }
//}
//
//final class DoubleMatrix {
//
//    private final double[] array;
//    private int m;
//    private int n;
//
//    private final double[][] matrix;
//
//    DoubleMatrix(double[] array, int m, int n) throws InsufficientElementsException {
//        if (array.length < m * n) throw new InsufficientElementsException();
//
//        this.m = m;
//        this.n = n;
//        this.array = Arrays.copyOfRange(array, array.length - m * n, array.length); // this takes care of immutability and also takes care of check if less elements
//
//        matrix = new double[m][n];
//        int begin = 0;
//        for (int i = m - 1; i >= 0; i--) {
//            for (int j = n - 1; j >= 0; j--) {
//                matrix[i][j] = array[array.length - 1 - begin];
//                begin++;
//            }
//        }
//
//    }
//
//    public String getDimensions() {
//        return "[" + this.m + " x " + this.n + "]";
//    }
//
//    public int rows() {
//        return m;
//    }
//
//    public int columns() {
//        return n;
//    }
//
//    public double maxElementAtRow(int row) throws InvalidRowNumberException {
//        if (row < 1 || row > m) throw new InvalidRowNumberException();
//
//        row--;
//        double element = matrix[row][0];
//        for(int i = 1; i < n; i++) {
//            if(matrix[row][i] > element) {
//                element = matrix[row][i];
//            }
//        }
//        return element;
//    }
//
//    public double maxElementAtColumn(int column) throws InvalidColumnNumberException {
//        if(column < 1 || column > n) {
//            throw new InvalidColumnNumberException();
//        }
//
//        column--;
//        double element = matrix[0][column];
//        for(int i = 1; i < m; i++) {
//            if(matrix[i][column] > element) {
//                element = matrix[i][column];
//            }
//        }
//        return element;
//    }
//
//    public double sum() {
//        return Arrays.stream(matrix).flatMapToDouble(Arrays::stream).sum();
//    }
//
//    public double[] toSortedArray() {
//        double[] flatArray = Arrays.copyOf(array, array.length);
//
//        flatArray = Arrays.stream(matrix).flatMapToDouble(Arrays::stream)
//                .boxed()
//                .sorted(Comparator.reverseOrder())
//                .mapToDouble(Double::doubleValue).toArray();
//
//        return flatArray;
//    }
//
////    @Override
////    public String toString() {
////        return Arrays.stream(matrix).map(row -> Arrays.stream(row)
////                        .mapToObj(number -> String.format("%2.f", number))
////                        .collect(Collectors.joining("\t")))
////                .collect(Collectors.joining("\n"));
////    }
//
//    public String toString() {
//        StringBuilder result = new StringBuilder();
//        for(int i = 0; i < m; i++) {
//            for(int j = 0; j < n - 1; j++) {
//                result.append(String.format("%.2f", matrix[i][j]));
//                result.append('\t');
//            }
//            result.append(String.format("%.2f", matrix[i][n - 1]));
//            if(i != m - 1) result.append("\n");
//        }
//        return result.toString();
//    }
//
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        DoubleMatrix that = (DoubleMatrix) o;
//        return m == that.m && n == that.n && Arrays.deepEquals(matrix, that.matrix);
//    }
//
//    @Override
//    public int hashCode() {
//        int result = Objects.hash(m, n);
//        result = 31 * result + Arrays.deepHashCode(matrix);
//        return result;
//    }
//}
//
//class MatrixReader {
//    public static DoubleMatrix read(InputStream input) throws InsufficientElementsException {
//        Scanner sc = new Scanner(input);
//        int m = sc.nextInt();
//        int n = sc.nextInt();
//        double[] array = new double[m * n];
//
//        int i = 0;
//        while (sc.hasNextDouble()) {
//            array[i++] = sc.nextDouble();
//        }
//
//        return new DoubleMatrix(array, m, n);
//    }
//}
//
// public class DoubleMatrixTester {
//
//    public static void main(String[] args) throws Exception {
//        Scanner scanner = new Scanner(System.in);
//
//        int tests = scanner.nextInt();
//        DoubleMatrix fm = null;
//
//        double[] info = null;
//
//        DecimalFormat format = new DecimalFormat("0.00");
//
//        for (int t = 0; t < tests; t++) {
//
//            String operation = scanner.next();
//
//            switch (operation) {
//                case "READ": {
//                    int N = scanner.nextInt();
//                    int R = scanner.nextInt();
//                    int C = scanner.nextInt();
//
//                    double[] f = new double[N];
//
//                    for (int i = 0; i < f.length; i++)
//                        f[i] = scanner.nextDouble();
//
//                    try {
//                        fm = new DoubleMatrix(f, R, C);
//                        info = Arrays.copyOf(f, f.length);
//
//                    } catch (InsufficientElementsException e) {
//                        System.out.println("Exception caught: " + e.getMessage());
//                    }
//
//                    break;
//                }
//
//                case "INPUT_TEST": {
//                    int R = scanner.nextInt();
//                    int C = scanner.nextInt();
//
//                    StringBuilder sb = new StringBuilder();
//
//                    sb.append(R + " " + C + "\n");
//
//                    scanner.nextLine();
//
//                    for (int i = 0; i < R; i++)
//                        sb.append(scanner.nextLine() + "\n");
//
//                    fm = MatrixReader.read(new ByteArrayInputStream(sb.toString().getBytes()));
//
//                    info = new double[R * C];
//                    Scanner tempScanner = new Scanner(new ByteArrayInputStream(sb.toString().getBytes()));
//                    tempScanner.nextDouble();
//                    tempScanner.nextDouble();
//                    for (int z = 0; z < R * C; z++) {
//                        info[z] = tempScanner.nextDouble();
//                    }
//
//                    tempScanner.close();
//
//                    break;
//                }
//
//                case "PRINT": {
//                    System.out.println(fm.toString());
//                    break;
//                }
//
//                case "DIMENSION": {
//                    System.out.println("Dimensions: " + fm.getDimensions());
//                    break;
//                }
//
//                case "COUNT_ROWS": {
//                    System.out.println("Rows: " + fm.rows());
//                    break;
//                }
//
//                case "COUNT_COLUMNS": {
//                    System.out.println("Columns: " + fm.columns());
//                    break;
//                }
//
//                case "MAX_IN_ROW": {
//                    int row = scanner.nextInt();
//                    try {
//                        System.out.println("Max in row: " + format.format(fm.maxElementAtRow(row)));
//                    } catch (InvalidRowNumberException e) {
//                        System.out.println("Exception caught: " + e.getMessage());
//                    }
//                    break;
//                }
//
//                case "MAX_IN_COLUMN": {
//                    int col = scanner.nextInt();
//                    try {
//                        System.out.println("Max in column: " + format.format(fm.maxElementAtColumn(col)));
//                    } catch (InvalidColumnNumberException e) {
//                        System.out.println("Exception caught: " + e.getMessage());
//                    }
//                    break;
//                }
//
//                case "SUM": {
//                    System.out.println("Sum: " + format.format(fm.sum()));
//                    break;
//                }
//
//                case "CHECK_EQUALS": {
//                    int val = scanner.nextInt();
//
//                    int maxOps = val % 7;
//
//                    for (int z = 0; z < maxOps; z++) {
//                        double work[] = Arrays.copyOf(info, info.length);
//
//                        int e1 = (31 * z + 7 * val + 3 * maxOps) % info.length;
//                        int e2 = (17 * z + 3 * val + 7 * maxOps) % info.length;
//
//                        if (e1 > e2) {
//                            double temp = work[e1];
//                            work[e1] = work[e2];
//                            work[e2] = temp;
//                        }
//
//                        DoubleMatrix f1 = fm;
//                        DoubleMatrix f2 = new DoubleMatrix(work, fm.rows(), fm.columns());
//                        System.out.println("Equals check 1: " + f1.equals(f2) + " " + f2.equals(f1) + " " + (f1.hashCode() == f2.hashCode() && f1.equals(f2)));
//                    }
//
//                    if (maxOps % 2 == 0) {
//                        DoubleMatrix f1 = fm;
//                        DoubleMatrix f2 = new DoubleMatrix(new double[]{3.0, 5.0, 7.5}, 1, 1);
//
//                        System.out.println("Equals check 2: " + f1.equals(f2) + " " + f2.equals(f1) + " " + (f1.hashCode() == f2.hashCode() && f1.equals(f2)));
//                    }
//
//                    break;
//                }
//
//                case "SORTED_ARRAY": {
//                    double[] arr = fm.toSortedArray();
//
//                    String arrayString = "[";
//
//                    if (arr.length > 0) arrayString += format.format(arr[0]) + "";
//
//                    for (int i = 1; i < arr.length; i++)
//                        arrayString += ", " + format.format(arr[i]);
//
//                    arrayString += "]";
//
//                    System.out.println("Sorted array: " + arrayString);
//                    break;
//                }
//
//            }
//
//        }
//
//        scanner.close();
//    }
//}
//
