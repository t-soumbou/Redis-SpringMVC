package org.demo.data.record.listitem;

import org.demo.data.record.SynopsisRecord;
import org.demo.commons.ListItem;

public class SynopsisListItem implements ListItem {

	private final String value ;
	private final String label ;
	
	public SynopsisListItem(SynopsisRecord synopsis) {
		super();

		this.value = ""
			 + synopsis.getBookId()
		;

		//TODO : Define here the attributes to be displayed as the label
		this.label = synopsis.toString();
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
