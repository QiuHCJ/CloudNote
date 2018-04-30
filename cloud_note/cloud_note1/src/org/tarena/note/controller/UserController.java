package org.tarena.note.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.dao.UserDao;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.entity.User;
import org.tarena.note.service.UserService;
import org.tarena.note.util.NoteUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	/**
	 * ��֤��¼
	 * @param name
	 * @param password
	 * @return
	 */
	@RequestMapping("/login")///user/login.do
	@ResponseBody
	public NoteResult login(HttpServletRequest request){
		//��ȡ�����Ϣ
		String author = request.getHeader("Authorization");
		//���������Ϣ ��ȡ��basic ������Ϣ�� �еļ�����Ϣ
		String base64_msg = author.split(" ")[1];
		NoteResult result = service.checkLogin(base64_msg);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("changePassword")
	public NoteResult changePassword(HttpServletRequest request){
		//��ȡ������Ϣ
		String msg = request.getHeader("password");
		//����������Ϣ
		String base64_msg = msg.split(" ")[1];
		
		NoteResult result = service.changePassword(base64_msg);
		
		return result;
	}
	
	@RequestMapping("/regist")
	@ResponseBody
	public NoteResult regist(User user){
		NoteResult result = service.addUser(user);
		return result;
	}
}
