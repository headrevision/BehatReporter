package headrevision.BehatReporter.report;

import java.util.List;

import android.app.Activity;

import com.fasterxml.jackson.databind.JsonNode;

public class ScenariosAndOutlinesAdapterFactory extends ItemsAdapterFactory {

	@Override
	public ItemsAdapter getItemsAdapter(Activity activity, int itemViewId, List<JsonNode> items) {
		return new ScenariosAndOutlinesAdapter(activity, itemViewId, items);
	}

	@Override
	public String getItemType() {
		return "scenario";
	}

	@Override
	public int getItemDepth() {
		return 2;
	}

}