package org.tarena.note.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.entity.NoteBook;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.impl.NoteBookServiceImpl;

@Controller
@RequestMapping("noteBook")
public class NoteBookcontroller {

	@Autowired
	private NoteBookServiceImpl service;
	
	@ResponseBody
	@RequestMapping("loadBooks")
	public NoteResult loadBooks(String cn_user_id){
		NoteResult result = service.loadNoteBooks(cn_user_id);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("addNoteBook")
	public NoteResult addNoteBook(NoteBook noteBook){
	 	NoteResult result = service.addNoteBook(noteBook);
	 	
	 	return result;
	}
	
	@ResponseBody
	@RequestMapping("deleteNoteBook")
	public NoteResult deleteBook(String noteBookId){
		NoteResult result = service.delNoteBook(noteBookId);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("renameNoteBook")
	public NoteResult rename(String noteBookId,String newNoteBookName){
		NoteResult result = service.rename(noteBookId, newNoteBookName);
		return result;
	}
	
}









