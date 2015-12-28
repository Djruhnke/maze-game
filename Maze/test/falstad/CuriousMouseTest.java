package falstad;

import static org.junit.Assert.*;

import java.awt.event.KeyEvent;

import org.junit.*;

public class CuriousMouseTest extends MazeApplication{

	//Global Variables
	RobotDriver driver = new CuriousMouse();
		
	@Before
	public void set(){
		maze.state = Constants.STATE_TITLE;
		//maze.keyDown(KeyEvent.VK_0);
		driverCombo.setSelectedItem("Curious Mouse");
		skillCombo.setSelectedItem("0");
	}
	
	/**
	 * Test to make sure the driver runs properly with a DFS generation
	 * Expects for the maze to be successfully solved without hitting walls,
	 * so if the robot has not stopped after drive to exit then the test has passed
	 */
	@Test
	public void testDriverDFS(){
		mazeCombo.setSelectedItem("DFS");
		startButton.doClick();
		assertFalse(maze.stopped);
	}
	
	/**
	 * Test to make sure the driver runs properly with an Eller generation
	 * Expects for the maze to be successfully solved without hitting walls,
	 * so if the robot has not stopped after drive to exit then the test has passed
	 */
	@Test
	public void testDriverEller(){
		mazeCombo.setSelectedItem("Eller");
		startButton.doClick();
		assertFalse(maze.stopped);
	}
	
	/**
	 * Test to make sure the driver runs properly with a Prim generation
	 * Expects for the maze to be successfully solved without hitting walls,
	 * so if the robot has not stopped after drive to exit then the test has passed
	 */
	@Test
	public void testDriverPrim(){
		mazeCombo.setSelectedItem("Prim");
		startButton.doClick();
		assertFalse(maze.stopped);
	}
}
