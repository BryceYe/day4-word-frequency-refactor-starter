import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {

    public static final String ANY_SPACE_SEPARATOR = "\\s+";

    public String getResult(String inputStr) {
        if (inputStr.split(ANY_SPACE_SEPARATOR).length == 1) {
            return inputStr + " 1";
        } else {
            try {
                String[] words = inputStr.split(ANY_SPACE_SEPARATOR);
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
        for (Input w : frequencies) {
            String s = w.getValue() + " " + w.getWordCount();
            joiner.add(s);
        }
        return joiner.toString();
    }

    private List<Input> countFrequencies(String[] words) {
        Map<String, List<String>> groups = groupSameWords(words);
        List<Input> frequencies = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : groups.entrySet()) {
            Input input = new Input(entry.getKey(), entry.getValue().size());
            frequencies.add(input);
        }
        return frequencies;
    }

    private static Map<String, List<String>> groupSameWords(String[] words) {
        List<String> inputList = new ArrayList<>();
        for (String s : words) {
            inputList.add(s);
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String input : inputList) {
            if (!map.containsKey(input)) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input, arr);
            } else {
                map.get(input).add(input);
            }
        }
        return map;
    }

}
