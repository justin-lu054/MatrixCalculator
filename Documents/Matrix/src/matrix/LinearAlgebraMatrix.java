/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;

/**
 *
 * @author Justin
 */
public class LinearAlgebraMatrix {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double[][] contentsA = {{2, 3, 4, 3}, {1, 1, -2 , 3}, {-2, -6, 4, 5} , {11, -15, 25, 19}};
        double[][] contentsB = {{1, 2, 3, 4}, {5, 6, 7, 8}, {2, 3, 2, 5}, {1, 3, 2, 1}}; 
        Matrix A = new Matrix(contentsA); 
        Matrix B = new Matrix(contentsB);
        System.out.println(A.multiply(B));
        System.out.println(A.transpose());
        System.out.println(A.determinant());
        System.out.println(A);
        System.out.println(A.inverse());
        System.out.println(A.excludeRowColMatrix(0, 0));
    }
    
}
