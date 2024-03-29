package Random.TextProcessor2;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

class TextProcessor {
    List<Integer> wordFrequency;
    List<String> parsedLines;

    public TextProcessor() {
        wordFrequency = new ArrayList<>();
        parsedLines = new ArrayList<>();
    }

    public void readText(InputStream in) {
        Scanner scanner = new Scanner(in);

        while(scanner.hasNext()){
            String line = scanner.nextLine();
            line = line.replaceAll("[^a-zA-z\\s+]", "");

            parsedLines.add(line);
        }
    }

    @Override
    public String toString() {
        return "TextProcessor{" +
                "parsedLines=" + parsedLines +
                '}';
    }

    public void printTextsVectors(PrintStream out) {

    }

    public void printCorpus(PrintStream out, int i, boolean b) {

    }

    public void mostSimilarTexts(PrintStream out) {

    }
}

class CosineSimilarityCalculator {
    public static double cosineSimilarity (Collection<Integer> c1, Collection<Integer> c2) {
        int [] array1;
        int [] array2;
        array1 = c1.stream().mapToInt(i -> i).toArray();
        array2 = c2.stream().mapToInt(i -> i).toArray();
        double up = 0.0;
        double down1=0, down2=0;

        for (int i=0;i<c1.size();i++) {
            up+=(array1[i] * array2[i]);
        }

        for (int i=0;i<c1.size();i++) {
            down1+=(array1[i]*array1[i]);
        }

        for (int i=0;i<c1.size();i++) {
            down2+=(array2[i]*array2[i]);
        }

        return up/(Math.sqrt(down1)*Math.sqrt(down2));
    }
}

public class TextProcessorTest {

    public static void main(String[] args) {
        TextProcessor textProcessor = new TextProcessor();

        textProcessor.readText(System.in);

        System.out.println("===PRINT VECTORS===");
        textProcessor.printTextsVectors(System.out);

        System.out.println("PRINT FIRST 20 WORDS SORTED ASCENDING BY FREQUENCY ");
        textProcessor.printCorpus(System.out,  20, true);

        System.out.println("PRINT FIRST 20 WORDS SORTED DESCENDING BY FREQUENCY");
        textProcessor.printCorpus(System.out, 20, false);

        System.out.println("===MOST SIMILAR TEXTS===");
        textProcessor.mostSimilarTexts(System.out);
    }
}