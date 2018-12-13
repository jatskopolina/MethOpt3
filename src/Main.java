public class Main {
    static double[][] A = { // important!!! columns amount is c.length, rows amount is b.length
            {-1, 3, 0, 2, 1},
            {2, -1, 1, 2, 3},
            {1, -1, 2, 1, 0}
    };

    // column!
    static double[] b = {
            1,
            2,
            4
    };

    // column!
    static double c[] = {
            -1,
            -3,
            2,
            1,
            4
    };

    /*
     * Table structure:
     *       n   x1   x2   x3   x4   x5
     * x6 = .. + .. + .. + .. + .. + ..
     * x7 = .. + .. + .. + .. + .. + ..
     * x8 = .. + .. + .. + .. + .. + ..
     * Q  = .. + .. + .. + .. + .. + ..
     * x1 - x5 are free now, x6 - x8 are basis (is is just an example, it depends on b.length and c.length
     */
    static double[][] initTable() {
        // there will be b.length of basis vars and c.length of free vars and also 1 nor-x number.
        // The last line contains coefficients for the goal function.
        double arr[][] = new double [b.length + 1][c.length + 1];

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
        return arr;
    }

    public static void main(String[] args) {
        // now it is like c * x -> min, A * x <= b, x >= 0. Let it become A * x = b by adding several vars
        double [] x = new double [c.length + b.length];

        double arr[][] = initTable();
    }
}
