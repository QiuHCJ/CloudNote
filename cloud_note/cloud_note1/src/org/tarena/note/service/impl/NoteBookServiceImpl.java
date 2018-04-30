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
	 * ���رʼǱ��б�
	 */
	@Transactional(readOnly=true)
	public NoteResult loadNoteBooks(String cnUserId) {
		
		NoteResult result = new NoteResult();
		
		//�ж�Id�Ƿ�Ϊ��
		if(cnUserId==null||"".equals(cnUserId)){
			result.setMsg("�û�IdΪ��");
			result.setStatus(1);
			return result;
		}
		
		//����Id����NoteBook
		List<NoteBook> list = dao.findAll(cnUserId);
		result.setData(list);
		result.setMsg("��ѯ�ɹ�");
		result.setStatus(0);
		
		return result;
	}

	
	/**
	 * ���һ���ʼǱ�
	 */
	public NoteResult addNoteBook(NoteBook noteBook) {
		
		NoteResult result = new NoteResult();
		
		//�ж�Id�Ƿ�Ϊ��
		String userId = noteBook.getCn_user_id();
		if(userId==null||"".equals(userId)){
			result.setMsg("�û�IdΪ��");
			result.setStatus(1);
			return result;
		}
		
		//�жϱʼǱ������Ƿ�Ϊ��
		String noteBookName = noteBook.getCn_notebook_name();
		if(noteBookName==null||"".equals(noteBookName)){
			result.setMsg("�ʼǱ�����Ϊ��");
			result.setStatus(2);
			return result;
		}
		
		//��֤ͨ�����������
		String noteBookId = NoteUtil.createId();
		Timestamp noteBookCreatime = new Timestamp(System.currentTimeMillis());
		System.out.println(noteBookCreatime);
		
		noteBook.setCn_notebook_createtime(noteBookCreatime);
		noteBook.setCn_notebook_id(noteBookId);
		
		dao.saveNote(noteBook);
		result.setMsg("�ʼǱ���ӳɹ�");
		result.setStatus(0);
		
		return result;
	
	}

	
	/**
	 * ɾ���ʼǱ�
	 */
	public NoteResult delNoteBook(String noteBookId) {
		
		NoteResult result = new NoteResult();
		
		//�ж������Ƿ�Ϊ��
		if(noteBookId==null||"".equals(noteBookId)){
			result.setMsg("notebookIdΪ��");
			result.setStatus(1);
			return result;
		}
		//�жϵ�ǰ�ʼǱ����Ƿ��бʼǣ����з�����ʾ���ܾ�ɾ��
		if(hasNotes(noteBookId)){
			result.setMsg("�ñʼǱ��´��ڱʼǣ���ɾ���ʼǺ��ٲ���");
			result.setStatus(2);
			return result;
		}
		
		dao.deleteById(noteBookId);
		result.setMsg("ɾ���ɹ�");
		result.setStatus(0);
		
		return result;
	}

	/**
	 * �жϱʼǱ����Ƿ���ڱʼ�
	 * @param noteBookId
	 * @return
	 */
	private boolean hasNotes(String noteBookId) {
		//�ж������Ƿ�Ϊ��
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
	 * �ʼǱ�������
	 */
	public NoteResult rename(String noteBookId, String newNoteBookName) {
		NoteResult result = new NoteResult();
		
		//�ж������Ƿ�Ϊ��
		if(noteBookId==null||"".equals(noteBookId)){
			result.setMsg("notebookIdΪ��");
			result.setStatus(1);
			return result;
		}
		//�ж������Ƿ�Ϊ��
		if(newNoteBookName==null||"".equals(newNoteBookName)){
			result.setMsg("������Ϊ��");
			result.setStatus(2);
			return result;
		}
		
		Map<String,String> datas = new HashMap<String,String>();
		datas.put("cn_notebook_id", noteBookId);
		datas.put("cn_notebook_name", newNoteBookName);
		dao.updateName(datas);
		
		result.setMsg("�����Ѹ���");
		result.setStatus(0);
		
		return result;
	}

}
