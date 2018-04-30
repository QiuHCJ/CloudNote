package org.tarena.note.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.dao.UserDao;
import org.tarena.note.entity.User;

public class TestUserdao {
	
	private  ApplicationContext context;
	private  UserDao dao;
	
	@Before
	public void init(){
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		dao = context.getBean(UserDao.class);
	}
	
	@Test
	public void test1(){
		System.out.println(dao.findByName("test1").getCn_user_name());
	}
	
	@Test
	public void test2(){
		User user = new User();
		user.setCn_user_desc("西天取经");
		user.setCn_user_id("1111");
		user.setCn_user_name("唐僧");
		user.setCn_user_password("123");
		user.setCn_user_token("位登录");
		dao.save(user);
	}
	
}
