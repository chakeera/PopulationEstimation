import cern.colt.matrix.DoubleMatrix1D;
import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.impl.DenseDoubleMatrix1D;
import cern.colt.matrix.impl.DenseDoubleMatrix2D;
import cern.colt.matrix.linalg.Algebra;
import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.*;

public class Eigen{
    public List<Double> getEVals(double[][] m, int k){
        List<Double> ans = new ArrayList<Double>(); //all positive eigenValues
        List<Double> vector = new ArrayList<Double>();
        Algebra algebra = new Algebra();
        DoubleMatrix2D matrix;
        DoubleMatrix2D U;
        DoubleMatrix2D X = null;
        double[] x = new double[k-1];
        double sum = 0;
        for (int n = 0; n < k; n++) { //find norm
            for (int j = 0; j < k-1; j++) {
                sum += x[j];
            }
            double norm = sqrt(sum);
            sum = 0; //reset some for later use
            double[]u = new double[k-1];
            for (int o = 0; o < k-1; o++) { //get u
                u[o] = x[o]/norm;
            }
            double[] v = new double[k-1]; //start of rayleigh quotient
            for (int l = 0; l < k-1; l++) { //first dot product not sure if it correct waiting for chucky
                v[l] = m[l][0]*u[0]+m[l][1]*u[1]+m[l][2]*u[2]+m[l][3]*u[3]+m[l][4]*u[4]+m[l][5]*u[5];
            }
            for (int p = 0; p < k-1; p++) { //get lam
                sum += u[k]*v[k];
            }
            double lam = sum; //end of rayleigh quotient
            sum = 0; //reset some for later use
            double[][] I = new double[k-1][k-1]; //lam * I
            for (int z = 0; z < k-1; z++) {
                for (int y = 0; y < k-1; y++) {
                    if (z == y){
                        I[z][y] = lam;
                    }
                }
            }
            double[][] temp = new double[k-1][k-1];
            for (int w = 0; w < k-1; w++) { // m-lam*I
                for (int s = 0; s < k-1; s++) {
                    temp[w][s] = m[w][s] - I[w][s];
                }
            }
            double[][] t = new double[1][1];
            t[0] = u;
            //DoubleMatrix2D matrix = new DenseDoubleMatrix2D(m);
            //DoubleMatrix2D i = new DenseDoubleMatrix2D(I);
            matrix = new DenseDoubleMatrix2D(temp);
            U = new DenseDoubleMatrix2D(t);
            X = algebra.solve(matrix, U);
        }
        double norm = 0;
        double max = 0;
        assert X != null;
        return ans;
    }
    public double norm1(DoubleMatrix2D A) {
        double max = 0;
        for (int column = A.columns(); --column >=0; ) {
            max = Math.max(max, norm1(A.viewColumn(column)));
        }
        return max;
    }
    public static void main(String[] args) {
        System.out.println(100);
    }
}