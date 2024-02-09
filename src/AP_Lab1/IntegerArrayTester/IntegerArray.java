package AP_Lab1.IntegerArrayTester;

import java.lang.reflect.Array;
import java.util.Arrays;

public final class  IntegerArray {
    private final int[] arr;
    private final int n;

    public IntegerArray(int[] ints) {
        this.arr = Arrays.copyOf(ints, ints.length); // this was causing the immutable problem
        this.n = ints.length;
    }

    public IntegerArray getSorted() {
        int[] copyOfArray = Arrays.copyOf(this.arr, this.arr.length);
        Arrays.sort(copyOfArray);
        return new IntegerArray(copyOfArray);
    }

    public IntegerArray concat(IntegerArray array2) {
        int[] newArray = Arrays.copyOf(this.arr, arr.length + array2.length());
        int j = 0;

       for(int i = this.arr.length; i < newArray.length; i++, j++) {
            newArray[i] = array2.getElementAt(j);
       }
        return new IntegerArray(newArray);
    }

    public int sum() {
        int sum = 0;
        for (int value : this.arr) {
            sum += value;
        }
        return sum;
    }

    public double average() {
        double result = 0;
        for (int value : this.arr) {
            result += value;
        }
        return result / this.n;
    }

    public int length() {
        return this.n;
    }

    public int getElementAt(int i) {
        return this.arr[i];
    }

    @Override
    public java.lang.String toString() {
        String result = "[";
        for (int i = 0; i < this.n - 1; i++) {
            result += String.valueOf(arr[i]) + ", ";
        }
        result += String.valueOf(arr[n - 1]) + "]";

        return result;
    }

    public boolean equals(IntegerArray obj) { //this has to be implemented in order for testEquals to work
        if(this.length() != obj.length())
            return false;
        for(int i = 0; i < arr.length; i++){
            if(this.getElementAt(i) != obj.getElementAt(i))
                return false;
        }
        return true;
    }
}
