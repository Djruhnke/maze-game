package falstad;

public class MazeBuilderEllerTest extends MazeBuilderTest{
	
	/**
	 * Method obtains a new Mazebuilder and has it compute new maze, 
	 * it is only used in keyDown()
	 * gets rid of interaction with GUI
	 * @param skill level determines the width, height and number of rooms for the new maze
	 */
	@Override
	public void build(int skill) {
		// generate a Eller maze
		mazebuilder = new MazeBuilderEller(true);
		// adjust settings and launch generation in a separate thread
		mazew = Constants.SKILL_X[skill];
		mazeh = Constants.SKILL_Y[skill];
		mazebuilder.build(this, mazew, mazeh, Constants.SKILL_ROOMS[skill], Constants.SKILL_PARTCT[skill]);
		// mazebuilder performs in a separate thread and calls back by calling newMaze() to return newly generated maze
	}
}
