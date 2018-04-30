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
	 * ���رʼ��б�
	 */
	@Transactional(readOnly=true)
	public NoteResult loadNotes(String noteBookId) {
		
		NoteResult result = new NoteResult();
		
		//����noteBookId�Ƿ�Ϊ�գ�
		if(noteBookId==null||"".equals(noteBookId)){
			result.setStatus(1);
			result.setMsg("�ñʼǱ���û�бʼ�");
			return result;
		}

		//������ȷ�Ĳ�ѯ�����
		List<Note> notes = noteDao.findNotesByNoteBookId(noteBookId);
		result.setData(notes);
		result.setMsg("�ʼǼ��سɹ�");
		result.setStatus(0);
		
		return result;
	}
	
	/**
	 * ���ػ���վ�ʼ��б�
	 */
	public NoteResult loadRecNotes(String noteBookId) {
		
		NoteResult result = new NoteResult();
		
		//����noteBookId�Ƿ�Ϊ�գ�
		if(noteBookId==null||"".equals(noteBookId)){
			result.setStatus(1);
			result.setMsg("�ñʼǱ���û�бʼ�");
			return result;
		}

		//������ȷ�Ĳ�ѯ�����
		List<Note> notes = noteDao.findRecNotes(noteBookId);
		result.setData(notes);
		result.setMsg("�ʼǼ��سɹ�");
		result.setStatus(0);
		
		return result;
	}
	
	
	

	/**
	 * ����ID���رʼ�
	 */
	@Transactional(readOnly=true)
	public NoteResult loadNote(String noteId) {
		
		NoteResult result = new NoteResult();
		
		if(noteId==null||"".equals(noteId)){
			result.setStatus(1);
			result.setMsg("�ʼǼ���ʧ��");
			return result;
		}
		
		Note note = noteDao.findNoteById(noteId);
		Map<String,String> datas = new HashMap<String,String>();
		datas.put("title",note.getCn_note_title());
		datas.put("body", note.getCn_note_body());
		
		result.setData(datas);
		result.setMsg("�ʼǼ��سɹ�");
		result.setStatus(0);
		
		return result;
	}

	/**
	 * �޸ıʼ����ݺͱ���
	 */
	public NoteResult updateNote(Note note){
		
		NoteResult result = new NoteResult();
		
		String id = note.getCn_note_id();
		if(id==null||"".equals(id)){
			result.setStatus(1);
			result.setMsg("�ʼ��޸�ʧ��");
			return result;
		}
		
		
		System.out.println("service:"+note);
		note.setCn_note_last_modify_time(System.currentTimeMillis());//�����޸�ʱ��
		
		noteDao.updateNote(note);
		result.setMsg("�ʼ��޸ĳɹ�");
		result.setStatus(0);
		
		return result;
	}

	/**
	 * ��ӱʼ�
	 */
	public NoteResult addNote(Note note) {
		
		NoteResult result = new NoteResult();
		
		String title = note.getCn_note_title();
		if(title==null||"".equals(title)){
			result.setStatus(1);
			result.setMsg("���ʧ�ܣ��ʼǱ���Ϊ��");
			return result;
		}
		
		String userId = note.getCn_user_id();
		if(userId==null||"".equals(userId)){
			result.setStatus(2);
			result.setMsg("���ʧ�ܣ��û�idΪ��");
			return result;
		}
		
		String noteBookId = note.getCn_notebook_id();
		if(noteBookId==null||"".equals(noteBookId)){
			result.setStatus(3);
			result.setMsg("���ʧ�ܣ��ʼǱ�idΪ��");
			return result;
		}
		
		note.setCn_note_last_modify_time(System.currentTimeMillis());//�����޸�ʱ��
		String noteId = NoteUtil.createId();
		note.setCn_note_id(noteId);
		note.setCn_note_status_id("0");
		noteDao.saveNote(note);
		result.setMsg("�ʼ���ӳɹ�");
		result.setStatus(0);
		
		return result;
	}

	
	/**
	 * ɾ���ʼ�
	 */
	public NoteResult deleteNote(String noteId) {
		NoteResult result = new NoteResult();
		
		if(noteId==null||"".equals(noteId)){
			result.setStatus(1);
			result.setMsg("�ʼǼ���ʧ��");
			return result;
		}
		
		noteDao.deleteNote(noteId);
		
		result.setStatus(0);
		result.setMsg("ɾ���ɹ�");
		
		
		return result;
	}

	/**
	 * ����һ���ʼ�
	 */
	public NoteResult shareOne(String noteId) {
		
		NoteResult result = new NoteResult();
		ShareNote shareNote = new ShareNote();
		
		//������֤�Ƿ�Ϊ��
		if(noteId==null||"".equals(noteId)){
			result.setStatus(1);
			result.setMsg("noteIdΪ��");
			return result;
		}
		
		//��֤�ñʼ��Ƿ��Ѿ�����
		Note note = noteDao.findNoteById(noteId);
		//��֤�ʼǱ��������
		String title = note.getCn_note_title();
		String body = note.getCn_note_body();
		if(title==null||"".equals(title)||body==null||"".equals(body)){
			result.setStatus(3);
			result.setMsg("�ʼǱ��������Ϊ�գ����ܷ���");
			return result;
		}
		
		
		if(shareDao.findNoteId(note)!=null){
			result.setStatus(2);
			result.setMsg("�ñʼ��ѷ���");
			return result;
		}
		
		//������֤ͨ��
		shareNote.setCn_note_id(noteId);
		
		String noteTitle = note.getCn_note_title();
		String noteBody = note.getCn_note_body();
		shareNote.setCn_share_body(noteBody);
		shareNote.setCn_share_title(noteTitle);
		
		String ShareId = NoteUtil.createId();
		shareNote.setCn_share_id(ShareId);
		
		shareDao.saveOne(shareNote);
		result.setStatus(0);
		result.setMsg("����ɹ�");
		
		return result;
	}

	
	/**
	 * ��������ֵģ�����ҷ���ıʼ�
	 */
	public NoteResult searchshareNote(String keyword) {
		
		NoteResult result = new NoteResult();
		
		if(keyword==null||"".equals(keyword.trim())){
			result.setStatus(1);
			result.setMsg("����Ϊ��");
			return result;
		}
		
		String title = "%"+keyword.trim()+"%";
		List<String> datas = shareDao.findTitleByName(title);
		result.setStatus(0);
		result.setData(datas);
		result.setMsg("�����ɹ���");
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
