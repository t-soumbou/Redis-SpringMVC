package org.demo.data.record;


import org.junit.Assert;
import org.junit.Test;

public class BookOrderItemRecordTest 
{

	@Test
	public void testSettersAndGetters() {
		
		System.out.println("Checking class BookOrderItemRecord getters and setters ..." );
		
		BookOrderItemRecord bookOrderItemRecord = new BookOrderItemRecord();


		//--- Test setter/getter for attribute "bookOrderId"  ( model type : Integer / wrapperType : Integer )
		bookOrderItemRecord.setBookOrderId( Integer.valueOf(100) ) ;
		Assert.assertEquals( Integer.valueOf(100), bookOrderItemRecord.getBookOrderId() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "bookId"  ( model type : Integer / wrapperType : Integer )
		bookOrderItemRecord.setBookId( Integer.valueOf(100) ) ;
		Assert.assertEquals( Integer.valueOf(100), bookOrderItemRecord.getBookId() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "quantity"  ( model type : Integer / wrapperType : Integer )
		bookOrderItemRecord.setQuantity( Integer.valueOf(100) ) ;
		Assert.assertEquals( Integer.valueOf(100), bookOrderItemRecord.getQuantity() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "price"  ( model type : BigDecimal / wrapperType : BigDecimal )
		bookOrderItemRecord.setPrice( java.math.BigDecimal.valueOf(10000) ) ;
		Assert.assertEquals( java.math.BigDecimal.valueOf(10000), bookOrderItemRecord.getPrice() ) ; // Not primitive type in model


	}

}
