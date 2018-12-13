public class Main {
    private static double[][] A = { // important!!! columns amount is c.length, rows amount is b.length
            {-1, 3, 0, 2, 1},
            {2, -1, 1, 2, 3},
            {1, -1, 2, 1, 0}
    };

    // column!
    private static double[] b = {
            1,
            2,
            4
    };

    // column!
    private static double[] c = {
            -1,
            -3,
            2,
            1,
            4
    };

    private static int[] freeX; // numbers of the Xes, which are free
    private static int[] basisX;
    private static double[][] arr;

    /*
     * Table structure:
     *                         n   freeX[0]   freeX[1]   ........   freeX[c.length - 1]
     *        basisX[0]     = .. + ........ + ........ + ........ + ...................
     *        basisX[1]     = .. + ........ + ........ + ........ + ...................
     *        .........     = .. + ........ + ........ + ........ + ...................
     * basisX[b.length - 1] = .. + ........ + ........ + ........ + ...................
     *           Q          = .. + ........ + ........ + ........ + ...................
     *
     * x1 - x5 are free now, x6 - x8 are basis (is is just an example, it depends on b.length and c.length)
     * they are in the freeX and basisX arrays (the order is important!)
     */
    private static void initTable() {
        // there will be b.length of basis vars and c.length of free vars and also 1 nor-x number.
        // The last line contains coefficients for the goal function.
        arr = new double [b.length + 1][c.length + 1];

        // at the beginning we have additional vars at the basis, others are free
        // first (№0) column contains free numbers (in the beginning they are b)
        for (int i = 0; i < b.length; i++) {
            arr[i][0] = b[i];
        }

        for (int i = 0; i < b.length; i++) {
            for (int j = 1; j < c.length + 1; j++) {
                arr[i][j] = -A[i][j];
            }
        }

        arr[b.length][0] = 0;
        for (int j = 0; j < c.length; j++) {
            arr[b.length][j + 1] = c[j];
        }

        for (int j = 0; j < c.length; j++) {
            freeX[j] = j + 1;
        }

        for (int i = 0; i < b.length; i++) {
            basisX[i] = c.length + i + 1;
        }

    }

    private static boolean isTheGoalMinimum() {
        boolean isMinimum = true;
        for(int j = 1; j < c.length + 1; j++) {
            isMinimum = isMinimum && arr[b.length][j] >= 0;
        }
        return isMinimum;
    }



    public static void main(String[] args) {
        initTable();

        while(!isTheGoalMinimum()) {

        }
    }
}
