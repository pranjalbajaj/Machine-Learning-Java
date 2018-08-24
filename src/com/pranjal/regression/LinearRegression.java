package com.pranjal.regression;
public class LinearRegression {
	
	public static void main(String[] args){
		
		Dataset dataset = new Dataset();
		// get the entire data into dataFrame by calling loadDataset method
		DataFrame dataFrame = dataset.loadDataset("/home/pranjal/workspace/CourseraAssignment/src/com/pranjal/regression/ex1data2.txt", ",");
		
		try {
			// X matrix which contains features based on which prediction has to be performed
			Double[][] X = dataFrame.getSelectedColumnMatrix(dataFrame.getDataValues(),0, 1); 
			// Y matrix which contains the result y corresponding to x
			Double[][] y = dataFrame.getSelectedColumnMatrix(dataFrame.getDataValues(),2, 2);
			// Define a theta matrix based on no. of features used for prediction.
			Double[][] theta = dataFrame.getZeroMatrix(1, dataFrame.size(X, 2)+1);
			// get the transpose of theta
			Double[][] thetaTranspose = dataFrame.transposeMatrix(theta);
			// calculate the mean of X
			Double[][] mean = dataFrame.mean(X);
			// calculate std. deviation of X
			Double[][] stdDev = dataFrame.stdDeviation(X);
			// scale the features
			Double[][] x = featureScaling(X,dataFrame,mean,stdDev);
			// add additional column of all 1's to X to make it hypothesis compatible
			x = dataFrame.appendOnesAtFirstColumnIndex(x);
			// alpha is the learning rate
			double alpha = 0.01;
			// no. of iterations used to reduce gradient descent
			int iterations = 400;
			// method to evaluate gradient descent and minimize the cost function
			gradientDescent(x,y,thetaTranspose,alpha,iterations,dataFrame);
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method scales every feature used
	 * for prediction.
	 * scaling = (x - mean) /standardDevaiation 
	 * @param x
	 * @param dataFrame
	 * @param mean
	 * @param stdDev
	 * @return
	 */
	private static Double[][] featureScaling(Double[][] x, DataFrame dataFrame, Double[][] mean, Double[][] stdDev) {
		
		Double[][] scaledFeature = dataFrame.getZeroMatrix(dataFrame.size(x, 1), dataFrame.size(x, 2));
		for(int i=0; i<dataFrame.size(x,1); i++){
			for(int j=0; j<dataFrame.size(x, 2); j++){
				scaledFeature[i][j] = (x[i][j] - mean[0][j])/stdDev[0][j];
			}
		}
		
		return scaledFeature;
	}
	
	/**
	 * This method evaluates the gradient descent 
	 * and minimizes it.
	 * theta = theta - (1/m)*alpha*sum(((h-y).*x))
	 * @param x
	 * @param y
	 * @param thetaTranspose
	 * @param alpha
	 * @param iterations
	 * @param dataFrame
	 * @throws CustomException
	 */
	private static void gradientDescent(Double[][] x, Double[][] y, Double[][] thetaTranspose, double alpha,
			int iterations, DataFrame dataFrame) throws CustomException {

		// no. of training examples
		double m = dataFrame.size(y, 1);

		// loop to minimize the gradient descent
		for (int i = 0; i < iterations; i++) {

			/**
			 * Define a hypothesis h(x) = theta'*x
			 */
			Double[][] h = dataFrame.multiplyMatrices(x, thetaTranspose);

			// deviation of h from y
			Double[][] deviation = dataFrame.subtractMatrices(h, y);
			// loop to update entire theta matrix simultaneously
			for (int j = 0; j < dataFrame.size(thetaTranspose, 1); j++) {

				Double[][] vectorX = dataFrame.getSelectedColumnMatrix(x,j, j);
				// array which holds the sum of all the deviation after element wise multiplication with vectorX
				Double[][] tempArray = dataFrame
						.columnWiseSum(dataFrame.elementWiseMultiplyVectors(deviation, vectorX));

				double temp = tempArray[0][0];

				thetaTranspose[j][0] = thetaTranspose[j][0] - (1 / m) * alpha * temp;
			}
		}
		System.out.println(thetaTranspose[0][0]+","+thetaTranspose[1][0]+","+thetaTranspose[2][0]);
	}
}
