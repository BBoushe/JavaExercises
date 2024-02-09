package AP_Lab2.DoubleMatrixTester;

public class InsufficientElementsException extends Exception {
    public InsufficientElementsException() {
        super("Insufficient number of elements");
    }
}
