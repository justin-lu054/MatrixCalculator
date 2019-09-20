/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;

import java.text.DecimalFormat;
import java.util.Arrays;

/**
 *
 * @author Justin
 */
public class Matrix {    
    private final int numOfRows; 
    private final int numOfColumns; 
    private double[][] contents;
    
    public Matrix(double[][] contentArray) {
        numOfRows = contentArray.length; 
        numOfColumns = contentArray[0].length; 
        contents = new double[numOfRows][numOfColumns];
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfColumns; j++) {
                contents[i][j] = contentArray[i][j];
            }
        }
    }
    
    public Matrix(int numOfRows, int numOfColumns) {
        this.numOfRows = numOfRows;
        this.numOfColumns = numOfColumns; 
        contents = new double[numOfRows][numOfColumns]; 
    }
    
    public void setElement(int row, int col, double val) {
    	this.contents[row][col] = val; 
    }
    
    public double getElement(int row, int col) {
    	return this.contents[row][col]; 
    }
    
    public Matrix add(Matrix addMatrix) {
        Matrix result = new Matrix(this.numOfRows, this.numOfColumns);
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfColumns; j++) {
                result.contents[i][j] = this.contents[i][j] + addMatrix.contents[i][j];
            }
        }
        return result; 
    }
    
    public Matrix subtract(Matrix subtractMatrix) {
        Matrix result = new Matrix(this.numOfRows, this.numOfColumns); 
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfColumns; j++) {
                result.contents[i][j] = this.contents[i][j] - subtractMatrix.contents[i][j];
            }
        }
        return result; 
    }
    
    public Matrix scalarMultiply(double scalar) {
    	Matrix result = new Matrix(numOfRows, numOfColumns); 
    	for (int i = 0; i < numOfRows; i++) {
    		for (int j = 0; j < numOfColumns; j++) {
    			result.contents[i][j] = scalar * this.contents[i][j]; 
    		}    		
    	}
    	return result; 
    }
    
    public Matrix multiply(Matrix multiplyMatrix) {
        Matrix result = new Matrix(this.numOfRows, multiplyMatrix.numOfColumns);
        for (int i = 0; i < result.numOfRows; i++) {
            for (int j = 0; j < result.numOfColumns; j++) {
                for (int k = 0; k < this.numOfColumns; k++) {
                    result.contents[i][j] += (this.contents[i][k] * multiplyMatrix.contents[k][j]);
                }
            }
        }
        return result; 
    }
    
    public boolean equals(Matrix equalMatrix) {
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfColumns; j++) {
                if(this.contents[i][j] != equalMatrix.contents[i][j]) {
                    return false; 
                }
            }
        }
        return true; 
    }
    
    public Matrix getSubMatrix(int row, int col) {
    	Matrix submatrix = new Matrix(row, col); 
    	for (int i = 0; i < row; i++) {
    		for (int j = 0; j < col; j++) {
    			submatrix.contents[i][j] = this.contents[i][j]; 
    		}
    	}
    	return submatrix; 
    }
    
    public Matrix excludeRowColMatrix(int excludeRow, int excludeCol) {
    	Matrix exclusionMatrix = new Matrix(numOfRows - 1, numOfColumns - 1); 
    	
    	for (int i = 0, a = 0; i < numOfRows; i++) {
    		if (i != excludeRow) {
    			for (int j = 0, b = 0; j < numOfColumns; j++) {
    				if (j != excludeCol) {
    					exclusionMatrix.contents[a][b] = this.contents[i][j];
    					b++;
    				}
    			}
    			a++; 
    		}
    		
    	}
    	return exclusionMatrix; 
    }
    
    public Matrix transpose() {
    	Matrix transposed = new Matrix(numOfColumns, numOfRows);
    	for (int i = 0; i < numOfRows; i++) {
    		for (int j = 0; j < numOfColumns; j++) {
    			transposed.contents[j][i] = this.contents[i][j]; 
    		}
    	}
    	return transposed; 
    }
    
    public double determinant() {
   
    	
    	double result = 0.0;
    	if (numOfRows != numOfColumns) {
    		return Double.NaN;
    	}
    	
    	else if (numOfColumns == 1) {
    		result = this.contents[0][0]; 
    	}
    	
    	else if (numOfColumns == 2) {
    		result = (this.contents[0][0] * this.contents[1][1]) - (this.contents[0][1] * this.contents[1][0]);
    		
    	}
    	
    	else {
    		
    		for (int i = 0; i < numOfColumns; i++) {
    			Matrix cofactor = this.excludeRowColMatrix(0, i);
    			result += (Math.pow(-1, i + 2) * this.contents[0][i] * cofactor.determinant());
    		}
    		
    		
    	}
    	return result;
    	 
    	
    }
    
    public Matrix inverse() {
    	double determinant = this.determinant(); 
    	Matrix cofactorMatrix = new Matrix(numOfRows, numOfColumns); 
    	Matrix inverted = new Matrix(numOfRows, numOfColumns); 
    	if (numOfRows != numOfColumns || determinant == 0) {
    		return null; 
    	}
    	
    	else {
    		for (int i = 0; i < numOfRows; i++) {
    			for (int j = 0; j < numOfColumns; j++) {
    				cofactorMatrix.contents[i][j] = this.excludeRowColMatrix(i, j).determinant();
    				cofactorMatrix.contents[i][j] *= Math.pow(-1, i + j + 2); 
    			}
    		}
    		inverted = cofactorMatrix.transpose().scalarMultiply(1.0 / this.determinant());
    		
    	}
    	
    	return inverted; 
    }
    
       
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
    	String output = "";
        for (int i = 0; i < numOfRows; i++) {	
        	output += "[ ";
        	for (int j = 0; j < numOfColumns; j++) {
        		output += df.format(this.contents[i][j]);
        		if (j != numOfColumns - 1) {
        			output += ","; 
        		}
            }
        	output += "]";
        	output += "\n";
        }
        return output; 
    }
    
    
    
    
    
           
}
