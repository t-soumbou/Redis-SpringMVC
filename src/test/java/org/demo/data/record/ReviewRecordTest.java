package org.demo.data.record;


import org.junit.Assert;
import org.junit.Test;

public class ReviewRecordTest 
{

	@Test
	public void testSettersAndGetters() {
		
		System.out.println("Checking class ReviewRecord getters and setters ..." );
		
		ReviewRecord reviewRecord = new ReviewRecord();


		//--- Test setter/getter for attribute "customerCode"  ( model type : String / wrapperType : String )
		reviewRecord.setCustomerCode( "AAAAA" ) ;
		Assert.assertEquals( "AAAAA", reviewRecord.getCustomerCode() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "bookId"  ( model type : Integer / wrapperType : Integer )
		reviewRecord.setBookId( Integer.valueOf(100) ) ;
		Assert.assertEquals( Integer.valueOf(100), reviewRecord.getBookId() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "reviewText"  ( model type : String / wrapperType : String )
		reviewRecord.setReviewText( "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" ) ;
		Assert.assertEquals( "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", reviewRecord.getReviewText() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "reviewNote"  ( model type : Integer / wrapperType : Integer )
		reviewRecord.setReviewNote( Integer.valueOf(100) ) ;
		Assert.assertEquals( Integer.valueOf(100), reviewRecord.getReviewNote() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "creation"  ( model type : Date / wrapperType : Date )
		reviewRecord.setCreation( java.sql.Timestamp.valueOf("2001-05-21 01:46:52") ) ;
		Assert.assertEquals( java.sql.Timestamp.valueOf("2001-05-21 01:46:52"), reviewRecord.getCreation() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "lastUpdate"  ( model type : Date / wrapperType : Date )
		reviewRecord.setLastUpdate( java.sql.Timestamp.valueOf("2001-05-21 01:46:52") ) ;
		Assert.assertEquals( java.sql.Timestamp.valueOf("2001-05-21 01:46:52"), reviewRecord.getLastUpdate() ) ; // Not primitive type in model


	}

}
