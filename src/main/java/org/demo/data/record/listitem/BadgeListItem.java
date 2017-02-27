package org.demo.data.record.listitem;

import org.demo.data.record.BadgeRecord;
import org.demo.commons.ListItem;

public class BadgeListItem implements ListItem {

	private final String value ;
	private final String label ;
	
	public BadgeListItem(BadgeRecord badge) {
		super();

		this.value = ""
			 + badge.getBadgeNumber()
		;

		//TODO : Define here the attributes to be displayed as the label
		this.label = badge.toString();
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
