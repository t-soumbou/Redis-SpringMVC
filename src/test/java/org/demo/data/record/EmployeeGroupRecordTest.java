package org.demo.data.record;


import org.junit.Assert;
import org.junit.Test;

public class EmployeeGroupRecordTest 
{

	@Test
	public void testSettersAndGetters() {
		
		System.out.println("Checking class EmployeeGroupRecord getters and setters ..." );
		
		EmployeeGroupRecord employeeGroupRecord = new EmployeeGroupRecord();


		//--- Test setter/getter for attribute "employeeCode"  ( model type : String / wrapperType : String )
		employeeGroupRecord.setEmployeeCode( "AAAA" ) ;
		Assert.assertEquals( "AAAA", employeeGroupRecord.getEmployeeCode() ) ; // Not primitive type in model


		//--- Test setter/getter for attribute "groupId"  ( model type : Short / wrapperType : Short )
		employeeGroupRecord.setGroupId( Short.valueOf((short)1) ) ;
		Assert.assertEquals( Short.valueOf((short)1), employeeGroupRecord.getGroupId() ) ; // Not primitive type in model


	}

}
