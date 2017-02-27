package org.demo.persistence.impl.redis;
import java.util.List;

import javax.inject.Named;
import org.demo.persistence.impl.redis.commons.GenericDAO;


import org.demo.data.record.AuthorRecord;
import org.demo.persistence.AuthorPersistence;

@Named("AuthorPersistence")
public class AuthorPersistenceImplRedis extends GenericDAO<AuthorRecord> implements AuthorPersistence {

	/**
	 * DAO constructor
	 */
	public AuthorPersistenceImplRedis() {
		super("author", AuthorRecord.class);
	}
	/**
	 * make key in a good format
	 * 
	 * @param bean;
	 * @return key  with a good format
	 */
	@Override
	protected String getKey(AuthorRecord bean) {
		return buildString(bean.getId());
	}
	/**
	 * Creates a new instance of the bean
	 * primary value(s)
	 * 
	 * @param key;
	 * @return the new instance
	 */
	private AuthorRecord newInstanceWithPrimaryKey(Integer id) {
		AuthorRecord author = new AuthorRecord ();
        author.setId(id); 
		return author;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public AuthorRecord findById(Integer id){
        AuthorRecord  author = newInstanceWithPrimaryKey(id);
		return super.doSelect(author);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public List<AuthorRecord> findAll() {
		return super.doSelectAll();
	}

	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s)
	 * in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from
	 * the database<br>
	 * If not found, the given instance remains unchanged
	 */
	public AuthorRecord load(AuthorRecord author) {
		return super.doSelect(author);
	}

	/**
	 * Inserts the given bean in the database
	 * 
	 */
	public boolean insert(AuthorRecord author){
		return super.doInsert(author);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public AuthorRecord save(AuthorRecord author){
		if (super.doExists(author)) {
			super.doUpdate(author);
		} else {
			insert(author);
		}
        return author;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public boolean update(AuthorRecord author){
		return super.doUpdate(author);	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public AuthorRecord create(AuthorRecord author){
		insert(author);
		return author;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public boolean deleteById(Integer id) {
		AuthorRecord author = newInstanceWithPrimaryKey(id);
		long r = super.doDelete(author);
		return r > 0L;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public boolean delete(AuthorRecord author) {
		long r = super.doDelete(author);
		return r > 0L;
	}

	/**
	 * Checks the existence of a record in the database using the given primary
	 * key value(s)
	 
	 * @return
	 */
	public boolean exists(Integer id) {
		AuthorRecord author = newInstanceWithPrimaryKey(id);
		return super.doExists(author);
	}

	/**
	 * Checks the existence of the given bean in the database
	
	 * @return
	 */
	public boolean exists(AuthorRecord author) {
		return super.doExists(author);
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
