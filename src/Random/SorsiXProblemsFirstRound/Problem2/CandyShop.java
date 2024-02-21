package Random.SorsiXProblemsFirstRound.Problem2;

import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

public class CandyShop {
    public static void main(String[] args) {
        final List<String> numbersInput = readInput();
        calculateCandies(numbersInput);
    }

    private static void calculateCandies(List<String> numbersInput) {
        int pari = Integer.parseInt(numbersInput.get(1));
        String[] prvaLinija = numbersInput.get(0).split(" ");
        List<Integer> ceni = Arrays.stream(prvaLinija).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());

        int numCandies = 0;
        int preostanatiPari = pari;
        Collections.sort(ceni);
        for(int cena : ceni){
            if(preostanatiPari >= cena){
                preostanatiPari-=cena;
                numCandies++;
            } else {
                break;
            }
        }



        Collections.reverse(ceni);
        int mostExpensive = 0;
        for(int cena : ceni){
            if(pari - cena >= 0 && cena > mostExpensive){
                mostExpensive = cena;
            }
        }

        System.out.println(numCandies);
        System.out.println(mostExpensive);
    }

    public static List<String> readInput() {
        final Scanner scan = new Scanner(System.in);
        final List<String> items = new ArrayList<>();
        while (scan.hasNextLine()) {
            items.add(scan.nextLine());
        }
        return items;
    }
}
