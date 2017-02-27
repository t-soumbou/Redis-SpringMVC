package org.demo.persistence.impl.redis;
import java.util.List;

import javax.inject.Named;
import org.demo.persistence.impl.redis.commons.GenericDAO;


import org.demo.data.record.EmployeeRecord;
import org.demo.persistence.EmployeePersistence;

@Named("EmployeePersistence")
public class EmployeePersistenceImplRedis extends GenericDAO<EmployeeRecord> implements EmployeePersistence {

	/**
	 * DAO constructor
	 */
	public EmployeePersistenceImplRedis() {
		super("employee", EmployeeRecord.class);
	}
	/**
	 * make key in a good format
	 * 
	 * @param bean;
	 * @return key  with a good format
	 */
	@Override
	protected String getKey(EmployeeRecord bean) {
		return buildString(bean.getCode());
	}
	/**
	 * Creates a new instance of the bean
	 * primary value(s)
	 * 
	 * @param key;
	 * @return the new instance
	 */
	private EmployeeRecord newInstanceWithPrimaryKey(String code) {
		EmployeeRecord employee = new EmployeeRecord ();
        employee.setCode(code); 
		return employee;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public EmployeeRecord findById(String code){
        EmployeeRecord  employee = newInstanceWithPrimaryKey(code);
		return super.doSelect(employee);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public List<EmployeeRecord> findAll() {
		return super.doSelectAll();
	}

	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s)
	 * in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from
	 * the database<br>
	 * If not found, the given instance remains unchanged
	 */
	public EmployeeRecord load(EmployeeRecord employee) {
		return super.doSelect(employee);
	}

	/**
	 * Inserts the given bean in the database
	 * 
	 */
	public boolean insert(EmployeeRecord employee){
		return super.doInsert(employee);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public EmployeeRecord save(EmployeeRecord employee){
		if (super.doExists(employee)) {
			super.doUpdate(employee);
		} else {
			insert(employee);
		}
        return employee;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public boolean update(EmployeeRecord employee){
		return super.doUpdate(employee);	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public EmployeeRecord create(EmployeeRecord employee){
		insert(employee);
		return employee;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public boolean deleteById(String code) {
		EmployeeRecord employee = newInstanceWithPrimaryKey(code);
		long r = super.doDelete(employee);
		return r > 0L;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public boolean delete(EmployeeRecord employee) {
		long r = super.doDelete(employee);
		return r > 0L;
	}

	/**
	 * Checks the existence of a record in the database using the given primary
	 * key value(s)
	 
	 * @return
	 */
	public boolean exists(String code) {
		EmployeeRecord employee = newInstanceWithPrimaryKey(code);
		return super.doExists(employee);
	}

	/**
	 * Checks the existence of the given bean in the database
	
	 * @return
	 */
	public boolean exists(EmployeeRecord employee) {
		return super.doExists(employee);
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
