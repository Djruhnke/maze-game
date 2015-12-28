package falstad;

//import java.awt.Color;
//import java.awt.Font;
//import java.awt.FontMetrics;
//import java.awt.Graphics;
import falstad.GraphicsWrapper.*;

public class MazeView extends DefaultViewer {

	Maze maze ; // need to know the maze model to check the state 
	// and to provide progress information in the generating state
	
	public MazeView(Maze m) {
		super() ;
		maze = m;
	}

	/*public void redraw(Graphics gc, int state, int px, int py, int view_dx,
			int view_dy, int walk_step, int view_offset, RangeSet rset, int ang) {
		//dbg("redraw") ;
		switch (state) {
		case Constants.STATE_TITLE:
			redrawTitle(gc);
			break;
		case Constants.STATE_GENERATING:
			redrawGenerating(gc);
			break;
		case Constants.STATE_PLAY:
			// skip this one
			break;
		case Constants.STATE_FINISH:
			redrawFinish(gc);
			break;
		}
	}*/
	
	@Override
	public void redraw(/*Graphics gc*/GraphicsWrapper panel, int state, int px, int py, int view_dx,
			int view_dy, int walk_step, int view_offset, RangeSet rset, int ang, boolean stopped, float battery, int path) {
		//dbg("redraw") ;
		switch (state) {
		case Constants.STATE_TITLE:
			redrawTitle(/*gc*/panel);
			break;
		case Constants.STATE_GENERATING:
			redrawGenerating(/*gc*/panel);
			break;
		case Constants.STATE_PLAY:
			// skip this one
			break;
		case Constants.STATE_FINISH:
			redrawFinish(/*gc*/panel, stopped, battery, path);
			break;
		}
	}
	
	private void dbg(String str) {
		System.out.println("MazeView:" + str);
	}
	
	// 
	
	/**
	 * Helper method for redraw to draw the title screen, screen is hardcoded
	 * @param  gc graphics is the off screen image
	 */
	void redrawTitle(/*Graphics gc*/GraphicsWrapper panel) {
		/*gc*/panel./*setColor*/WrapperSetColor(/*Color.white*/WrapperColor.white);
		/*gc*/panel./*fillRect*/WrapperFillRect(0, 0, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
		/*gc*/panel./*setFont*/WrapperSetFont(panel.WrapperLargeBannerFont);
		//FontMetrics fm = gc.getFontMetrics();
		/*gc*/panel./*setColor*/WrapperSetColor(/*Color.red*/WrapperColor.red);
		panel.WrapperCenterString/*centerString*/(/*gc, fm,*/ "MAZE", 100);
		/*gc*/panel./*setColor*/WrapperSetColor(/*Color.blue*/WrapperColor.blue);
		/*gc*/panel./*setFont*/WrapperSetFont(panel.WrapperSmallBannerFont);
		//fm = gc.getFontMetrics();
		panel.WrapperCenterString/*centerString*/(/*gc, fm,*/ "by Paul Falstad", 160);
		panel.WrapperCenterString/*centerString*/(/*gc, fm,*/ "www.falstad.com", 190);
		/*gc*/panel./*setColor*/WrapperSetColor(/*Color.black*/WrapperColor.black);
		//centerString(gc, fm, "To start, select a skill level.", 250);
		//centerString(gc, fm, "(Press a number from 0 to 9,", 300);
		//centerString(gc, fm, "or a letter from A to F)", 320);
		//centerString(gc, fm, "v1.2", 350);
		panel.WrapperCenterString/*centerString*/(/*gc, fm,*/ "v1.2", 250);
	}
	/**
	 * Helper method for redraw to draw final screen, screen is hard coded
	 * @param gc graphics is the off screen image
	 */
	void redrawFinish(/*Graphics gc*/GraphicsWrapper panel) {
		/*gc*/panel./*setColor*/WrapperSetColor(/*Color.blue*/WrapperColor.blue);
		/*gc*/panel./*fillRect*/WrapperFillRect(0, 0, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
		/*gc*/panel./*setFont*/WrapperSetFont(panel.WrapperLargeBannerFont);
		//FontMetrics fm = gc.getFontMetrics();
		/*gc*/panel./*setColor*/WrapperSetColor(/*Color.yellow*/WrapperColor.yellow);
		panel.WrapperCenterString/*centerString*/(/*gc, fm,*/ "You won!", 100);
		/*gc*/panel./*setColor*/WrapperSetColor(/*Color.orange*/WrapperColor.orange);
		/*gc*/panel./*setFont*/WrapperSetFont(panel.WrapperSmallBannerFont);
		//fm = gc.getFontMetrics();
		panel.WrapperCenterString/*centerString*/(/*gc, fm,*/ "Congratulations!", 160);
		/*gc*/panel./*setColor*/WrapperSetColor(/*Color.white*/WrapperColor.white);
		panel.WrapperCenterString/*centerString*/(/*gc, fm,*/ "Hit any key to restart", 300);
	}
	
	/**
	 * Helper method for redraw to draw final screen, screen is hard coded
	 * @param gc graphics is the off screen image
	 * @param stopped the boolean which describes whether or not the robot has stopped
	 * @param battery the battery used by the robot
	 * @param path the number of steps the robot took
	 */
	void redrawFinish(/*Graphics gc*/GraphicsWrapper panel, boolean stopped, float battery, int path) {
		if (!stopped){
			/*gc*/panel./*setColor*/WrapperSetColor(/*Color.blue*/WrapperColor.blue);
			/*gc*/panel./*fillRect*/WrapperFillRect(0, 0, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
			/*gc*/panel./*setFont*/WrapperSetFont(panel.WrapperLargeBannerFont);
			//FontMetrics fm = gc.getFontMetrics();
			/*gc*/panel./*setColor*/WrapperSetColor(/*Color.yellow*/WrapperColor.yellow);
			panel.WrapperCenterString/*centerString*/(/*gc, fm,*/ "You won!", 100);
			/*gc*/panel./*setColor*/WrapperSetColor(/*Color.orange*/WrapperColor.orange);
			/*gc*/panel./*setFont*/WrapperSetFont(panel.WrapperSmallBannerFont);
			//fm = gc.getFontMetrics();
			panel.WrapperCenterString/*centerString*/(/*gc, fm,*/ "Congratulations!", 160);
			panel.WrapperCenterString/*centerString*/(/*gc, fm,*/ "Path: " + Integer.toString(path), 190);
			panel.WrapperCenterString/*centerString*/(/*gc, fm,*/ "Battery Used: " + Float.toString(battery), 220);
			/*gc*/panel./*setColor*/WrapperSetColor(/*Color.white*/WrapperColor.white);
			panel.WrapperCenterString/*centerString*/(/*gc, fm,*/ "Hit any key to restart", 300);
		}
		else{
			/*gc*/panel./*setColor*/WrapperSetColor(/*Color.blue*/WrapperColor.blue);
			/*gc*/panel./*fillRect*/WrapperFillRect(0, 0, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
			/*gc*/panel./*setFont*/WrapperSetFont(panel.WrapperLargeBannerFont);
			//FontMetrics fm = gc.getFontMetrics();
			/*gc*/panel./*setColor*/WrapperSetColor(/*Color.yellow*/WrapperColor.yellow);
			panel.WrapperCenterString/*centerString*/(/*gc, fm,*/ "You Lost!", 100);
			/*gc*/panel./*setColor*/WrapperSetColor(/*Color.orange*/WrapperColor.orange);
			/*gc*/panel./*setFont*/WrapperSetFont(panel.WrapperSmallBannerFont);
			//fm = gc.getFontMetrics();
			panel.WrapperCenterString/*centerString*/(/*gc, fm,*/ "Try Again!", 160);
			panel.WrapperCenterString/*centerString*/(/*gc, fm,*/ "Path: " + Integer.toString(path), 190);
			panel.WrapperCenterString/*centerString*/(/*gc, fm,*/ "Battery Used: " + Float.toString(battery), 220);
			/*gc*/panel./*setColor*/WrapperSetColor(/*Color.white*/WrapperColor.white);
			panel.WrapperCenterString/*centerString*/(/*gc, fm,*/ "Hit any key to restart", 300);
		}
	}

	/**
	 * Helper method for redraw to draw screen during phase of maze generation, screen is hard coded
	 * only attribute percentdone is dynamic
	 * @param gc graphics is the off screen image
	 */
	void redrawGenerating(/*Graphics gc*/GraphicsWrapper panel) {
		/*gc*/panel./*setColor*/WrapperSetColor(/*Color.yellow*/WrapperColor.yellow);
		/*gc*/panel./*fillRect*/WrapperFillRect(0, 0, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
		/*gc*/panel./*setFont*/WrapperSetFont(panel.WrapperLargeBannerFont);
		//FontMetrics fm = gc.getFontMetrics();
		/*gc*/panel./*setColor*/WrapperSetColor(/*Color.red*/WrapperColor.red);
		panel.WrapperCenterString/*centerString*/(/*gc, fm,*/ "Building maze", 150);
		/*gc*/panel./*setFont*/WrapperSetFont(panel.WrapperSmallBannerFont);
		//fm = gc.getFontMetrics();
		/*gc*/panel./*setColor*/WrapperSetColor(/*Color.black*/WrapperColor.black);
		panel.WrapperCenterString/*centerString*/(/*gc, fm,*/ maze.getPercentDone()+"% completed", 200);
		panel.WrapperCenterString/*centerString*/(/*gc, fm,*/ "Hit escape to stop", 300);
	}
	
	/*private void centerString(Graphics g, FontMetrics fm, String str, int ypos) {
		g.drawString(str, (Constants.VIEW_WIDTH-fm.stringWidth(str))/2, ypos);
	}*/

	//Font largeBannerFont = new Font("TimesRoman", Font.BOLD, 48);
	//Font smallBannerFont = new Font("TimesRoman", Font.BOLD, 16);

}
