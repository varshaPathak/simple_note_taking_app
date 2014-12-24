package com.example.notetakingapp.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import com.example.notetakingapp.MainActivity;

import android.content.Context;
import android.content.SharedPreferences;

public class NotesDataSourse {

	private static final String PREFKEY = "notes";
	private SharedPreferences notePrefs;

	public NotesDataSourse(Context context) {
		notePrefs = context.getSharedPreferences(PREFKEY,Context.MODE_PRIVATE);
	}

	

	public List<NoteItem> findAll(){
		Map<String, ?> notesMap = notePrefs.getAll();
		SortedSet<String> keys = new TreeSet<String>(notesMap.keySet());
		List<NoteItem> noteList = new ArrayList<NoteItem>();
//		NoteItem note = new NoteItem();
		
		for(String key : keys) {
			NoteItem note =  NoteItem.getNew();
			note.setKey(key);
			note.setText((String)notesMap.get(key));
			noteList.add(note);
		}
		if (notesMap.isEmpty()) {
			NoteItem note = NoteItem.getNew();
			noteList.add(note);
		}
	//	NoteItem note = NoteItem.getNew();
	//	noteList.add(note);
		return noteList;
	}

	public boolean update(NoteItem note) {
		
		SharedPreferences.Editor editor	= notePrefs.edit();
		editor.putString(note.getKey(), note.getText());
		editor.commit();


		return true;
	}
	public boolean remove(NoteItem note) {

		if(notePrefs.contains(note.getKey())){
			SharedPreferences.Editor editor	= notePrefs.edit();
			editor.remove(note.getKey());
			editor.commit();

		}

			return true;
		}



	};

