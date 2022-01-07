package edu.neu.csye6200.bg;

import java.util.Observable;
import static edu.neu.csye6200.bg.ui.MyAppUI.ruleAngle;
import static edu.neu.csye6200.bg.ui.MyAppUI.generationNumber;

/**
 * BGSimulation class that creates and maintains a thread for running the
 * simulation of stem growth
 * 
 * @author Prakruthi BS 
 * NUID: 001448042
 */
@SuppressWarnings("deprecation")
public class BGSimulation extends Observable implements Runnable {

	private Thread thread = null;
	private boolean done = false; // Set true to end a simulation loop
	private boolean paused = false; // Set true to pause the simulation loop
	private int counter = 0;
	private static int initialDepth = 0;
	private double initialLength = 0;
	private double cLength;
	BGGeneration generation = BGGeneration.getBGGenerationInstance();

	public BGSimulation() {
		System.out.println("Simulation is working");
	}

	
	public void setcLength(double cLength) {
		this.cLength = cLength;
	}


	/**
	 * Method to start simulation
	 */
	public void startSimulation() {
		System.out.println("Starting with simulation");

		if (thread == null) {
			thread = new Thread(this); // We are runnable
		}
		thread.start();
	}

	/**
	 * Method to pause simulation
	 */
	public void pauseSimulation() {
		paused = !paused;
		System.out.println("Pause state is " + paused);
	}

	/**
	 * Method to stop simulation
	 */
	public void stopSimulation() {
		System.out.println("Stopping with simulation");
		if (thread == null)
			return;
		done = true; // stops an existing simulation
	}

	/**
	 * Perform an update to simulation
	 */
	private void startGrowing() {
		System.out.println("Updating the simulation state - growth" + counter++);
		this.generation.growGeneration(0, 0, this.initialLength, ruleAngle, initialDepth);
		this.initialLength += cLength;
		initialDepth++;
		extracted();
		extractedGen();
		if (initialDepth > generationNumber)
			done = true;

	}

	@SuppressWarnings("deprecation")
	private void extractedGen() {
		notifyObservers(generation);
	}

	@SuppressWarnings("deprecation")
	private void extracted() {
		setChanged();
	}

	/**
	 * Main simulation loop
	 */
	private void runSimulationLoop() {

		while (!done) {
			if (!paused)
				startGrowing();
			sleep(1500);
		}

	}

	private void sleep(long millies) {
		try {
			Thread.sleep(millies);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		runSimulationLoop(); // run simulation
		thread = null;

	}

}
