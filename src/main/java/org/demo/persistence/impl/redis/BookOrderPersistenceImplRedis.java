package org.demo.persistence.impl.redis;
import java.util.List;

import javax.inject.Named;
import org.demo.persistence.impl.redis.commons.GenericDAO;


import org.demo.data.record.BookOrderRecord;
import org.demo.persistence.BookOrderPersistence;

@Named("BookOrderPersistence")
public class BookOrderPersistenceImplRedis extends GenericDAO<BookOrderRecord> implements BookOrderPersistence {

	/**
	 * DAO constructor
	 */
	public BookOrderPersistenceImplRedis() {
		super("bookOrder", BookOrderRecord.class);
	}
	/**
	 * make key in a good format
	 * 
	 * @param bean;
	 * @return key  with a good format
	 */
	@Override
	protected String getKey(BookOrderRecord bean) {
		return buildString(bean.getId());
	}
	/**
	 * Creates a new instance of the bean
	 * primary value(s)
	 * 
	 * @param key;
	 * @return the new instance
	 */
	private BookOrderRecord newInstanceWithPrimaryKey(Integer id) {
		BookOrderRecord bookOrder = new BookOrderRecord ();
        bookOrder.setId(id); 
		return bookOrder;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public BookOrderRecord findById(Integer id){
        BookOrderRecord  bookOrder = newInstanceWithPrimaryKey(id);
		return super.doSelect(bookOrder);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public List<BookOrderRecord> findAll() {
		return super.doSelectAll();
	}

	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s)
	 * in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from
	 * the database<br>
	 * If not found, the given instance remains unchanged
	 */
	public BookOrderRecord load(BookOrderRecord bookOrder) {
		return super.doSelect(bookOrder);
	}

	/**
	 * Inserts the given bean in the database
	 * 
	 */
	public boolean insert(BookOrderRecord bookOrder){
		return super.doInsert(bookOrder);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public BookOrderRecord save(BookOrderRecord bookOrder){
		if (super.doExists(bookOrder)) {
			super.doUpdate(bookOrder);
		} else {
			insert(bookOrder);
		}
        return bookOrder;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public boolean update(BookOrderRecord bookOrder){
		return super.doUpdate(bookOrder);	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public BookOrderRecord create(BookOrderRecord bookOrder){
		insert(bookOrder);
		return bookOrder;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public boolean deleteById(Integer id) {
		BookOrderRecord bookOrder = newInstanceWithPrimaryKey(id);
		long r = super.doDelete(bookOrder);
		return r > 0L;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public boolean delete(BookOrderRecord bookOrder) {
		long r = super.doDelete(bookOrder);
		return r > 0L;
	}

	/**
	 * Checks the existence of a record in the database using the given primary
	 * key value(s)
	 
	 * @return
	 */
	public boolean exists(Integer id) {
		BookOrderRecord bookOrder = newInstanceWithPrimaryKey(id);
		return super.doExists(bookOrder);
	}

	/**
	 * Checks the existence of the given bean in the database
	
	 * @return
	 */
	public boolean exists(BookOrderRecord bookOrder) {
		return super.doExists(bookOrder);
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
