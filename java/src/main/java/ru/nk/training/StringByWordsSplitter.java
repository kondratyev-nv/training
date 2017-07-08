package ru.nk.training;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Given a non-empty string s and a dictionary of words containing non-empty words,
 * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 * You may assume the dictionary does not contain duplicate words.
 * For example, given s = "leetcode", dict = ["leet", "code"]
 * return space separated sentence "leet code".
 */
public class StringByWordsSplitter {
    public Optional<List<String>> split(String sentence, Set<String> words) {
        if (sentence == null || sentence.length() <= 0) {
            throw new IllegalArgumentException();
        }
        if (words == null || words.size() <= 0) {
            throw new IllegalArgumentException();
        }
        return splitSentenceFrom(sentence, words, 0, new HashSet<>());
    }

    private Optional<List<String>> splitSentenceFrom(String sentence, Set<String> words, int from,
                                                     Set<Integer> failedIndices) {
        if (failedIndices.contains(from)) {
            return Optional.empty();
        }
        if (from == sentence.length()) {
            return Optional.of(new ArrayList<>());
        }
        for (String word : words) {
            boolean isWordMatches = sentence.startsWith(word, from);
            if (!isWordMatches) {
                continue;
            }

            Optional<List<String>> splittedRemainSentence = splitSentenceFrom(sentence, words, from + word.length(),
                                                                              failedIndices);
            if (!splittedRemainSentence.isPresent()) {
                failedIndices.add(from);
                continue;
            }

            splittedRemainSentence.get().add(0, word);
            return splittedRemainSentence;
        }
        return Optional.empty();
    }
}
