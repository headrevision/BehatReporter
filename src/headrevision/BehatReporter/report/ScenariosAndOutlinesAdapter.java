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

public class ScenariosAndOutlinesAdapter extends ItemsAdapter {

	public ScenariosAndOutlinesAdapter(Activity activity, int itemViewId, List<JsonNode> items) {
		super(activity, itemViewId, items);
	}

	@Override
	public ItemParserFactory getItemParserFactory() {
		return new ScenarioOrOutlineParserFactory();
	}

	@Override
	public ItemsAdapterFactory getSubItemsAdapterFactory(JsonNode item) {
		try {
			if ((new OutlineParser(item)).isOutline()) {
				return new StepsAndOutlineExamplesAdapterFactory();
			}
		} catch (ParserException e) {
			return null;
		}

		return new StepsAdapterFactory();
	}

	@Override
	protected List<List<JsonNode>> getGroupedItems(List<JsonNode> items) {
		List<List<JsonNode>> groupedItems = new ArrayList<List<JsonNode>>();
		groupedItems.add(items);
		return groupedItems;
	}

	@Override
	protected List<String> getGroupNames(List<List<JsonNode>> groupedItems) {
		List<String> groupNames = new ArrayList<String>();
		groupNames.add(getGroupHeading(new ScenariosAndOutlinesAdapterFactory()));
		return groupNames;
	}

	@Override
	protected void setItemViewContent(View itemView, int groupPosition, int childPosition) {
		JsonNode item = getChild(groupPosition, childPosition);
		ItemParser itemParser = getItemParserFactory().getItemParser(item);
		try {
			Result result = itemParser.parseResult();
			setTitle(itemParser.parseTitle(), result, itemView);
		} catch (ParserException e) {
			Message.getInstance(activity).showError(e);
		}
	}

	private void setTitle(String title, Result result, View itemView) {
		TextView titleView = (TextView) itemView.findViewById(R.id.scenarioTitle);
		titleView.setText(title);
		ResultColor.getInstance(activity).set(result, itemView, titleView, true);
	}

}