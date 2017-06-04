package ru.nk.training.Strings;

public class IntToStringConverter {
    private final static int MAX_INT_LENGTH = 11;

    public String intToString(int n) {
        StringBuilder builder = new StringBuilder(MAX_INT_LENGTH);
        int v = n;
        if (n < 0) {
            v = -v;
        }
        do {
            builder.insert(0, v % 10);
            v /= 10;
        } while (v > 0);
        if (n < 0) {
            builder.insert(0, '-');
        }
        return builder.toString();
    }
}
