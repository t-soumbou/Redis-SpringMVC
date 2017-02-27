package org.demo.data.record;


import org.junit.Assert;
import org.junit.Test;

public class BookOrderRecordTest 
{

	@Test
	public void testSettersAndGetters() {
		
		System.out.println("Checking class BookOrderRecord getters and setters ..." );
		
		BookOrderRecord bookOrderRecord = new BookOrderRecord();


		//--- Test setter/getter for attribute "id"  ( model type : Integer / wrapperType : Integer )
		bookOrderRecord.setId( Integer.valueOf(100) ) ;
		Assert.assertEquals( Integer.valueOf(100), bookOrderRecord.getId() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "shopCode"  ( model type : String / wrapperType : String )
		bookOrderRecord.setShopCode( "AAA" ) ;
		Assert.assertEquals( "AAA", bookOrderRecord.getShopCode() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "customerCode"  ( model type : String / wrapperType : String )
		bookOrderRecord.setCustomerCode( "AAAAA" ) ;
		Assert.assertEquals( "AAAAA", bookOrderRecord.getCustomerCode() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "employeeCode"  ( model type : String / wrapperType : String )
		bookOrderRecord.setEmployeeCode( "AAAA" ) ;
		Assert.assertEquals( "AAAA", bookOrderRecord.getEmployeeCode() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "date"  ( model type : Date / wrapperType : Date )
		bookOrderRecord.setDate( java.sql.Date.valueOf("2001-06-22") ) ;
		Assert.assertEquals( java.sql.Date.valueOf("2001-06-22"), bookOrderRecord.getDate() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "state"  ( model type : Integer / wrapperType : Integer )
		bookOrderRecord.setState( Integer.valueOf(100) ) ;
		Assert.assertEquals( Integer.valueOf(100), bookOrderRecord.getState() ) ; // Not primitive type in model


	}

}
