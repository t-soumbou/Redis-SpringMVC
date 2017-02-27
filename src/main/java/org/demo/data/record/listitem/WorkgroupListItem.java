package org.demo.data.record.listitem;

import org.demo.data.record.WorkgroupRecord;
import org.demo.commons.ListItem;

public class WorkgroupListItem implements ListItem {

	private final String value ;
	private final String label ;
	
	public WorkgroupListItem(WorkgroupRecord workgroup) {
		super();

		this.value = ""
			 + workgroup.getId()
		;

		//TODO : Define here the attributes to be displayed as the label
		this.label = workgroup.toString();
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
