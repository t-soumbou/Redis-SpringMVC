package org.demo.persistence.impl.redis;
import java.util.List;

import javax.inject.Named;
import org.demo.persistence.impl.redis.commons.GenericDAO;


import org.demo.data.record.CountryRecord;
import org.demo.persistence.CountryPersistence;

@Named("CountryPersistence")
public class CountryPersistenceImplRedis extends GenericDAO<CountryRecord> implements CountryPersistence {

	/**
	 * DAO constructor
	 */
	public CountryPersistenceImplRedis() {
		super("country", CountryRecord.class);
	}
	/**
	 * make key in a good format
	 * 
	 * @param bean;
	 * @return key  with a good format
	 */
	@Override
	protected String getKey(CountryRecord bean) {
		return buildString(bean.getCode());
	}
	/**
	 * Creates a new instance of the bean
	 * primary value(s)
	 * 
	 * @param key;
	 * @return the new instance
	 */
	private CountryRecord newInstanceWithPrimaryKey(String code) {
		CountryRecord country = new CountryRecord ();
        country.setCode(code); 
		return country;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public CountryRecord findById(String code){
        CountryRecord  country = newInstanceWithPrimaryKey(code);
		return super.doSelect(country);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public List<CountryRecord> findAll() {
		return super.doSelectAll();
	}

	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s)
	 * in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from
	 * the database<br>
	 * If not found, the given instance remains unchanged
	 */
	public CountryRecord load(CountryRecord country) {
		return super.doSelect(country);
	}

	/**
	 * Inserts the given bean in the database
	 * 
	 */
	public boolean insert(CountryRecord country){
		return super.doInsert(country);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public CountryRecord save(CountryRecord country){
		if (super.doExists(country)) {
			super.doUpdate(country);
		} else {
			insert(country);
		}
        return country;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public boolean update(CountryRecord country){
		return super.doUpdate(country);	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public CountryRecord create(CountryRecord country){
		insert(country);
		return country;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public boolean deleteById(String code) {
		CountryRecord country = newInstanceWithPrimaryKey(code);
		long r = super.doDelete(country);
		return r > 0L;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public boolean delete(CountryRecord country) {
		long r = super.doDelete(country);
		return r > 0L;
	}

	/**
	 * Checks the existence of a record in the database using the given primary
	 * key value(s)
	 
	 * @return
	 */
	public boolean exists(String code) {
		CountryRecord country = newInstanceWithPrimaryKey(code);
		return super.doExists(country);
	}

	/**
	 * Checks the existence of the given bean in the database
	
	 * @return
	 */
	public boolean exists(CountryRecord country) {
		return super.doExists(country);
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
