package org.tarena.note.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tarena.note.dao.NoteBookDao;
import org.tarena.note.dao.NoteDao;
import org.tarena.note.entity.Note;
import org.tarena.note.entity.NoteBook;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.NoteBookService;
import org.tarena.note.util.NoteUtil;

@Service
@Transactional
public class NoteBookServiceImpl implements NoteBookService {

	@Autowired
	private NoteBookDao dao;
	@Autowired
	private NoteDao noteDao;
	
	/**
	 * 加载笔记本列表
	 */
	@Transactional(readOnly=true)
	public NoteResult loadNoteBooks(String cnUserId) {
		
		NoteResult result = new NoteResult();
		
		//判断Id是否为空
		if(cnUserId==null||"".equals(cnUserId)){
			result.setMsg("用户Id为空");
			result.setStatus(1);
			return result;
		}
		
		//根据Id查找NoteBook
		List<NoteBook> list = dao.findAll(cnUserId);
		result.setData(list);
		result.setMsg("查询成功");
		result.setStatus(0);
		
		return result;
	}

	
	/**
	 * 添加一条笔记本
	 */
	public NoteResult addNoteBook(NoteBook noteBook) {
		
		NoteResult result = new NoteResult();
		
		//判断Id是否为空
		String userId = noteBook.getCn_user_id();
		if(userId==null||"".equals(userId)){
			result.setMsg("用户Id为空");
			result.setStatus(1);
			return result;
		}
		
		//判断笔记本名称是否为空
		String noteBookName = noteBook.getCn_notebook_name();
		if(noteBookName==null||"".equals(noteBookName)){
			result.setMsg("笔记本名称为空");
			result.setStatus(2);
			return result;
		}
		
		//验证通过，添加数据
		String noteBookId = NoteUtil.createId();
		Timestamp noteBookCreatime = new Timestamp(System.currentTimeMillis());
		System.out.println(noteBookCreatime);
		
		noteBook.setCn_notebook_createtime(noteBookCreatime);
		noteBook.setCn_notebook_id(noteBookId);
		
		dao.saveNote(noteBook);
		result.setMsg("笔记本添加成功");
		result.setStatus(0);
		
		return result;
	
	}

	
	/**
	 * 删除笔记本
	 */
	public NoteResult delNoteBook(String noteBookId) {
		
		NoteResult result = new NoteResult();
		
		//判断数据是否为空
		if(noteBookId==null||"".equals(noteBookId)){
			result.setMsg("notebookId为空");
			result.setStatus(1);
			return result;
		}
		//判断当前笔记本下是否有笔记，若有返回提示，拒绝删除
		if(hasNotes(noteBookId)){
			result.setMsg("该笔记本下存在笔记！请删除笔记后再操作");
			result.setStatus(2);
			return result;
		}
		
		dao.deleteById(noteBookId);
		result.setMsg("删除成功");
		result.setStatus(0);
		
		return result;
	}

	/**
	 * 判断笔记本下是否存在笔记
	 * @param noteBookId
	 * @return
	 */
	private boolean hasNotes(String noteBookId) {
		//判断数据是否为空
		if(noteBookId==null||"".equals(noteBookId)){
			return false;
		}
		
		List<Note> notes = noteDao.findNotesByNoteBookId(noteBookId);
		if(notes.size()>0){
			return true;
		}
		
		return false;
	}

	/**
	 * 笔记本重命名
	 */
	public NoteResult rename(String noteBookId, String newNoteBookName) {
		NoteResult result = new NoteResult();
		
		//判断数据是否为空
		if(noteBookId==null||"".equals(noteBookId)){
			result.setMsg("notebookId为空");
			result.setStatus(1);
			return result;
		}
		//判断数据是否为空
		if(newNoteBookName==null||"".equals(newNoteBookName)){
			result.setMsg("新名称为空");
			result.setStatus(2);
			return result;
		}
		
		Map<String,String> datas = new HashMap<String,String>();
		datas.put("cn_notebook_id", noteBookId);
		datas.put("cn_notebook_name", newNoteBookName);
		dao.updateName(datas);
		
		result.setMsg("名称已更改");
		result.setStatus(0);
		
		return result;
	}

}
