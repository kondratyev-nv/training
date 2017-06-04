package ru.nk.training.Strings;

/**
 * Write an efficient function that deletes characters from an ASCII string. Use the prototype
 * string removeChars( string str, string remove );
 * where any character existing in remove must be deleted from str.
 * For example, given a str of "Battle of the Vowels: Hawaii vs. Grozny"
 * and a remove of "aeiou", the function should transform str to
 * "Bttl f th Vwls: Hw vs. Grzny".
 */
public class CharRemover {
    public String remove(String string, String toRemove) {
        if (string == null || toRemove == null) {
            throw new IllegalArgumentException();
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < string.length(); ++i) {
            char c = string.charAt(i);
            if (toRemove.indexOf(c) < 0) {
                builder.append(c);
            }
        }
        return builder.toString();
    }
}
