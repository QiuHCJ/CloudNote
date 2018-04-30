package org.tarena.note.dao;

import java.util.List;
import java.util.Map;

import org.tarena.note.annotation.mybatisRepository;
import org.tarena.note.entity.NoteBook;

@mybatisRepository
public interface NoteBookDao {
	
	/**
	 * 根据cn_user_id查找所有的NoteBooks
	 * @param cn_user_id
	 * @return
	 */
	List<NoteBook> findAll(String userId);
	
	/**
	 * 根据笔记本名和userId查找笔记本Id
	 * @param noteBookName
	 * @return
	 */
	String findNameByIdAndName(Map datas);
	
	/**
	 * 添加笔记本
	 * @param noteBook
	 */
	void saveNote(NoteBook noteBook);
	
	/**
	 * 根据Id删除笔记本
	 * @param noteBookId
	 */
	void deleteById(String noteBookId);
	
	/**
	 * 根据Id重命名笔记本
	 */
	void updateName(Map datas);
}
