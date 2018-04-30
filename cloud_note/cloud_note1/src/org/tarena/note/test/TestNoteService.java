package org.tarena.note.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.entity.Note;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.NoteService;
import org.tarena.note.util.NoteUtil;

public class TestNoteService {
	private  ApplicationContext context;
	private NoteService service;
	@Before
	public void init(){
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		service = context.getBean(NoteService.class);
	}
	
	@Test
	public void test1(){
		Map<String,String> datas = new HashMap<String, String>();
//		datas.put("cn_user_id", "39295a3d-cc9b-42b4-b206-a2e7fab7e77c");
//		datas.put("cn_notebook_name", "hadoop");
		NoteResult notes = service.loadNotes("516f6f4f-eaa3-4c76-84ff-530b92c7f64d");
		System.out.println(notes.getData());
	}
	
	@Test
	public void test(){
		
		Note note = new Note();
		note.setCn_note_title("ºÚÈô");
		note.setCn_note_id(NoteUtil.createId());
		note.setCn_user_id("39295a3d-cc9b-42b4-b206-a2e7fab7e77c");
		note.setCn_notebook_id("516f6f4f-eaa3-4c76-84ff-530b92c7f64d");
		
		NoteResult result = service.addNote(note);
		
		System.out.println(result.getMsg());
	}
	
}









