package org.tarena.note.service;


import org.tarena.note.entity.Note;
import org.tarena.note.entity.NoteResult;

public interface NoteService {

	NoteResult loadNotes(String noteBookId);
	
	NoteResult loadNote(String noteId);
	
	NoteResult updateNote(Note note);
	
	NoteResult addNote(Note note);
	
	NoteResult deleteNote(String noteId);
	
	NoteResult loadRecNotes(String noteBookId);
	
	NoteResult shareOne(String noteId);
	
	NoteResult searchshareNote(String keyword);
}
