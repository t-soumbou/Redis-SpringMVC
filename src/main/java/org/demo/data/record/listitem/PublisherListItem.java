package org.demo.data.record.listitem;

import org.demo.data.record.PublisherRecord;
import org.demo.commons.ListItem;

public class PublisherListItem implements ListItem {

	private final String value ;
	private final String label ;
	
	public PublisherListItem(PublisherRecord publisher) {
		super();

		this.value = ""
			 + publisher.getCode()
		;

		//TODO : Define here the attributes to be displayed as the label
		this.label = publisher.toString();
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
