package Math;

import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.impl.DenseDoubleMatrix2D;
import cern.colt.matrix.linalg.Algebra;
import com.sun.deploy.security.SelectableSecurityManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static java.lang.Math.*;

public class Eigen{
    static double lambda;
    static double[] p = {1,1,1,1,1,1};
    public static double getEVals(double[][] m, int k, double[] x){
        Algebra algebra = new Algebra();
        DoubleMatrix2D matrix;
        DoubleMatrix2D U;
        DoubleMatrix2D X = null;
        double sum = 0;
        for (int n = 0; n < k; n++) {
            for (int j = 0; j < k-1; j++) {
                sum += Math.pow(x[j],2);
            }
            double norm = sqrt(sum);
            sum = 0;
            double[]u = new double[k-1];
            for (int o = 0; o < k-1; o++) {
                u[o] = x[o]/norm;
            }
            double[] v = new double[k-1];
            for (int l = 0; l < k-1; l++) {
                v[l] = m[l][0]*u[0]+m[l][1]*u[1]+m[l][2]*u[2]+m[l][3]*u[3]+m[l][4]*u[4]+m[l][5]*u[5];
            }
            for (int p = 0; p < k-1; p++) {
                sum += u[p]*v[p];
            }
            double lam = sum;
            sum = 0;
            double[][] I = new double[k-1][k-1];
            for (int z = 0; z < k-1; z++) {
                for (int y = 0; y < k-1; y++) {
                    if (z == y){
                        I[z][y] = lam;
                    }
                }
            }
            double[][] temp = new double[k-1][k-1];
            for (int w = 0; w < k-1; w++) {
                for (int s = 0; s < k-1; s++) {
                    temp[w][s] = m[w][s] - I[w][s];
                }
            }
            double[][] t = new double[1][6];
            matrix = new DenseDoubleMatrix2D(temp);
            U = new DenseDoubleMatrix2D(6,1);
            for (int z = 0; z < k-1; z++) {
                U.set(z,0,u[z]);
            }
            X = algebra.solve(matrix, U);
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
        double[][] m = new double[][] {{10,-12,-6,5,-2,6},{5,-5,-4,2,4,5},{-1,0,76,5,-6,-1},{8,9,6,4,5,6},{4,5,7,-4,-6,2},{7,-3,4,2,6,8}};// change to matrix from db
        double[] x = new double[6];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 6; j++) {
                x[j] = getRandomDoubleBetweenRange(-0.3,0.45);
            }
            double lam = 0;
            lam = getEVals(m,7,x);
            ans[i] = lam;
        }
        for (double an : ans) {
            if (an > 0) {
                return an;
            }
        }
        return 0;
    }
    public static double[] AxP(double[][] a, double[] p){
        double[] q = new double[p.length];
        double s;
        for (int i=0;i<p.length;i++){
            q[i] = 0;
            for (int j=0;j<p.length;j++)
                q[i] = q[i] + a[i][j]*p[j];
        }
        return q;
    }
    public static double[] PxL(double[] p, double lambda){
        double[] q = new double[p.length];
        for (int i=0;i<p.length;i++)
            q[i] = p[i]*lambda;
        return q;
    }
    public static double[] getEVec(double[][] matrix){
        double[][] a = matrix; //change to matrix from sql
        lambda = getWantedEval();
        double[] q;
        q = p;
        p = AxP(a,q);
        p = PxL(p,lambda);
        return p;
    }
    public double[] answer(List<String> offSpring, List<String> peopleSurvive){
        for (String s: peopleSurvive)
        {
            System.out.println(s);
        }
        double[][] m = {{0,0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0}}; //change to matrix from sql

        for (int i = 1; i < peopleSurvive.size(); i++)
        {
            m[i][i-1] = Double.parseDouble(peopleSurvive.get(i-1))/Double.parseDouble(peopleSurvive.get(i));
        }

        for(int i = 1; i< offSpring.size();i++){
            m[0][i]= Double.parseDouble(offSpring.get(i-1));
        }
//        for printing matrix
        for (double[] i: m)
        {
            for (double j: i)
            {
                System.out.print(j);
                System.out.print(" ");
            }
            System.out.println();
        }
        double[] u = getEVec(m);
        double[] v = new double[u.length];
        for (int l = 0; l < m.length; l++) {
            v[l] = m[l][0]*u[0]+m[l][1]*u[1]+m[l][2]*u[2]+m[l][3]*u[3]+m[l][4]*u[4]+m[l][5]*u[5];
        }
        return v;
    }

//    public static void main(String[] args) {
//        Eigen eigen = new Eigen();
//        List<String> pop = new ArrayList<String>();
//        {
//            pop.add("235662342");
//            pop.add("345768974");
//            pop.add("465773453");
//            pop.add("353632245");
//            pop.add("456463423");
//            pop.add("647679684");
//        }
//        List<String> off = new ArrayList<String>();
//        {
//            off.add("123");
//            off.add("123");
//            off.add("123");
//            off.add("123");
//            off.add("123");
//            off.add("123");
//        }
//        double[] answer = eigen.answer(off, pop);
//        System.out.println("Answer:\n");
//        for (double i: answer)
//        {
//            System.out.print(i);
//            System.out.println(" ");
//        }
//    }
}