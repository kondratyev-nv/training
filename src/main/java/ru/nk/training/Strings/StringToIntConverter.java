package ru.nk.training.Strings;

public class StringToIntConverter {
    private static int charToDigit(char c) {
        return c - '0';
    }

    public int stringToInt(String string) {
        if (string == null || string.isEmpty()) {
            throw new IllegalArgumentException();
        }

        int sign = 1, start = 0, r = 0;
        if (string.charAt(0) == '-') {
            sign = -1;
            start = 1;
        }
        for (int i = start; i < string.length(); ++i) {
            char c = string.charAt(i);
            r = r * 10 + charToDigit(c);
        }
        return sign * r;
    }
}
