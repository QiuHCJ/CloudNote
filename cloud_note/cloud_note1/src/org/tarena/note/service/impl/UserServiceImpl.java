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
	 * ��֤��¼
	 * @param name
	 * @param password
	 * @return
	 */
	public NoteResult checkLogin(String name, String password) {
		NoteResult result = new NoteResult();
		User user = dao.findByName(name);
		
		if(user==null){//�û���������
			result.setStatus(1);
			result.setMsg("�û���������");
			return result;
		}
			
		if(!user.getCn_user_password().equals(password)){//���벻��ȷ
			result.setStatus(2);
			result.setMsg("���벻��ȷ");
			return result;
		}
		
		
		//����userTaken
		String userToken = NoteUtil.createToken();
		user.setCn_user_token(userToken);
		dao.setUserTokenByUser(user);
		
		//��֤ͨ����������ȷ���
		result.setStatus(0);
		result.setMsg("�û�����������ȷ");
		
		//������쳣 why
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
	 * ����base64��������
	 */
	public NoteResult checkLogin(String base64_msg) {
		String msg = null;
		try {
			byte[] output = Base64.decodeBase64(base64_msg);
			msg = new String(output,"UTF-8");
		} catch (Exception e) { 
			throw new NoteException("�����֤����");
		}
		
		String name = msg.split(":")[0];
		String password = msg.split(":")[1];
		String md5_pwd = NoteUtil.md5(password);
		
		return checkLogin(name, md5_pwd);
	}

	
	
	
	/**
	 * ������û�
	 */
	public NoteResult addUser(User user) {
		System.out.println(user);
		NoteResult result = new NoteResult();
		
		//TODO �ж������Ƿ�Ϊ��
		if(user.getCn_user_name()==null||"".equals(user.getCn_user_name())){
			result.setMsg("�û�������Ϊ��");
			result.setStatus(1);
			return result;
		}
		
		String nickname = user.getCn_user_desc();
		if(nickname==null||"".equals(nickname)){
			result.setMsg("�ǳƲ���Ϊ��");
			result.setStatus(2);
			return result;
		}
		
		String password = user.getCn_user_password();
		if(password==null||"".equals(password)){
			result.setMsg("���벻��Ϊ��");
			result.setStatus(3);
			return result;
		}
		// �ж��û���Ψһ�� �ͻ������ж�
		
		//��֤ͨ��
		String id = NoteUtil.createId();
		String md5_pwd = NoteUtil.md5(user.getCn_user_password());
		String token = NoteUtil.createToken();
		
		user.setCn_user_id(id);
		user.setCn_user_password(md5_pwd);
		user.setCn_user_token(token);
		
		dao.save(user);
		
		result.setStatus(0);
		result.setMsg("ע��ɹ�");
//		result.setData(user);
		
		return result;
	}

	/**
	 * ��������
	 */
	public NoteResult changePassword(String base64_msg) {
		
		NoteResult result = new NoteResult();
		
		//��������
		String msg = null;
		try {
			byte[] output = Base64.decodeBase64(base64_msg);
			msg = new String(output,"UTF-8");
		} catch (Exception e) { 
			throw new NoteException("�����֤����");
		}
		
		String oldPwd = msg.split(":")[0];
		String newPwd = msg.split(":")[1];
		String userId = msg.split(":")[2];
		
		//��֤�������Ƿ���ȷ
		if(!valiPassword(userId,oldPwd)){
			result.setStatus(0);
			result.setMsg("���������");
			return result;
		}
		
		//��֤ͨ��
		User user = new User();
		user.setCn_user_id(userId);
		user.setCn_user_password(NoteUtil.md5(newPwd));
		
		dao.changePassword(user);
		result.setStatus(0);
		result.setMsg("�����޸ĳɹ�");
		
		return result;
	}

	
	/**
	 * ��֤�����Ƿ���ȷ
	 * @param userId
	 * @param oldPwd
	 * @return
	 */
	private boolean valiPassword(String userId, String oldPwd) {
		
		//�ж���ֵ�Ƿ�Ϊ��
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
