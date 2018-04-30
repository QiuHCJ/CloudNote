package org.tarena.note.dao;

import java.util.List;

import org.tarena.note.annotation.mybatisRepository;
import org.tarena.note.entity.Note;
import org.tarena.note.entity.ShareNote;

@mybatisRepository
public interface ShareNoteDao {

	void saveOne(ShareNote shareNote);
	
	String findNoteId(Note note);
	
	List<String> findTitleByName(String noteName);
}
