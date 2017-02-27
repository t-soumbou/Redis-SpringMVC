package org.demo.data.record;


import org.junit.Assert;
import org.junit.Test;

public class BookRecordTest 
{

	@Test
	public void testSettersAndGetters() {
		
		System.out.println("Checking class BookRecord getters and setters ..." );
		
		BookRecord bookRecord = new BookRecord();


		//--- Test setter/getter for attribute "id"  ( model type : Integer / wrapperType : Integer )
		bookRecord.setId( Integer.valueOf(100) ) ;
		Assert.assertEquals( Integer.valueOf(100), bookRecord.getId() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "publisherId"  ( model type : Integer / wrapperType : Integer )
		bookRecord.setPublisherId( Integer.valueOf(100) ) ;
		Assert.assertEquals( Integer.valueOf(100), bookRecord.getPublisherId() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "authorId"  ( model type : Integer / wrapperType : Integer )
		bookRecord.setAuthorId( Integer.valueOf(100) ) ;
		Assert.assertEquals( Integer.valueOf(100), bookRecord.getAuthorId() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "isbn"  ( model type : String / wrapperType : String )
		bookRecord.setIsbn( "AAAAAAAAAAAAA" ) ;
		Assert.assertEquals( "AAAAAAAAAAAAA", bookRecord.getIsbn() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "title"  ( model type : String / wrapperType : String )
		bookRecord.setTitle( "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" ) ;
		Assert.assertEquals( "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", bookRecord.getTitle() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "price"  ( model type : BigDecimal / wrapperType : BigDecimal )
		bookRecord.setPrice( java.math.BigDecimal.valueOf(10000) ) ;
		Assert.assertEquals( java.math.BigDecimal.valueOf(10000), bookRecord.getPrice() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "quantity"  ( model type : Integer / wrapperType : Integer )
		bookRecord.setQuantity( Integer.valueOf(100) ) ;
		Assert.assertEquals( Integer.valueOf(100), bookRecord.getQuantity() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "discount"  ( model type : Integer / wrapperType : Integer )
		bookRecord.setDiscount( Integer.valueOf(100) ) ;
		Assert.assertEquals( Integer.valueOf(100), bookRecord.getDiscount() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "availability"  ( model type : Short / wrapperType : Short )
		bookRecord.setAvailability( Short.valueOf((short)1) ) ;
		Assert.assertEquals( Short.valueOf((short)1), bookRecord.getAvailability() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "bestSeller"  ( model type : Short / wrapperType : Short )
		bookRecord.setBestSeller( Short.valueOf((short)1) ) ;
		Assert.assertEquals( Short.valueOf((short)1), bookRecord.getBestSeller() ) ; // Not primitive type in model


	}

}
