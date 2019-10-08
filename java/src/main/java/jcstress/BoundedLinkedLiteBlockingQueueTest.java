package jcstress;


import org.openjdk.jcstress.annotations.Actor;
import org.openjdk.jcstress.annotations.Expect;
import org.openjdk.jcstress.annotations.JCStressTest;
import org.openjdk.jcstress.annotations.Outcome;
import org.openjdk.jcstress.annotations.State;
import org.openjdk.jcstress.infra.results.II_Result;
import ru.nk.training.DataStructures.BlockingQueue.BoundedLinkedBlockingQueue;
import ru.nk.training.DataStructures.BlockingQueue.BlockingQueue;

@JCStressTest
@Outcome(id = "1, 2", expect = Expect.ACCEPTABLE, desc = "Default outcome.")
@State
public class BoundedLinkedLiteBlockingQueueTest {
    private BlockingQueue<Integer> q = new BoundedLinkedBlockingQueue<>(1);

    @Actor
    public void actor1(II_Result result) {
        try {
            q.waitingAdd(1);
            result.r1 = q.waitingRemove();
        } catch(Exception ex) {
            ex.printStackTrace();
            result.r1 = -1;
        }
    }

    @Actor
    public void actor2(II_Result result) {
        try {
            q.waitingAdd(2);
            result.r2 = q.waitingRemove();
        } catch(Exception ex) {
            ex.printStackTrace();
            result.r2 = -1;
        }
    }
}
