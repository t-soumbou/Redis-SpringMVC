package org.demo.persistence;

import java.util.List;

import org.demo.data.record.AuthorRecord;

/**
 * Persistence Interface for AuthorRecord.
 */
public interface AuthorPersistence { 

	/**
	 * Tries to find an entity using its Id / Primary Key
	 * @param id
	 * @return entity
	 */
	AuthorRecord findById( Integer id  ) ;

	/**
	 * Finds all entities.
	 * @return all entities
	 */
	List<AuthorRecord> findAll();

	/**
	 * Saves the given entity in the database (create or update)
	 * @param entity
	 * @return entity
	 */
	AuthorRecord save(AuthorRecord entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return true if the entity has been updated, false if not found and not updated
	 */
	boolean update(AuthorRecord entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	AuthorRecord create(AuthorRecord entity);

	/**
	 * Deletes an entity using its Id / Primary Key
	 * @param id
	 * @return true if the entity has been deleted, false if not found and not deleted
	 */
	boolean deleteById( Integer id );

	/**
	 * Deletes an entity using the Id / Primary Key stored in the given object
	 * @param the entity to be deleted (supposed to have a valid Id/PK )
	 * @return true if the entity has been deleted, false if not found and not deleted
	 */
	boolean delete( AuthorRecord entity );

}
