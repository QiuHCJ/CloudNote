package org.tarena.note.entity;

import java.io.Serializable;

public class User implements Serializable{
	/*DROP TABLE IF EXISTS `cn_user`;
	CREATE TABLE `cn_user` (
	  `cn_user_id` varchar(100) NOT NULL COMMENT '用户ID',
	  `cn_user_name` varchar(100) DEFAULT NULL COMMENT '用户名',
	  `cn_user_password` varchar(100) DEFAULT NULL COMMENT '密码',
	  `cn_user_token` varchar(100) DEFAULT NULL COMMENT '令牌',
	  `cn_user_desc` text COMMENT '说明',
	  PRIMARY KEY (`cn_user_id`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;*/
	
	private String cn_user_id;
	private String cn_user_name;
	private String cn_user_password;
	private String cn_user_token;
	private String cn_user_desc;
	public String getCn_user_id() {
		return cn_user_id;
	}
	public void setCn_user_id(String cnUserId) {
		cn_user_id = cnUserId;
	}
	public String getCn_user_name() {
		return cn_user_name;
	}
	public void setCn_user_name(String cnUserName) {
		cn_user_name = cnUserName;
	}
	public String getCn_user_password() {
		return cn_user_password;
	}
	public void setCn_user_password(String cnUserPassword) {
		cn_user_password = cnUserPassword;
	}
	public String getCn_user_token() {
		return cn_user_token;
	}
	public void setCn_user_token(String cnUserToken) {
		cn_user_token = cnUserToken;
	}
	public String getCn_user_desc() {
		return cn_user_desc;
	}
	public void setCn_user_desc(String cnUserDesc) {
		cn_user_desc = cnUserDesc;
	}
	
	@Override
	public String toString() {
		return "User [cn_user_desc=" + cn_user_desc + ", cn_user_id="
				+ cn_user_id + ", cn_user_name=" + cn_user_name
				+ ", cn_user_password=" + cn_user_password + ", cn_user_token="
				+ cn_user_token + "]";
	}
	
	
}
