package org.demo.persistence.impl.redis;
import java.util.List;

import javax.inject.Named;
import org.demo.persistence.impl.redis.commons.GenericDAO;


import org.demo.data.record.SynopsisRecord;
import org.demo.persistence.SynopsisPersistence;

@Named("SynopsisPersistence")
public class SynopsisPersistenceImplRedis extends GenericDAO<SynopsisRecord> implements SynopsisPersistence {

	/**
	 * DAO constructor
	 */
	public SynopsisPersistenceImplRedis() {
		super("synopsis", SynopsisRecord.class);
	}
	/**
	 * make key in a good format
	 * 
	 * @param bean;
	 * @return key  with a good format
	 */
	@Override
	protected String getKey(SynopsisRecord bean) {
		return buildString(bean.getBookId());
	}
	/**
	 * Creates a new instance of the bean
	 * primary value(s)
	 * 
	 * @param key;
	 * @return the new instance
	 */
	private SynopsisRecord newInstanceWithPrimaryKey(Integer bookId) {
		SynopsisRecord synopsis = new SynopsisRecord ();
        synopsis.setBookId(bookId); 
		return synopsis;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public SynopsisRecord findById(Integer bookId){
        SynopsisRecord  synopsis = newInstanceWithPrimaryKey(bookId);
		return super.doSelect(synopsis);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public List<SynopsisRecord> findAll() {
		return super.doSelectAll();
	}

	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s)
	 * in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from
	 * the database<br>
	 * If not found, the given instance remains unchanged
	 */
	public SynopsisRecord load(SynopsisRecord synopsis) {
		return super.doSelect(synopsis);
	}

	/**
	 * Inserts the given bean in the database
	 * 
	 */
	public boolean insert(SynopsisRecord synopsis){
		return super.doInsert(synopsis);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public SynopsisRecord save(SynopsisRecord synopsis){
		if (super.doExists(synopsis)) {
			super.doUpdate(synopsis);
		} else {
			insert(synopsis);
		}
        return synopsis;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public boolean update(SynopsisRecord synopsis){
		return super.doUpdate(synopsis);	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public SynopsisRecord create(SynopsisRecord synopsis){
		insert(synopsis);
		return synopsis;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public boolean deleteById(Integer bookId) {
		SynopsisRecord synopsis = newInstanceWithPrimaryKey(bookId);
		long r = super.doDelete(synopsis);
		return r > 0L;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public boolean delete(SynopsisRecord synopsis) {
		long r = super.doDelete(synopsis);
		return r > 0L;
	}

	/**
	 * Checks the existence of a record in the database using the given primary
	 * key value(s)
	 
	 * @return
	 */
	public boolean exists(Integer bookId) {
		SynopsisRecord synopsis = newInstanceWithPrimaryKey(bookId);
		return super.doExists(synopsis);
	}

	/**
	 * Checks the existence of the given bean in the database
	
	 * @return
	 */
	public boolean exists(SynopsisRecord synopsis) {
		return super.doExists(synopsis);
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
