package falstad;

//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;

public class ManualDriver implements RobotDriver/*, KeyListener*/ {
	
	//Fields needed for the ManualDriver
	public Robot robot;
	public int mazeWidth;
	public int mazeHeight;
	public Distance distanceToExit;

	@Override
	public void setRobot(Robot r) {
		robot = r;
	}

	@Override
	public void setDimensions(int width, int height) {
		mazeWidth = width;
		mazeHeight = height;
	}

	@Override
	public void setDistance(Distance distance) {
		distanceToExit = distance;
	}

	@Override
	public boolean drive2Exit() throws Exception {
		if (robot.hasStopped()){
			throw new Exception();
		}
		return false;
	}

	@Override
	public float getEnergyConsumption() {
		return 2500 - robot.getBatteryLevel();
	}

	@Override
	public int getPathLength() {
		return ((BasicRobot) robot).pathLength;
	}
	
	/**
	 * Moves the assigned robot forward one space
	 */
	public void moveForward(){
		try{
			robot.move(1);
		}
		catch (Exception exception){
			//System.out.println("fail");
			//exception.printStackTrace();
		}
	}
	
	/**
	 * Turns robot once to the right
	 */
	public void turnRight(){
		try{
			robot.rotate(Robot.Turn.RIGHT);
		}
		catch(Exception exception){
			//System.out.println("fail");
			//exception.printStackTrace();
		}
	}
	
	/**
	 * Turns robot once to the left
	 */
	public void turnLeft(){
		try{
			robot.rotate(Robot.Turn.LEFT);
		}
		catch(Exception exception){
			//System.out.println("fail");
			//exception.printStackTrace();
		}
	}

	/*@Override
	public void keyPressed(KeyEvent arg0) {
		int key = arg0.getKeyChar() ;
		int code = arg0.getKeyCode() ;

		if (KeyEvent.CHAR_UNDEFINED == key)
		{
			if (KeyEvent.VK_UP == code){
				try{
					robot.move(1);
				}
				catch (Exception exception){
					//System.out.println("fail");
					//exception.printStackTrace();
				}
			}
			if (KeyEvent.VK_LEFT == code){
				try{
					robot.rotate(Robot.Turn.LEFT);
				}
				catch(Exception exception){
					//System.out.println("fail");
					//exception.printStackTrace();
				}
			}
			if (KeyEvent.VK_RIGHT == code){
				try{
					robot.rotate(Robot.Turn.RIGHT);
				}
				catch(Exception exception){
					//System.out.println("fail");
					//exception.printStackTrace();
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//Nothing to do
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//Nothing to do
	}*/
}
