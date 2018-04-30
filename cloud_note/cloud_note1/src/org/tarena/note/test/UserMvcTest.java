package org.tarena.note.test;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.tarena.note.controller.UserController;
import org.tarena.note.entity.NoteResult;

import com.fasterxml.jackson.databind.ObjectMapper;

@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMvcTest {
	
	@Autowired
	private UserController controller;
	private MockMvc mockMvc;
	
	@Before
	public void init(){
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void test1() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders
							.post("/user/regist.do")
							.param("cn_user_name", "wer")
							.param("cn_user_password", "123")
							.param("cn_user_desc", "123");
		//发送执行一个HTTP请求
		MvcResult result = mockMvc.perform(request).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		
		//提取响应信息中的json字符串
		String jsonStr = result.getResponse().getContentAsString();
		System.out.println(jsonStr);
		
		//将返回的json字符串转换为Java对象
		ObjectMapper mapper = new ObjectMapper();
		NoteResult noteResult = mapper.readValue(jsonStr, NoteResult.class);
		
		//使用断言判断
		Assert.assertEquals(0, noteResult.getStatus());
	}  
}
