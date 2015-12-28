package edu.wm.cs.cs301.danielruhnke.ui;

import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import java.util.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class AMazeActivity extends ActionBarActivity {
	
	public final static String EXTRA_SKILL = "edu.wm.cs.cs301.danielruhnke.ui.SKILL";
	public final static String EXTRA_GENERATION = "edu.wm.cs.cs301.danielruhnke.ui.GENERATION";
	
	private static final String skillTAG = "Skill Selected";
	private static final String generationTAG = "Generation Selected";

	/**
	 * Creates start screen
	 * Adds spinner with different generation options
	 * Adds seek bar for skill level
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_amaze);
		
		Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		List<String> list = new ArrayList<String>();
		list.add("Backtracking");
		list.add("Prim\'s");
		list.add("Eller\'s");
		list.add("Preloaded Maze");
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		
		SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar1); 
	    final TextView seekBarValue = (TextView)findViewById(R.id.textViewSkill);
	    seekBarValue.setText(String.valueOf(0));

	    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){ 

	    	@Override 
	    	public void onProgressChanged(SeekBar seekBar, int progress, 
	    	boolean fromUser) { 
	    		seekBarValue.setText(String.valueOf(progress)); 
	    	} 

	    	@Override 
	    	public void onStartTrackingTouch(SeekBar seekBar) { 
	    		//Nothing
	    	} 

	    	@Override 
	    	public void onStopTrackingTouch(SeekBar seekBar) { 
	    	//Nothing
	    	} 
	    }); 
	}

	/**
	 * Creates options menu
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.amaze, menu);
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
	
	/** Called when the user clicks the Generate button */
	public void generateMaze(View view) {
		Intent intent = new Intent(this, GeneratingActivity.class);
		
		SeekBar skillLevelBar = (SeekBar) findViewById(R.id.seekBar1);
		int skillLevel = skillLevelBar.getProgress();
		intent.putExtra(EXTRA_SKILL, skillLevel);
		
		Spinner generationSelectionSpinner = (Spinner) findViewById(R.id.spinner1);
		String generationSelection = generationSelectionSpinner.getSelectedItem().toString();
		intent.putExtra(EXTRA_GENERATION, generationSelection);
		Log.v(skillTAG, Integer.toString(skillLevel));
		Log.v(generationTAG, generationSelection);
		startActivity(intent);
	}
}
