package org.demo.data.record.listitem;

import org.demo.data.record.ReviewRecord;
import org.demo.commons.ListItem;

public class ReviewListItem implements ListItem {

	private final String value ;
	private final String label ;
	
	public ReviewListItem(ReviewRecord review) {
		super();

		this.value = ""
			 + review.getCustomerCode()
			 + "|"  + review.getBookId()
		;

		//TODO : Define here the attributes to be displayed as the label
		this.label = review.toString();
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
