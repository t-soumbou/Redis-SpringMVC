package org.demo.data.record;


import org.junit.Assert;
import org.junit.Test;

public class BadgeRecordTest 
{

	@Test
	public void testSettersAndGetters() {
		
		System.out.println("Checking class BadgeRecord getters and setters ..." );
		
		BadgeRecord badgeRecord = new BadgeRecord();


		//--- Test setter/getter for attribute "badgeNumber"  ( model type : Integer / wrapperType : Integer )
		badgeRecord.setBadgeNumber( Integer.valueOf(100) ) ;
		Assert.assertEquals( Integer.valueOf(100), badgeRecord.getBadgeNumber() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "authorizationLevel"  ( model type : Short / wrapperType : Short )
		badgeRecord.setAuthorizationLevel( Short.valueOf((short)1) ) ;
		Assert.assertEquals( Short.valueOf((short)1), badgeRecord.getAuthorizationLevel() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "endOfValidity"  ( model type : Date / wrapperType : Date )
		badgeRecord.setEndOfValidity( java.sql.Date.valueOf("2001-06-22") ) ;
		Assert.assertEquals( java.sql.Date.valueOf("2001-06-22"), badgeRecord.getEndOfValidity() ) ; // Not primitive type in model


	}

}
