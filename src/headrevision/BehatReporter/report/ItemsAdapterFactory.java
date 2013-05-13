package headrevision.BehatReporter.report;

import java.util.List;

import android.app.Activity;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.base.CaseFormat;

public abstract class ItemsAdapterFactory {

	protected CaseFormat itemTypeFormat = CaseFormat.LOWER_CAMEL;

	public abstract ItemsAdapter getItemsAdapter(Activity activity,
			int itemViewId, List<JsonNode> items);

	public abstract String getItemType();

	public String getItemType(CaseFormat format) {
		return itemTypeFormat.to(format, getItemType());
	}

	public abstract int getItemDepth();

}