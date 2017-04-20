package ru.nk.training;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

class Trie {
    private static final TrieNode EMPTY_NODE = new TrieNode(null);

    private final TrieNode root = new TrieNode(null);

    public void add(String s) {
        TrieNode next = root;
        for (int i = 0; i < s.length() - 1; ++i) {
            next = next.addChild(s.charAt(i));
        }
        next.addChild(s.charAt(s.length() - 1)).setCompleteWord();
    }

    public int wordCount(String prefix) {
        return findNodeByPrefix(prefix).wordCount();
    }

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
