package org.demo.data.record.listitem;

import org.demo.data.record.CustomerRecord;
import org.demo.commons.ListItem;

public class CustomerListItem implements ListItem {

	private final String value ;
	private final String label ;
	
	public CustomerListItem(CustomerRecord customer) {
		super();

		this.value = ""
			 + customer.getCode()
		;

		//TODO : Define here the attributes to be displayed as the label
		this.label = customer.toString();
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public String getLabel() {
		return label;
	}

}
