package org.tarena.note.service;

import org.tarena.note.entity.NoteResult;
import org.tarena.note.entity.User;

public interface UserService {
	
	NoteResult checkLogin(String base64_msg);
	
	NoteResult addUser(User user);
	
	NoteResult changePassword(String Base64_msg);
}
