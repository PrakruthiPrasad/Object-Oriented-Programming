package edu.neu.csye6200.bg;

import java.util.ArrayList;

/**
 * BG Generation - Grows generations of stems and adds them to generation set
 * Implements singleton pattern to maintain same instance of generations across
 * the application
 * 
 * @author Prakruthi BS NUID: 001448042
 */

public class BGGeneration {

	public static ArrayList<BGStem> stemsList = new ArrayList<BGStem>(); // stemsList to hold list of stems
	private static BGGeneration bgGenerationInstance;

	private BGGeneration() {

	}

	/**
	 * Singleton pattern to maintain same instance of BGGeneration
	 * 
	 * @return
	 */
	public static BGGeneration getBGGenerationInstance() {
		if (bgGenerationInstance == null) {
			bgGenerationInstance = new BGGeneration();
		}
		return bgGenerationInstance;
	}

	/**
	 * Method to grow generation of stems using the input generations, stem length
	 * and rule angle
	 * 
	 * @param x
	 * @param y
	 * @param length
	 * @param angle
	 * @param depth
	 */
	public void growGeneration(double x, double y, double length, double angle, int depth) {
		BGRule rule = new BGRule(); // Calls selected rule
		rule.drawStem(x, y, length, angle, depth);

		createGenerationSet(); // Adds to generation set
	}

	/**
	 * Private method to store generations into a generation set
	 */
	private void createGenerationSet() {
		BGGenerationSet set = new BGGenerationSet();
		set.getGenerationSet().add(stemsList);
	}

	/**
	 * Print routine to display stems in the generation list
	 */
	public void displayStems() {
		for (BGStem st : getStemsList()) {
			st.showStem(st);
		}
	}

	public ArrayList<BGStem> getStemsList() {
		return stemsList;
	}

}
