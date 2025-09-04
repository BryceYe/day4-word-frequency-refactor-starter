import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {

    public static final String ANY_SPACE_SEPARATOR = "\\s+";

    public String getResult(String inputStr) {
        String[] words = inputStr.split(ANY_SPACE_SEPARATOR);

        if (words.length == 1 && !words[0].isEmpty()) {
            return inputStr + " 1";
        } else {
            try {
                List<Input> frequencies = countFrequencies(words);
                frequencies.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());
                return composeOutput(frequencies);
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private static String composeOutput(List<Input> frequencies) {
        StringJoiner joiner = new StringJoiner("\n");
        frequencies.stream()
            .map(w -> w.getWord() + " " + w.getWordCount())
            .forEach(joiner::add);
        return joiner.toString();
    }

    private List<Input> countFrequencies(String[] words) {
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String word : words) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }
        List<Input> frequencies = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            frequencies.add(new Input(entry.getKey(), entry.getValue()));
        }
        return frequencies;
    }

}
