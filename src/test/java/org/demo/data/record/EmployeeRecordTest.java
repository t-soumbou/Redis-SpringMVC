package org.demo.data.record;


import org.junit.Assert;
import org.junit.Test;

public class EmployeeRecordTest 
{

	@Test
	public void testSettersAndGetters() {
		
		System.out.println("Checking class EmployeeRecord getters and setters ..." );
		
		EmployeeRecord employeeRecord = new EmployeeRecord();


		//--- Test setter/getter for attribute "code"  ( model type : String / wrapperType : String )
		employeeRecord.setCode( "AAAA" ) ;
		Assert.assertEquals( "AAAA", employeeRecord.getCode() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "shopCode"  ( model type : String / wrapperType : String )
		employeeRecord.setShopCode( "AAA" ) ;
		Assert.assertEquals( "AAA", employeeRecord.getShopCode() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "firstName"  ( model type : String / wrapperType : String )
		employeeRecord.setFirstName( "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" ) ;
		Assert.assertEquals( "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", employeeRecord.getFirstName() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "lastName"  ( model type : String / wrapperType : String )
		employeeRecord.setLastName( "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" ) ;
		Assert.assertEquals( "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", employeeRecord.getLastName() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "manager"  ( model type : Short / wrapperType : Short )
		employeeRecord.setManager( Short.valueOf((short)1) ) ;
		Assert.assertEquals( Short.valueOf((short)1), employeeRecord.getManager() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "badgeNumber"  ( model type : Integer / wrapperType : Integer )
		employeeRecord.setBadgeNumber( Integer.valueOf(100) ) ;
		Assert.assertEquals( Integer.valueOf(100), employeeRecord.getBadgeNumber() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "email"  ( model type : String / wrapperType : String )
		employeeRecord.setEmail( "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" ) ;
		Assert.assertEquals( "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", employeeRecord.getEmail() ) ; // Not primitive type in model


	}

}
