package AaDS_Lab1.PushZero;
import java.util.*;

public class PushZero
{
    static void pushZerosToBeginning(int[] arr, int n) {
        List<Integer> zeroList = new ArrayList<>();
        List<Integer> nonZeroList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (arr[i] == 0)
                zeroList.add(arr[i]);
            else nonZeroList.add(arr[i]);
        }
        zeroList.addAll(nonZeroList);

        for (int i = 0; i < n; i++) {
               arr[i] = zeroList.get(i);
        }
    }

    public static void main (String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int n =  scanner.nextInt();
        int[] arr = new int[n];

        for(int i = 0; i<n; i++){
            arr[i] = scanner.nextInt();
        }
        scanner.close();

        pushZerosToBeginning(arr, n);
        System.out.println(Arrays.toString(arr));
    }
}
