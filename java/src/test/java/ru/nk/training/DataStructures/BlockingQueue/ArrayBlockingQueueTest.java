package ru.nk.training.DataStructures.BlockingQueue;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class ArrayBlockingQueueTest {
    @Test
    public void put() throws Exception {
        ArrayBlockingQueue<Integer> q = new ArrayBlockingQueue<>(Integer.class, 4);

        q.add(1);

        boolean addResult = q.tryAdd(2);
        Assertions.assertTrue(addResult);

        q.waitingAdd(3);

        addResult = q.waitingAdd(4, 1, TimeUnit.MINUTES);
        Assertions.assertTrue(addResult);

        addResult = q.waitingAdd(5, 1, TimeUnit.SECONDS);
        Assertions.assertFalse(addResult);

        // ---

        Integer result = q.remove();
        Assertions.assertEquals((Integer) 1, result);

        addResult = q.waitingAdd(5, 1, TimeUnit.SECONDS);
        Assertions.assertTrue(addResult);

        result = q.tryRemove();
        Assertions.assertEquals((Integer) 2, result);

        result = q.waitingRemove();
        Assertions.assertEquals((Integer) 3, result);

        result = q.waitingRemove(1, TimeUnit.MINUTES);
        Assertions.assertEquals((Integer) 4, result);

        result = q.waitingRemove(1, TimeUnit.SECONDS);
        Assertions.assertEquals((Integer) 5, result);

        result = q.waitingRemove(1, TimeUnit.SECONDS);
        Assertions.assertEquals(null, result);
    }
}