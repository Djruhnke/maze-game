package falstad;

public class MazeStub extends Maze{
	
	// Additional fields needed for testing.
	int xstart, ystart;
	
	/**
	 * Method obtains a new Mazebuilder and has it compute new maze, 
	 * it is only used in keyDown()
	 * gets rid of interaction with GUI
	 * @param skill level determines the width, height and number of rooms for the new maze
	 */
	public void build(int skill) {
		// select generation method
		switch(method){
		case 1 : mazebuilder = new MazeBuilderPrim(true); // generate with Prim's algorithm
		break ;
		case 0: // generate with Falstad's original algorithm (0 and default), note the missing break statement
		default : mazebuilder = new MazeBuilder(true); 
		break ;
		}
		// adjust settings and launch generation in a separate thread
		mazew = Constants.SKILL_X[skill];
		mazeh = Constants.SKILL_Y[skill];
		mazebuilder.build(this, mazew, mazeh, Constants.SKILL_ROOMS[skill], Constants.SKILL_PARTCT[skill]);
		// mazebuilder performs in a separate thread and calls back by calling newMaze() to return newly generated maze
	}
	
	/**
	 * Call back method for MazeBuilder to communicate newly generated maze as reaction to a call to build()
	 * gets rid of unnecessary things for testing
	 * @param root node for traversals, used for the first person perspective
	 * @param cells encodes the maze with its walls and border
	 * @param dists encodes the solution by providing distances to the exit for each position in the maze
	 * @param startx current position, x coordinate
	 * @param starty current position, y coordinate
	 */
	@Override
	public void newMaze(BSPNode root, Cells c, Distance dists, int startx, int starty) {
		if (Cells.deepdebugWall)
		{   // for debugging: dump the sequence of all deleted walls to a log file
			// This reveals how the maze was generated
			c.saveLogFile(Cells.deepedebugWallFileName);
		}
		// adjust internal state of maze model
		mazecells = c ;
		mazedists = dists;
		rootnode = root;
		xstart = startx;
		ystart = starty;
	}
}
