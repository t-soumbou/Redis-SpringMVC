package org.demo.data.record;


import org.junit.Assert;
import org.junit.Test;

public class CustomerRecordTest 
{

	@Test
	public void testSettersAndGetters() {
		
		System.out.println("Checking class CustomerRecord getters and setters ..." );
		
		CustomerRecord customerRecord = new CustomerRecord();


		//--- Test setter/getter for attribute "code"  ( model type : String / wrapperType : String )
		customerRecord.setCode( "AAAAA" ) ;
		Assert.assertEquals( "AAAAA", customerRecord.getCode() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "countryCode"  ( model type : String / wrapperType : String )
		customerRecord.setCountryCode( "AA" ) ;
		Assert.assertEquals( "AA", customerRecord.getCountryCode() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "firstName"  ( model type : String / wrapperType : String )
		customerRecord.setFirstName( "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" ) ;
		Assert.assertEquals( "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", customerRecord.getFirstName() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "lastName"  ( model type : String / wrapperType : String )
		customerRecord.setLastName( "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" ) ;
		Assert.assertEquals( "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", customerRecord.getLastName() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "login"  ( model type : String / wrapperType : String )
		customerRecord.setLogin( "AAAAAAAAAAAAAAAAAAAA" ) ;
		Assert.assertEquals( "AAAAAAAAAAAAAAAAAAAA", customerRecord.getLogin() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "password"  ( model type : String / wrapperType : String )
		customerRecord.setPassword( "AAAAAAAAAAAAAAAAAAAA" ) ;
		Assert.assertEquals( "AAAAAAAAAAAAAAAAAAAA", customerRecord.getPassword() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "age"  ( model type : Integer / wrapperType : Integer )
		customerRecord.setAge( Integer.valueOf(100) ) ;
		Assert.assertEquals( Integer.valueOf(100), customerRecord.getAge() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "city"  ( model type : String / wrapperType : String )
		customerRecord.setCity( "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" ) ;
		Assert.assertEquals( "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", customerRecord.getCity() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "zipCode"  ( model type : Integer / wrapperType : Integer )
		customerRecord.setZipCode( Integer.valueOf(100) ) ;
		Assert.assertEquals( Integer.valueOf(100), customerRecord.getZipCode() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "phone"  ( model type : String / wrapperType : String )
		customerRecord.setPhone( "AAAAAAAAAAAAAAAAAAAA" ) ;
		Assert.assertEquals( "AAAAAAAAAAAAAAAAAAAA", customerRecord.getPhone() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "reviewer"  ( model type : Short / wrapperType : Short )
		customerRecord.setReviewer( Short.valueOf((short)1) ) ;
		Assert.assertEquals( Short.valueOf((short)1), customerRecord.getReviewer() ) ; // Not primitive type in model


	}

}
