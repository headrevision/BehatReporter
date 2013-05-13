package headrevision.BehatReporter.report;

import java.util.List;

import android.app.Activity;

import com.fasterxml.jackson.databind.JsonNode;

public class FeaturesAdapterFactory extends ItemsAdapterFactory {

	@Override
	public ItemsAdapter getItemsAdapter(Activity activity, int itemViewId, List<JsonNode> items) {
		return new FeaturesAdapter(activity, itemViewId, items);
	}

	@Override
	public String getItemType() {
		return "feature";
	}

	@Override
	public int getItemDepth() {
		return 1;
	}

}