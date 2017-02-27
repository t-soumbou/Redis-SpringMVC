package org.demo.data.record.listitem;

import org.demo.data.record.AuthorRecord;
import org.demo.commons.ListItem;

public class AuthorListItem implements ListItem {

	private final String value ;
	private final String label ;
	
	public AuthorListItem(AuthorRecord author) {
		super();

		this.value = ""
			 + author.getId()
		;

		//TODO : Define here the attributes to be displayed as the label
		this.label = author.toString();
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
