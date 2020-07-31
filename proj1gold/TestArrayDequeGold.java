import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestArrayDequeGold {
    private StudentArrayDeque<Integer> stuDq = new StudentArrayDeque<Integer>();
    private ArrayDequeSolution<Integer> solDq = new ArrayDequeSolution<Integer>();
    private String message = "";
    @Test
    public void randomDequeTest(){
        int testNum= 1000;
        double[] A = new double[]{0.0, 0.2, 0.4, 0.6, 0.8, 1.0};
        for(int i = 0; i < testNum; i ++) {
            double num = StdRandom.uniform();

            if (num >= A[0] && num < A[1]) {
                int n = (int) (100 * num);
                stuDq.addFirst(n);
                solDq.addFirst(n);
                message += "addFirst(" + n + ")\n";
                Integer x = stuDq.get(0);
                Integer y = solDq.get(0);
                if (x == null || y == null) {
                    continue;
                }
                assertEquals(message, y, x);
                /*
                if (!x.equals(y)) {
                    System.out.println(i + ": test 1 mismatch");
                    break;
                }*/

            }
            else if (num >= A[1] && num < A[2]) {
                int n = (int) (100 * num);
                stuDq.addLast(n);
                solDq.addLast(n);
                message += "addLast(" + n + ")\n";
                Integer x = stuDq.get(stuDq.size() - 1);
                Integer y = solDq.get(solDq.size() - 1);
                if (x == null || y == null) {
                    continue;
                }
                assertEquals(message, y, x);
                /*if (!x.equals(y)) {
                    System.out.println(i + ": test 2 mismatch");
                    break;
                }*/

            }
            else if (num >= A[2] && num < A[3]) {
                if (stuDq.size() == 0 || solDq.size() == 0) {
                    continue;
                }
                Integer x = stuDq.removeFirst();
                Integer y = solDq.removeFirst();
                message += "removeFirst()\n";
                if (x == null || y == null) {
                    continue;
                }
                assertEquals(message, y, x);
                /*if (!x.equals(y)) {
                    System.out.println(i + ": test 3 mismatch");
                    break;
                }*/

            }
            else if (num >= A[3] && num < A[4]) {
                if (stuDq.size() == 0 || solDq.size() == 0) {
                    continue;
                }
                Integer x = stuDq.removeLast();
                Integer y = solDq.removeLast();
                message += "removeLast()\n";
                if (x == null || y == null) {
                    continue;
                }
                assertEquals(message, y, x);
                /*
                if (!x.equals(y)) {
                    System.out.println(i + ": test 4 mismatch");
                    break;
                }*/

            }
            else if (num >= A[4] && num <= A[5]) {
                if (stuDq.size() != solDq.size()) {
                    System.out.println(i + ": test 5 mismatch");
                    break;
                }

            }


        }

    }
}
