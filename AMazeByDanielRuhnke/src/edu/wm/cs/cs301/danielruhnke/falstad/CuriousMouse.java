package edu.wm.cs.cs301.danielruhnke.falstad;

import java.util.*;

import android.os.Handler;
import android.util.Log;
import edu.wm.cs.cs301.danielruhnke.ui.PlayActivity;

public class CuriousMouse implements RobotDriver {

	//Fields needed for the Curious Mouse
	public Robot robot;
	public int mazeWidth;
	public int mazeHeight;
	public Distance distanceToExit;
	
	private Handler handler = new Handler();
	public PlayActivity playActivity;
	int[][] visits;
		
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
		setDimensions(((BasicRobot) robot).maze.mazew, ((BasicRobot) robot).maze.mazeh);
		visits = new int[mazeWidth][mazeHeight];
		for (int[] row : visits){
			Arrays.fill(row, 1);
		}
		try{
			visits[robot.getCurrentPosition()[0]][robot.getCurrentPosition()[1]] = visits[robot.getCurrentPosition()[0]][robot.getCurrentPosition()[1]] + 1;
		}
		catch (Exception exception){
			//continue working
		}
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
		int front;
		int back;
		int right;
		int left;
		int sum;
		Robot.Direction moveDirection = null;
		if (!robot.isAtGoal()){
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
				moveRobot();
				break;
			case BACKWARD:
				turnRobotBack();
				break;
			case RIGHT:
				turnRobotRight();
				break;
			case LEFT:
				turnRobotLeft();
				break;
			default:
				throw new Exception();
			}
		}
		else if (robot.isAtGoal()){
			if (robot.distanceToObstacle(Robot.Direction.FORWARD) == Integer.MAX_VALUE){
				moveRobot();
			}
			else if (robot.distanceToObstacle(Robot.Direction.BACKWARD) == Integer.MAX_VALUE){
				turnRobot(Robot.Turn.LEFT);
			}
			else if (robot.distanceToObstacle(Robot.Direction.RIGHT) == Integer.MAX_VALUE){
				turnRobot(Robot.Turn.RIGHT);
			}
			else if (robot.distanceToObstacle(Robot.Direction.LEFT) == Integer.MAX_VALUE){
				turnRobot(Robot.Turn.LEFT);
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
	
	public void moveRobot(){
		Log.v("Driver", "Move");
		if (!playActivity.paused){
			handler.postDelayed(new Runnable() {
				public void run() {
					try{
						playActivity.battery.setProgress(playActivity.battery.getProgress() - 5);
						playActivity.path = playActivity.path + 1;
						Log.v("Driver", "Moving");
						robot.move(1);
						visits[robot.getCurrentPosition()[0]][robot.getCurrentPosition()[1]] = visits[robot.getCurrentPosition()[0]][robot.getCurrentPosition()[1]] + 1;
						drive2Exit();
					}
					catch(Exception exception){
						//Continue working
					}
				}
			}, 100);
		}
	}
	
	public void turnRobot(final Robot.Turn turn){
		Log.v("Driver", "Turn");
		if (!playActivity.paused){
			handler.postDelayed(new Runnable() {
				public void run() {
					try{
						playActivity.battery.setProgress(playActivity.battery.getProgress() - 3);
						Log.v("Driver", "Turning");
						robot.rotate(turn);
						drive2Exit();
					}
					catch(Exception exception){
						//Continue working
					}
				}
			}, 100);
		}
	}
	
	public void turnRobotLeft(){
		if (!playActivity.paused){
			handler.postDelayed(new Runnable() {
				public void run() {
					try{
						playActivity.battery.setProgress(playActivity.battery.getProgress() - 3);
						Log.v("Driver", "Turning");
						robot.rotate(Robot.Turn.LEFT);
						moveRobot();
					}
					catch(Exception exception){
						//Continue working
					}
				}
			}, 100);
		}
	}
	
	public void turnRobotRight(){
		if (!playActivity.paused){
			handler.postDelayed(new Runnable() {
				public void run() {
					try{
						playActivity.battery.setProgress(playActivity.battery.getProgress() - 3);
						Log.v("Driver", "Turning");
						robot.rotate(Robot.Turn.RIGHT);
						moveRobot();
					}
					catch(Exception exception){
						//Continue working
					}
				}
			}, 100);
		}
	}
	
	public void turnRobotBack(){
		if (!playActivity.paused){
			handler.postDelayed(new Runnable() {
				public void run() {
					try{
						Log.v("Driver", "Turning");
						turnRobotDoNotDrive(Robot.Turn.LEFT);
						turnRobotDoNotDrive(Robot.Turn.LEFT);
						moveRobot();
					}
					catch(Exception exception){
						//Continue working
					}
				}
			}, 100);
		}
	}
	
	public void turnRobotDoNotDrive(final Robot.Turn turn){
		Log.v("Driver", "Turn");
		if (!playActivity.paused){
			handler.postDelayed(new Runnable() {
				public void run() {
					try{
						playActivity.battery.setProgress(playActivity.battery.getProgress() - 3);
						Log.v("Driver", "Turning");
						robot.rotate(turn);
					}
					catch(Exception exception){
						//Continue working
					}
				}
			}, 100);
		}
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
