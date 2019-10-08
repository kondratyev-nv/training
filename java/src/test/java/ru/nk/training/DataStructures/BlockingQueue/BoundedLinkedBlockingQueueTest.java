package ru.nk.training.DataStructures.BlockingQueue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class BoundedLinkedBlockingQueueTest {
    @Test
    public void put() throws Exception {
        BoundedLinkedBlockingQueue<Integer> q = new BoundedLinkedBlockingQueue<>();
        q.add(1);
        Integer result = q.remove();
        Assertions.assertEquals((Integer) 1, result);
        q.tryAdd(2);
        result = q.tryRemove();
        Assertions.assertEquals((Integer) 2, result);
        q.waitingAdd(3);
        result = q.waitingRemove();
        Assertions.assertEquals((Integer) 3, result);
        q.waitingAdd(4, 1, TimeUnit.MINUTES);
        result = q.waitingRemove(1, TimeUnit.MINUTES);
        Assertions.assertEquals((Integer) 4, result);
    }
}