package com.pranjal.regression;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Dataset {

	/**
	 * reads the dataset file and loads the data into a dataframe object
	 * 
	 * @param fileName
	 * @param delimitter
	 */
	public DataFrame loadDataset(String fileName, String delimiter) {

		// arrayList of contents returned after reading dataset file
		ArrayList<String[]> contentList = readDatasetFile(fileName, delimiter);
		// the size of contentList gives the no. of rows
		int rows = contentList.size();
		// the size of string array inside contentList give the no. of columns
		/**
		 * logically the no. of columns should not change along each row,
		 * therefore the no. of columns across each row must be same as the no
		 * of columns in first row, hence reading the no. of columns from first
		 * row only.
		 */
		int columns = contentList.get(0).length;
		// store the contents into object array.
		Object[][] dataValues = storeContentIntoDataArray(contentList, rows, columns);
		// create a dataframe object and initialize the values via constructor
		DataFrame dataFrame = new DataFrame(dataValues, rows, columns);
		// return dataframe object
		return dataFrame;
	}

	/**
	 * store the data into object array by reading its content from contentList
	 * 
	 * @param contentList
	 */
	private Object[][] storeContentIntoDataArray(ArrayList<String[]> contentList, int rows, int columns) {

		// initialize the data array
		Object[][] data = new Object[rows][columns];
		// loop to iterate through rows
		for (int i = 0; i < rows; i++) {

			// loop to iterate through columns
			for (int j = 0; j < columns; j++) {
				// insert data into object array
				data[i][j] = contentList.get(i)[j];
			}
		}

		return data;
	}

	/**
	 * fetches entire row and then stores each column
	 * 
	 * @param fileName
	 * @param delimiter
	 * @return
	 */
	private ArrayList<String[]> readDatasetFile(String fileName, String delimiter) {

		BufferedReader buffer = null;
		// stores the splitted data row wise
		ArrayList<String[]> contentList = null;

		try {
			String line;
			buffer = new BufferedReader(new FileReader(fileName));
			// ArrayList to hold each row of the dataset
			contentList = new ArrayList<String[]>();
			// loop through each line of file
			while ((line = buffer.readLine()) != null) {
				// split the data with respect to delimiter
				String[] splitData = line.split(delimiter);
				// add the splitData array to arraylist
				contentList.add(splitData);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (buffer != null)
					buffer.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return contentList;

	}
}
