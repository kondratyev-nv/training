package jcstress;


import org.openjdk.jcstress.annotations.Actor;
import org.openjdk.jcstress.annotations.Expect;
import org.openjdk.jcstress.annotations.JCStressTest;
import org.openjdk.jcstress.annotations.Outcome;
import org.openjdk.jcstress.annotations.State;
import org.openjdk.jcstress.infra.results.IIII_Result;
import ru.nk.training.DataStructures.BlockingQueue.BlockingQueue;
import ru.nk.training.DataStructures.BlockingQueue.ConditionArrayBlockingQueue;

@JCStressTest
@Outcome(id = "1, 2, 3, 4", expect = Expect.ACCEPTABLE, desc = "Default outcome.")
@Outcome(id = "1, 2, 4, 3", expect = Expect.ACCEPTABLE, desc = "Default outcome.")
@Outcome(id = "1, 3, 2, 4", expect = Expect.ACCEPTABLE, desc = "Default outcome.")
@Outcome(id = "1, 3, 4, 2", expect = Expect.ACCEPTABLE, desc = "Default outcome.")
@Outcome(id = "1, 4, 3, 2", expect = Expect.ACCEPTABLE, desc = "Default outcome.")
@Outcome(id = "1, 4, 2, 3", expect = Expect.ACCEPTABLE, desc = "Default outcome.")
@Outcome(id = "2, 1, 3, 4", expect = Expect.ACCEPTABLE, desc = "Default outcome.")
@Outcome(id = "2, 1, 4, 3", expect = Expect.ACCEPTABLE, desc = "Default outcome.")
@Outcome(id = "2, 3, 1, 4", expect = Expect.ACCEPTABLE, desc = "Default outcome.")
@Outcome(id = "2, 3, 4, 1", expect = Expect.ACCEPTABLE, desc = "Default outcome.")
@Outcome(id = "2, 4, 1, 3", expect = Expect.ACCEPTABLE, desc = "Default outcome.")
@Outcome(id = "2, 4, 3, 1", expect = Expect.ACCEPTABLE, desc = "Default outcome.")
@Outcome(id = "3, 1, 2, 4", expect = Expect.ACCEPTABLE, desc = "Default outcome.")
@Outcome(id = "3, 1, 4, 2", expect = Expect.ACCEPTABLE, desc = "Default outcome.")
@Outcome(id = "3, 2, 1, 4", expect = Expect.ACCEPTABLE, desc = "Default outcome.")
@Outcome(id = "3, 2, 4, 1", expect = Expect.ACCEPTABLE, desc = "Default outcome.")
@Outcome(id = "3, 4, 1, 2", expect = Expect.ACCEPTABLE, desc = "Default outcome.")
@Outcome(id = "3, 4, 2, 1", expect = Expect.ACCEPTABLE, desc = "Default outcome.")
@Outcome(id = "4, 1, 2, 3", expect = Expect.ACCEPTABLE, desc = "Default outcome.")
@Outcome(id = "4, 1, 3, 2", expect = Expect.ACCEPTABLE, desc = "Default outcome.")
@Outcome(id = "4, 2, 1, 3", expect = Expect.ACCEPTABLE, desc = "Default outcome.")
@Outcome(id = "4, 2, 3, 1", expect = Expect.ACCEPTABLE, desc = "Default outcome.")
@Outcome(id = "4, 3, 1, 2", expect = Expect.ACCEPTABLE, desc = "Default outcome.")
@Outcome(id = "4, 3, 2, 1", expect = Expect.ACCEPTABLE, desc = "Default outcome.")
@State
public class ConditionArrayBlockingQueueTest2 {
    private BlockingQueue<Integer> q = new ConditionArrayBlockingQueue<>(Integer.class, 2);

    @Actor
    public void actor1(IIII_Result result) {
        try {
            q.waitingAdd(1);
            result.r1 = q.waitingRemove();
        } catch(Exception ex) {
            result.r1 = -1;
            throw new RuntimeException(ex);
        }
    }

    @Actor
    public void actor2(IIII_Result result) {
        try {
            q.waitingAdd(2);
            result.r2 = q.waitingRemove();
        } catch(Exception ex) {
            result.r2 = -1;
            throw new RuntimeException(ex);
        }
    }

    @Actor
    public void actor3(IIII_Result result) {
        try {
            q.waitingAdd(3);
            result.r3 = q.waitingRemove();
        } catch(Exception ex) {
            result.r3 = -1;
            throw new RuntimeException(ex);
        }
    }

    @Actor
    public void actor4(IIII_Result result) {
        try {
            q.waitingAdd(4);
            result.r4 = q.waitingRemove();
        } catch(Exception ex) {
            result.r4 = -1;
            throw new RuntimeException(ex);
        }
    }
}



