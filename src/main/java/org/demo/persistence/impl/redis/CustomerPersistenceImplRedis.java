package org.demo.persistence.impl.redis;
import java.util.List;

import javax.inject.Named;
import org.demo.persistence.impl.redis.commons.GenericDAO;


import org.demo.data.record.CustomerRecord;
import org.demo.persistence.CustomerPersistence;

@Named("CustomerPersistence")
public class CustomerPersistenceImplRedis extends GenericDAO<CustomerRecord> implements CustomerPersistence {

	/**
	 * DAO constructor
	 */
	public CustomerPersistenceImplRedis() {
		super("customer", CustomerRecord.class);
	}
	/**
	 * make key in a good format
	 * 
	 * @param bean;
	 * @return key  with a good format
	 */
	@Override
	protected String getKey(CustomerRecord bean) {
		return buildString(bean.getCode());
	}
	/**
	 * Creates a new instance of the bean
	 * primary value(s)
	 * 
	 * @param key;
	 * @return the new instance
	 */
	private CustomerRecord newInstanceWithPrimaryKey(String code) {
		CustomerRecord customer = new CustomerRecord ();
        customer.setCode(code); 
		return customer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public CustomerRecord findById(String code){
        CustomerRecord  customer = newInstanceWithPrimaryKey(code);
		return super.doSelect(customer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public List<CustomerRecord> findAll() {
		return super.doSelectAll();
	}

	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s)
	 * in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from
	 * the database<br>
	 * If not found, the given instance remains unchanged
	 */
	public CustomerRecord load(CustomerRecord customer) {
		return super.doSelect(customer);
	}

	/**
	 * Inserts the given bean in the database
	 * 
	 */
	public boolean insert(CustomerRecord customer){
		return super.doInsert(customer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public CustomerRecord save(CustomerRecord customer){
		if (super.doExists(customer)) {
			super.doUpdate(customer);
		} else {
			insert(customer);
		}
        return customer;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public boolean update(CustomerRecord customer){
		return super.doUpdate(customer);	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public CustomerRecord create(CustomerRecord customer){
		insert(customer);
		return customer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public boolean deleteById(String code) {
		CustomerRecord customer = newInstanceWithPrimaryKey(code);
		long r = super.doDelete(customer);
		return r > 0L;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public boolean delete(CustomerRecord customer) {
		long r = super.doDelete(customer);
		return r > 0L;
	}

	/**
	 * Checks the existence of a record in the database using the given primary
	 * key value(s)
	 
	 * @return
	 */
	public boolean exists(String code) {
		CustomerRecord customer = newInstanceWithPrimaryKey(code);
		return super.doExists(customer);
	}

	/**
	 * Checks the existence of the given bean in the database
	
	 * @return
	 */
	public boolean exists(CustomerRecord customer) {
		return super.doExists(customer);
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
