package com.example.noteapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class NoteEditor extends ActionBarActivity {
	
	private BasicNote currentNote;
	private EditText editText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_note_editor);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		Intent intent = this.getIntent();
		currentNote = new BasicNote();
		currentNote.setNoteKey(intent.getStringExtra("key"));
		currentNote.setNoteVal(intent.getStringExtra("text"));
		
		editText = (EditText) findViewById(R.id.noteText);
		editText.setText(currentNote.getNoteVal());
		editText.setSelection(currentNote.getNoteVal().length());
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int itemId = item.getItemId();
		if (itemId == android.R.id.home) {
			saveAndFinish();
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void saveAndFinish(){
		String noteText = editText.getText().toString();
		
		Intent intent = new Intent();
		intent.putExtra("key", currentNote.getNoteKey());
		intent.putExtra("text", noteText);
		setResult(RESULT_OK, intent);
		finish();
		
	}
}
