package ru.nk.training;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class StringByWordsSplitter {
    public Optional<List<String>> split(String sentence, Set<String> words) {
        return splitSentenceFrom(sentence, words, 0);
    }

    private Optional<List<String>> splitSentenceFrom(String sentence, Set<String> words, int from) {
        if (from == sentence.length()) {
            return Optional.of(new ArrayList<>());
        }
        if (from > sentence.length()) {
            return Optional.empty();
        }
        for (String word : words) {
            boolean isWordMatches = sentence.startsWith(word, from);
            if (!isWordMatches) {
                continue;
            }

            Optional<List<String>> splittedRemainSentence = splitSentenceFrom(sentence, words, from + word.length());
            if (!splittedRemainSentence.isPresent()) {
                continue;
            }

            splittedRemainSentence.get().add(0, word);
            return splittedRemainSentence;
        }
        return Optional.empty();
    }
}
