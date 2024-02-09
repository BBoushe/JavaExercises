package AP_Lab1.RomanNumerals;
import java.util.Scanner;
import java.util.stream.IntStream;

public class RomanConverterTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        IntStream.range(0, n)
                .forEach(x -> System.out.println(RomanConverter.toRoman(scanner.nextInt())));
        scanner.close();
    }
}
class RomanConverter {
    /**
     * Roman to decimal converter
     *
     * @param number number in decimal format
     * @return string representation of the number in Roman numeral
     */
    public static String toRoman(int number) {
        StringBuilder result = new StringBuilder();
        RomanNumbers values[] = RomanNumbers.values();
        int i = values.length - 1; // So we start at the largest numeral and go backwards for the check

        while (number > 0) {
            RomanNumbers currentNumeral = values[i]; // Again we start from the largest numeral and we go down
            // the reason we do this is if the number is a:3456 and 1000 is the largest numeral then we check if a >= 1000
            // or the largest numeral if it is then subtract it's value from the number and rinse and repeat until we get to 0

            if (number >= currentNumeral.getValue()) {
                result.append(currentNumeral);
                number -= currentNumeral.getValue();
            } else i--;
        }

        return result.toString();
    }
}
