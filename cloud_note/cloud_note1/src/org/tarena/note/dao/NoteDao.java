package org.tarena.note.dao;

import java.util.List;
import java.util.Map;

import org.tarena.note.annotation.mybatisRepository;
import org.tarena.note.entity.Note;

@mybatisRepository
public interface NoteDao {
	
	List<Note>	findNotesByNoteBookId(String noteBookId);
	
	Note findNoteById(String noteId);
	
	int updateNote(Note note);
	
	void saveNote(Note note);
	
	void deleteNote(String noteId);
	
	List<Note> findRecNotes(String noteBookId);
}
