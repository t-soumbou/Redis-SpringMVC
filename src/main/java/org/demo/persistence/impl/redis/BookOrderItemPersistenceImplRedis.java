package org.demo.persistence.impl.redis;
import java.util.List;

import javax.inject.Named;
import org.demo.persistence.impl.redis.commons.GenericDAO;


import org.demo.data.record.BookOrderItemRecord;
import org.demo.persistence.BookOrderItemPersistence;

@Named("BookOrderItemPersistence")
public class BookOrderItemPersistenceImplRedis extends GenericDAO<BookOrderItemRecord> implements BookOrderItemPersistence {

	/**
	 * DAO constructor
	 */
	public BookOrderItemPersistenceImplRedis() {
		super("bookOrderItem", BookOrderItemRecord.class);
	}
	/**
	 * make key in a good format
	 * 
	 * @param bean;
	 * @return key  with a good format
	 */
	@Override
	protected String getKey(BookOrderItemRecord bean) {
		return buildString(bean.getBookOrderId(), bean.getBookId());
	}
	/**
	 * Creates a new instance of the bean
	 * primary value(s)
	 * 
	 * @param key;
	 * @return the new instance
	 */
	private BookOrderItemRecord newInstanceWithPrimaryKey(Integer bookOrderId, Integer bookId) {
		BookOrderItemRecord bookOrderItem = new BookOrderItemRecord ();
        bookOrderItem.setBookOrderId(bookOrderId); 
        bookOrderItem.setBookId(bookId); 
		return bookOrderItem;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public BookOrderItemRecord findById(Integer bookOrderId, Integer bookId){
        BookOrderItemRecord  bookOrderItem = newInstanceWithPrimaryKey(bookOrderId, bookId);
		return super.doSelect(bookOrderItem);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public List<BookOrderItemRecord> findAll() {
		return super.doSelectAll();
	}

	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s)
	 * in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from
	 * the database<br>
	 * If not found, the given instance remains unchanged
	 */
	public BookOrderItemRecord load(BookOrderItemRecord bookOrderItem) {
		return super.doSelect(bookOrderItem);
	}

	/**
	 * Inserts the given bean in the database
	 * 
	 */
	public boolean insert(BookOrderItemRecord bookOrderItem){
		return super.doInsert(bookOrderItem);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public BookOrderItemRecord save(BookOrderItemRecord bookOrderItem){
		if (super.doExists(bookOrderItem)) {
			super.doUpdate(bookOrderItem);
		} else {
			insert(bookOrderItem);
		}
        return bookOrderItem;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public boolean update(BookOrderItemRecord bookOrderItem){
		return super.doUpdate(bookOrderItem);	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public BookOrderItemRecord create(BookOrderItemRecord bookOrderItem){
		insert(bookOrderItem);
		return bookOrderItem;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public boolean deleteById(Integer bookOrderId, Integer bookId) {
		BookOrderItemRecord bookOrderItem = newInstanceWithPrimaryKey(bookOrderId, bookId);
		long r = super.doDelete(bookOrderItem);
		return r > 0L;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public boolean delete(BookOrderItemRecord bookOrderItem) {
		long r = super.doDelete(bookOrderItem);
		return r > 0L;
	}

	/**
	 * Checks the existence of a record in the database using the given primary
	 * key value(s)
	 
	 * @return
	 */
	public boolean exists(Integer bookOrderId, Integer bookId) {
		BookOrderItemRecord bookOrderItem = newInstanceWithPrimaryKey(bookOrderId, bookId);
		return super.doExists(bookOrderItem);
	}

	/**
	 * Checks the existence of the given bean in the database
	
	 * @return
	 */
	public boolean exists(BookOrderItemRecord bookOrderItem) {
		return super.doExists(bookOrderItem);
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
