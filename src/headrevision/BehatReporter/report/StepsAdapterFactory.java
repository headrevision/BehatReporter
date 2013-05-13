package headrevision.BehatReporter.report;

import java.util.List;

import android.app.Activity;

import com.fasterxml.jackson.databind.JsonNode;

public class StepsAdapterFactory extends ItemsAdapterFactory {

	@Override
	public ItemsAdapter getItemsAdapter(Activity activity, int itemViewId, List<JsonNode> items) {
		return new StepsAdapter(activity, itemViewId, items);
	}

	@Override
	public String getItemType() {
		return "step";
	}

	@Override
	public int getItemDepth() {
		return 3;
	}

}