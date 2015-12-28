package falstad;

import java.awt.*;

public class GraphicsWrapper extends MazePanel{

	
	//Graphics field
	Graphics WrapperGraphic;
	Point p;
	Color col;
	
	//Directions for GraphicsWrapper
	final static int UP = Event.UP;
	final static int DOWN = Event.DOWN;
	final static int LEFT = Event.LEFT;
	final static int RIGHT = Event.RIGHT;
	
	final static Font WrapperLargeBannerFont = new Font("TimesRoman", Font.BOLD, 48);
	final static Font WrapperSmallBannerFont = new Font("TimesRoman", Font.BOLD, 16);
	
	/**
	 * Constructor for the class
	 */
	public GraphicsWrapper() {
		super() ;
		this.setFocusable(false) ;
	}
	
	/**
	 * Replacement class for Color
	 * @author djruh_000
	 *
	 */
	public static class WrapperColor extends Color{
		
		WrapperColor(int r, int g, int b){
			super(r,g,b);
		}
	}
	
	/**
	 * Calls the java.awt setColor method
	 * @param color color to be set
	 */
	public void WrapperSetColor(Color color){
		WrapperGraphic.setColor(color);
	}
	
	/**
	 * Replacement class for Point
	 * @author djruh_000
	 *
	 */
	public static class WrapperPoint extends Point{
		
		WrapperPoint(int x1, int x2){
			super(x1, x2);
		}
	}
	
	/**
	 * Replacement class for Font
	 * @author djruh_000
	 *
	 */
	public static class WrapperFont extends Font{
		
		WrapperFont(String fonttype, int type, int size){
			super(fonttype, type, size);
		}
	}
	
	/**
	 * Saves the graphics object to a global field
	 */
	public void setWrapperGraphics() {
		WrapperGraphic = getBufferGraphics();
	}
	
	/**
	 * Calls the java.awt fillRect method
	 * @param x param for fellRect
	 * @param y param for fellRect
	 * @param width param for fellRect
	 * @param height param for fellRect
	 */
	public void WrapperFillRect(int x, int y, int width, int height){
		WrapperGraphic.fillRect(x, y, width, height);
	}
	
	/**
	 * Calls the java.awt setFront method
	 * @param font font to be set
	 */
	public void WrapperSetFont(Font font){
		WrapperGraphic.setFont(font);
	}
	
	/**
	 * Calls the java.awt drawString method
	 * @param str param for drawString
	 * @param ypos param for drawString
	 */
	public void WrapperCenterString(String str, int ypos){
		WrapperGraphic.drawString(str, (Constants.VIEW_WIDTH-WrapperGraphic.getFontMetrics().stringWidth(str))/2, ypos);
	}
	
	/**
	 * Sets a point variable to the global variable p
	 * @param x x coordinate for point
	 * @param y y coordinate for point
	 */
	public void WrapperSetPoint(int x, int y){
		p = new Point(x, y);
	}
	
	/**
	 * Calls the java.awt fillPolygon method
	 * @param x param for fillPolygon
	 * @param y param for fillPolygon
	 * @param z param for fillPolygon
	 */
	public void WrapperFillPolygon(int[] x, int[] y, int z){
		WrapperGraphic.fillPolygon(x, y, z);
	}
	
	/**
	 * Calls the java.awt drawLine method
	 * @param a param for drawLine
	 * @param b param for drawLine
	 * @param c param for drawLine
	 * @param d param for drawLine
	 */
	public void WrapperDrawLine(int a, int b, int c, int d){
		WrapperGraphic.drawLine(a, b, c, d);
	}
	
	/**
	 * Calls the java.awt fillOval method
	 * @param a param for fillOval
	 * @param b param for fillOval
	 * @param c param for fillOval
	 * @param d param for fillOval
	 */
	public void WrapperFillOval(int a, int b, int c, int d){
		WrapperGraphic.fillOval(a, b, c, d);
	}
	
	/**
	 * Saves the given Color object to the global field col
	 * @param a red value of color
	 * @param b green value of color
	 * @param c blue value of color
	 */
	public void WrapperSaveColor(int a, int b, int c){
		col = new Color(a, b, c);
	}
	
	public void WrapperSaveDifferentColor(int x){
		col = new Color(x);
	}
}
