package ru.nk.training;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EvenTreeCounterTest {
    @Test
    public void throwsWhenGraphIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new EvenTreeCounter(null)
        );
    }

    @Test
    public void throwsWhenGraphIsEmpty() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new EvenTreeCounter(new HashMap<>())
        );
    }

    @Test
    public void throwsWhenGraphHasSingleNode() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new EvenTreeCounter(new HashMap<Integer, List<Integer>>() {{
                    put(0, new ArrayList<>());
                }})
        );
    }

    @Test
    public void returnsZeroWhenGraphHasOnlyTwoNodes() {
        int edgesToRemove = new EvenTreeCounter(new HashMap<Integer, List<Integer>>() {{
            put(0, new ArrayList<Integer>() {{
                add(1);
            }});
            put(1, new ArrayList<>());
        }}).getNumberOfEdgesToRemove();
        assertEquals(0, edgesToRemove);
    }

    @Test
    public void throwsWhenGraphHasOddNumberOfElements() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new EvenTreeCounter(new HashMap<Integer, List<Integer>>() {{
                    put(0, new ArrayList<Integer>() {{
                        add(1);
                    }});
                    put(1, new ArrayList<Integer>() {{
                        add(2);
                    }});
                    put(2, new ArrayList<>());
                }})
        );
    }

    @Test
    public void returnsOneWhenGraphHasSubtreeOfTwoNodes() {
        int edgesToRemove = new EvenTreeCounter(new HashMap<Integer, List<Integer>>() {{
            put(0, new ArrayList<Integer>() {{
                add(1);
            }});
            put(1, new ArrayList<Integer>() {{
                add(2);
            }});
            put(2, new ArrayList<Integer>() {{
                add(3);
            }});
            put(3, new ArrayList<>());
        }}).getNumberOfEdgesToRemove();
        assertEquals(1, edgesToRemove);
    }

    @Test
    public void returnsTwoWhenTwoEdgesCanBeRemovedFromGraph() {
        int edgesToRemove = new EvenTreeCounter(new HashMap<Integer, List<Integer>>() {{
            put(0, new ArrayList<Integer>() {{
                add(1);
                add(2);
                add(5);
            }});
            put(1, new ArrayList<Integer>() {{
                add(4);
                add(6);
            }});
            put(2, new ArrayList<Integer>() {{
                add(3);
            }});
            put(3, new ArrayList<>());
            put(4, new ArrayList<>());
            put(5, new ArrayList<Integer>() {{
                add(7);
            }});
            put(6, new ArrayList<>());
            put(7, new ArrayList<Integer>() {{
                add(8);
                add(9);
            }});
            put(8, new ArrayList<>());
            put(9, new ArrayList<>());
        }}).getNumberOfEdgesToRemove();
        assertEquals(2, edgesToRemove);
    }
}
