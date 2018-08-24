package com.pranjal.regression;

public class DataFrame {

	// object array to store dataValues
	private Object[][] dataValues = null;
	// no. of rows/training sets in dataset
	private int rows;
	// no. of columns/features in dataset
	private int columns;

	// constructor to initialize instance variables
	public DataFrame(Object[][] dataValues, int rows, int columns) {
		super();
		this.dataValues = dataValues;
		this.rows = rows;
		this.columns = columns;
	}

	public Object[][] getDataValues() {
		return dataValues;
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	/**
	 * This method is used to get the submatrix from the entire data matrix This
	 * returns a matrix for selected columns for all rows
	 * 
	 * @param columnFrom
	 * @param columnTo
	 * @return
	 * @throws CustomException
	 */
	public Double[][] getSelectedColumnMatrix(Object[][] matrix,int columnFrom, int columnTo) throws CustomException {

		Double[][] subMatrix = null;
		// Necessary conditions to be checked for creation of subMatrix
		if (columnFrom >= 0 && columnTo >= 0 && columnFrom < size(matrix, 2) && columnTo < size(matrix, 2)
				&& columnTo >= columnFrom) {
			// create an object of subMatrix with all rows
			subMatrix = getZeroMatrix(size(matrix, 1), columnTo - columnFrom + 1);
			for (int i = 0; i < size(matrix, 1); i++) {
				int z = 0;
				for (int j = columnFrom; j <= columnTo; j++) {
					// set the values at respective index
					subMatrix[i][z] = Double.parseDouble((String) matrix[i][j]);
					z++;
				}
			}
		} else {
			throw new CustomException("Error in generating subMatrix due to illegal arguments");
		}
		return subMatrix;
	}
	
	/**
	 * This method is used to get the submatrix from the entire data matrix This
	 * returns a matrix for selected columns for all rows
	 * 
	 * @param columnFrom
	 * @param columnTo
	 * @return
	 * @throws CustomException
	 */
	public Double[][] getSelectedColumnMatrix(Double[][] matrix,int columnFrom, int columnTo) throws CustomException {

		Double[][] subMatrix = null;
		// Necessary conditions to be checked for creation of subMatrix
		if (columnFrom >= 0 && columnTo >= 0 && columnFrom < size(matrix, 2) && columnTo < size(matrix, 2)
				&& columnTo >= columnFrom) {
			// create an object of subMatrix with all rows
			subMatrix = getZeroMatrix(size(matrix, 1), columnTo - columnFrom + 1);
			for (int i = 0; i < size(matrix, 1); i++) {
				int z = 0;
				for (int j = columnFrom; j <= columnTo; j++) {
					// set the values at respective index
					subMatrix[i][z] = matrix[i][j];
					z++;
				}
			}
		} else {
			throw new CustomException("Error in generating subMatrix due to illegal arguments");
		}
		return subMatrix;
	}
	
	/**
	 * This method is used to get the submatrix from the entire data matrix This
	 * returns a matrix for selected columns for selected rows
	 * 
	 * @param columnFrom
	 * @param columnTo
	 * @return
	 * @throws CustomException
	 */
	public Double[][] getSelectedColumnMatrix(Object[][] matrix,int rowFrom, int rowTo, int columnFrom, int columnTo)
			throws CustomException {

		Double[][] subMatrix = null;
		// Necessary conditions to be checked for creation of subMatrix
		if (columnFrom >= 0 && columnTo >= 0 && columnFrom < size(matrix, 2) && columnTo < size(matrix, 2)
				&& columnTo >= columnFrom && rowFrom >= 0 && rowTo >= 0 && rowFrom < size(matrix, 1) && rowTo < size(matrix, 1)
				&& rowTo >= rowFrom) {
			// create an object of subMatrix with selected no. of rows
			subMatrix = getZeroMatrix(rowTo - rowFrom + 1, columnTo - columnFrom +1);
			for (int i = rowFrom; i <= rowTo; i++) {
				for (int j = columnFrom; j <= columnTo; j++) {
					// set the values at respective index
					subMatrix[i][j] = Double.parseDouble((String) matrix[i][j]);
				}
			}
		} else {
			throw new CustomException("Error in generating subMatrix due to illegal arguments");
		}
		return subMatrix;
	}

	/**
	 * This method is used to get the submatrix from the entire data matrix This
	 * returns a matrix with all columns and selected rows
	 * 
	 * @param columnFrom
	 * @param columnTo
	 * @return
	 * @throws CustomException
	 */
	public Double[][] getSelectedRowMatrix(Object[][] matrix,int rowFrom, int rowTo) throws CustomException {

		Double[][] subMatrix = null;
		// Necessary conditions to be checked for creation of subMatrix
		if (rowFrom >= 0 && rowTo >= 0 && rowFrom < size(matrix, 1) && rowTo < size(matrix, 1) && rowTo >= rowFrom) {
			// create an object of subMatrix with selected no .of rows and all
			// columns
			subMatrix = getZeroMatrix(rowTo - rowFrom + 1, size(matrix, 2));
			for (int i = rowFrom; i <= rowTo; i++) {
				for (int j = 0; j < size(matrix, 2); j++) {
					// set the values at respective index
					subMatrix[i][j] = Double.parseDouble((String) matrix[i][j]);
				}
			}
		} else {
			throw new CustomException("Error in generating subMatrix due to illegal arguments");
		}
		return subMatrix;
	}

	/**
	 * This method appends an additional column of all "1's" at the first column
	 * index.
	 * 
	 * @param matrix
	 * @return
	 */
	public Double[][] appendOnesAtFirstColumnIndex(Double[][] matrix) {

		// initialize the subMatrix with the no. of rows of argument matrix
		Double[][] oneAppendedMatrix = getZeroMatrix(size(matrix, 1), size(matrix, 2)+1);
		// loop through the no. of rows of argument matrix
		for (int i = 0; i < matrix.length; i++) {
			// loop through the (no. of columns + 1) time because an additional
			// column of "1's" is added
			for (int j = 0; j < matrix[0].length + 1; j++) {

				// set 1 if j is 0 because we have to append 1 in first column
				if (j == 0) {
					oneAppendedMatrix[i][j] = 1.0;
				} else {
					oneAppendedMatrix[i][j] = matrix[i][j - 1];
				}
			}
		}

		return oneAppendedMatrix;
	}

	/**
	 * This method return zero matrix of rows x columns dimension
	 * 
	 * @param rows
	 * @param columns
	 * @return
	 */
	public Double[][] getZeroMatrix(int rows, int columns) {

		// initialize the subMatrix with rows in argument
		Double[][] zeroMatrix = new Double[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				zeroMatrix[i][j] = 0.0;
			}
		}

		return zeroMatrix;
	}
	
	/**
	 * This method transposes a matrix
	 * @param matrix
	 * @return
	 */
	public Double[][] transposeMatrix(Double[][] matrix){
		
		/**
		 *  initialize the transposedMatrix with columns of matrix in argument as rows
		 *   and rows of matrix in argument as columns
		 */
		Double[][] transposedMatrix = getZeroMatrix(size(matrix,2),size(matrix, 1));
		
		for (int i = 0; i < matrix[0].length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				transposedMatrix[i][j] = matrix[j][i];
			}
		}
		
		return transposedMatrix;
	}
	
	/**
	 * Method to multiply two matrices
	 * @param matrix1
	 * @param matrix2
	 * @return
	 * @throws CustomException
	 */
	public Double[][] multiplyMatrices(Double[][] matrix1, Double[][] matrix2) throws CustomException{
		
		int rows1 = size(matrix1, 1);
		int rows2 = size(matrix2, 1);
		int cols1 = size(matrix1, 2);
		int cols2 = size(matrix2, 2);
	
		Double[][] resultMatrix = null;
		
		if (cols1 == rows2) {
			
			resultMatrix = this.getZeroMatrix(rows1, cols2);

			for (int m = 0; m < rows1; m++) {
				for (int i = 0; i < cols2; i++) {
					for (int j = 0; j < cols1; j++) {

						resultMatrix[m][i]+=matrix1[m][j] * matrix2[j][i];
					}
				}
			}
		}
		else
		{
			throw new CustomException("Matrix Multiplication not possible because no. of columns of first matrix do not match with no. of rows of second matrix");
		}
		
		return resultMatrix;
	}
	
	/**
	 * This method squares each element of matrix
	 * 
	 * @param matrix
	 * @return
	 */
	public Double[][] elementWiseSquare(Double[][] matrix) {

		Double[][] elementSquaredMatrix = getZeroMatrix(size(matrix,1), size(matrix, 2));

		for (int i = 0; i < size(matrix, 1); i++) {
			for (int j = 0; j < size(matrix, 2); j++) {
				// use the pow function to calculate squared value
				elementSquaredMatrix[i][j] = Math.pow(matrix[i][j], 2);
			}
		}

		return elementSquaredMatrix;
	}

	/**
	 * This method returns array of length equal to the no. of rows in argument
	 * matrix containing sum of each row at the respective row index. rows
	 * 
	 * @param matrix
	 * @return
	 */
	public Double[][] rowWiseSum(Double[][] matrix) {

		Double[][] rowSummedMatrix = getZeroMatrix(matrix.length, 1);

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				rowSummedMatrix[i][0] += matrix[i][j];
			}
		}

		return rowSummedMatrix;
	}

	/**
	 * This method returns array of length 1 and sub array of no. of columns
	 * equal to the no. of columns in argument matrix containing sum of each
	 * column at the respective column index. rows
	 * 
	 * @param matrix
	 * @return
	 */
	public Double[][] columnWiseSum(Double[][] matrix) {

		Double[][] columnSummedMatrix = getZeroMatrix(1, matrix[0].length);

		for (int i = 0; i < matrix[0].length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				columnSummedMatrix[0][i] += matrix[j][i];
			}
		}

		return columnSummedMatrix;
	}
	
	/**
	 * This method adds two matrices
	 * @param matrix1
	 * @param matrix2
	 * @return
	 * @throws CustomException
	 */
	public Double[][] addMatrices(Double[][] matrix1, Double[][] matrix2) throws CustomException{
		
		int rows1 = size(matrix1, 1);
		int rows2 = size(matrix2, 1);
		int cols1 = size(matrix1, 2);
		int cols2 = size(matrix2, 2);
		Double[][] addedMatrix = null; 
		if(rows1 == rows2 && cols1 == cols2){
			
			addedMatrix = getZeroMatrix(rows1, cols1);
					
			for(int i=0; i<rows1; i++){
				for(int j=0; j<cols1; j++){
					addedMatrix[i][j] = matrix1[i][j] + matrix2[i][j];
				}
			}
		}
		else
		{
			throw new CustomException("addition can be performed only when both the matrices are square matrix");
		}
		
		return addedMatrix;
	}
	
	/**
	 * This method subtracts two matrices
	 * @param matrix1
	 * @param matrix2
	 * @return
	 * @throws CustomException
	 */
	public Double[][] subtractMatrices(Double[][] matrix1, Double[][] matrix2) throws CustomException{
		
		int rows1 = size(matrix1, 1);
		int rows2 = size(matrix2, 1);
		int cols1 = size(matrix1, 2);
		int cols2 = size(matrix2, 2);
		Double[][] subtractedMatrix = null; 
		if(rows1 == rows2 && cols1 == cols2){
			
			subtractedMatrix = getZeroMatrix(rows1, cols1);
					
			for(int i=0; i<rows1; i++){
				for(int j=0; j<cols1; j++){
					subtractedMatrix[i][j] = matrix1[i][j] - matrix2[i][j];
				}
			}
		}
		else
		{
			throw new CustomException("addition can be performed only when both the matrices are square matrix");
		}
		
		return subtractedMatrix;
	}
	
	/**
	 * This method returns a resultant vector after multiplying
	 * two vectors
	 * @param vector1
	 * @param vector2
	 * @return
	 */
	public Double[][] elementWiseMultiplyVectors(Double[][] vector1, Double[][] vector2){
		
		Double[][] vector = null;
		
		int rows1 = size(vector1, 1);
		int cols1 = size(vector1, 2);
		int rows2 = size(vector2, 1);
		int cols2 = size(vector2, 2);
		
		// element wise multiplication can be performed only when both vectors are of equal size
		if(rows1==rows2 && cols1==cols2){
			
			vector = getZeroMatrix(rows1, cols1);
			
			for(int i=0;i<rows1; i++){
				for(int j=0; j<cols1; j++){
					
					vector[i][j] = vector1[i][j] * vector2[i][j];
				}
			}
		}
		
		return vector;
	}
	
	/**
	 * This method returns a integer value which 
	 * represents the size of the dimension denoted
	 * by axis.
	 * if axis = 1 -> row
	 * if axis = 2 -> column
	 * @param matrix
	 * @return
	 */
	public int size(Object[][] matrix, int axis){
		
		int dimensionSize = 0;
		if(axis == 1){
			
			dimensionSize = matrix.length;
		}
		else if(axis ==2){
			dimensionSize = matrix[0].length;
		}
		
		return dimensionSize;
	}
	
	/**
	 * This method returns a single row vector
	 * with first column containing no. of rows
	 * and second column containing no. of cols
	 * of argument matrix.
	 * @param matrix
	 * @return
	 */
	public Object[][] size(Object[][] matrix){
		
		int rows = matrix.length;
		int columns = matrix[0].length;
		Object[][] singleRowVector = new Object[1][2];
		
		singleRowVector[0][0] = rows;
		singleRowVector[0][1] = columns;
		
		return singleRowVector;
	}
	
	/**
	 * This method calculates the column wise mean
	 * of matrix 
	 * @param matrix
	 * @return
	 */
	public Double[][] mean(Double[][] matrix){
		// calculate the column wise sum
		Double[][] columnWiseSum = columnWiseSum(matrix);
		
		int cols = size(columnWiseSum, 2);
		double rows = size(matrix, 1);
		// initialize the mean array 
		Double[][] mean = getZeroMatrix(1, cols);
		
		for(int i=0; i<cols; i++){
			mean[0][i] = columnWiseSum[0][i]/rows;
		}
		
		return mean;
	}
	
	/**
	 * This method returns a vector of std. deviation
	 * @param matrix
	 * @return
	 * @throws CustomException
	 */
	public Double[][] stdDeviation(Double[][] matrix) throws CustomException{
		
		double n = size(matrix, 1);
		Double[][] mean = mean(matrix);
		Double[][] stdDev = getZeroMatrix(1, size(matrix, 2));
		Double[][] temp = getZeroMatrix(size(matrix,1), size(matrix, 2));
		for(int i=0; i<size(matrix,1); i++){
			for(int j=0; j<size(matrix, 2); j++){
				temp[i][j] = matrix[i][j] - mean[0][j];
			}
		}
		
		temp = elementWiseSquare(temp);
		stdDev = columnWiseSum(temp);
		
		for(int i=0; i<size(stdDev,1); i++){
			for(int j=0; j<size(stdDev, 2); j++){
				stdDev[i][j] = stdDev[i][j]/(n-1);
				stdDev[i][j] = Math.sqrt(stdDev[i][j]);
			}
		}
		
		return stdDev;
		
	}
}
