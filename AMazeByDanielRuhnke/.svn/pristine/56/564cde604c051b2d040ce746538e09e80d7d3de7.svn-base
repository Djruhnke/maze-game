package edu.wm.cs.cs301.danielruhnke.falstad;

import java.util.*;

public class MazeBuilderEller extends MazeBuilder{
	
	Random randomNum = new Random();
	
	/**
	 * Constructor for a randomized maze generation
	 */
	public MazeBuilderEller(){
		random = SingleRandom.getRandom();
	}
	
	/**
	 * Constructor with option to make maze generation deterministic or random
	 */
	public MazeBuilderEller(boolean deterministic){
		if (true == deterministic)
		{
			// Control random number generation
			// HINT: check http://download.oracle.com/javase/6/docs/api/java/util/Random.html\
			SingleRandom.setSeed(1);
		}
		random = SingleRandom.getRandom();
	}
	
	/**
	 * Generate pathways with Eller Algorithm
	 * Uses a 2D array and populates it with individual numbers to represent sets
	 * If room present, all cells in room receive same set number
	 * Then iterate through each row randomly tearing down walls
	 * Then checks to make sure each set has a path to next row
	 * Then on last row joins all disjoint sets
	 * 
	 * Concerning Rooms:
	 * Every wall that is not a border leading into a room is automatically torn down
	 * If there is a set that is above a room border so that it cannot make a path down
	 * it then tears walls horizontally on both sides to connect it to other sets
	 * 
	 * Probability for wall tear down is 50% horizontally and 25% vertically
	 * This is easily adjustable in the code below
	 */
	@Override
	protected void generatePathways() {
		//Initialize 2D array with set numbers
		int[][] cellSets = new int[width][height];
		int count = 1;
		int row = 0;
		int test;
		for (int i = 0; i < width; i++){
			for (int j = 0; j < height; j++){
				if (cells.hasNoWallOnLeft(i, j) && (dists.getDistance(i, j) != 1)){
					cellSets[i][j] = cellSets[i-1][j];
				}
				else if (cells.hasNoWallOnTop(i, j) && (dists.getDistance(i, j) != 1)){
					cellSets[i][j] = cellSets[i][j-1];
				}
				else{
					cellSets[i][j] = count;
					count = count + 1;
				}
			}
		}
		
		//
		while(row != (height-1)){
			//Break horizontal walls in row
			for (int k = 0; k < (width - 1); k++){
				if ((cellSets[k][row] != cellSets[k+1][row]) && (randomNum.nextInt() % 2 == 0) 
						&& cells.hasWallOnRight(k, row) && cells.canGo(k, row, 1, 0)){
					cells.deleteWall(k, row, 1, 0);
					test = cellSets[k+1][row];
					for (int i = 0; i < width; i++){
						for (int j = 0; j < height; j++){
							if (cellSets[i][j] == test){
								cellSets[i][j] = cellSets[k][row];
							}
						}
					}
				}
				else if (cells.canGo(k, row, 1, 0) && (cells.isInRoom(k, row+1) ^ cells.isInRoom(k, row))){
					cells.deleteWall(k, row, 1, 0);
					test = cellSets[k+1][row];
					for (int i = 0; i < width; i++){
						for (int j = 0; j < height; j++){
							if (cellSets[i][j] == test){
								cellSets[i][j] = cellSets[k][row];
							}
						}
					}
				}
			}
			//Insure each set has at least one broken wall to next row
			Set<Integer> resolvedSets = new HashSet<Integer>();
			for (int m = 0; m < width; m++){
				if (!resolvedSets.contains(cellSets[m][row])){
					boolean verticalWall = false;
					while(!verticalWall){
						for (int n = m; n < width; n++){
							if ((cellSets[m][row] == cellSets[n][row]) && (randomNum.nextInt() % 4 == 0) 
									&& cells.hasWallOnBottom(n, row) && cells.canGo(n, row, 0, 1)){
								cells.deleteWall(n, row, 0, 1);
								test = cellSets[n][row+1];
								for (int i = 0; i < width; i++){
									for (int j = 0; j < height; j++){
										if (cellSets[i][j] == test){
											cellSets[i][j] = cellSets[n][row];
										}
									}
								}
								verticalWall = true;
								resolvedSets.add(cellSets[n][row]);
							}
							else if ((cellSets[m][row] == cellSets[n][row]) && cells.hasNoWallOnBottom(n, row)){
								verticalWall = true;
								resolvedSets.add(cellSets[n][row]);
							}
							else if ((cellSets[m][row] == cellSets[n][row]) && cells.hasWallOnBottom(n, row) 
									&& cells.canGo(n, row, 0, 1) && (cells.isInRoom(n, row) ^ cells.isInRoom(n, row+1))){
								cells.deleteWall(n, row, 0, 1);
								test = cellSets[n][row+1];
								for (int i = 0; i < width; i++){
									for (int j = 0; j < height; j++){
										if (cellSets[i][j] == test){
											cellSets[i][j] = cellSets[n][row];
										}
									}
								}
								verticalWall = true;
								resolvedSets.add(cellSets[n][row]);
							}
							else if ((!cells.canGo(n, row, 0, 1)) && (cellSets[m][row] == cellSets[n][row]) && (!verticalWall)){
								if (resolvedSets.contains(cellSets[n-1][row]) || resolvedSets.contains(cellSets[n+1][row])){
									verticalWall = true;
									resolvedSets.add(cellSets[n][row]);
								}
								cells.deleteWall(n, row, 1, 0);
								cells.deleteWall(n, row, -1, 0);
								test = cellSets[n+1][row];
								int test1 = cellSets[n-1][row];
								for (int i = 0; i < width; i++){
									for (int j = 0; j < height; j++){
										if ((cellSets[i][j] == test) || (cellSets[i][j] == test1)){
											cellSets[i][j] = cellSets[n][row];
										}
									}
								}
							}
						}
					}
				}
			}
			//Increment to next row
			row = row + 1;
		}
		for (int p = 0; p < (width - 1); p++){
			if(cellSets[p][row] != cellSets[p+1][row]){
				cells.deleteWall(p, row, 1, 0);
				test = cellSets[p+1][row];
				for (int i = 0; i < width; i++){
					for (int j = 0; j < height; j++){
						if (cellSets[i][j] == test){
							cellSets[i][j] = cellSets[p][row];
						}
					}
				}
			}
		}
	}
}
