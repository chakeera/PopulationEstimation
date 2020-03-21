import java.util.Arrays;
class decomposition {
    static int MAX = 100;
    static String s="";
    static double[][] luDecomposition(double [][]mat, int n){
        double [][]lower = new double[n][n];
        double [][]upper = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int k = i; k < n; k++) {
                int sum = 0;
                for (int j = 0; j < i; j++)
                    sum += (lower[i][j] * upper[j][k]);
                upper[i][k] = mat[i][k] - sum;
            }
            for (int k = i; k < n; k++) {
                if (i == k)
                    lower[i][i] = 1;
                else{
                    int sum = 0;
                    for (int j = 0; j < i; j++) {
                        sum += (lower[k][j] * upper[j][i]);
                    }
                    lower[k][i] = (mat[k][i] - sum) / upper[i][i];
                }
            }
        }
        return upper;
    }
    static String setw(int noOfSpace) {
        s="";
        for(int i = 0;i<noOfSpace;i++) {
            s += " ";
        }
        return s;
    }
    public static void main(String arr[]) {
        double mat[][] = { { 2, -1, -2 },
                { -4, 6, 3 },
                { -4, -2, 8 } };
       luDecomposition(mat, 3);
    }
}

/* This code contributed by PrinciRaj1992 */

