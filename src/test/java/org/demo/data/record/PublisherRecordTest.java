package org.demo.data.record;


import org.junit.Assert;
import org.junit.Test;

public class PublisherRecordTest 
{

	@Test
	public void testSettersAndGetters() {
		
		System.out.println("Checking class PublisherRecord getters and setters ..." );
		
		PublisherRecord publisherRecord = new PublisherRecord();


		//--- Test setter/getter for attribute "code"  ( model type : Integer / wrapperType : Integer )
		publisherRecord.setCode( Integer.valueOf(100) ) ;
		Assert.assertEquals( Integer.valueOf(100), publisherRecord.getCode() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "countryCode"  ( model type : String / wrapperType : String )
		publisherRecord.setCountryCode( "AA" ) ;
		Assert.assertEquals( "AA", publisherRecord.getCountryCode() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "name"  ( model type : String / wrapperType : String )
		publisherRecord.setName( "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" ) ;
		Assert.assertEquals( "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", publisherRecord.getName() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "email"  ( model type : String / wrapperType : String )
		publisherRecord.setEmail( "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" ) ;
		Assert.assertEquals( "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", publisherRecord.getEmail() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "contact"  ( model type : String / wrapperType : String )
		publisherRecord.setContact( "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" ) ;
		Assert.assertEquals( "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", publisherRecord.getContact() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "city"  ( model type : String / wrapperType : String )
		publisherRecord.setCity( "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" ) ;
		Assert.assertEquals( "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", publisherRecord.getCity() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "zipCode"  ( model type : Integer / wrapperType : Integer )
		publisherRecord.setZipCode( Integer.valueOf(100) ) ;
		Assert.assertEquals( Integer.valueOf(100), publisherRecord.getZipCode() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "phone"  ( model type : String / wrapperType : String )
		publisherRecord.setPhone( "AAAAAAAAAAAAAA" ) ;
		Assert.assertEquals( "AAAAAAAAAAAAAA", publisherRecord.getPhone() ) ; // Not primitive type in model


	}

}
