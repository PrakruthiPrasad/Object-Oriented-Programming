package edu.neu.csye6200.bg.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;
import static edu.neu.csye6200.bg.ui.MyAppUI.generationNumber;
import static edu.neu.csye6200.bg.ui.MyAppUI.customLength;
import static edu.neu.csye6200.bg.ui.MyAppUI.ruleAngle;
import edu.neu.csye6200.bg.BGGeneration;
import static edu.neu.csye6200.bg.ui.MyAppUI.ruleItem;
import static edu.neu.csye6200.bg.BGGeneration.stemsList;
import edu.neu.csye6200.bg.BGStem;

import javax.swing.JPanel;

public class MyCanvas extends JPanel implements Observer {

	private Logger log = Logger.getLogger(MyCanvas.class.getName());
	private int counter = 0;
	public int generationCount = generationNumber;
	private Color color = null;
	BGGeneration generation = BGGeneration.getBGGenerationInstance();
	public static Graphics2D g2d;
	private String comboRule;

	/**
	 * Constructor
	 */
	public MyCanvas() {

	}

	public void paint(Graphics g) {
		this.comboRule = ruleItem;

		drawCanvas(g);
	}

	/**
	 * Draw the panel contents manually
	 * 
	 * @param g
	 */
	public void drawCanvas(Graphics g) {
		g2d = (Graphics2D) g; // Cast to the better G2D interface
		log.info("Drawing BG Canvas panel: " + counter++);
		Dimension size = getSize();

		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, size.width, size.height);

		g2d.setColor(Color.white);
		g2d.drawString("Legend:", 10, 20);

		g2d.setColor(Color.pink);
		g2d.drawString("Stem growth simulation", 1150, 20);

		g2d.setColor(Color.pink);
		g2d.drawString("Prakruthi BS", 1150, 40);

		g2d.setColor(Color.pink);
		g2d.drawString("NUID: 001448042", 1150, 60);

		g2d.setColor(Color.white);
		g2d.drawString("STATISTICS:", 1150, 80);

		displayStatistics(); // Display statistics

		g2d.setColor(Color.green);
		g2d.drawString("> Rules 1,2 = Stem growth in green, forks in white and magenta", 10, 40);

		g2d.setColor(Color.magenta);
		g2d.drawString("> Rule 3 = Stem growth in magenta", 10, 60);

		g2d.translate(size.width - 900, size.height - 300);
		g2d.scale(-1.0, 1.0);

		if (generationCount >= 1) {
			System.out.println("Second if");
			for (BGStem stem : generation.getStemsList()) {
				if (stem.getStemID() >= 2) {

					switch (comboRule) {
					case ("Rule 1"):
						g2d.rotate(-stem.getAngle(), (int) stem.getX0(), (int) stem.getY0());
						g2d.drawLine((int) (stem.getX0()), (int) (stem.getY0()), (int) (stem.getXe()),
								(int) (stem.getYe()));
						g2d.setColor(Color.white);
					case ("Rule 2"):
						g2d.rotate(-stem.getAngle(), (int) stem.getX0(), (int) stem.getY0());
						g2d.drawLine((int) (stem.getX0()), (int) (stem.getY0()), (int) (stem.getXe()),
								(int) (stem.getYe()));
						g2d.setColor(Color.GREEN);
					case ("Rule 3"):
						g2d.rotate(-stem.getAngle(), (int) stem.getX0(), (int) stem.getY0());
						g2d.drawLine((int) (stem.getX0()), (int) (stem.getY0()), (int) (stem.getXe()),
								(int) (stem.getYe()));
						g2d.setColor(Color.magenta);
					}

				}
			}

		} else {
			System.out.println("Level 0");
			g2d.setColor(Color.white);
			g2d.drawString("Please enter valid generations", 800, 800);
		}

	}
	
	@Override
	public void update(Observable o, Object arg) {
		this.repaint();
		generation = (BGGeneration) arg;
		repaint();

	}

	/**
	 * Method to display statistics
	 */
	private void displayStatistics() {

		int offSetX = 1150;
		int offsetY = 100;
		for (BGStem st : generation.getStemsList()) {
			g2d.setColor(Color.white);
			g2d.drawString("X0: " + Math.round(st.getX0()), offSetX, offsetY);

			g2d.setColor(Color.white);
			g2d.drawString("Y0: " + Math.round(st.getY0()), offSetX + 50, offsetY);

			g2d.setColor(Color.white);
			g2d.drawString("Xe: " + Math.round(st.getXe()), offSetX + 100, offsetY);

			g2d.setColor(Color.white);
			g2d.drawString("Ye: " + Math.round(st.getYe()), offSetX + 150, offsetY);

			g2d.setColor(Color.white);
			g2d.drawString("Angle: " + Math.round(st.getAngle()), offSetX + 200, offsetY);

			g2d.setColor(Color.white);
			g2d.drawString("Depth: " + Math.round(st.getDepth()), offSetX + 270, offsetY);

			offsetY += 20;
		}

	}


}
