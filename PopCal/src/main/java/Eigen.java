import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.impl.DenseDoubleMatrix2D;
import cern.colt.matrix.linalg.Algebra;
import com.sun.deploy.security.SelectableSecurityManager;

import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.*;

public class Eigen{
    public static double getEVals(double[][] m, int k, double[] x){
        List<Double> ans = new ArrayList<Double>(); //all positive eigenValues
        List<Double> vector = new ArrayList<Double>();
        Algebra algebra = new Algebra();
        //DoubleMatrix2D A = new DenseDoubleMatrix2D(m);
        //System.out.println("This is A,"+A);
        DoubleMatrix2D matrix;
        DoubleMatrix2D U;
        DoubleMatrix2D X = null;
        double sum = 0;
        for (int n = 0; n < k; n++) { //find norm
            for (int j = 0; j < k-1; j++) {
                sum += Math.pow(x[j],2);
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
                sum += u[p]*v[p];
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
            double[][] t = new double[1][6];
//            for (int z = 0; z < k-1; z++) {
//               t[0][z] = u[z];
//            }
            //DoubleMatrix2D matrix = new DenseDoubleMatrix2D(m);
            //DoubleMatrix2D i = new DenseDoubleMatrix2D(I);
            matrix = new DenseDoubleMatrix2D(temp);
//            matrix = new DenseDoubleMatrix2D(6,6);
//            for (int p = 0; p < k-1; p++) {
//                for (int z = 0; z < k-1; z++) {
//                    matrix.set(p,z,temp[p][z]);
//                }
//            }
            //System.out.println("This is temp,"+ Arrays.deepToString(temp));
            U = new DenseDoubleMatrix2D(6,1);
            for (int z = 0; z < k-1; z++) {
                U.set(z,0,u[z]);
            }
            //System.out.println("This is U," + U);
            X = algebra.solve(matrix, U);
            //System.out.println("This is X," + X);
        }
        double norm = 0;
        double total = 0;
        assert X != null;
        for (int g = 0; g < X.rows(); g++) {
            for (int h = 0; h < X.columns(); h++) {
                total += Math.pow(X.get(g,h),2);
            }
        }
        norm = sqrt(total);
        double[][] T = new double[X.rows()][1];
        for (int d = 0; d < k-1; d++) {
            for (int e = 0; e < k-1; e++) {
                T[d][0] = X.get(e,0)/norm;
            }
        }
        double[] c = new double[6];
        DoubleMatrix2D Temp = new DenseDoubleMatrix2D(T);
        for (int o = 0; o < Temp.rows(); o++) {
            c[o] = m[o][0]*Temp.get(0,0)+m[o][1]*Temp.get(1,0)+m[o][2]*Temp.get(2,0)+m[o][3]*Temp.get(3,0)+m[o][4]*Temp.get(4,0)+m[o][5]*Temp.get(5,0);
        }
        double lam = 0; //another lam
        for (int z = 0; z < 6; z++) {
            lam += Temp.get(z,0)*c[z];
        }
        return lam;
    }

    public static double getRandomDoubleBetweenRange(double min, double max){
        return (Math.random()*((max-min)+1))+min;
    }
    public static double getWantedEval(){
        double[] ans = new double[20];
        double[][] m = new double[][] {{10,-12,-6,5,-2,6},{5,-5,-4,2,4,5},{-1,0,76,5,-6,-1},{8,9,6,4,5,6},{4,5,7,-4,-6,2},{7,-3,4,2,6,8}};
        double[] x = new double[6];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 6; j++) {
                x[j] = getRandomDoubleBetweenRange(-0.3,0.45);
            }
            double lam = 0;
            lam = getEVals(m,7,x);
            ans[i] = lam;
        }
        for (int i = 0; i < ans.length; i++) {
            if (ans[i] > 0){
                return ans[i];
            }
        }
        return 0;
    }
    public static void main(String[] args) {
        System.out.println(getWantedEval());
    }
}