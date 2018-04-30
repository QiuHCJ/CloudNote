package org.tarena.note.entity;

import java.sql.Timestamp;

public class NoteBook {
/*	DROP TABLE IF EXISTS `cn_notebook`;
	CREATE TABLE `cn_notebook` (
	  `cn_notebook_id` varchar(100) NOT NULL COMMENT '笔记本ID',
	  `cn_user_id` varchar(100) DEFAULT NULL COMMENT '用户ID',
	  `cn_notebook_type_id` varchar(100) DEFAULT NULL COMMENT '笔记本类型ID',
	  `cn_notebook_name` varchar(500) DEFAULT NULL COMMENT '笔记本名',
	  `cn_notebook_desc` text COMMENT '笔记本说明',
	  `cn_notebook_createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
	  PRIMARY KEY (`cn_notebook_id`),
	  KEY `FK_Note_User_Reference` (`cn_user_id`),
	  KEY `FK_Reference_6` (`cn_notebook_type_id`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;*/
	
	private String cn_notebook_id;
	private String cn_user_id;
	private String cn_notebook_type_id;
	private String cn_notebook_name;
	private String cn_notebook_desc;
	private Timestamp cn_notebook_createtime;
	public String getCn_notebook_id() {
		return cn_notebook_id;
	}
	public void setCn_notebook_id(String cnNotebookId) {
		cn_notebook_id = cnNotebookId;
	}
	public String getCn_user_id() {
		return cn_user_id;
	}
	public void setCn_user_id(String cnUserId) {
		cn_user_id = cnUserId;
	}
	public String getCn_notebook_type_id() {
		return cn_notebook_type_id;
	}
	public void setCn_notebook_type_id(String cnNotebookTypeId) {
		cn_notebook_type_id = cnNotebookTypeId;
	}
	public String getCn_notebook_name() {
		return cn_notebook_name;
	}
	public void setCn_notebook_name(String cnNotebookName) {
		cn_notebook_name = cnNotebookName;
	}
	public String getCn_notebook_desc() {
		return cn_notebook_desc;
	}
	public void setCn_notebook_desc(String cnNotebookDesc) {
		cn_notebook_desc = cnNotebookDesc;
	}
	public Timestamp getCn_notebook_createtime() {
		return cn_notebook_createtime;
	}
	public void setCn_notebook_createtime(Timestamp cnNotebookCreatetime) {
		cn_notebook_createtime = cnNotebookCreatetime;
	}
	
	
	@Override
	public String toString() {
		return "NoteBook [cn_notebook_createtime=" + cn_notebook_createtime
				+ ", cn_notebook_desc=" + cn_notebook_desc
				+ ", cn_notebook_id=" + cn_notebook_id + ", cn_notebook_name="
				+ cn_notebook_name + ", cn_notebook_type_id="
				+ cn_notebook_type_id + ", cn_user_id=" + cn_user_id + "]";
	}
	
	
	
}
