package AaDS_ExamExercises.FirstColloq.BusPassanger;

import java.util.Scanner;

public class Bus {

    static int minPrice(int n, int m){
        int adults  = n;
        int kids = m;
        int kidsLeft = (adults > kids)? 0 : (kids - adults);

        return adults*100 + kidsLeft*100;
     }
    static int maxPrice(int n, int m){
        return (m == 0)? (n*100) : (n*100 + ((m-1)*100));
    }
    public static void main(String[] args) throws Exception {
        int i,j,k;

        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();

        System.out.println(minPrice(N, M));
        System.out.println(maxPrice(N,M));
    }

}

