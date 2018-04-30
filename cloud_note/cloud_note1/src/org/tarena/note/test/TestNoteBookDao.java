package org.tarena.note.test;


import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.NoteBookService;

public class TestNoteBookDao {
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
}
