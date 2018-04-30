package org.tarena.note.dao;

import java.util.List;
import java.util.Map;

import org.tarena.note.annotation.mybatisRepository;
import org.tarena.note.entity.NoteBook;

@mybatisRepository
public interface NoteBookDao {
	
	/**
	 * ����cn_user_id�������е�NoteBooks
	 * @param cn_user_id
	 * @return
	 */
	List<NoteBook> findAll(String userId);
	
	/**
	 * ���ݱʼǱ�����userId���ұʼǱ�Id
	 * @param noteBookName
	 * @return
	 */
	String findNameByIdAndName(Map datas);
	
	/**
	 * ��ӱʼǱ�
	 * @param noteBook
	 */
	void saveNote(NoteBook noteBook);
	
	/**
	 * ����Idɾ���ʼǱ�
	 * @param noteBookId
	 */
	void deleteById(String noteBookId);
	
	/**
	 * ����Id�������ʼǱ�
	 */
	void updateName(Map datas);
}
