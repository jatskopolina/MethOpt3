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

    private static int[] freeX = new int[c.length]; // numbers of the Xes, which are free
    private static int[] basisX = new int[b.length];
    private static double[][] arr = new double [b.length + 1][c.length + 1];

    private static int permittingColumn; // they are the indexes in arr!
    private static int permittingRow;

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
     * They are in the freeX and basisX arrays (the order is important!)
     *
     * There will be b.length of basis vars and c.length of free vars and also 1 not-x number.
     * The last line contains coefficients for the goal function.
     */
    private static void initTable() {
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
        for (int j = 1; j < c.length + 1; j++) {
            isMinimum = isMinimum && arr[b.length][j] >= 0;
        }
        return isMinimum;
    }

    private static void findTheBearingElement() {
        // find the permittingColumn
        double minAtQ = arr[b.length][1];
        permittingColumn = 1;
        for (int i = 2; i < c.length + 1; i++) {
            if (arr[b.length][i] < minAtQ) {
                minAtQ = arr[b.length][i];
                permittingColumn = i;
            }
        }

        // find the permitting row
        double minAtPermittingColumn = Double.MAX_VALUE;
        for (int i = 0; i < b.length; i++) {
            double tmp = arr[i][0] / arr[i][permittingColumn];
            if (arr[i][permittingColumn] < 0 && tmp < minAtPermittingColumn) {
                minAtPermittingColumn = tmp;
                permittingRow = i;
            }
        }

        // if there were no elements < 0 in the column
        if (minAtPermittingColumn == Double.MAX_VALUE) {
            System.out.println("Oh, sorry, it seems like there is no solution at all");
            System.exit(0);
        }
    }

    private static void reportTheSolution() {
        System.out.println("I have found the solution!");
        System.out.println("The goal function is " + arr[b.length][0]);
        System.out.println("At:");
        for(int i = 0; i < freeX.length; i++) {
            System.out.println("x" + freeX[i] + " = 0");
        }
        for(int i = 0; i < basisX.length; i++) {
            System.out.println("x" + basisX[i] + " = " + arr[i][0]);
        }
    }

    public static void main(String[] args) {
        initTable();

        while(!isTheGoalMinimum()) {
            findTheBearingElement();
            // TODO
        }
        reportTheSolution();
    }
}
