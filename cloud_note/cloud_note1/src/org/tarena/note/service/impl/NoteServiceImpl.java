package org.tarena.note.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tarena.note.dao.NoteDao;
import org.tarena.note.dao.ShareNoteDao;
import org.tarena.note.entity.Note;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.entity.ShareNote;
import org.tarena.note.service.NoteService;
import org.tarena.note.util.NoteUtil;


@Service
@Transactional
public class NoteServiceImpl implements NoteService {

	@Autowired
	private NoteDao noteDao;
	@Autowired
	private ShareNoteDao shareDao;
	
	
	/**
	 * 加载笔记列表
	 */
	@Transactional(readOnly=true)
	public NoteResult loadNotes(String noteBookId) {
		
		NoteResult result = new NoteResult();
		
		//验正noteBookId是否为空；
		if(noteBookId==null||"".equals(noteBookId)){
			result.setStatus(1);
			result.setMsg("该笔记本下没有笔记");
			return result;
		}

		//返回正确的查询结果。
		List<Note> notes = noteDao.findNotesByNoteBookId(noteBookId);
		result.setData(notes);
		result.setMsg("笔记加载成功");
		result.setStatus(0);
		
		return result;
	}
	
	/**
	 * 加载回收站笔记列表
	 */
	public NoteResult loadRecNotes(String noteBookId) {
		
		NoteResult result = new NoteResult();
		
		//验正noteBookId是否为空；
		if(noteBookId==null||"".equals(noteBookId)){
			result.setStatus(1);
			result.setMsg("该笔记本下没有笔记");
			return result;
		}

		//返回正确的查询结果。
		List<Note> notes = noteDao.findRecNotes(noteBookId);
		result.setData(notes);
		result.setMsg("笔记加载成功");
		result.setStatus(0);
		
		return result;
	}
	
	
	

	/**
	 * 根据ID加载笔记
	 */
	@Transactional(readOnly=true)
	public NoteResult loadNote(String noteId) {
		
		NoteResult result = new NoteResult();
		
		if(noteId==null||"".equals(noteId)){
			result.setStatus(1);
			result.setMsg("笔记加载失败");
			return result;
		}
		
		Note note = noteDao.findNoteById(noteId);
		Map<String,String> datas = new HashMap<String,String>();
		datas.put("title",note.getCn_note_title());
		datas.put("body", note.getCn_note_body());
		
		result.setData(datas);
		result.setMsg("笔记加载成功");
		result.setStatus(0);
		
		return result;
	}

	/**
	 * 修改笔记内容和标题
	 */
	public NoteResult updateNote(Note note){
		
		NoteResult result = new NoteResult();
		
		String id = note.getCn_note_id();
		if(id==null||"".equals(id)){
			result.setStatus(1);
			result.setMsg("笔记修改失败");
			return result;
		}
		
		
		System.out.println("service:"+note);
		note.setCn_note_last_modify_time(System.currentTimeMillis());//设置修改时间
		
		noteDao.updateNote(note);
		result.setMsg("笔记修改成功");
		result.setStatus(0);
		
		return result;
	}

	/**
	 * 添加笔记
	 */
	public NoteResult addNote(Note note) {
		
		NoteResult result = new NoteResult();
		
		String title = note.getCn_note_title();
		if(title==null||"".equals(title)){
			result.setStatus(1);
			result.setMsg("添加失败！笔记标题为空");
			return result;
		}
		
		String userId = note.getCn_user_id();
		if(userId==null||"".equals(userId)){
			result.setStatus(2);
			result.setMsg("添加失败！用户id为空");
			return result;
		}
		
		String noteBookId = note.getCn_notebook_id();
		if(noteBookId==null||"".equals(noteBookId)){
			result.setStatus(3);
			result.setMsg("添加失败！笔记本id为空");
			return result;
		}
		
		note.setCn_note_last_modify_time(System.currentTimeMillis());//设置修改时间
		String noteId = NoteUtil.createId();
		note.setCn_note_id(noteId);
		note.setCn_note_status_id("0");
		noteDao.saveNote(note);
		result.setMsg("笔记添加成功");
		result.setStatus(0);
		
		return result;
	}

	
	/**
	 * 删除笔记
	 */
	public NoteResult deleteNote(String noteId) {
		NoteResult result = new NoteResult();
		
		if(noteId==null||"".equals(noteId)){
			result.setStatus(1);
			result.setMsg("笔记加载失败");
			return result;
		}
		
		noteDao.deleteNote(noteId);
		
		result.setStatus(0);
		result.setMsg("删除成功");
		
		
		return result;
	}

	/**
	 * 分享一条笔记
	 */
	public NoteResult shareOne(String noteId) {
		
		NoteResult result = new NoteResult();
		ShareNote shareNote = new ShareNote();
		
		//数据验证是否为空
		if(noteId==null||"".equals(noteId)){
			result.setStatus(1);
			result.setMsg("noteId为空");
			return result;
		}
		
		//验证该笔记是否已经分享
		Note note = noteDao.findNoteById(noteId);
		//验证笔记标题和内容
		String title = note.getCn_note_title();
		String body = note.getCn_note_body();
		if(title==null||"".equals(title)||body==null||"".equals(body)){
			result.setStatus(3);
			result.setMsg("笔记标题或内容为空！不能分享");
			return result;
		}
		
		
		if(shareDao.findNoteId(note)!=null){
			result.setStatus(2);
			result.setMsg("该笔记已分享");
			return result;
		}
		
		//数据验证通过
		shareNote.setCn_note_id(noteId);
		
		String noteTitle = note.getCn_note_title();
		String noteBody = note.getCn_note_body();
		shareNote.setCn_share_body(noteBody);
		shareNote.setCn_share_title(noteTitle);
		
		String ShareId = NoteUtil.createId();
		shareNote.setCn_share_id(ShareId);
		
		shareDao.saveOne(shareNote);
		result.setStatus(0);
		result.setMsg("分享成功");
		
		return result;
	}

	
	/**
	 * 根据输入值模糊查找分享的笔记
	 */
	public NoteResult searchshareNote(String keyword) {
		
		NoteResult result = new NoteResult();
		
		if(keyword==null||"".equals(keyword.trim())){
			result.setStatus(1);
			result.setMsg("输入为空");
			return result;
		}
		
		String title = "%"+keyword.trim()+"%";
		List<String> datas = shareDao.findTitleByName(title);
		result.setStatus(0);
		result.setData(datas);
		result.setMsg("搜索成功！");
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
