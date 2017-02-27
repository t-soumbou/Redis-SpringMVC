package org.demo.persistence.impl.redis;
import java.util.List;

import javax.inject.Named;
import org.demo.persistence.impl.redis.commons.GenericDAO;


import org.demo.data.record.PublisherRecord;
import org.demo.persistence.PublisherPersistence;

@Named("PublisherPersistence")
public class PublisherPersistenceImplRedis extends GenericDAO<PublisherRecord> implements PublisherPersistence {

	/**
	 * DAO constructor
	 */
	public PublisherPersistenceImplRedis() {
		super("publisher", PublisherRecord.class);
	}
	/**
	 * make key in a good format
	 * 
	 * @param bean;
	 * @return key  with a good format
	 */
	@Override
	protected String getKey(PublisherRecord bean) {
		return buildString(bean.getCode());
	}
	/**
	 * Creates a new instance of the bean
	 * primary value(s)
	 * 
	 * @param key;
	 * @return the new instance
	 */
	private PublisherRecord newInstanceWithPrimaryKey(Integer code) {
		PublisherRecord publisher = new PublisherRecord ();
        publisher.setCode(code); 
		return publisher;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public PublisherRecord findById(Integer code){
        PublisherRecord  publisher = newInstanceWithPrimaryKey(code);
		return super.doSelect(publisher);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public List<PublisherRecord> findAll() {
		return super.doSelectAll();
	}

	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s)
	 * in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from
	 * the database<br>
	 * If not found, the given instance remains unchanged
	 */
	public PublisherRecord load(PublisherRecord publisher) {
		return super.doSelect(publisher);
	}

	/**
	 * Inserts the given bean in the database
	 * 
	 */
	public boolean insert(PublisherRecord publisher){
		return super.doInsert(publisher);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public PublisherRecord save(PublisherRecord publisher){
		if (super.doExists(publisher)) {
			super.doUpdate(publisher);
		} else {
			insert(publisher);
		}
        return publisher;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public boolean update(PublisherRecord publisher){
		return super.doUpdate(publisher);	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public PublisherRecord create(PublisherRecord publisher){
		insert(publisher);
		return publisher;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public boolean deleteById(Integer code) {
		PublisherRecord publisher = newInstanceWithPrimaryKey(code);
		long r = super.doDelete(publisher);
		return r > 0L;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public boolean delete(PublisherRecord publisher) {
		long r = super.doDelete(publisher);
		return r > 0L;
	}

	/**
	 * Checks the existence of a record in the database using the given primary
	 * key value(s)
	 
	 * @return
	 */
	public boolean exists(Integer code) {
		PublisherRecord publisher = newInstanceWithPrimaryKey(code);
		return super.doExists(publisher);
	}

	/**
	 * Checks the existence of the given bean in the database
	
	 * @return
	 */
	public boolean exists(PublisherRecord publisher) {
		return super.doExists(publisher);
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
