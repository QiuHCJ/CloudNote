package org.tarena.note.entity;

public class Note {
	// CREATE TABLE `cn_note` (
	// `cn_note_id` varchar(100) NOT NULL COMMENT '笔记ID',
	// `cn_notebook_id` varchar(100) DEFAULT NULL COMMENT '笔记本ID',
	// `cn_user_id` varchar(100) DEFAULT NULL COMMENT '用户ID',
	// `cn_note_status_id` varchar(100) DEFAULT NULL COMMENT '笔记状态ID:备用',
	// `cn_note_type_id` varchar(100) DEFAULT NULL COMMENT '笔记本类型ID：备用',
	// `cn_note_title` varchar(500) DEFAULT NULL COMMENT '笔记标题',
	// `cn_note_body` text COMMENT '笔记内容',
	// `cn_note_create_time` bigint(20) DEFAULT NULL COMMENT '笔记创建时间',
	// `cn_note_last_modify_time` bigint(20) DEFAULT NULL COMMENT '笔记最近修改时间',
	
	private String cn_note_id;
	private String cn_notebook_id;
	private String cn_user_id;
	private String cn_note_status_id;
	private String cn_note_type_id;
	private String cn_note_title;
	private String cn_note_body;
	private long cn_note_create_time;
	private long cn_note_last_modify_time;
	
	public String getCn_note_id() {
		return cn_note_id;
	}
	public void setCn_note_id(String cnNoteId) {
		cn_note_id = cnNoteId;
	}
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
	public String getCn_note_status_id() {
		return cn_note_status_id;
	}
	public void setCn_note_status_id(String cnNoteStatusId) {
		cn_note_status_id = cnNoteStatusId;
	}
	public String getCn_note_type_id() {
		return cn_note_type_id;
	}
	public void setCn_note_type_id(String cnNoteTypeId) {
		cn_note_type_id = cnNoteTypeId;
	}
	public String getCn_note_title() {
		return cn_note_title;
	}
	public void setCn_note_title(String cnNoteTitle) {
		cn_note_title = cnNoteTitle;
	}
	public String getCn_note_body() {
		return cn_note_body;
	}
	public void setCn_note_body(String cnNoteBody) {
		cn_note_body = cnNoteBody;
	}
	public long getCn_note_create_time() {
		return cn_note_create_time;
	}
	public void setCn_note_create_time(long cnNoteCreateTime) {
		cn_note_create_time = cnNoteCreateTime;
	}
	public long getCn_note_last_modify_time() {
		return cn_note_last_modify_time;
	}
	public void setCn_note_last_modify_time(long cnNoteLastModifyTime) {
		cn_note_last_modify_time = cnNoteLastModifyTime;
	}
	
	@Override
	public String toString() {
		return "Note [cn_note_body=" + cn_note_body + ", cn_note_create_time="
				+ cn_note_create_time + ", cn_note_id=" + cn_note_id
				+ ", cn_note_last_modify_time=" + cn_note_last_modify_time
				+ ", cn_note_status_id=" + cn_note_status_id
				+ ", cn_note_title=" + cn_note_title + ", cn_note_type_id="
				+ cn_note_type_id + ", cn_notebook_id=" + cn_notebook_id
				+ ", cn_user_id=" + cn_user_id + "]";
	}
	
	
}
