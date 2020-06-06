package arrays;


import org.junit.Test;
import static org.junit.Assert.*;

/** Test for Arrays
 *  @author Keitaro Murakami
 */

public class ArraysTest {
    @Test
    public void testCatenate() {
        int[] a1 = {1, 3, 7};
        int[] a2 = {5, 4};
        int[] A = Arrays.catenate(a1, a2);
        assertArrayEquals(A, new int[]{1, 3, 7, 5, 4});
        assertArrayEquals(Arrays.catenate(a1, null), a1);
        assertArrayEquals(Arrays.catenate(null, a1), a1);
        assertArrayEquals(Arrays.catenate(null, null), null);
    }

    @Test
    public void testRemove() {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8};
        assertArrayEquals(new int[]{1, 5, 6, 7, 8}, Arrays.remove(a, 1, 3));
        int[] b = {1, 2, 3, 4, 5, 6, 7, 8};
        assertArrayEquals(b, Arrays.remove(a, 3, 0));
        assertArrayEquals(new int[]{1, 5, 6, 7, 8}, Arrays.remove(a, -7, 3));
    }

    @Test
    public void testNaturalRuns() {
        int[][] a = {{1, 3, 7}, {5}, {4, 6, 9, 10}, {10, 11}};
        int[] b = {1, 3, 7, 5, 4, 6, 9, 10, 10, 11};
        assertArrayEquals(a, Arrays.naturalRuns(b));
        int[][] c = {{1, 3, 5, 6, 9, 10, 11}};
        int[] d = {1, 3, 5, 6, 9, 10, 11};
        assertArrayEquals(c, Arrays.naturalRuns(d));
        int[][] e = {{21}, {16}, {14}, {13}, {12}, {10}, {9}, {8}, {5}, {4}};
        int[] f = {21, 16, 14, 13, 12, 10, 9, 8, 5, 4};
        assertArrayEquals(e, Arrays.naturalRuns(f));
        int[][] g = {};
        int[] h = {};
        assertArrayEquals(g, Arrays.naturalRuns(h));
        int[][] i = {{1}};
        int[] j = {1};
        assertArrayEquals(i, Arrays.naturalRuns(j));
    }

    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(ArraysTest.class));
    }
}
