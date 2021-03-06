package org.demo.persistence;

import java.util.List;

import org.demo.data.record.BookRecord;

/**
 * Persistence Interface for BookRecord.
 */
public interface BookPersistence { 

	/**
	 * Tries to find an entity using its Id / Primary Key
	 * @param id
	 * @return entity
	 */
	BookRecord findById( Integer id  ) ;

	/**
	 * Finds all entities.
	 * @return all entities
	 */
	List<BookRecord> findAll();

	/**
	 * Saves the given entity in the database (create or update)
	 * @param entity
	 * @return entity
	 */
	BookRecord save(BookRecord entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return true if the entity has been updated, false if not found and not updated
	 */
	boolean update(BookRecord entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	BookRecord create(BookRecord entity);

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
	boolean delete( BookRecord entity );

}
