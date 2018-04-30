package org.tarena.note.dao;

import java.util.Map;

import org.tarena.note.annotation.mybatisRepository;
import org.tarena.note.entity.User;

@mybatisRepository
public interface UserDao  {
	
	User findByName(String nc_user_name);
	
	void save(User user);
	
	void setUserTokenByUser(User user);
	
	void changePassword(User user);
	
	User fingdById(String userId);
	
}
