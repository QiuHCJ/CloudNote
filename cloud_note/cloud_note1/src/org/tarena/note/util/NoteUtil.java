package org.tarena.note.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class NoteUtil {
	/**
	 * ����MD5ժҪ�㷨������Ϣ
	 */
	public static String md5(String msg){
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			
			byte[] input = msg.getBytes();
			byte[] output = md.digest(input);
			
			//����base64�㷨���ֽ�����ת��Ϊ�ַ���
			String base64_msg = Base64.encode(output);
			return base64_msg;
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String createId(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	
	public static String createToken(){
		return createId().replaceAll("-", "");//ȥ��"-"
	}
	
	public static void main(String[] args) {
		System.out.println(md5("123"));
		System.out.println(createId());
	}
}
