package org.demo.data.record;


import org.junit.Assert;
import org.junit.Test;

public class WorkgroupRecordTest 
{

	@Test
	public void testSettersAndGetters() {
		
		System.out.println("Checking class WorkgroupRecord getters and setters ..." );
		
		WorkgroupRecord workgroupRecord = new WorkgroupRecord();


		//--- Test setter/getter for attribute "id"  ( model type : Short / wrapperType : Short )
		workgroupRecord.setId( Short.valueOf((short)1) ) ;
		Assert.assertEquals( Short.valueOf((short)1), workgroupRecord.getId() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "name"  ( model type : String / wrapperType : String )
		workgroupRecord.setName( "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" ) ;
		Assert.assertEquals( "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", workgroupRecord.getName() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "description"  ( model type : String / wrapperType : String )
		workgroupRecord.setDescription( "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" ) ;
		Assert.assertEquals( "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", workgroupRecord.getDescription() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "creationDate"  ( model type : Date / wrapperType : Date )
		workgroupRecord.setCreationDate( java.sql.Date.valueOf("2001-06-22") ) ;
		Assert.assertEquals( java.sql.Date.valueOf("2001-06-22"), workgroupRecord.getCreationDate() ) ; // Not primitive type in model


	}

}
