package falstad;

import java.util.*;

public class CuriousMouse implements RobotDriver {

	//Fields needed for the Curious Mouse
	public Robot robot;
	public int mazeWidth;
	public int mazeHeight;
	public Distance distanceToExit;
		
	/**
	 * Constructor class for CuriousMouse for project requirements
	 * No need to do anything
	 */
	public CuriousMouse(){
		//Do nothing
	}
		
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
		setDimensions(((BasicRobot) robot).maze.mazew, ((BasicRobot) robot).maze.mazeh);
		int front;
		int back;
		int right;
		int left;
		int sum;
		int[][] visits = new int[mazeWidth][mazeHeight];
		for (int[] row : visits){
			Arrays.fill(row, 1);
		}
		visits[robot.getCurrentPosition()[0]][robot.getCurrentPosition()[1]] = visits[robot.getCurrentPosition()[0]][robot.getCurrentPosition()[1]] + 1;
		Robot.Direction moveDirection = null;
		while (!robot.isAtGoal()){
			front = 0;
			back = 0;
			right = 0;
			left = 0;
			sum = 0;
			if (robot.distanceToObstacle(Robot.Direction.FORWARD) > 0){
				front = visits[robot.getCurrentPosition()[0] + robot.getCurrentDirection()[0]][robot.getCurrentPosition()[1] + robot.getCurrentDirection()[1]];
				sum = sum + front;
			}
			if (robot.distanceToObstacle(Robot.Direction.BACKWARD) > 0){
				back = visits[robot.getCurrentPosition()[0] - robot.getCurrentDirection()[0]][robot.getCurrentPosition()[1] - robot.getCurrentDirection()[1]];
				sum = sum + back;
			}
			if (robot.distanceToObstacle(Robot.Direction.LEFT) > 0){
				left = visits[robot.getCurrentPosition()[0] - robot.getCurrentDirection()[1]][robot.getCurrentPosition()[1] + robot.getCurrentDirection()[0]];
				sum = sum + left;
			}
			if (robot.distanceToObstacle(Robot.Direction.RIGHT) > 0){
				right = visits[robot.getCurrentPosition()[0] + robot.getCurrentDirection()[1]][robot.getCurrentPosition()[1] - robot.getCurrentDirection()[0]];
				sum = sum + right;
			}
			if (front > 0){
				front = sum - front + 1;
			}
			if (back > 0){
				back = sum - back + 1;
			}
			if (left > 0){
				left = sum - left + 1;
			}
			if (right > 0){
				right = sum - right + 1;
			}
			sum = front + back + left + right;
			moveDirection = chooseOnWeight(front, back, left, right, sum);
			switch(moveDirection){
			case FORWARD:
				robot.move(1);
				visits[robot.getCurrentPosition()[0]][robot.getCurrentPosition()[1]] = visits[robot.getCurrentPosition()[0]][robot.getCurrentPosition()[1]] + 1;
				break;
			case BACKWARD:
				robot.rotate(Robot.Turn.LEFT);
				robot.rotate(Robot.Turn.LEFT);
				robot.move(1);
				visits[robot.getCurrentPosition()[0]][robot.getCurrentPosition()[1]] = visits[robot.getCurrentPosition()[0]][robot.getCurrentPosition()[1]] + 1;
				break;
			case RIGHT:
				robot.rotate(Robot.Turn.RIGHT);
				robot.move(1);
				visits[robot.getCurrentPosition()[0]][robot.getCurrentPosition()[1]] = visits[robot.getCurrentPosition()[0]][robot.getCurrentPosition()[1]] + 1;
				break;
			case LEFT:
				robot.rotate(Robot.Turn.LEFT);
				robot.move(1);
				visits[robot.getCurrentPosition()[0]][robot.getCurrentPosition()[1]] = visits[robot.getCurrentPosition()[0]][robot.getCurrentPosition()[1]] + 1;
				break;
			default:
				throw new Exception();
			}
		}
		if (robot.isAtGoal()){
			if (robot.distanceToObstacle(Robot.Direction.FORWARD) == Integer.MAX_VALUE){
				robot.move(1);
			}
			else if (robot.distanceToObstacle(Robot.Direction.BACKWARD) == Integer.MAX_VALUE){
				robot.rotate(Robot.Turn.LEFT);
				robot.rotate(Robot.Turn.LEFT);
				robot.move(1);
			}
			else if (robot.distanceToObstacle(Robot.Direction.RIGHT) == Integer.MAX_VALUE){
				robot.rotate(Robot.Turn.RIGHT);
				robot.move(1);
			}
			else if (robot.distanceToObstacle(Robot.Direction.LEFT) == Integer.MAX_VALUE){
				robot.rotate(Robot.Turn.LEFT);
				robot.move(1);
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Method for choosing a random direction weighted by fewest number of visits
	 * If direction is possible to go in, then that weight will be at least 1, otherwise it will be 0 and never chosen
	 * This method refactored to appply to this project from code at: http://stackoverflow.com/questions/6409652/random-weighted-selection-in-java
	 * @param front is the weight of the cell in front of the robot
	 * @param back is the weight of the cell in back of the robot
	 * @param left is the weight of the cell to the right of the robot
	 * @param right is the weight of the cell to the left of the robot
	 * @param sum is the total of all the weights
	 * @return a direction that the robot should move in
	 */
	public Robot.Direction chooseOnWeight(int front, int back, int left, int right, int sum) {
        double completeWeight = (double) sum;
        double r = Math.random() * completeWeight;
        double countWeight = 0.0;
        countWeight += (double) front;
        if (countWeight > r){
            return Robot.Direction.FORWARD;
        }
        countWeight += (double) back;
        if (countWeight > r){
            return Robot.Direction.BACKWARD;
        }
        countWeight += (double) left;
        if (countWeight > r){
            return Robot.Direction.LEFT;
        }
        countWeight += (double) right;
        if (countWeight > r){
            return Robot.Direction.RIGHT;
        }
        throw new RuntimeException("Should never be shown.");
    }

	@Override
	public float getEnergyConsumption() {
		return 2500 - robot.getBatteryLevel();
	}

	@Override
	public int getPathLength() {
		return ((BasicRobot) robot).pathLength;
	}

}
