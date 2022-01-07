package edu.neu.csye6200.bg;

import java.util.ArrayList;
import java.util.Stack;

/**
 * BGRule class that implements algorithm that governs stem growth
 * 
 * @author Prakruthi BS 
 * NUID: 001448042
 */
public class BGRule {

	BGGeneration generation = BGGeneration.getBGGenerationInstance();
	private double radians = Math.PI / 180;

	/**
	 * Constructor
	 */
	public BGRule() {

	}

	public void drawStem(double x, double y, double length, double angle, int generationDepth) {
		int depth = 0;
		// Create base stem
		BGStem stem = new BGStem(0, 0, 0, 0, length, radians, depth);

		// Insert base stem to generation list
		generation.getStemsList().add(stem);

		// Calculate y-coordinate of end point for the base stem (x coordinate will be
		// the same for end point of base stem)
		double y1 = y + (length * Math.sin(stem.getAngle()));

		// Grow further child stems
		drawChildStems(x, y1, length, depth, angle, generationDepth);
	}

	public void drawChildStems(double a, double b, double length, int depth, double angle, int generationDepth) {

		// Calculate end coordinates of child stem. Using math functions to determine
		// the rotation of child stems based on angles
		double x2 = a + length * Math.sin(angle);
		double y2 = b + length * Math.cos(angle);

		// Create a child stem with previous stem's end points as start points and x2,y2
		// as end points
		BGStem stem1 = new BGStem(a, b, x2, y2, length, angle, depth);

		// change x-axis and y-axis value
		a = a + (length * Math.sin(angle));
		b = b + (length * Math.cos(angle));

		// add stem to child stem list and generation list
		stem1.getChildStemList().add(stem1);
		generation.getStemsList().add(stem1);

		// level increments at each growth cycle and length decreases by 75%
		depth++;

		// if current level is less than generation level, call recursive function to
		// grow one child stem
		if (depth < generationDepth) {
			drawChildStems(a, b, length * 0.75, depth, angle, generationDepth); // left stem

			// call recursive function to grow other child stem with supplimentary angle of
			// given angle at the same start point
			drawChildStems(a, b, length * 0.75, depth, -angle, generationDepth); // right stem
		}
	}

}
