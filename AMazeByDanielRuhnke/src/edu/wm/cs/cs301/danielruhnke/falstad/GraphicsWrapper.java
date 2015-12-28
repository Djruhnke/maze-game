package edu.wm.cs.cs301.danielruhnke.falstad;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

//import java.awt.*;

public class GraphicsWrapper extends View{

	
	//Graphics field
	//Graphics WrapperGraphic;
	//Point p;
	//Color col;
	
	//Directions for GraphicsWrapper
	//final static int UP = Event.UP;
	//final static int DOWN = Event.DOWN;
	//final static int LEFT = Event.LEFT;
	//final static int RIGHT = Event.RIGHT;
	
	public static enum DirectionEvents {UP, DOWN, LEFT, RIGHT}
	
	//final static Font WrapperLargeBannerFont = new Font("TimesRoman", Font.BOLD, 48);
	//final static Font WrapperSmallBannerFont = new Font("TimesRoman", Font.BOLD, 16);
	
	//New Fields for Anroid
	Canvas canvas;
	Bitmap bitmap;
	Paint paint;
	
	/**
	 * Constructor for the class
	 */
	public GraphicsWrapper(Context context, AttributeSet attrs) {
		super(context, attrs) ;
		paint = new Paint();
		bitmap = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888);
		canvas = new Canvas(bitmap);
		//this.setFocusable(false) ;
	}
	
	protected void onDraw(Canvas canvas){
		super.onDraw(canvas);
		canvas.drawBitmap(bitmap, 0, 0, paint);
	}
	
	/**
	 * Replacement class for Color
	 * @author djruh_000
	 *
	 */
	public static class WrapperColor extends Color{
		final static int darkGray = DKGRAY;
		final static int black = BLACK;
		final static int white = WHITE;
		final static int gray = GRAY;
		final static int red = RED;
		final static int yellow = YELLOW;
		
		private int r;
		private int g;
		private int b;
		private int a = 255;
		private int RGBvalue;
		
		WrapperColor(int val1, int val2, int val3){
			super();
			r = val1;
			g = val2;
			b = val3;
			RGBvalue = 0;
		}
		
		WrapperColor(int rgb){
			super();
			RGBvalue = 0xff000000 | rgb;
		}
		
		public int getRGB(){
			int value;
			if (RGBvalue == 0){
				value = ((a & 0xFF) << 24) |
		                ((r & 0xFF) << 16) |
		                ((g & 0xFF) << 8)  |
		                ((b & 0xFF) << 0);
			}
			else{
				value = RGBvalue;
			}
			return value;
		}
	}
	
	/**
	 * Calls the java.awt setColor method
	 * @param color color to be set
	 */
	public void WrapperSetColor(int color){
		paint.setColor(color);
		//WrapperGraphic.setColor(color);
	}
	
	/**
	 * Replacement class for Point
	 * @author djruh_000
	 *
	 */
	/*public static class WrapperPoint extends Point{
		
		WrapperPoint(int x1, int x2){
			super(x1, x2);
		}
	}*/
	
	/**
	 * Replacement class for Font
	 * @author djruh_000
	 *
	 */
	/*public static class WrapperFont extends Font{
		
		WrapperFont(String fonttype, int type, int size){
			super(fonttype, type, size);
		}
	}*/
	
	/**
	 * Saves the graphics object to a global field
	 */
	/*public void setWrapperGraphics() {
		WrapperGraphic = getBufferGraphics();
	}*/
	
	/**
	 * Calls the java.awt fillRect method
	 * @param x param for fellRect
	 * @param y param for fellRect
	 * @param width param for fellRect
	 * @param height param for fellRect
	 */
	public void WrapperFillRect(int x, int y, int width, int height){
		//WrapperGraphic.fillRect(x, y, width, height);
		canvas.drawRect(x, y, x + width, y + height, paint);
	}
	
	/**
	 * Calls the java.awt setFront method
	 * @param font font to be set
	 */
	/*public void WrapperSetFont(Font font){
		WrapperGraphic.setFont(font);
	}*/
	
	/**
	 * Calls the java.awt drawString method
	 * @param str param for drawString
	 * @param ypos param for drawString
	 */
	/*public void WrapperCenterString(String str, int ypos){
		WrapperGraphic.drawString(str, (Constants.VIEW_WIDTH-WrapperGraphic.getFontMetrics().stringWidth(str))/2, ypos);
	}*/
	
	/**
	 * Sets a point variable to the global variable p
	 * @param x x coordinate for point
	 * @param y y coordinate for point
	 */
	/*public void WrapperSetPoint(int x, int y){
		p = new Point(x, y);
	}*/
	
	/**
	 * Calls the java.awt fillPolygon method
	 * @param x param for fillPolygon
	 * @param y param for fillPolygon
	 * @param z param for fillPolygon
	 */
	public void WrapperFillPolygon(int[] x, int[] y, int z){
		//WrapperGraphic.fillPolygon(x, y, z);
		Path outline = new Path();
		outline.reset();
		outline.moveTo(x[0], y[0]);
		for (int i = 1; i < z; i++){
			outline.lineTo(x[i], y[i]);
		}
		outline.lineTo(x[0], y[0]);
		canvas.drawPath(outline, paint);
	}
	
	/**
	 * Calls the java.awt drawLine method
	 * @param a param for drawLine
	 * @param b param for drawLine
	 * @param c param for drawLine
	 * @param d param for drawLine
	 */
	public void WrapperDrawLine(int a, int b, int c, int d){
		//WrapperGraphic.drawLine(a, b, c, d);
		canvas.drawLine(a, b, c, d, paint);
	}
	
	/**
	 * Calls the java.awt fillOval method
	 * @param a param for fillOval
	 * @param b param for fillOval
	 * @param c param for fillOval
	 * @param d param for fillOval
	 */
	public void WrapperFillOval(int a, int b, int c, int d){
		//WrapperGraphic.fillOval(a, b, c, d);
		RectF oval = new RectF(a, b, a + c, b + d);
		canvas.drawOval(oval, paint);
	}
	
	/**
	 * Saves the given Color object to the global field col
	 * @param a red value of color
	 * @param b green value of color
	 * @param c blue value of color
	 */
	/*public void WrapperSaveColor(int a, int b, int c){
		col = new Color(a, b, c);
	}
	
	public void WrapperSaveDifferentColor(int x){
		col = new Color(x);
	}*/
	
	public void update(){
		invalidate();
	}
}
