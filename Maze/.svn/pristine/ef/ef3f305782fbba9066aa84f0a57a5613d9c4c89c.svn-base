/**
 * 
 */
package falstad;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * This class is a wrapper class to startup the Maze as a Java application
 * 
 *
 * This code is refactored code from Maze.java by Paul Falstad, www.falstad.com, Copyright (C) 1998, all rights reserved
 * Paul Falstad granted permission to modify and use code for teaching purposes.
 * Refactored by Peter Kemper
 */
public class MazeApplication extends JFrame {

	Maze maze ;
	KeyListener kl ;
	
	public JPanel lowerPanel;
	public JComboBox driverCombo;
	public JComboBox mazeCombo;
	public JComboBox skillCombo;
	public JButton startButton;
	
	//Fields required for incorporating robots
	public Robot mazeRobot;
	ManualDriver manualDriver;
	public RobotDriver driver;

	/**
	 * Constructor
	 */
	public MazeApplication() {
		super() ;
		System.out.println("MazeApplication: maze will be generated with a randomized algorithm.");
		setLayout(new BorderLayout());
		maze = new Maze() ;
		init() ;
	}

	/**
	 * Constructor that loads a maze from a given file or uses a particular method to generate a maze
	 */
	public MazeApplication(String parameter) {
		super() ;
		if ("Prim".equalsIgnoreCase(parameter))
		{
			System.out.println("MazeApplication: generating random maze with Prim's algorithm");
			maze = new Maze(1) ;
			init() ;
		}
		else if ("Eller".equalsIgnoreCase(parameter)){
			System.out.println("MazeApplication: generating random maze with Eller's algorithm");
			maze = new Maze(2);
			init();
		}
		else
		{
			File f = new File(parameter) ;
			if (f.exists() && f.canRead())
			{
				System.out.println("MazeApplication: loading maze from file: " + parameter);
				// TODO: adjust this to mazeview
				maze = new Maze() ;
				init();
				MazeFileReader mfr = new MazeFileReader(parameter) ;
				maze.mazeh = mfr.getHeight() ;
				maze.mazew = mfr.getWidth() ;
				Distance d = new Distance(mfr.getDistances()) ;
				maze.newMaze(mfr.getRootNode(),mfr.getCells(),d,mfr.getStartX(), mfr.getStartY()) ;
			}
			else
			{
				System.out.println("MazeApplication: unknown parameter value: " + parameter + " ignored, operating in default mode.");
				maze = new Maze() ;
				init() ;
			}
		}
		
		

	}
	/**
	 * Initializes some internals and puts the game on display.
	 */
	private void init() {
		add(maze.getPanel()) ;
		
		//kl = new SimpleKeyListener(this, maze) ;
		//addKeyListener(kl) ;
		
		setSize(400, 400) ;
		setVisible(true) ;
		
		//Add lower combo boxes and start button.
		lowerPanel = new JPanel();
		maze.lowerPanel = lowerPanel;
		lowerPanel.setLayout(new FlowLayout());
		add(lowerPanel, BorderLayout.SOUTH);
		String[] driverOptions = {"Manual", "Curious Mouse", "Wizard", "Wall Follower"};
		driverCombo = new JComboBox(driverOptions);
		String[] mazeOptions = {"DFS", "Prim", "Eller"};
		mazeCombo = new JComboBox(mazeOptions);
		String[] skillOptions = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};
		skillCombo = new JComboBox(skillOptions);
		lowerPanel.add(driverCombo);
		lowerPanel.add(mazeCombo);
		lowerPanel.add(skillCombo);
		startButton = new JButton("Start");
        startButton.addActionListener(new startButtonListener()); 
		lowerPanel.add(startButton);
		setVisible(true);
		
		// focus should be on the JFrame of the MazeApplication and not on the maze panel
		// such that the SimpleKeyListener kl is used
		//maze.setFocusable(false) ; // happens internally on MazePanel
		setFocusable(true) ;
		maze.init();
		
		//Set up key listener for manual driver
		manualDriver = new ManualDriver();
		kl = new SimpleKeyListener(this, maze, manualDriver) ;
		addKeyListener(kl) ;
		//addKeyListener(manualDriver);
	}


	/**
	 * Method for start button listener
	 */
	public class startButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			driver = null;
			String driverString = (String) driverCombo.getSelectedItem();
			String mazeString = (String) mazeCombo.getSelectedItem();
			String skillString = (String) skillCombo.getSelectedItem();
			int skillInt = Integer.parseInt(skillString);
			
			mazeRobot = new BasicRobot();
			mazeRobot.setMaze(maze);
			mazeRobot.setBatteryLevel(2500);
			
			if (driverString == "Manual"){
				((ManualDriver) manualDriver).setRobot(mazeRobot);
			}
			else if (driverString == "Wizard"){
				driver = new Wizard();
				driver.setRobot(mazeRobot);
			}
			else if (driverString == "Curious Mouse"){
				driver = new CuriousMouse();
				driver.setRobot(mazeRobot);
			}
			else if (driverString == "Wall Follower"){
				driver = new WallFollower();
				driver.setRobot(mazeRobot);
			}
			
			lowerPanel.setVisible(false);
			validate();
			maze.build(skillInt, mazeString);
			
			try{
				maze.mazebuilder.buildThread.join();
			}
			catch(Exception exception){
				//Continue Working
			}
			
			if (driver != null){
				try{
					//maze.keyDown('m');
					//maze.keyDown('z');
					//maze.keyDown('s');
					driver.drive2Exit();
				}
				catch(Exception exception){
					//Continue working
				}
			}
		}
		
	}


	
	/**
	 * Main method to launch Maze as a java application.
	 * The application can be operated in two ways. The intended normal operation is to provide no parameters
	 * and the maze will be generated by a particular algorithm. If a filename is given, the maze will be loaded
	 * from that file. The latter option is useful for development to check particular mazes.
	 * @param args is optional, first parameter is a filename with a given maze
	 */
	public static void main(String[] args) {
		MazeApplication a ; 
		switch (args.length) {
		case 1 : a = new MazeApplication(args[0]);
		break ;
		case 0 : 
		default : a = new MazeApplication() ;
		break ;
		}
		a.repaint() ;
	}

}
