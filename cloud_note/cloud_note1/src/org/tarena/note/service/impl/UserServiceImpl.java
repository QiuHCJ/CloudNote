package org.tarena.note.service.impl;




import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tarena.note.dao.UserDao;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.entity.User;
import org.tarena.note.exception.NoteException;
import org.tarena.note.service.UserService;
import org.tarena.note.util.NoteUtil;


@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;
	
	/**
	 * 验证登录
	 * @param name
	 * @param password
	 * @return
	 */
	public NoteResult checkLogin(String name, String password) {
		NoteResult result = new NoteResult();
		User user = dao.findByName(name);
		
		if(user==null){//用户名不存在
			result.setStatus(1);
			result.setMsg("用户名不存在");
			return result;
		}
			
		if(!user.getCn_user_password().equals(password)){//密码不正确
			result.setStatus(2);
			result.setMsg("密码不正确");
			return result;
		}
		
		
		//设置userTaken
		String userToken = NoteUtil.createToken();
		user.setCn_user_token(userToken);
		dao.setUserTokenByUser(user);
		
		//验证通过，返回正确结果
		result.setStatus(0);
		result.setMsg("用户名或密码正确");
		
		//代码包异常 why
		/*JSONObject jsonUser = JSONObject.fromObject(user);
		System.out.println(jsonUser);
		result.setData(jsonUser);*/
		
		Map<String,String> map = new HashedMap();
		map.put("userId", user.getCn_user_id());
		map.put("userToken", user.getCn_user_token());
		result.setData(map);
		
		return result;
	}
	
	/**
	 * 解析base64加密数据
	 */
	public NoteResult checkLogin(String base64_msg) {
		String msg = null;
		try {
			byte[] output = Base64.decodeBase64(base64_msg);
			msg = new String(output,"UTF-8");
		} catch (Exception e) { 
			throw new NoteException("身份验证错误");
		}
		
		String name = msg.split(":")[0];
		String password = msg.split(":")[1];
		String md5_pwd = NoteUtil.md5(password);
		
		return checkLogin(name, md5_pwd);
	}

	
	
	
	/**
	 * 添加新用户
	 */
	public NoteResult addUser(User user) {
		System.out.println(user);
		NoteResult result = new NoteResult();
		
		//TODO 判断数据是否为空
		if(user.getCn_user_name()==null||"".equals(user.getCn_user_name())){
			result.setMsg("用户名不能为空");
			result.setStatus(1);
			return result;
		}
		
		String nickname = user.getCn_user_desc();
		if(nickname==null||"".equals(nickname)){
			result.setMsg("昵称不能为空");
			result.setStatus(2);
			return result;
		}
		
		String password = user.getCn_user_password();
		if(password==null||"".equals(password)){
			result.setMsg("密码不能为空");
			result.setStatus(3);
			return result;
		}
		// 判断用户名唯一性 客户端已判断
		
		//验证通过
		String id = NoteUtil.createId();
		String md5_pwd = NoteUtil.md5(user.getCn_user_password());
		String token = NoteUtil.createToken();
		
		user.setCn_user_id(id);
		user.setCn_user_password(md5_pwd);
		user.setCn_user_token(token);
		
		dao.save(user);
		
		result.setStatus(0);
		result.setMsg("注册成功");
//		result.setData(user);
		
		return result;
	}

	/**
	 * 更改密码
	 */
	public NoteResult changePassword(String base64_msg) {
		
		NoteResult result = new NoteResult();
		
		//解析数据
		String msg = null;
		try {
			byte[] output = Base64.decodeBase64(base64_msg);
			msg = new String(output,"UTF-8");
		} catch (Exception e) { 
			throw new NoteException("身份验证错误");
		}
		
		String oldPwd = msg.split(":")[0];
		String newPwd = msg.split(":")[1];
		String userId = msg.split(":")[2];
		
		//验证旧密码是否正确
		if(!valiPassword(userId,oldPwd)){
			result.setStatus(0);
			result.setMsg("旧密码错误！");
			return result;
		}
		
		//验证通过
		User user = new User();
		user.setCn_user_id(userId);
		user.setCn_user_password(NoteUtil.md5(newPwd));
		
		dao.changePassword(user);
		result.setStatus(0);
		result.setMsg("密码修改成功");
		
		return result;
	}

	
	/**
	 * 验证密码是否正确
	 * @param userId
	 * @param oldPwd
	 * @return
	 */
	private boolean valiPassword(String userId, String oldPwd) {
		
		//判断数值是否为空
		if(userId==null||"".equals(userId)||oldPwd==null||"".equals(oldPwd)){
			return false;
		}
		System.out.println(userId);
		User user = dao.fingdById(userId);
		if(user==null){
			return false;
		}
		System.out.println(oldPwd);
		String md5_pwd = NoteUtil.md5(oldPwd);
		System.out.println(md5_pwd);
		if(md5_pwd.equals(user.getCn_user_password())){
			return true;
		}
		
		return false;
	}


	
	

}
