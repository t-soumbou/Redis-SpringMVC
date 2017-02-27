package org.demo.data.record;


import org.junit.Assert;
import org.junit.Test;

public class CountryRecordTest 
{

	@Test
	public void testSettersAndGetters() {
		
		System.out.println("Checking class CountryRecord getters and setters ..." );
		
		CountryRecord countryRecord = new CountryRecord();


		//--- Test setter/getter for attribute "code"  ( model type : String / wrapperType : String )
		countryRecord.setCode( "AA" ) ;
		Assert.assertEquals( "AA", countryRecord.getCode() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "name"  ( model type : String / wrapperType : String )
		countryRecord.setName( "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" ) ;
		Assert.assertEquals( "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", countryRecord.getName() ) ; // Not primitive type in model


	}

}
