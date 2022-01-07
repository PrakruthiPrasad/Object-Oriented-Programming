package edu.neu.csye6200.bg.ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import static java.lang.Integer.parseInt;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import edu.neu.csye6200.bg.BGGeneration;
import edu.neu.csye6200.bg.BGSimulation;

/**
 * @author mgmunson
 *
 */
public class MyAppUI extends BGApp {

	private static Logger log = Logger.getLogger(MyAppUI.class.getName());

	// private JFrame frame = null; // Now held in BGApp
	private JPanel mainPanel;
	protected JPanel northPanel = null;
	protected static JButton startBtn = null;
	protected static JButton stopBtn = null;
	protected static JButton pauseBtn = null;
	private MyCanvas canvas;
	protected JComboBox<String> comboBox = null;
	public static JTextField txtGenerationNumber = null;
	public static JTextField txtLength = null;
	public static int customLength = 0;
	public static int generationNumber = 0;
	public static double ruleAngle = 0;
	private JLabel label = null;
	private JLabel label1 = null;
	BGGeneration generation = BGGeneration.getBGGenerationInstance();
	private BGSimulation simulation;
	public static String ruleItem;

	/**
	 * Constructor
	 */
	public MyAppUI() {
		simulation = new BGSimulation();

		customizeGUI();
		showUI(); // Execute a UI Thread startup
		this.canvas = new MyCanvas();
	}

	/**
	 * Misc. UI adjustments are implemented here
	 */
	private void customizeGUI() {
		frame.setSize(1500, 800); // Width, height
		frame.setTitle("MyAppUI");
		frame.setResizable(true);
		menuMgr.createDefaultActions(); // Set up default menu items
	}

	/**
	 * Build a Main panel
	 * 
	 * @return a JPanel with content
	 */
	public JPanel getMainPanel() {
		if (mainPanel != null)
			return mainPanel; // Only build the panel once

		mainPanel = new JPanel();
		mainPanel.setBackground(Color.white);
		mainPanel.setLayout(new FlowLayout());

		// Create the buttons
		startBtn = new JButton("Start");
		pauseBtn = new JButton("Pause/Resume");
		stopBtn = new JButton("Stop");

		label = new JLabel("Input generations: ");
		mainPanel.add(label);
		txtGenerationNumber = new JTextField("", 5);
		mainPanel.add(txtGenerationNumber);

		label1 = new JLabel("Enter stem length: ");
		mainPanel.add(label1);
		txtLength = new JTextField("", 10);
		mainPanel.add(txtLength);

		String[] rule = { "Select rule", "Rule 1", "Rule 2", "Rule 3" };
		comboBox = new JComboBox<String>(rule);
		comboBox.setPreferredSize(new Dimension(100, 50));
		comboBox.setLightWeightPopupEnabled(false);
		comboBox.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				comboactionperformed(evt);
			}
		});
		mainPanel.add(comboBox);

		startBtn.addActionListener(this); // Subscribe this class to events
		pauseBtn.addActionListener(this);
		stopBtn.addActionListener(this);

		startBtn.setEnabled(false);
		pauseBtn.setEnabled(false);
		stopBtn.setEnabled(false);

		// Add two buttons that 'flow'
		mainPanel.setLayout(new FlowLayout()); // Widgets will flow form left to right

		// Add buttons to the panel
		mainPanel.add(startBtn);
		mainPanel.add(pauseBtn);
		mainPanel.add(stopBtn);

		return mainPanel;
	}

	private JPanel getCanvasPanel() {
		if (canvas == null) // Only build this once
			canvas = new MyCanvas(); // Build a custom JPanel that we can draw into
		return canvas;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyAppUI myApp = new MyAppUI();
		System.out.println("MyAppUI is starting !!!!!!!!!");
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		log.info(e.getActionCommand());
		System.out.println("Action Event: " + e);
		if (e.getActionCommand().equalsIgnoreCase("Start")) {
			System.out.println("Start pressed");
			generationNumber = parseInt(txtGenerationNumber.getText());
			customLength = parseInt(txtLength.getText());
			this.canvas.generationCount = generationNumber;
			simulation.setcLength(customLength);
			simulation.addObserver(this.canvas);
			frame.add(this.canvas, BorderLayout.CENTER); // Adds canvas at the center
			frame.setVisible(true); // let's see it
			simulation.startSimulation(); // Start the Simulation
			startBtn.setEnabled(false);
			stopBtn.setEnabled(true);
			pauseBtn.setEnabled(true);

		} else if (e.getActionCommand().equalsIgnoreCase("Pause/Resume")) {
			System.out.println("Pause pressed");
			simulation.pauseSimulation(); // Pause the Simulation
			startBtn.setEnabled(false);
			stopBtn.setEnabled(true);
			pauseBtn.setEnabled(true);
		} else if (e.getActionCommand().equalsIgnoreCase("Stop")) {
			System.out.println("Stop pressed");
			simulation.stopSimulation(); // Stop the Simulation
			this.canvas.repaint();
			startBtn.setEnabled(true);
			stopBtn.setEnabled(false);
			pauseBtn.setEnabled(false);
		}

	}

	public void comboactionperformed(ActionEvent e) {

		JComboBox combo = (JComboBox<String>) e.getSource();
		String comboItem = (String) combo.getSelectedItem();
		double angle = 0;
		switch (comboItem) {
		case ("Rule 1"):
			ruleItem = "Rule 1";
			ruleAngle = 327;
			break;
		case ("Rule 2"):
			ruleItem = "Rule 2";
			ruleAngle = 13;
			break;
		case ("Rule 3"):
			ruleItem = "Rule 3";
			ruleAngle = 63;
		}
		startBtn.setEnabled(true);
	}

	@Override
	public JPanel getNorthPanel() {
		return getMainPanel();
	}

	@Override
	public JPanel getCenterPanel() {
		return getCanvasPanel();
	}

}
