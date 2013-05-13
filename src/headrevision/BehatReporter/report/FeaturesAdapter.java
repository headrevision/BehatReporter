package headrevision.BehatReporter.report;

import headrevision.BehatReporter.R;
import headrevision.BehatReporter.json.ParserException;
import headrevision.BehatReporter.ui.Message;
import headrevision.BehatReporter.ui.ResultColor;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.fasterxml.jackson.databind.JsonNode;

public class FeaturesAdapter extends ItemsAdapter {

	public FeaturesAdapter(Activity activity, int itemViewId, List<JsonNode> items) {
		super(activity, itemViewId, items);
	}

	@Override
	public ItemParserFactory getItemParserFactory() {
		return new FeatureParserFactory();
	}

	@Override
	public ItemsAdapterFactory getSubItemsAdapterFactory(JsonNode item) {
		return new ScenariosAndOutlinesAdapterFactory();
	}

	@Override
	protected List<String> getGroupNames() {
		List<String> groupNames = new ArrayList<String>();
		groupNames.add(getGroupHeading(new FeaturesAdapterFactory()));
		return groupNames;
	}

	@Override
	protected List<List<JsonNode>> getGroupedItems(List<JsonNode> items) {
		List<List<JsonNode>> groupedItems = new ArrayList<List<JsonNode>>();
		groupedItems.add(items);
		return groupedItems;
	}

	@Override
	protected void setItemViewContent(View itemView, int groupPosition, int childPosition) {
		JsonNode feature = getChild(groupPosition, childPosition);
		FeatureParser featureParser = new FeatureParser(feature);
		try {
			Result result = featureParser.parseResult();
			setTitle(featureParser.parseTitle(), result, itemView);
			setDesc(featureParser.parseDesc(), result, itemView);
		} catch (ParserException e) {
			Message.getInstance(activity).showError(e);
		}
	}

	private void setTitle(String title, Result result, View itemView) {
		TextView titleView = (TextView) itemView.findViewById(R.id.featureTitle);
		titleView.setText(title);
		setResultColor(result, itemView, titleView);
	}

	private void setDesc(String desc, Result result, View itemView) {
		if (desc != null) {
			TextView descView = (TextView) itemView.findViewById(R.id.featureDesc);
			descView.setText(desc);
			setResultColor(result, itemView, descView);
		}
	}

	private void setResultColor(Result result, View itemView, TextView textView) {
		ResultColor.getInstance(activity).set(result, itemView, textView, true);
	}

}