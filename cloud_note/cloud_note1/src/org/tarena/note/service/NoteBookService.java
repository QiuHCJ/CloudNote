package org.tarena.note.service;

import org.tarena.note.entity.NoteBook;
import org.tarena.note.entity.NoteResult;

public interface NoteBookService {
	
 	NoteResult loadNoteBooks(String cn_user_id);
 	
 	NoteResult addNoteBook(NoteBook notebook);
 	
 	NoteResult delNoteBook(String noteBookId);
 	
 	NoteResult rename(String noteBookId,String newNoteBookName);
}
