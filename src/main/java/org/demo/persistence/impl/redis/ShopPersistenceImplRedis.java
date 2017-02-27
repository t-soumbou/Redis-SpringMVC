package org.demo.persistence.impl.redis;
import java.util.List;

import javax.inject.Named;
import org.demo.persistence.impl.redis.commons.GenericDAO;


import org.demo.data.record.ShopRecord;
import org.demo.persistence.ShopPersistence;

@Named("ShopPersistence")
public class ShopPersistenceImplRedis extends GenericDAO<ShopRecord> implements ShopPersistence {

	/**
	 * DAO constructor
	 */
	public ShopPersistenceImplRedis() {
		super("shop", ShopRecord.class);
	}
	/**
	 * make key in a good format
	 * 
	 * @param bean;
	 * @return key  with a good format
	 */
	@Override
	protected String getKey(ShopRecord bean) {
		return buildString(bean.getCode());
	}
	/**
	 * Creates a new instance of the bean
	 * primary value(s)
	 * 
	 * @param key;
	 * @return the new instance
	 */
	private ShopRecord newInstanceWithPrimaryKey(String code) {
		ShopRecord shop = new ShopRecord ();
        shop.setCode(code); 
		return shop;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public ShopRecord findById(String code){
        ShopRecord  shop = newInstanceWithPrimaryKey(code);
		return super.doSelect(shop);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public List<ShopRecord> findAll() {
		return super.doSelectAll();
	}

	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s)
	 * in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from
	 * the database<br>
	 * If not found, the given instance remains unchanged
	 */
	public ShopRecord load(ShopRecord shop) {
		return super.doSelect(shop);
	}

	/**
	 * Inserts the given bean in the database
	 * 
	 */
	public boolean insert(ShopRecord shop){
		return super.doInsert(shop);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public ShopRecord save(ShopRecord shop){
		if (super.doExists(shop)) {
			super.doUpdate(shop);
		} else {
			insert(shop);
		}
        return shop;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public boolean update(ShopRecord shop){
		return super.doUpdate(shop);	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public ShopRecord create(ShopRecord shop){
		insert(shop);
		return shop;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public boolean deleteById(String code) {
		ShopRecord shop = newInstanceWithPrimaryKey(code);
		long r = super.doDelete(shop);
		return r > 0L;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public boolean delete(ShopRecord shop) {
		long r = super.doDelete(shop);
		return r > 0L;
	}

	/**
	 * Checks the existence of a record in the database using the given primary
	 * key value(s)
	 
	 * @return
	 */
	public boolean exists(String code) {
		ShopRecord shop = newInstanceWithPrimaryKey(code);
		return super.doExists(shop);
	}

	/**
	 * Checks the existence of the given bean in the database
	
	 * @return
	 */
	public boolean exists(ShopRecord shop) {
		return super.doExists(shop);
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
