package org.demo.persistence.impl.redis;
import java.util.List;

import javax.inject.Named;
import org.demo.persistence.impl.redis.commons.GenericDAO;


import org.demo.data.record.EmployeeGroupRecord;
import org.demo.persistence.EmployeeGroupPersistence;

@Named("EmployeeGroupPersistence")
public class EmployeeGroupPersistenceImplRedis extends GenericDAO<EmployeeGroupRecord> implements EmployeeGroupPersistence {

	/**
	 * DAO constructor
	 */
	public EmployeeGroupPersistenceImplRedis() {
		super("employeeGroup", EmployeeGroupRecord.class);
	}
	/**
	 * make key in a good format
	 * 
	 * @param bean;
	 * @return key  with a good format
	 */
	@Override
	protected String getKey(EmployeeGroupRecord bean) {
		return buildString(bean.getEmployeeCode(), bean.getGroupId());
	}
	/**
	 * Creates a new instance of the bean
	 * primary value(s)
	 * 
	 * @param key;
	 * @return the new instance
	 */
	private EmployeeGroupRecord newInstanceWithPrimaryKey(String employeeCode, Short groupId) {
		EmployeeGroupRecord employeeGroup = new EmployeeGroupRecord ();
        employeeGroup.setEmployeeCode(employeeCode); 
        employeeGroup.setGroupId(groupId); 
		return employeeGroup;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public EmployeeGroupRecord findById(String employeeCode, Short groupId){
        EmployeeGroupRecord  employeeGroup = newInstanceWithPrimaryKey(employeeCode, groupId);
		return super.doSelect(employeeGroup);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public List<EmployeeGroupRecord> findAll() {
		return super.doSelectAll();
	}

	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s)
	 * in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from
	 * the database<br>
	 * If not found, the given instance remains unchanged
	 */
	public EmployeeGroupRecord load(EmployeeGroupRecord employeeGroup) {
		return super.doSelect(employeeGroup);
	}

	/**
	 * Inserts the given bean in the database
	 * 
	 */
	public boolean insert(EmployeeGroupRecord employeeGroup){
		return super.doInsert(employeeGroup);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public EmployeeGroupRecord save(EmployeeGroupRecord employeeGroup){
		if (super.doExists(employeeGroup)) {
			super.doUpdate(employeeGroup);
		} else {
			insert(employeeGroup);
		}
        return employeeGroup;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public boolean update(EmployeeGroupRecord employeeGroup){
		return super.doUpdate(employeeGroup);	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public EmployeeGroupRecord create(EmployeeGroupRecord employeeGroup){
		insert(employeeGroup);
		return employeeGroup;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public boolean deleteById(String employeeCode, Short groupId) {
		EmployeeGroupRecord employeeGroup = newInstanceWithPrimaryKey(employeeCode, groupId);
		long r = super.doDelete(employeeGroup);
		return r > 0L;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public boolean delete(EmployeeGroupRecord employeeGroup) {
		long r = super.doDelete(employeeGroup);
		return r > 0L;
	}

	/**
	 * Checks the existence of a record in the database using the given primary
	 * key value(s)
	 
	 * @return
	 */
	public boolean exists(String employeeCode, Short groupId) {
		EmployeeGroupRecord employeeGroup = newInstanceWithPrimaryKey(employeeCode, groupId);
		return super.doExists(employeeGroup);
	}

	/**
	 * Checks the existence of the given bean in the database
	
	 * @return
	 */
	public boolean exists(EmployeeGroupRecord employeeGroup) {
		return super.doExists(employeeGroup);
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
