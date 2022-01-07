package edu.neu.csye6200.bg;

import java.util.ArrayList;

/**
 * BGStem class - Has stem attributes
 * 
 * @author Prakruthi BS NUID: 001448042
 */

public class BGStem {

	private int StemID; // ID tagged to each stem
	private double x0; // x-coordinate of start point of stem
	private double y0; // y-coordinate of start point of stem
	private double xe; // x-coordinate of end point of stem
	private double ye; // y-coordinate of end point of stem
	private double length; // length of stem
	private double angle; // angle of displacement of stem
	private int depth; // depth of stem growth
	private static int counter = 0; // counter to increment stem ID for each stem
	private ArrayList<BGStem> childStemList = new ArrayList<BGStem>(); // arraylist to hold all child stems

	public BGStem(double x0, double y0, double xe, double ye, double length, double angle, int depth) {
		this.x0 = x0;
		this.y0 = y0;
		this.xe = xe;
		this.ye = ye;
		this.length = length;
		this.angle = angle;
		this.depth = depth;
		setStemID(counter++);
	}

	// Getters and Setters
	public int getStemID() {
		return StemID;
	}

	public void setStemID(int stemID) {
		StemID = stemID;
	}

	public double getX0() {
		return x0;
	}

	public void setX0(double x0) {
		this.x0 = x0;
	}

	public double getY0() {
		return y0;
	}

	public void setY0(double y0) {
		this.y0 = y0;
	}

	public double getXe() {
		return xe;
	}

	public void setXe(double xe) {
		this.xe = xe;
	}

	public double getYe() {
		return ye;
	}

	public void setYe(double ye) {
		this.ye = ye;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public ArrayList<BGStem> getChildStemList() {
		return childStemList;
	}

	public void setChildStemList(ArrayList<BGStem> childStemList) {
		this.childStemList = childStemList;
	}

	/**
	 * Method to add stem to childStemList
	 * 
	 * @param stem
	 * @return
	 */
	public BGStem add(BGStem stem) {
		childStemList.add(stem);
		return stem;
	}

	/**
	 * Print routine to display stem details
	 * 
	 * @param st
	 */
	public void showStem(BGStem st) {
		System.out.println(String.format(
				"StemId:%1$2d  Length:%2$2f  X:%3$2.2f  Y:%4$2.2f  Xe:%5$2.2f  Ye:%6$2.2f  Angle:%7$2.2f  Depth:%8$3d",
				st.getStemID(), st.getLength(), st.getX0(), st.getY0(), st.getXe(), st.getYe(), st.getAngle(),
				st.getDepth()));

	}

}
