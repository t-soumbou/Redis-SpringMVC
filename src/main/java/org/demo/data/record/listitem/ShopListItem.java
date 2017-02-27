package org.demo.data.record.listitem;

import org.demo.data.record.ShopRecord;
import org.demo.commons.ListItem;

public class ShopListItem implements ListItem {

	private final String value ;
	private final String label ;
	
	public ShopListItem(ShopRecord shop) {
		super();

		this.value = ""
			 + shop.getCode()
		;

		//TODO : Define here the attributes to be displayed as the label
		this.label = shop.toString();
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
