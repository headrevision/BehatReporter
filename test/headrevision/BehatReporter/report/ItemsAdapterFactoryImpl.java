package headrevision.BehatReporter.report;

import java.util.List;

import android.app.Activity;

import com.fasterxml.jackson.databind.JsonNode;

public class ItemsAdapterFactoryImpl extends ItemsAdapterFactory {

	@Override
	public ItemsAdapter getItemsAdapter(Activity activity, int itemViewId, List<JsonNode> items) {
		return null;
	}

	@Override
	public String getItemType() {
		return "fooBar";
	}

	@Override
	public int getItemDepth() {
		return 1;
	}

}