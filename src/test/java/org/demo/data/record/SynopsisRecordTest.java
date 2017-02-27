package org.demo.data.record;


import org.junit.Assert;
import org.junit.Test;

public class SynopsisRecordTest 
{

	@Test
	public void testSettersAndGetters() {
		
		System.out.println("Checking class SynopsisRecord getters and setters ..." );
		
		SynopsisRecord synopsisRecord = new SynopsisRecord();


		//--- Test setter/getter for attribute "bookId"  ( model type : Integer / wrapperType : Integer )
		synopsisRecord.setBookId( Integer.valueOf(100) ) ;
		Assert.assertEquals( Integer.valueOf(100), synopsisRecord.getBookId() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "synopsis"  ( model type : String / wrapperType : String )
		synopsisRecord.setSynopsis( "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" ) ;
		Assert.assertEquals( "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", synopsisRecord.getSynopsis() ) ; // Not primitive type in model


	}

}
