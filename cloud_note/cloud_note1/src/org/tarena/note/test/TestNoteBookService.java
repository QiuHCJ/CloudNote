package org.tarena.note.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.entity.NoteBook;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.NoteBookService;

public class TestNoteBookService {
	private  ApplicationContext context;
	private NoteBookService service;
	
	@Before
	public void init(){
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		service = context.getBean(NoteBookService.class);
	}
	
	@Test
	public void test1(){
		NoteResult result = service.loadNoteBooks("39295a3d-cc9b-42b4-b206-a2e7fab7e77c");
		System.out.println(result.getData());
	}
	
	@Test
	public void test2(){
		
		NoteBook noteBook = new NoteBook();
		noteBook.setCn_notebook_name("hello");
		noteBook.setCn_user_id("39295a3d-cc9b-42b4-b206-a2e7fab7e77c");
		
		NoteResult result = service.addNoteBook(noteBook);
		
		System.out.println(result.getMsg());
		
	}
}
