package edu.neu.csye6200.bg;

import java.util.ArrayList;

/**
 * BGGeneration set class that maintains a list of generations
 * 
 * @author Prakruthi BS NUID: 001448042
 *
 */

public class BGGenerationSet {

	private ArrayList<ArrayList<BGStem>> generationSet = new ArrayList<ArrayList<BGStem>>(); // Arra list to hold list
																								// of generations

	/**
	 * Constructor
	 */
	public BGGenerationSet() {
	}

	public ArrayList<ArrayList<BGStem>> getGenerationSet() {
		return generationSet;
	}

	public void setGenerationSet(ArrayList<ArrayList<BGStem>> generationSet) {
		this.generationSet = generationSet;
	}

}
