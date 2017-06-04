package ru.nk.training.Trees;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EvenTreeCounterTest {
    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenGraphIsNull() throws Exception {
        new EvenTreeCounter(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenGraphIsEmpty() throws Exception {
        new EvenTreeCounter(new HashMap<>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenGraphHasSingleNode() throws Exception {
        new EvenTreeCounter(new HashMap<Integer, List<Integer>>() {{
            put(0, new ArrayList<>());
        }});
    }

    @Test
    public void returnsZeroWhenGraphHasOnlyTwoNodes() throws Exception {
        int edgesToRemove = new EvenTreeCounter(new HashMap<Integer, List<Integer>>() {{
            put(0, new ArrayList<Integer>() {{
                add(1);
            }});
            put(1, new ArrayList<>());
        }}).getNumberOfEdgesToRemove();
        assertEquals(0, edgesToRemove);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenGraphHasOddNumberOfElements() throws Exception {
        new EvenTreeCounter(new HashMap<Integer, List<Integer>>() {{
            put(0, new ArrayList<Integer>() {{
                add(1);
            }});
            put(1, new ArrayList<Integer>() {{
                add(2);
            }});
            put(2, new ArrayList<>());
        }});
    }

    @Test
    public void returnsOneWhenGraphHasSubtreeOfTwoNodes() throws Exception {
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
    public void returnsTwoWhenTwoEdgesCanBeRemovedFromGraph() throws Exception {
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
            put(4, new ArrayList<Integer>());
            put(5, new ArrayList<Integer>() {{
                add(7);
            }});
            put(6, new ArrayList<Integer>());
            put(7, new ArrayList<Integer>() {{
                add(8);
                add(9);
            }});
            put(8, new ArrayList<Integer>());
            put(9, new ArrayList<Integer>());
        }}).getNumberOfEdgesToRemove();
        assertEquals(2, edgesToRemove);
    }
}