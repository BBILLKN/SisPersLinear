/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package croutdecomposition;

/**
 *
 * @author BILL_0058
 */
import java.util.Arrays;

public class CroutDecomposition {

    public static void croutDecomposition(double[][] A, double[][] L, double[][] U, int n) {
        for (int j = 0; j < n; j++) {
            U[j][j] = 1;
            for (int i = j; i < n; i++) {
                double sum = 0;
                for (int k = 0; k < j; k++) {
                    sum += L[i][k] * U[k][j];
                }
                L[i][j] = A[i][j] - sum;
            }
            for (int i = j + 1; i < n; i++) {
                double sum = 0;
                for (int k = 0; k < j; k++) {
                    sum += L[j][k] * U[k][i];
                }
                U[j][i] = (A[j][i] - sum) / L[j][j];
            }
        }
    }

    public static double[] forwardSubstitution(double[][] L, double[] B) {
        int n = L.length;
        double[] y = new double[n];
        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < i; j++) {
                sum += L[i][j] * y[j];
            }
            y[i] = B[i] - sum;
        }
        return y;
    }

    public static double[] backSubstitution(double[][] U, double[] y) {
        int n = U.length;
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0;
            for (int j = i + 1; j < n; j++) {
                sum += U[i][j] * x[j];
            }
            x[i] = (y[i] - sum) / U[i][i];
        }
        return x;
    }

    public static double[] solveUsingCrout(double[][] A, double[] B) {
        int n = A.length;
        double[][] L = new double[n][n];
        double[][] U = new double[n][n];

        croutDecomposition(A, L, U, n);

        double[] y = forwardSubstitution(L, B);
        return backSubstitution(U, y);
    }

    public static void main(String[] args) {
        double[][] A = {
            {4, 3},
            {3, 2}
        };
        double[] B = {10, 7};

        double[] solution = solveUsingCrout(A, B);

        System.out.println("Solution using Crout Decomposition: " + Arrays.toString(solution));
    }
}

