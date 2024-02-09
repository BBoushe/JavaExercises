package Random.WordArrange;

import java.util.Arrays;
import java.util.stream.Collectors;

public class WordArrangeTest {
    public static void main(String[] args) {
        WordArrange word = new WordArrange("This is the fiNal sentence");

        Arrangable lambdaArrange = dummySen -> Arrays.stream(word.getSentence().split("\\s+"))
                .map(i -> word.rearrangeWord(i))
                .sorted()
                .collect(Collectors.joining(" "));
//        genericArrange(word, word.getSentence());
        genericArrange(lambdaArrange, word.getSentence());

    }


    static void genericArrange(Arrangable obj, String sentence){
        System.out.printf(obj.arrangeSentence(sentence));
    }
}
