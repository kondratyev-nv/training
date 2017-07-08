package ru.nk.training;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ru.nk.training.IntervalMap.Interval;

/**
 * You are given n x m matrix and a list of tracks that always run in straight horizontal lines along a row. 
 * In other words, the start and end points of a track are (r, c1) and (r, c2), where r represents the row number, 
 * c1 represents the starting column, and c2 represents the ending column of the track. 
 * Determine the number of cell that is not occupied by a track.
 */
public class MatrixAvailablePositionsCounter {
    public long count(long n, long m, List<RowInterval> occupiedIntervals) {
        if (n <= 0 || m <= 0 || occupiedIntervals == null) {
            throw new IllegalArgumentException();
        }
        Map<Integer, IntervalMap<Integer, Boolean>> occupiedCells = new HashMap<>();
        for (RowInterval interval : occupiedIntervals) {
            if (!occupiedCells.containsKey(interval.row)) {
                occupiedCells.put(interval.row, new IntervalMap<>(Boolean.FALSE));
            }
            occupiedCells.get(interval.row).set(interval.from, interval.to + 1, Boolean.TRUE);
        }
        long availableCellsCount = (n - occupiedCells.size()) * m;
        for (IntervalMap<Integer, Boolean> intervals : occupiedCells.values()) {
            Iterator<Interval<Integer, Boolean>> intervalIterator = intervals.intervalIterator();
            long occupiedCellInRowCount = 0;
            while (intervalIterator.hasNext()) {
                Interval<Integer, Boolean> interval = intervalIterator.next();
                if (interval.value == Boolean.TRUE) {
                    occupiedCellInRowCount += interval.to - interval.from;
                }
            }
            availableCellsCount += m - occupiedCellInRowCount;
        }
        return availableCellsCount;
    }

    public static class RowInterval {
        public final int row;
        public final int from;
        public final int to;

        public RowInterval(int row, int from, int to) {
            this.row = row;
            this.from = from;
            this.to = to;
        }
    }
}
