package org.demo.persistence.impl.redis;
import java.util.List;

import javax.inject.Named;
import org.demo.persistence.impl.redis.commons.GenericDAO;


import org.demo.data.record.WorkgroupRecord;
import org.demo.persistence.WorkgroupPersistence;

@Named("WorkgroupPersistence")
public class WorkgroupPersistenceImplRedis extends GenericDAO<WorkgroupRecord> implements WorkgroupPersistence {

	/**
	 * DAO constructor
	 */
	public WorkgroupPersistenceImplRedis() {
		super("workgroup", WorkgroupRecord.class);
	}
	/**
	 * make key in a good format
	 * 
	 * @param bean;
	 * @return key  with a good format
	 */
	@Override
	protected String getKey(WorkgroupRecord bean) {
		return buildString(bean.getId());
	}
	/**
	 * Creates a new instance of the bean
	 * primary value(s)
	 * 
	 * @param key;
	 * @return the new instance
	 */
	private WorkgroupRecord newInstanceWithPrimaryKey(Short id) {
		WorkgroupRecord workgroup = new WorkgroupRecord ();
        workgroup.setId(id); 
		return workgroup;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public WorkgroupRecord findById(Short id){
        WorkgroupRecord  workgroup = newInstanceWithPrimaryKey(id);
		return super.doSelect(workgroup);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public List<WorkgroupRecord> findAll() {
		return super.doSelectAll();
	}

	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s)
	 * in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from
	 * the database<br>
	 * If not found, the given instance remains unchanged
	 */
	public WorkgroupRecord load(WorkgroupRecord workgroup) {
		return super.doSelect(workgroup);
	}

	/**
	 * Inserts the given bean in the database
	 * 
	 */
	public boolean insert(WorkgroupRecord workgroup){
		return super.doInsert(workgroup);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public WorkgroupRecord save(WorkgroupRecord workgroup){
		if (super.doExists(workgroup)) {
			super.doUpdate(workgroup);
		} else {
			insert(workgroup);
		}
        return workgroup;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public boolean update(WorkgroupRecord workgroup){
		return super.doUpdate(workgroup);	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public WorkgroupRecord create(WorkgroupRecord workgroup){
		insert(workgroup);
		return workgroup;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public boolean deleteById(Short id) {
		WorkgroupRecord workgroup = newInstanceWithPrimaryKey(id);
		long r = super.doDelete(workgroup);
		return r > 0L;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface
	 */
	public boolean delete(WorkgroupRecord workgroup) {
		long r = super.doDelete(workgroup);
		return r > 0L;
	}

	/**
	 * Checks the existence of a record in the database using the given primary
	 * key value(s)
	 
	 * @return
	 */
	public boolean exists(Short id) {
		WorkgroupRecord workgroup = newInstanceWithPrimaryKey(id);
		return super.doExists(workgroup);
	}

	/**
	 * Checks the existence of the given bean in the database
	
	 * @return
	 */
	public boolean exists(WorkgroupRecord workgroup) {
		return super.doExists(workgroup);
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
