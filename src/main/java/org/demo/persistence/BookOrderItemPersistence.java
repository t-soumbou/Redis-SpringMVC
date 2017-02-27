package org.demo.persistence;

import java.util.List;

import org.demo.data.record.BookOrderItemRecord;

/**
 * Persistence Interface for BookOrderItemRecord.
 */
public interface BookOrderItemPersistence { 

	/**
	 * Tries to find an entity using its Id / Primary Key
	 * @param bookOrderId
	 * @param bookId
	 * @return entity
	 */
	BookOrderItemRecord findById( Integer bookOrderId, Integer bookId  ) ;

	/**
	 * Finds all entities.
	 * @return all entities
	 */
	List<BookOrderItemRecord> findAll();

	/**
	 * Saves the given entity in the database (create or update)
	 * @param entity
	 * @return entity
	 */
	BookOrderItemRecord save(BookOrderItemRecord entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return true if the entity has been updated, false if not found and not updated
	 */
	boolean update(BookOrderItemRecord entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	BookOrderItemRecord create(BookOrderItemRecord entity);

	/**
	 * Deletes an entity using its Id / Primary Key
	 * @param bookOrderId
	 * @param bookId
	 * @return true if the entity has been deleted, false if not found and not deleted
	 */
	boolean deleteById( Integer bookOrderId, Integer bookId );

	/**
	 * Deletes an entity using the Id / Primary Key stored in the given object
	 * @param the entity to be deleted (supposed to have a valid Id/PK )
	 * @return true if the entity has been deleted, false if not found and not deleted
	 */
	boolean delete( BookOrderItemRecord entity );

}
