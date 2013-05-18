package headrevision.BehatReporter.report;

import headrevision.BehatReporter.R;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.base.CaseFormat;

public abstract class ItemsAdapter extends BaseExpandableListAdapter {

	protected Activity activity;

	private int itemViewId;

	private List<List<JsonNode>> groupedItems;

	private List<String> groupNames;

	public ItemsAdapter(Activity activity, int itemViewId, List<JsonNode> items) {
		super();
		this.activity = activity;
		this.itemViewId = itemViewId;
		groupedItems = getGroupedItems(items);
		groupNames = getGroupNames(groupedItems);
	}

	@Override
	public JsonNode getChild(int groupPosition, int childPosition) {
		return groupedItems.get(groupPosition).get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return groupedItems.get(groupPosition).size();
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		LayoutInflater layoutInflater = activity.getLayoutInflater();
		View childView = layoutInflater.inflate(itemViewId, parent, false);

		setItemViewContent(childView, groupPosition, childPosition);

		return childView;
	}

	@Override
	public String getGroup(int groupPosition) {
		return groupNames.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return groupNames.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		if (convertView == null) {
			int groupViewId = activity.getResources().getIdentifier("list_group", "layout", activity.getPackageName());
			LayoutInflater layoutInflater = activity.getLayoutInflater();
			convertView = layoutInflater.inflate(groupViewId, parent, false);
		}

		TextView groupNameView = (TextView) convertView.findViewById(R.id.groupName);
		groupNameView.setText(getGroup(groupPosition));

		((ExpandableListView) parent).expandGroup(groupPosition);

		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	public abstract ItemParserFactory getItemParserFactory();

	public abstract ItemsAdapterFactory getSubItemsAdapterFactory(JsonNode item);

	protected abstract List<List<JsonNode>> getGroupedItems(List<JsonNode> items);

	protected abstract List<String> getGroupNames(List<List<JsonNode>> groupedItems);

	protected abstract void setItemViewContent(View itemView, int groupPosition, int childPosition);

	protected String getGroupHeading(ItemsAdapterFactory adapterFactory) {
		return getGroupHeading(adapterFactory.getItemType(CaseFormat.LOWER_UNDERSCORE));
	}

	protected String getGroupHeading(String itemType) {
		String headingName = "heading_" + itemType;
		int headingId = activity.getResources().getIdentifier(headingName, "string", activity.getPackageName());
		return activity.getResources().getString(headingId);
	}

}