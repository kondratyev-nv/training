package ru.nk.training;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

class Trie {
    private static final TrieNode EMPTY_NODE = new TrieNode(null);

    private final TrieNode root = new TrieNode(null);

    /**
     * Add new word
     *
     * @param word - Word to add
     */
    public void add(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length() - 1; ++i) {
            node = node.addChild(word.charAt(i));
        }
        node.addChild(word.charAt(word.length() - 1)).setCompleteWord();
    }

    /**
     * Get number of words that start with specified prefix
     *
     * @param prefix prefix of words to count
     * @return number of words in trie
     */
    public int wordCount(String prefix) {
        return findNodeByPrefix(prefix).wordCount();
    }

    /**
     * Get words that start with specified prefix
     *
     * @param prefix prefix of words to look for
     * @return words that starts with the prefix
     */
    public String[] words(String prefix) {
        TrieNode node = findNodeByPrefix(prefix);
        Stream<String> suffixes = node.getWords().map(suffix -> prefix + suffix);
        if (node.isCompleteWord) {
            suffixes = Stream.concat(Arrays.stream(new String[]{ prefix }), suffixes);
        }
        return suffixes.toArray(String[]::new);
    }

    private TrieNode findNodeByPrefix(String prefix) {
        TrieNode current = root;
        for (int i = 0; i < prefix.length(); ++i) {
            TrieNode next = current.get(prefix.charAt(i));
            if (next == null) {
                return EMPTY_NODE;
            }
            current = next;
        }
        return current;
    }

    private static class TrieNode {
        private final TrieNode parent;
        private final Map<Character, TrieNode> children = new HashMap<>();
        private boolean isCompleteWord = false;
        private int wordCount = 0;

        private TrieNode(TrieNode parent) {
            this.parent = parent;
        }

        private TrieNode get(char c) {
            return children.get(c);
        }

        private TrieNode addChild(char c) {
            return children.computeIfAbsent(c, k -> new TrieNode(this));
        }

        private void setCompleteWord() {
            if (!isCompleteWord) {
                increaseWordCount();
            }
            isCompleteWord = true;
        }

        private Stream<String> getWords() {
            Stream<String> stream = Stream.empty();
            for (Map.Entry<Character, TrieNode> e : children.entrySet()) {
                TrieNode child = e.getValue();
                String prefix = e.getKey().toString();

                if (child.isCompleteWord) {
                    stream = Stream.concat(stream, Arrays.stream(new String[]{ prefix }));
                }

                stream = Stream.concat(stream, child.getWords().map(suffix -> prefix + suffix));
            }
            return stream;
        }

        private int wordCount() {
            return wordCount;
        }

        private void increaseWordCount() {
            wordCount++;
            if (parent != null) {
                parent.increaseWordCount();
            }
        }
    }
}
