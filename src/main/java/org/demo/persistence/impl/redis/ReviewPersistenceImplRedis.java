package org.demo.persistence.impl.redis;
import java.util.List;

import javax.inject.Named;
import org.demo.persistence.impl.redis.commons.GenericDAO;


import org.demo.data.record.ReviewRecord;
import org.demo.persistence.ReviewPersistence;

@Named("ReviewPersistence")
public class ReviewPersistenceImplRedis extends GenericDAO<ReviewRecord> implements ReviewPersistence {

	/**
	 * DAO constructor
	 */
	public ReviewPersistenceImplRedis() {
		super("review", ReviewRecord.class);
	}
	/**
	 * make key in a good format
	 * 
	 * @param bean;
	 * @return key  with a good format
	 */
	@Override
	protected String getKey(ReviewRecord bean) {
		return buildString(bean.getCustomerCode(), bean.getBookId());
	}
	/**
	 * Creates a new instance of the bean
	 * primary value(s)
	 * 
	 * @param key;
	 * @return the new instance
	 */
	private ReviewRecord newInstanceWithPrimaryKey(String customerCode, Integer bookId) {
		ReviewRecord review = new ReviewRecord ();
        review.setCustomerCode(customerCode); 
        review.setBookId(bookId); 
		return review;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public ReviewRecord findById(String customerCode, Integer bookId){
        ReviewRecord  review = newInstanceWithPrimaryKey(customerCode, bookId);
		return super.doSelect(review);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public List<ReviewRecord> findAll() {
		return super.doSelectAll();
	}

	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s)
	 * in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from
	 * the database<br>
	 * If not found, the given instance remains unchanged
	 */
	public ReviewRecord load(ReviewRecord review) {
		return super.doSelect(review);
	}

	/**
	 * Inserts the given bean in the database
	 * 
	 */
	public boolean insert(ReviewRecord review){
		return super.doInsert(review);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public ReviewRecord save(ReviewRecord review){
		if (super.doExists(review)) {
			super.doUpdate(review);
		} else {
			insert(review);
		}
        return review;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public boolean update(ReviewRecord review){
		return super.doUpdate(review);	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public ReviewRecord create(ReviewRecord review){
		insert(review);
		return review;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public boolean deleteById(String customerCode, Integer bookId) {
		ReviewRecord review = newInstanceWithPrimaryKey(customerCode, bookId);
		long r = super.doDelete(review);
		return r > 0L;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public boolean delete(ReviewRecord review) {
		long r = super.doDelete(review);
		return r > 0L;
	}

	/**
	 * Checks the existence of a record in the database using the given primary
	 * key value(s)
	 
	 * @return
	 */
	public boolean exists(String customerCode, Integer bookId) {
		ReviewRecord review = newInstanceWithPrimaryKey(customerCode, bookId);
		return super.doExists(review);
	}

	/**
	 * Checks the existence of the given bean in the database
	
	 * @return
	 */
	public boolean exists(ReviewRecord review) {
		return super.doExists(review);
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
