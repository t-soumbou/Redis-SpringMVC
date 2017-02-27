package org.demo.persistence.impl.redis;
import java.util.List;

import javax.inject.Named;
import org.demo.persistence.impl.redis.commons.GenericDAO;


import org.demo.data.record.BadgeRecord;
import org.demo.persistence.BadgePersistence;

@Named("BadgePersistence")
public class BadgePersistenceImplRedis extends GenericDAO<BadgeRecord> implements BadgePersistence {

	/**
	 * DAO constructor
	 */
	public BadgePersistenceImplRedis() {
		super("badge", BadgeRecord.class);
	}
	/**
	 * make key in a good format
	 * 
	 * @param bean;
	 * @return key  with a good format
	 */
	@Override
	protected String getKey(BadgeRecord bean) {
		return buildString(bean.getBadgeNumber());
	}
	/**
	 * Creates a new instance of the bean
	 * primary value(s)
	 * 
	 * @param key;
	 * @return the new instance
	 */
	private BadgeRecord newInstanceWithPrimaryKey(Integer badgeNumber) {
		BadgeRecord badge = new BadgeRecord ();
        badge.setBadgeNumber(badgeNumber); 
		return badge;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public BadgeRecord findById(Integer badgeNumber){
        BadgeRecord  badge = newInstanceWithPrimaryKey(badgeNumber);
		return super.doSelect(badge);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public List<BadgeRecord> findAll() {
		return super.doSelectAll();
	}

	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s)
	 * in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from
	 * the database<br>
	 * If not found, the given instance remains unchanged
	 */
	public BadgeRecord load(BadgeRecord badge) {
		return super.doSelect(badge);
	}

	/**
	 * Inserts the given bean in the database
	 * 
	 */
	public boolean insert(BadgeRecord badge){
		return super.doInsert(badge);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public BadgeRecord save(BadgeRecord badge){
		if (super.doExists(badge)) {
			super.doUpdate(badge);
		} else {
			insert(badge);
		}
        return badge;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public boolean update(BadgeRecord badge){
		return super.doUpdate(badge);	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public BadgeRecord create(BadgeRecord badge){
		insert(badge);
		return badge;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public boolean deleteById(Integer badgeNumber) {
		BadgeRecord badge = newInstanceWithPrimaryKey(badgeNumber);
		long r = super.doDelete(badge);
		return r > 0L;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public boolean delete(BadgeRecord badge) {
		long r = super.doDelete(badge);
		return r > 0L;
	}

	/**
	 * Checks the existence of a record in the database using the given primary
	 * key value(s)
	 
	 * @return
	 */
	public boolean exists(Integer badgeNumber) {
		BadgeRecord badge = newInstanceWithPrimaryKey(badgeNumber);
		return super.doExists(badge);
	}

	/**
	 * Checks the existence of the given bean in the database
	
	 * @return
	 */
	public boolean exists(BadgeRecord badge) {
		return super.doExists(badge);
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
