package com.example.noteapplication;

import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.internal.widget.AdapterViewCompat.AdapterContextMenuInfo;
import android.support.v7.internal.widget.AdapterViewCompat.OnItemClickListener;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity { // Originally, it extended ActionBarActivity

	private static final int EDITOR_ACTIVITY_REQUEST = 1001;
	private static final int MENU_DELETE_ID = 1002;
	private int currentNoteId;
	private NoteData dataSource;
	private List<BasicNote> notes;
	private NoteAdapter noteAdapter;
	private ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//	
		//		BasicNote testNote = BasicNote.createNote();
		//		testNote.setNoteVal("Note #1");
		//		try {
		//			Thread.sleep(1000);
		//		} catch (InterruptedException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}
		//		BasicNote testNote2 = BasicNote.createNote();
		//		testNote2.setNoteVal("Note #2");
		//		
		//		dataSource = new NoteData(this);
		//		dataSource.removeAll();
		//		dataSource.update(testNote);
		//		dataSource.update(testNote2);

		dataSource = new NoteData(this);
		refreshDisplay();
		registerForContextMenu(lv);

	}

	private void refreshDisplay() {
		notes = dataSource.findAll();
		//		String[] strList = {"note1", "note2", "note3"};

		noteAdapter = new NoteAdapter(this, R.layout.list_item, notes);
		//		ArrayAdapter<String> adapter = 
		//				new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strList);
		lv = (ListView) findViewById(R.id.noteList);
		lv.setAdapter(noteAdapter);

		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				BasicNote note = MainActivity.this.noteAdapter.getItem(arg2);
				Intent intent = new Intent(MainActivity.this, NoteEditor.class);
				intent.putExtra("key", note.getNoteKey());
				intent.putExtra("text", note.getNoteVal());
				startActivityForResult(intent, EDITOR_ACTIVITY_REQUEST);

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int itemId = item.getItemId();
		if (itemId == R.id.action_create) {
			createNote();
			Toast toast = Toast.makeText(this, "createNote() called", Toast.LENGTH_SHORT);
			toast.show();
		}
		return super.onOptionsItemSelected(item);
	}

	private void createNote() {
		BasicNote note = BasicNote.createNote();
		Intent intent = new Intent(this, NoteEditor.class);
		intent.putExtra("key", note.getNoteKey());
		intent.putExtra("text", note.getNoteVal());
		startActivityForResult(intent, EDITOR_ACTIVITY_REQUEST);
	}
	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		if(arg0 == EDITOR_ACTIVITY_REQUEST && arg1 == RESULT_OK){
			BasicNote note = new BasicNote();
			note.setNoteKey(arg2.getStringExtra("key"));
			note.setNoteVal(arg2.getStringExtra("text"));
			dataSource.update(note);
			refreshDisplay();
		}
		//			super.onActivityResult(arg0, arg1, arg2);
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		
		AdapterView.AdapterContextMenuInfo info = (android.widget.AdapterView.AdapterContextMenuInfo) menuInfo;
		currentNoteId = (int) info.id;
		menu.add(0, MENU_DELETE_ID, 0, "Delete");
	}
	@Override
		public boolean onContextItemSelected(MenuItem item) {
			
			if (item.getItemId() == MENU_DELETE_ID) {
				BasicNote note = notes.get(currentNoteId);
				dataSource.remove(note);
				refreshDisplay();
			}
			return super.onContextItemSelected(item);
		}

	//	@Override
	//	public void onItemClick(AdapterViewCompat<?> arg0, View arg1, int arg2,
	//			long arg3) {
	//		Toast toast = Toast.makeText(this, "called onItemClick()", Toast.LENGTH_LONG);
	//		toast.show();
	//		BasicNote note = this.noteAdapter.getItem(arg2);
	//		Intent intent = new Intent(this, NoteEditor.class);
	//		intent.putExtra("key", note.getNoteKey());
	//		intent.putExtra("text", note.getNoteVal());
	//		startActivityForResult(intent, 1001);
	//		
	//	}
}
