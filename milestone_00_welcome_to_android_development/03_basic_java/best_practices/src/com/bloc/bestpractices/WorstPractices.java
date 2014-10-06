package com.bloc.bestpractices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WorstPractices extends Object {

	// EDIT BELOW

	public static void main(String [] args) {
		int magicNumber = WorstPractices.generateMagicNumber(false);
		magicNumber *= 5;
		if (magicNumber > 18) {
			while(magicNumber > 0) {
				magicNumber--;
			}
		}
	}

	/* generateNumber
	* this method takes in a single parameter, a boolean. Using a very 
	* elaborate and complex algorithm, it calculate a magic number.
	* bool: a seed which helps generate the magic number
	* returns: a magical number */
	private static int generateMagicNumber(boolean bool) {
		// Start off with one of these
		int baseInt = bool ? 34 : 21;
		float fairyNumber = .5f; // This is the number that makes the magic
		for (int i = 0; i < baseInt; i++) { 
			fairyNumber *= baseInt;
		}	 
		return (int) fairyNumber * baseInt;
	}

	// STOP EDITING
}
