package edu.wm.cs.cs301.danielruhnke.falstad;
//import java.awt.*;
import edu.wm.cs.cs301.danielruhnke.falstad.GraphicsWrapper.DirectionEvents;

public class BasicRobot implements Robot {
	
	//Fields required for robot
	Maze maze; //maze that the robot will be navigating
	public boolean forwardSensor; //booleans for whether or not sensors exist
	public boolean backwardSensor; //            |
	public boolean rightSensor; //               |
	public boolean leftSensor; //                |
	public boolean roomSensor; //              <-
	public boolean stopped;
	public float batteryLevel;
	public Direction curDir = Direction.RIGHT;
	public int pathLength;
	
	/**
	 * Constructor for the Robot, initiates whether or not there is a room sensor,
	 * which direction sensors it has,
	 * whether or not it has stopped, and
	 * what the starting battery level should be
	 */
	public BasicRobot(){
		forwardSensor = true;
		backwardSensor = true;
		rightSensor = true;
		leftSensor = true;
		roomSensor = true;
		stopped = false;
		pathLength = 0;
	}

	@Override
	public void rotate(Turn turn) throws Exception {
		if ((getBatteryLevel()) < 3 || hasStopped()){
			stopped = true;
			maze.stopped = true;
			maze.state = Constants.STATE_FINISH;
			maze.notifyViewerRedraw();
			maze.stopped = false;
			stopped = false;
			setBatteryLevel(2500);
			pathLength = 0;
			maze.pathLength = pathLength;
			curDir = Direction.RIGHT;
			throw new Exception();
		}
		else{
			if (turn == Turn.LEFT){
				maze.keyDown(DirectionEvents.LEFT);
				this.setBatteryLevel(this.getBatteryLevel() - 3);
			}
			else if (turn == Turn.RIGHT){
				maze.keyDown(DirectionEvents.RIGHT);
				this.setBatteryLevel(this.getBatteryLevel() - 3);
			}
			else{
				throw new Exception();
			}
			int x = getCurrentDirection()[0];
			int y = getCurrentDirection()[1];
			switch(x){
			case -1:
				curDir = Direction.LEFT;
				break;
			case 1:
				curDir = Direction.RIGHT;
				break;
			case 0:
				if (y == 1){
					curDir = Direction.FORWARD;
				}
				else{
					curDir = Direction.BACKWARD;
				}
				break;
			default: 
				curDir = null;
				break;
			}
		}
	}

	@Override
	public void move(int distance) throws Exception {
		if (hasStopped()){
			maze.state = Constants.STATE_FINISH;
			maze.notifyViewerRedraw();
			maze.stopped = false;
			stopped = false;
			setBatteryLevel(2500);
			pathLength = 0;
			maze.pathLength = pathLength;
			curDir = Direction.RIGHT;
			throw new Exception();
		}
		int x = getCurrentDirection()[0];
		int y = getCurrentDirection()[1];
		switch(x){
		case -1:
			curDir = Direction.LEFT;
			break;
		case 1:
			curDir = Direction.RIGHT;
			break;
		case 0:
			if (y == 1){
				curDir = Direction.FORWARD;
			}
			else{
				curDir = Direction.BACKWARD;
			}
			break;
		default: 
			curDir = null;
			break;
		}
		
		int obstacleDistance = distanceToObstacle(Direction.FORWARD);
		setBatteryLevel(getBatteryLevel() + 1);
		if (distance > obstacleDistance){
			stopped = true;
			maze.stopped = true;
		}
		while ((getBatteryLevel() > 4) && distance > 0){
			maze.keyDown(DirectionEvents.UP);
			distance = distance - 1;
			setBatteryLevel(getBatteryLevel() - 5);
			pathLength = pathLength + 1;
			maze.pathLength = pathLength;
		}
		try{
			getCurrentPosition();
		}
		catch(Exception exception){
			maze.stopped = false;
			stopped = false;
			setBatteryLevel(2500);
			pathLength = 0;
			maze.pathLength = pathLength;
		}
		if (hasStopped() || (getBatteryLevel() < 5)){
			stopped = true;
			maze.stopped = true;
			maze.state = Constants.STATE_FINISH;
			maze.notifyViewerRedraw();
			maze.stopped = false;
			stopped = false;
			setBatteryLevel(2500);
			pathLength = 0;
			maze.pathLength = pathLength;
			curDir = Direction.RIGHT;
			throw new Exception();
		}
	}

	@Override
	public int[] getCurrentPosition() throws Exception {
		int[] x = new int[2];
		x[0] = maze.px;
		x[1] = maze.py;
		if(x[0] >= maze.mazew || x[0] < 0 || x[1] >= maze.mazeh || x[1] < 0){
			throw new Exception();
		}
		return x;
	}

	@Override
	public void setMaze(Maze maze) {
		this.maze = maze;
	}

	@Override
	public boolean isAtGoal() {
		try{
			return maze.mazecells.isExitPosition(getCurrentPosition()[0], getCurrentPosition()[1]);
		}
		catch(Exception exception){
			return false;
		}
	}

	@Override
	public boolean canSeeGoal(Direction direction) throws UnsupportedOperationException {
		if (!hasDistanceSensor(direction)){
			throw new UnsupportedOperationException();
		}
		if (distanceToObstacle(direction) == Integer.MAX_VALUE){
			setBatteryLevel(getBatteryLevel() + 1);
			return true;
		}
		else{
			setBatteryLevel(getBatteryLevel() + 1);
			return false;
		}
	}

	@Override
	public boolean isInsideRoom() throws UnsupportedOperationException {
		if (!hasRoomSensor()){
			throw new UnsupportedOperationException();
		}
		try{
			if(maze.mazecells.isInRoom(getCurrentPosition()[0], getCurrentPosition()[1])){
				return true;
			}
			return false;
		}
		catch(Exception exception){
			return false;
		}
	}

	@Override
	public boolean hasRoomSensor() {
		return roomSensor;
	}

	@Override
	public int[] getCurrentDirection() {
		int[] x = new int[2];
		x[0] = maze.dx;
		x[1] = maze.dy;
		return x;
	}

	@Override
	public float getBatteryLevel() {
		return batteryLevel;
	}

	@Override
	public void setBatteryLevel(float level) {
		batteryLevel = level;
		maze.battery = level;
	}

	@Override
	public float getEnergyForFullRotation() {
		return 12;
	}

	@Override
	public float getEnergyForStepForward() {
		return 5;
	}

	@Override
	public boolean hasStopped() {
		return stopped;
	}

	@Override
	public int distanceToObstacle(Direction direction) throws UnsupportedOperationException {
		int distance = 0;
		if (!hasDistanceSensor(direction)){
			throw new UnsupportedOperationException();
		}
		try{
			switch(curDir){
			case FORWARD:
				switch(direction){
				case FORWARD:
					while((getCurrentPosition()[1] + distance < maze.mazeh) 
							&& maze.mazecells.hasNoWallOnBottom(getCurrentPosition()[0], getCurrentPosition()[1] + distance)){
						distance = distance + 1;
					}
					if (getCurrentPosition()[1] + distance >= maze.mazeh){
						distance = Integer.MAX_VALUE;
					}
					break;
				case BACKWARD:
					while((getCurrentPosition()[1] - distance >= 0) 
							&& maze.mazecells.hasNoWallOnTop(getCurrentPosition()[0], getCurrentPosition()[1] - distance)){
						distance = distance + 1;
					}
					if (getCurrentPosition()[1] - distance < 0){
						distance = Integer.MAX_VALUE;
					}
					break;
				case RIGHT:
					while((getCurrentPosition()[0] + distance < maze.mazew) 
							&& maze.mazecells.hasNoWallOnRight(getCurrentPosition()[0] + distance, getCurrentPosition()[1])){
						distance = distance + 1;
					}
					if (getCurrentPosition()[0] + distance >= maze.mazew){
						distance = Integer.MAX_VALUE;
					}
					break;
				case LEFT:
					while((getCurrentPosition()[0] - distance >= 0) 
							&& maze.mazecells.hasNoWallOnLeft(getCurrentPosition()[0] - distance, getCurrentPosition()[1])){
						distance = distance + 1;
					}
					if (getCurrentPosition()[0] - distance < 0){
						distance = Integer.MAX_VALUE;
					}
					break;
				default: break;
				}
				break;
			case BACKWARD:
				switch(direction){
				case FORWARD:
					while((getCurrentPosition()[1] - distance >= 0) 
							&& maze.mazecells.hasNoWallOnTop(getCurrentPosition()[0], getCurrentPosition()[1] - distance)){
						distance = distance + 1;
					}
					if (getCurrentPosition()[1] - distance < 0){
						distance = Integer.MAX_VALUE;
					}
					break;
				case BACKWARD:
					while((getCurrentPosition()[1] + distance < maze.mazeh) 
							&& maze.mazecells.hasNoWallOnBottom(getCurrentPosition()[0], getCurrentPosition()[1] + distance)){
						distance = distance + 1;
					}
					if (getCurrentPosition()[1] + distance >= maze.mazeh){
						distance = Integer.MAX_VALUE;
					}
					break;
				case RIGHT:
					while((getCurrentPosition()[0] - distance >= 0) 
							&& maze.mazecells.hasNoWallOnLeft(getCurrentPosition()[0] - distance, getCurrentPosition()[1])){
						distance = distance + 1;
					}
					if (getCurrentPosition()[0] - distance < 0){
						distance = Integer.MAX_VALUE;
					}
					break;
				case LEFT:
					while((getCurrentPosition()[0] + distance < maze.mazew) 
							&& maze.mazecells.hasNoWallOnRight(getCurrentPosition()[0] + distance, getCurrentPosition()[1])){
						distance = distance + 1;
					}
					if (getCurrentPosition()[0] + distance >= maze.mazew){
						distance = Integer.MAX_VALUE;
					}
					break;
				default: break;
				}
				break;
			case LEFT:
				switch(direction){
				case FORWARD:
					while((getCurrentPosition()[0] - distance >= 0) 
							&& maze.mazecells.hasNoWallOnLeft(getCurrentPosition()[0] - distance, getCurrentPosition()[1])){
						distance = distance + 1;
					}
					if (getCurrentPosition()[0] - distance < 0){
						distance = Integer.MAX_VALUE;
					}
					break;
				case BACKWARD:
					while((getCurrentPosition()[0] + distance < maze.mazew) 
							&& maze.mazecells.hasNoWallOnRight(getCurrentPosition()[0] + distance, getCurrentPosition()[1])){
						distance = distance + 1;
					}
					if (getCurrentPosition()[0] + distance >= maze.mazew){
						distance = Integer.MAX_VALUE;
					}
					break;
				case RIGHT:
					while((getCurrentPosition()[1] + distance < maze.mazeh) 
							&& maze.mazecells.hasNoWallOnBottom(getCurrentPosition()[0], getCurrentPosition()[1] + distance)){
						distance = distance + 1;
					}
					if (getCurrentPosition()[1] + distance >= maze.mazeh){
						distance = Integer.MAX_VALUE;
					}
					break;
				case LEFT:
					while((getCurrentPosition()[1] - distance >= 0) 
							&& maze.mazecells.hasNoWallOnTop(getCurrentPosition()[0], getCurrentPosition()[1] - distance)){
						distance = distance + 1;
					}
					if (getCurrentPosition()[1] - distance < 0){
						distance = Integer.MAX_VALUE;
					}
					break;
				default: break;
				}
				break;
			case RIGHT:
				switch(direction){
				case FORWARD:
					while((getCurrentPosition()[0] + distance < maze.mazew) 
							&& maze.mazecells.hasNoWallOnRight(getCurrentPosition()[0] + distance, getCurrentPosition()[1])){
						distance = distance + 1;
					}
					if (getCurrentPosition()[0] + distance >= maze.mazew){
						distance = Integer.MAX_VALUE;
					}
					break;
				case BACKWARD:
					while((getCurrentPosition()[0] - distance >= 0) 
							&& maze.mazecells.hasNoWallOnLeft(getCurrentPosition()[0] - distance, getCurrentPosition()[1])){
						distance = distance + 1;
					}
					if (getCurrentPosition()[0] - distance < 0){
						distance = Integer.MAX_VALUE;
					}
					break;
				case RIGHT:
					while((getCurrentPosition()[1] - distance >= 0) 
							&& maze.mazecells.hasNoWallOnTop(getCurrentPosition()[0], getCurrentPosition()[1] - distance)){
						distance = distance + 1;
					}
					if (getCurrentPosition()[1] - distance < 0){
						distance = Integer.MAX_VALUE;
					}
					break;
				case LEFT:
					while((getCurrentPosition()[1] + distance < maze.mazeh) 
							&& maze.mazecells.hasNoWallOnBottom(getCurrentPosition()[0], getCurrentPosition()[1] + distance)){
						distance = distance + 1;
					}
					if (getCurrentPosition()[1] + distance >= maze.mazeh){
						distance = Integer.MAX_VALUE;
					}
					break;
				default: break;
				}
				break;
			default: break;
			}
		}
		catch (Exception exception){
			setBatteryLevel(getBatteryLevel() - 1);
			return Integer.MAX_VALUE;
		}
		setBatteryLevel(getBatteryLevel() - 1);
		return distance;
	}

	@Override
	public boolean hasDistanceSensor(Direction direction) {
		switch(direction){
		case FORWARD:
			return forwardSensor;
		case BACKWARD:
			return backwardSensor;
		case LEFT:
			return leftSensor;
		case RIGHT:
			return rightSensor;
		default: return false;
		}
	}
	
}
