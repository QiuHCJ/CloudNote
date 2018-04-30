package org.tarena.note.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.entity.Note;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.NoteService;

@Controller
@RequestMapping("/note")
public class NoteController {

	@Autowired
	private NoteService service;
	
	@ResponseBody
	@RequestMapping("/loadNotes")
	public NoteResult loadNotes(String noteBookId){
		
		NoteResult result = service.loadNotes(noteBookId);
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/loadNote")
	public NoteResult loadNote(String noteId){
		NoteResult result = service.loadNote(noteId); 
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/updateNote")
	public NoteResult updateNote(Note note){
		System.out.println(note);
		NoteResult result = service.updateNote(note);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/addNote")
	public NoteResult addNote(Note note){
		
		NoteResult result = service.addNote(note);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/loadRecNotes")
	public NoteResult loadRecNotes(String cn_notebook_id){
		
		NoteResult result = service.loadRecNotes(cn_notebook_id);
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/deleteNote")
	public NoteResult deleteNote(String noteId){
		NoteResult result = service.deleteNote(noteId);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("share")
	public NoteResult shareOne(String noteId){
		
		NoteResult result = service.shareOne(noteId);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("searchShareNote")
	public NoteResult searchShareNotes(String keyword){
		
		NoteResult result = service.searchshareNote(keyword);
		return result;
	}
}





























