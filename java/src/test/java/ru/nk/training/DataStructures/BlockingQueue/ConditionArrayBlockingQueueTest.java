package ru.nk.training.DataStructures.BlockingQueue;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class ConditionArrayBlockingQueueTest {
    @Test
    public void put() throws Exception {
        ConditionArrayBlockingQueue<Integer> q = new ConditionArrayBlockingQueue<>(Integer.class, 4);

        q.add(1);
        Assertions.assertEquals(1, q.size());

        boolean addResult = q.tryAdd(2);
        Assertions.assertTrue(addResult);
        Assertions.assertEquals(2, q.size());

        q.waitingAdd(3);
        Assertions.assertEquals(3, q.size());

        addResult = q.waitingAdd(4, 1, TimeUnit.MINUTES);
        Assertions.assertTrue(addResult);
        Assertions.assertEquals(4, q.size());

        addResult = q.waitingAdd(5, 1, TimeUnit.SECONDS);
        Assertions.assertFalse(addResult);
        Assertions.assertEquals(4, q.size());

        // ---

        Integer result = q.remove();
        Assertions.assertEquals((Integer) 1, result);
        Assertions.assertEquals(3, q.size());

        addResult = q.waitingAdd(5, 1, TimeUnit.SECONDS);
        Assertions.assertTrue(addResult);
        Assertions.assertEquals(4, q.size());

        result = q.tryRemove();
        Assertions.assertEquals((Integer) 2, result);
        Assertions.assertEquals(3, q.size());

        result = q.waitingRemove();
        Assertions.assertEquals((Integer) 3, result);
        Assertions.assertEquals(2, q.size());

        result = q.waitingRemove(1, TimeUnit.MINUTES);
        Assertions.assertEquals((Integer) 4, result);
        Assertions.assertEquals(1, q.size());

        result = q.waitingRemove(1, TimeUnit.SECONDS);
        Assertions.assertEquals((Integer) 5, result);
        Assertions.assertEquals(0, q.size());

        result = q.waitingRemove(1, TimeUnit.SECONDS);
        Assertions.assertEquals(null, result);
        Assertions.assertEquals(0, q.size());
    }
}