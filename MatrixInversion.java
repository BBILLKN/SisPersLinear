/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package matrixinversion;

/**
 *
 * @author BILL_0058
 */
import java.util.Arrays;

public class MatrixInversion {

    public static double[][] invert(double[][] A) {
        int n = A.length;
        double[][] I = new double[n][n];
        for (int i = 0; i < n; i++) {
            I[i][i] = 1;
        }
        
        for (int i = 0; i < n; i++) {
            double diag = A[i][i];
            for (int j = 0; j < n; j++) {
                A[i][j] /= diag;
                I[i][j] /= diag;
            }
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    double temp = A[j][i];
                    for (int k = 0; k < n; k++) {
                        A[j][k] -= temp * A[i][k];
                        I[j][k] -= temp * I[i][k];
                    }
                }
            }
        }
        return I;
    }

    public static double[] multiply(double[][] A, double[] B) {
        int n = A.length;
        double[] result = new double[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i] += A[i][j] * B[j];
            }
        }
        return result;
    }

    public static double[] solveUsingInverse(double[][] A, double[] B) {
        double[][] A_inv = invert(A);
        return multiply(A_inv, B);
    }

    public static void main(String[] args) {
        double[][] A = {
            {4, 3},
            {3, 2}
        };
        double[] B = {10, 7};

        double[] solution = solveUsingInverse(A, B);

        System.out.println("Solution using Matrix Inversion: " + Arrays.toString(solution));
    }
}

