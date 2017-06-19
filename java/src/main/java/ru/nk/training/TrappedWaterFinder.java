package ru.nk.training;

public class TrappedWaterFinder {
    public int findTrappedWaterUnits(int[] elevationMap) {
        int index = 1, units = 0;
        while (index < elevationMap.length) {
            while (index < elevationMap.length && elevationMap[index] >= elevationMap[index - 1]) {
                index++;
            }
            int maxHeight = elevationMap[index - 1], pitUnits = 0;
            while (index < elevationMap.length && elevationMap[index] < maxHeight) {
                pitUnits += maxHeight - elevationMap[index];
                index++;
            }
            if (index < elevationMap.length) {
                units += pitUnits;
            }
        }
        return units;
    }
}
