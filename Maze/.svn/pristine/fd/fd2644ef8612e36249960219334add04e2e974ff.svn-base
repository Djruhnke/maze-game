package falstad;

import static org.junit.Assert.*;
import org.junit.*;

import falstad.Robot.Direction;

import java.awt.event.KeyEvent;

public class BasicRobotTest extends MazeApplication{

	//Global Variables
	RobotDriver driver = new ManualDriver();
	
	@Before
	public void set(){
		maze.state = Constants.STATE_TITLE;
		//maze.keyDown(KeyEvent.VK_0);
		driverCombo.setSelectedItem("Manual");
		skillCombo.setSelectedItem("0");
		mazeCombo.setSelectedItem("DFS");
		startButton.doClick();
		/*try{
			maze.mazebuilder.buildThread.join();
		}
		catch(InterruptedException completeError){
			// Continue working
		}*/
	}
	
	/**
	 * Checks to see that the BasicRobot has a sensor on every side and a room sensor
	 */
	@Test
	public void testSensors(){
		assertTrue(mazeRobot.hasRoomSensor());
		assertTrue(mazeRobot.hasDistanceSensor(Robot.Direction.FORWARD));
		assertTrue(mazeRobot.hasDistanceSensor(Robot.Direction.BACKWARD));
		assertTrue(mazeRobot.hasDistanceSensor(Robot.Direction.LEFT));
		assertTrue(mazeRobot.hasDistanceSensor(Robot.Direction.RIGHT));
	}
	
	/**
	 * Makes sure it can turn properly
	 * Sets an original direction
	 * Turns to make sure that it is not the same as the original direction
	 * Then makes sure turn right returns it to original position
	 * Finally checks to make sure the appropriate amount of energy is used
	 */
	@Test
	public void testRotate(){
		try{
			mazeRobot.rotate(Robot.Turn.LEFT);
			mazeRobot.rotate(Robot.Turn.RIGHT);
			Robot.Direction originalDirection = ((BasicRobot) mazeRobot).curDir;
			mazeRobot.rotate(Robot.Turn.LEFT);
			assertFalse(originalDirection == ((BasicRobot) mazeRobot).curDir);
			mazeRobot.rotate(Robot.Turn.RIGHT);
			assertTrue(originalDirection == ((BasicRobot) mazeRobot).curDir);
			assertTrue(2500 - mazeRobot.getBatteryLevel() == 12);
		}
		catch(Exception exception){
			fail("Failed on turn");
		}
	}
	
	/**
	 * Runs the rotation until it should run out of battery
	 * If it doesn't throw exception then fail, otherwise pass
	 */
	@Test
	public void testRotateFails(){
		try{
			int t = 834;
			while (t > 0){
				mazeRobot.rotate(Robot.Turn.LEFT);
				t = t - 1;
			}
			fail("Didn't throw exception");
		}
		catch(Exception exception){
			assertTrue(true);
		}
	}
	
	/**
	 * Test move to make sure a step can be taken
	 * Makes sure it is stepping into an open place
	 * checks after the step that it is not in the same location
	 */
	@Test
	public void testMove(){
		try{
			mazeRobot.rotate(Robot.Turn.LEFT);
			mazeRobot.rotate(Robot.Turn.RIGHT);
			boolean noMove = true;
			float energySpent = 6;
			int[] originalPosition = mazeRobot.getCurrentPosition();
			while (noMove){
				if (1 > mazeRobot.distanceToObstacle(Direction.FORWARD)){
					mazeRobot.rotate(Robot.Turn.LEFT);
					energySpent = energySpent + 3;
				}
				else{
					mazeRobot.move(1);
					noMove = false;
					energySpent = energySpent + 5;
				}
				energySpent = energySpent + 1;
			}
			assertTrue(energySpent == (2500 - mazeRobot.getBatteryLevel()));
			assertTrue(originalPosition != mazeRobot.getCurrentPosition());
		}
		catch(Exception exception){
			exception.printStackTrace();
			fail("Failed to move");
		}
	}
	
	/**
	 * Makes sure sensors work in every direction
	 * For each relative direction of the robot senses in all four directions
	 * If after sensing in all four directions there isn't at least one where there is no adjacent obstacle
	 * then the test fails, otherwise rotates and continues
	 */
	@Test
	public void testDistanceToObstacle(){
		try{
			mazeRobot.rotate(Robot.Turn.LEFT);
			mazeRobot.rotate(Robot.Turn.RIGHT);
			boolean possibleMove = false;
			if(mazeRobot.distanceToObstacle(Direction.FORWARD) > 0){
				possibleMove = true;
			}
			if(mazeRobot.distanceToObstacle(Direction.BACKWARD) > 0){
				possibleMove = true;
			}
			if(mazeRobot.distanceToObstacle(Direction.LEFT) > 0){
				possibleMove = true;
			}
			if(mazeRobot.distanceToObstacle(Direction.RIGHT) > 0){
				possibleMove = true;
			}
			assertTrue(possibleMove);
			
			possibleMove = false;
			mazeRobot.rotate(Robot.Turn.LEFT);
			if(mazeRobot.distanceToObstacle(Direction.FORWARD) > 0){
				possibleMove = true;
			}
			if(mazeRobot.distanceToObstacle(Direction.BACKWARD) > 0){
				possibleMove = true;
			}
			if(mazeRobot.distanceToObstacle(Direction.LEFT) > 0){
				possibleMove = true;
			}
			if(mazeRobot.distanceToObstacle(Direction.RIGHT) > 0){
				possibleMove = true;
			}
			assertTrue(possibleMove);
			
			possibleMove = false;
			mazeRobot.rotate(Robot.Turn.LEFT);
			if(mazeRobot.distanceToObstacle(Direction.FORWARD) > 0){
				possibleMove = true;
			}
			if(mazeRobot.distanceToObstacle(Direction.BACKWARD) > 0){
				possibleMove = true;
			}
			if(mazeRobot.distanceToObstacle(Direction.LEFT) > 0){
				possibleMove = true;
			}
			if(mazeRobot.distanceToObstacle(Direction.RIGHT) > 0){
				possibleMove = true;
			}
			assertTrue(possibleMove);
			
			possibleMove = false;
			mazeRobot.rotate(Robot.Turn.LEFT);
			if(mazeRobot.distanceToObstacle(Direction.FORWARD) > 0){
				possibleMove = true;
			}
			if(mazeRobot.distanceToObstacle(Direction.BACKWARD) > 0){
				possibleMove = true;
			}
			if(mazeRobot.distanceToObstacle(Direction.LEFT) > 0){
				possibleMove = true;
			}
			if(mazeRobot.distanceToObstacle(Direction.RIGHT) > 0){
				possibleMove = true;
			}
			assertTrue(possibleMove);
		}
		catch(Exception exception){
			fail("Failed to Sense");
		}
	}
	
	/**
	 * Test method getEnergyForFullRotation
	 */
	@Test
	public void testForFullRotation(){
		assertTrue(mazeRobot.getEnergyForFullRotation() == 12);
	}
	
	/**
	 * Test method getEnergyForStepForward
	 */
	@Test
	public void testForStep(){
		assertTrue(mazeRobot.getEnergyForStepForward() == 5);
	}
	
	/**
	 * Checks that the start position is not at goal
	 */
	@Test
	public void testForAtGoal(){
		assertFalse(mazeRobot.isAtGoal());
	}
	
	/**
	 * Checks whether or not the robot is in a room
	 */
	@Test
	public void testForInsideRoom(){
		try{
			assertTrue(mazeRobot.isInsideRoom() == 
					maze.mazecells.isInRoom(mazeRobot.getCurrentPosition()[0], mazeRobot.getCurrentPosition()[1]));
		}
		catch(Exception excception){
			fail("Failed Inside Room Test");
		}
	}
}
