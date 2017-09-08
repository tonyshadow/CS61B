import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDeque1B {
    @Test
    public void testStudentArrayDeque() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();

        /* Create a OperationSequence Object to store the track of the sequence of operations. */
        OperationSequence os = new OperationSequence();

        /* Do T times random operation. */
        int t = 100;

        /* Upper boundary (N) of generated random Int numbers for inserting. */
        int n = 13;

        for (int i = 0; i < t; i += 1) {
            int numberBetweenZeroAndN = StdRandom.uniform(n);
            int caseNumber = StdRandom.uniform(4);

            switch (caseNumber) {
                case 0:
                    sad.addFirst(numberBetweenZeroAndN);
                    ads.addFirst(numberBetweenZeroAndN);
                    DequeOperation dequeOp1 = new DequeOperation("addFirst", numberBetweenZeroAndN);
                    os.addOperation(dequeOp1);
                    break;
                case 1:
                    sad.addLast(numberBetweenZeroAndN);
                    ads.addLast(numberBetweenZeroAndN);
                    DequeOperation dequeOp2 = new DequeOperation("addFirst", numberBetweenZeroAndN);
                    os.addOperation(dequeOp2);
                    break;
                case 2:
                    Integer sadFRemoved = sad.removeFirst();
                    Integer adsFRemoved = ads.removeFirst();
                    DequeOperation dequeOp3 = new DequeOperation("removeFirst");
                    os.addOperation(dequeOp3);
                    assertEquals(os.toString(), adsFRemoved, sadFRemoved);
                    break;
                case 3:
                    Integer sadLRemoved = sad.removeLast();
                    Integer adsLRemoved = ads.removeFirst();
                    DequeOperation dequeOp4 = new DequeOperation("removeLast");
                    os.addOperation(dequeOp4);
                    assertEquals(os.toString(), adsLRemoved, sadLRemoved);
                    break;
            }
        }
    }
}
