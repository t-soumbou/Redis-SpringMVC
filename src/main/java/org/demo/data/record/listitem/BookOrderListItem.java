package org.demo.data.record.listitem;

import org.demo.data.record.BookOrderRecord;
import org.demo.commons.ListItem;

public class BookOrderListItem implements ListItem {

	private final String value ;
	private final String label ;
	
	public BookOrderListItem(BookOrderRecord bookOrder) {
		super();

		this.value = ""
			 + bookOrder.getId()
		;

		//TODO : Define here the attributes to be displayed as the label
		this.label = bookOrder.toString();
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
