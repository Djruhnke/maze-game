package falstad;

import static org.junit.Assert.*;
import org.junit.*;

public class MazeBuilderTest extends MazeStub{
	
	//Fields needed for testing
	static final int INFINITY = Integer.MAX_VALUE;
	
	/**
	 * Set's up maze for tests
	 */
	@Before
	public void createMaze() {
		this.build(9);
	}
	
	/**
	 * Tear down maze
	 */
	@After
	public void resetMaze() {
		mazebuilder = null;
	}
	
	/**
	 * Makes sure the maze can be solved
	 * Looks to see all cells distances are positive numbers which are not infinity
	 */
	@Test
	public void solvable() {
		try{
			mazebuilder.buildThread.join();
		}
		catch(InterruptedException completeError){
			// Continue working
		}
		for (int i = 0; i != this.mazew; i++){
			for (int j = 0; j != this.mazeh; j++){
				assertTrue("Some cell is infinity", this.mazedists.getDistance(i, j) != INFINITY);
				assertTrue("Some cell is less than or equal to 0", this.mazedists.getDistance(i, j) > 0);
			}
		}
	}
	
	/**
	 * Tests that there is a start position for the maze is correct
	 */
	@Test
	public void correctStart() {
		try{
			mazebuilder.buildThread.join();
		}
		catch(InterruptedException completeError) {
			// Continue working
		}
		assertTrue("Incorrect start coordinates", this.xstart == mazedists.getStartPosition()[0]
				&& this.ystart == mazedists.getStartPosition()[1]);
	}
	
	/**
	 * Tests that exit position is of distance 1
	 */
	@Test
	public void correctExit() {
		try{
			mazebuilder.buildThread.join();
		}
		catch(InterruptedException completeError) {
			// Continue working
		}
		assertTrue("Incorrect Exit distance", 
				this.mazedists.getDistance(mazedists.getExitPosition()[0], mazedists.getExitPosition()[1]) == 1);
	}
	
	/**
	 * Test that each cell has at least one adjacent cell with a shorter distance, excluding the exit
	 */
	@Test
	public void shorterPathExists() {
		try{
			mazebuilder.buildThread.join();
		}
		catch(InterruptedException completeError) {
			// Continue working
		}
		for (int i = 0; i < mazew; i++){
			for (int j = 0; j < mazeh; j++){
				boolean shorterPath = false;
				if (mazedists.getDistance(i, j) != 1){
					if (mazecells.hasNoWallOnBottom(i, j)){
						if (mazedists.getDistance(i, j) > mazedists.getDistance(i, j+1)){
							shorterPath = true;
						}
					}
					if (mazecells.hasNoWallOnTop(i, j)){
						if (mazedists.getDistance(i, j) > mazedists.getDistance(i, j-1)){
							shorterPath = true;
						}
					}
					if (mazecells.hasNoWallOnLeft(i, j)){
						if (mazedists.getDistance(i, j) > mazedists.getDistance(i-1, j)){
							shorterPath = true;
						}
					}
					if (mazecells.hasNoWallOnRight(i, j)){
						if (mazedists.getDistance(i, j) > mazedists.getDistance(i+1, j)){
							shorterPath = true;
						}
					}
				}
				if (mazedists.getDistance(i, j) == 1){
					shorterPath = true;
				}
				assertTrue("There is a cell with no shorter distance adjacent", shorterPath);
			}
		}
	}
}
