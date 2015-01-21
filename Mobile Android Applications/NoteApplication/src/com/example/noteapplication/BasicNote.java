package com.example.noteapplication;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;



public class BasicNote {
	// A note consists of a key and a value where the key is a reference
	// to the value, or the actual text of the note
	// The key is not arbitrary, rather the key is based on the date at 
	// which the note was created (allows for sorting mechanisms)
	
	private String noteKey;
	private String noteVal;
	
	public String getNoteKey() {
		return noteKey;
	}
	public void setNoteKey(String noteKey) {
		this.noteKey = noteKey;
	}
	public String getNoteVal() {
		return noteVal;
	}
	public void setNoteVal(String noteVal) {
		this.noteVal = noteVal;
	}
	public static BasicNote createNote(){
		
		Locale locale = new Locale("en_US");
		Locale.setDefault(locale);
		
		String pattern = "yyyy-MM-dd HH:mm:ss Z";
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		String key = formatter.format(new Date());
		
		BasicNote note = new BasicNote();
		note.setNoteKey(key);
		note.setNoteVal("");
		return note;
		
	}
	
	@Override
	public String toString() {
		return this.noteVal;
	}
}
