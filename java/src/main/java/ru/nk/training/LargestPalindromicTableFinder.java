package ru.nk.training;

import java.util.HashMap;
import java.util.Map;

public class LargestPalindromicTableFinder {
    public static class LargestPalindromicTable {
        public final int fromRow;
        public final int toRow;
        public final int fromColumn;
        public final int toColumn;

        public LargestPalindromicTable(int fromRow, int toRow, int fromColumn, int toColumn) {
            super();
            this.fromRow = fromRow;
            this.toRow = toRow;
            this.fromColumn = fromColumn;
            this.toColumn = toColumn;
        }

        public int getArea() {
            return (toRow - fromRow + 1) * (toColumn - fromColumn + 1);
        }
    }

    public LargestPalindromicTable find(int[][] table) {
        return null;
    }

    private boolean canRearrangeToPalindrom(int[][] table, int fromRow, int toRow, int fromColumn, int toColumn) {
        Map<Integer, Integer> occurences = new HashMap<>();
        for (int i = fromRow; i < toRow; ++i) {
            for (int j = fromColumn; j < toColumn; ++j) {
                occurences.put(table[i][j], occurences.getOrDefault(table[i][j], 0) + 1);
            }
        }

        Integer numberOfZeroOccurences = occurences.get(0);
        if (occurences.size() < 2 && numberOfZeroOccurences != null) {
            return numberOfZeroOccurences < 2;
        }

        int oddCounter = 0;
        for (Map.Entry<Integer, Integer> numberOfOccurences : occurences.entrySet()) {
            if (numberOfOccurences.getValue() % 2 != 0) {
                oddCounter++;
            }
            if (oddCounter > 1) {
                return false;
            }
        }

        return oddCounter < 2 && numberOfZeroOccurences == null;
    }
}
