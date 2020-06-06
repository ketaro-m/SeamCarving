package lists;


import org.junit.Test;
import static org.junit.Assert.*;

/** Tests for Lists
 *  @author Keitaro Murakami
 */

public class ListsTest {

    @Test
    public void testNaturalRuns() {
        int[][] a = {{1, 3, 7}, {5}, {4, 6, 9, 10}, {10, 11}};
        int[] b = {1, 3, 7, 5, 4, 6, 9, 10, 10, 11};
        IntListList A = IntListList.list(a);
        IntList B = IntList.list(b);
        assertEquals(A, Lists.naturalRuns(B));
        assertEquals(null, Lists.naturalRuns(null));
        int[][] c = {{1, 2, 3, 4, 5}};
        int[] d = {1, 2, 3, 4, 5};
        IntListList C = IntListList.list(c);
        IntList D = IntList.list(d);
        assertEquals(C, Lists.naturalRuns(D));
    }


    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(ListsTest.class));
    }
}
