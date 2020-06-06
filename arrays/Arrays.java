package arrays;

/* NOTE: The file Arrays/Utils.java contains some functions that may be useful
 * in testing your answers. */

/** HW #2 */



/** Array utilities.
 *  @author Keitaro Murakami
 */
class Arrays {

    /* C1. */
    /** Returns a new array consisting of the elements of A followed by the
     *  the elements of B. */
    static int[] catenate(int[] A, int[] B) {
        if (A == null) {
            return B;
        } else if (B == null) {
            return A;
        }
        int[] result = new int[A.length + B.length];
        for (int i = 0; i < A.length; i += 1) {
            result[i] = A[i];
        }
        for (int i = 0; i < B.length; i += 1) {
            result[i + A.length] =  B[i];
        }
        return result;
    }

    /* C2. */
    /** Returns the array formed by removing LEN items from A,
     *  beginning with item #START. */
    static int[] remove(int[] A, int start, int len) {
        int[] result = new int[A.length - len];
        if (start < 0) {
            start += A.length;
        }
        for (int i = 0; i < start; i += 1) {
            result[i] = A[i];
        }
        for (int i = start + len; i < A.length; i += 1) {
            result[i - len] = A[i];
        }
        return result;
    }

    /* C3. */
    /** Returns the array of arrays formed by breaking up A into
     *  maximal ascending lists, without reordering.
     *  For example, if A is {1, 3, 7, 5, 4, 6, 9, 10}, then
     *  returns the three-element array
     *  {{1, 3, 7}, {5}, {4, 6, 9, 10}}. */
    static int[][] naturalRuns(int[] A) {
        if (A.length == 0) {
            int[][] n = {};
            return n;
        }
        int count = 1;
        int[][] result = new int[count][];
        while (A.length >= 1) {
            for (int j = 0; j < A.length; j += 1) {
                if (j == A.length - 1) {
                    result[result.length - 1] = A;
                    A = new int[]{};
                    break;
                }
                if (A[j] >= A[j + 1]) {
                    count += 1;
                    int[][] tmp = result;
                    result = new int[count][];
                    for (int i = 0; i < tmp.length; i += 1) {
                        result[i] = tmp[i];
                    }
                    int[] s = Utils.subarray(A, 0, j + 1);
                    A = remove(A, 0, s.length);
                    result[count - 2] = s;
                    break;
                }
            }
        }
        return result;
    }
}
