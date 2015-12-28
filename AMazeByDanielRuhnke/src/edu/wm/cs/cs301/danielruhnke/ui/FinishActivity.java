package edu.wm.cs.cs301.danielruhnke.ui;

import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import edu.wm.cs.cs301.danielruhnke.falstad.Constants;
import edu.wm.cs.cs301.danielruhnke.falstad.GlobalMazeHolder;
import edu.wm.cs.cs301.danielruhnke.falstad.Maze;
import edu.wm.cs.cs301.danielruhnke.falstad.MazeFileWriter;

public class FinishActivity extends ActionBarActivity {

	private static final String skillTAG = "Skill level of maze saved";
	private static final String saveTAG = "Save Maze button pressed";
	
	/**
	 * Creates the final screen
	 * writes text based off whether the player won or lost
	 * prints how much energy is consumed and path length
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finish);
		
		Bundle extras = getIntent().getExtras();
		boolean success = extras.getBoolean(PlayActivity.EXTRA_SOLVED);
		if (success){
			((TextView) findViewById(R.id.textViewFinish)).setText("You have won!");
		}
		else{
			((TextView) findViewById(R.id.textViewFinish)).setText("You have lost!");
		}
		
		int energy = extras.getInt(PlayActivity.EXTRA_BATTERY);
		int path = extras.getInt(PlayActivity.EXTRA_PATH);
		((TextView) findViewById(R.id.textViewEnergy)).setText("Energy Consumed: " + Integer.toString(energy));
		((TextView) findViewById(R.id.textViewPath)).setText("Path Length: " + Integer.toString(path));
	}

	/**
	 * Creates menu
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.finish, menu);
		return true;
	}

	/**
	 * Dictates what happens when an item on the menu is clicked
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * Sends the screen back to the main activity when the back button is pressed
	 */
	public void onBackPressed(){
		Intent intent = new Intent(this, AMazeActivity.class);
		startActivity(intent);
	}
	
	/**
	 * Saves maze when button is pressed
	 * @param view button pressed
	 */
	public void onSaveMaze(View view){
		Bundle extras = getIntent().getExtras();
		int skill = extras.getInt(AMazeActivity.EXTRA_SKILL);
		Maze maze = GlobalMazeHolder.maze;
		MazeFileWriter.store(getFilesDir().toString() + "/skill_" + Integer.toString(skill), maze.mazew, maze.mazeh, 
				Constants.SKILL_ROOMS[skill], Constants.SKILL_PARTCT[skill], maze.rootnode, 
				maze.mazecells, maze.mazedists.getDists(), maze.px, maze.py);
		Log.v(saveTAG, "Maze Saved");
		Log.v(skillTAG, Integer.toString(skill));
	}
}
