package Random.WordArrange;

import java.util.stream.Collectors;

public class WordArrange implements Arrangable {

    private String sentence;

    public WordArrange(String sentence) {
        this.sentence = sentence;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String newSentence) {
        this.sentence = newSentence;
    }

    public String rearrangeWord(String word) {
        return word.chars()
                .sorted()
                .mapToObj(ch -> String.valueOf((char) ch))
                .collect(Collectors.joining());
    }

    public String arrangeSentence(String sentence) {
//       return Arrays.stream(sentence.split("\\s+"))
//                .map(word -> rearrangeWord(word))
//                .sorted()
//                .collect(Collectors.joining(" "));
        return null;

    }
}
