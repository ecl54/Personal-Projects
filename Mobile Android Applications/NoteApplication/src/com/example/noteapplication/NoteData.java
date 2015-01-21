package com.example.noteapplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import android.content.Context;
import android.content.SharedPreferences;

// This class exists to handle a notebook's memory usage
// So, we can add and remove notes and also retrieve them from
// memory and give them to MainActivity so they can be actively
// displayed on the UI
public class NoteData {
	
	
	private SharedPreferences notePrefs; // Memory instance to store keys/values
	private final static String PREFKEY = "notes";
	
	public NoteData(Context context){
		notePrefs = context.getSharedPreferences(PREFKEY, context.MODE_PRIVATE);
	}
	
	public List<BasicNote> findAll(){
		
		Map<String, ?> notesMap = notePrefs.getAll();
		SortedSet<String> keys = new TreeSet<String>(notesMap.keySet());
		
		List<BasicNote> noteList = new ArrayList<BasicNote>();
		for (String noteKey: keys){
			BasicNote note = new BasicNote();
			note.setNoteKey(noteKey);
			note.setNoteVal((String) notesMap.get(noteKey));
			noteList.add(note);
		}
		return noteList;
	}
	
	public NoteData update(BasicNote note){
		SharedPreferences.Editor editor = notePrefs.edit();
		// Get an instance of an editor that can modify the SharedPreferences memory instance
		editor.putString(note.getNoteKey(), note.getNoteVal());
		editor.commit();
		return this;
	}
	
	public boolean remove(BasicNote note){

		if(notePrefs.contains(note.getNoteKey())){
			SharedPreferences.Editor editor = notePrefs.edit(); 
			editor.remove(note.getNoteKey());
			editor.commit();
		}
		return true;
	}
	
	public void removeAll(){
		SharedPreferences.Editor editor = notePrefs.edit();
		editor.clear();
		editor.commit();
	}
}
