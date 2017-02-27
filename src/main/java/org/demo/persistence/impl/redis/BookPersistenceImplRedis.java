package org.demo.persistence.impl.redis;
import java.util.List;

import javax.inject.Named;
import org.demo.persistence.impl.redis.commons.GenericDAO;


import org.demo.data.record.BookRecord;
import org.demo.persistence.BookPersistence;

@Named("BookPersistence")
public class BookPersistenceImplRedis extends GenericDAO<BookRecord> implements BookPersistence {

	/**
	 * DAO constructor
	 */
	public BookPersistenceImplRedis() {
		super("book", BookRecord.class);
	}
	/**
	 * make key in a good format
	 * 
	 * @param bean;
	 * @return key  with a good format
	 */
	@Override
	protected String getKey(BookRecord bean) {
		return buildString(bean.getId());
	}
	/**
	 * Creates a new instance of the bean
	 * primary value(s)
	 * 
	 * @param key;
	 * @return the new instance
	 */
	private BookRecord newInstanceWithPrimaryKey(Integer id) {
		BookRecord book = new BookRecord ();
        book.setId(id); 
		return book;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public BookRecord findById(Integer id){
        BookRecord  book = newInstanceWithPrimaryKey(id);
		return super.doSelect(book);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public List<BookRecord> findAll() {
		return super.doSelectAll();
	}

	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s)
	 * in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from
	 * the database<br>
	 * If not found, the given instance remains unchanged
	 */
	public BookRecord load(BookRecord book) {
		return super.doSelect(book);
	}

	/**
	 * Inserts the given bean in the database
	 * 
	 */
	public boolean insert(BookRecord book){
		return super.doInsert(book);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public BookRecord save(BookRecord book){
		if (super.doExists(book)) {
			super.doUpdate(book);
		} else {
			insert(book);
		}
        return book;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public boolean update(BookRecord book){
		return super.doUpdate(book);	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public BookRecord create(BookRecord book){
		insert(book);
		return book;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public boolean deleteById(Integer id) {
		BookRecord book = newInstanceWithPrimaryKey(id);
		long r = super.doDelete(book);
		return r > 0L;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public boolean delete(BookRecord book) {
		long r = super.doDelete(book);
		return r > 0L;
	}

	/**
	 * Checks the existence of a record in the database using the given primary
	 * key value(s)
	 
	 * @return
	 */
	public boolean exists(Integer id) {
		BookRecord book = newInstanceWithPrimaryKey(id);
		return super.doExists(book);
	}

	/**
	 * Checks the existence of the given bean in the database
	
	 * @return
	 */
	public boolean exists(BookRecord book) {
		return super.doExists(book);
	}

	/**
	 * Counts all the records present in the database
	 * 
	 * @return
	 */
	public long count() {
		return super.doCountAll();
	}
}
