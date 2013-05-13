package headrevision.BehatReporter.report;

import headrevision.BehatReporter.R;
import headrevision.BehatReporter.json.ParserException;
import headrevision.BehatReporter.ui.Message;
import headrevision.BehatReporter.ui.ResultColor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.fasterxml.jackson.databind.JsonNode;

public class StepsAndOutlineExamplesAdapter extends StepsAdapter {

	public StepsAndOutlineExamplesAdapter(Activity activity, int itemViewId, List<JsonNode> items) {
		super(activity, itemViewId, items);
	}

	@Override
	public ItemParserFactory getItemParserFactory() {
		return new StepOrOutlineExampleParserFactory();
	}

	@Override
	public ItemsAdapterFactory getSubItemsAdapterFactory(JsonNode item) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected List<String> getGroupNames() {
		List<String> groupNames = new ArrayList<String>();
		groupNames.add(getGroupHeading(new StepsAdapterFactory()));
		groupNames.add(getGroupHeading(new StepsAndOutlineExamplesAdapterFactory()));
		return groupNames;
	}

	@Override
	protected List<List<JsonNode>> getGroupedItems(List<JsonNode> items) {
		List<List<JsonNode>> groupedItems = new ArrayList<List<JsonNode>>();
		List<JsonNode> outlineSteps = new ArrayList<JsonNode>();
		List<JsonNode> outlineExamples = new ArrayList<JsonNode>();
		groupedItems.add(outlineSteps);
		groupedItems.add(outlineExamples);
		groupItems(items, outlineSteps, outlineExamples);
		return groupedItems;
	}

	@Override
	protected void setItemViewContent(View itemView, int groupPosition, int childPosition) {
		JsonNode item = getChild(groupPosition, childPosition);
		ItemParser itemParser = getItemParserFactory().getItemParser(item);
		if (itemParser instanceof StepParser) {
			itemView = itemView.findViewById(R.id.stepListItem);
			super.setItemViewContent(itemView, groupPosition, childPosition);
		} else if (itemParser instanceof OutlineExampleParser) {
			itemView = itemView.findViewById(R.id.outlineExampleListItem);
			try {
				String values = ((OutlineExampleParser) itemParser).parseValues();
				Result result = itemParser.parseResult();
				setValues(values, result, itemView);
			} catch (ParserException e) {
				Message.getInstance(activity).showError(e);
			}
		}
		itemView.setVisibility(View.VISIBLE);
	}

	@Override
	protected void setResultColor(Result result, View itemView, TextView textView) {
	}

	private void setValues(String values, Result result, View itemView) {
		TextView textView = (TextView) itemView.findViewById(R.id.outlineExampleValues);
		textView.setText(values);
		ResultColor.getInstance(activity).set(result, itemView, textView, false);
	}

	private void groupItems(List<JsonNode> items, List<JsonNode> outlineSteps, List<JsonNode> outlineExamples) {
		Iterator<JsonNode> iterator = items.iterator();
		while (iterator.hasNext()) {
			JsonNode item = iterator.next();
			StepParser stepParser = new StepParser(item);
			OutlineExampleParser outlineExampleParser = new OutlineExampleParser(item);
			try {
				if (stepParser.isStep()) {
					outlineSteps.add(item);
				} else if (outlineExampleParser.isOutlineExample()) {
					outlineExamples.add(item);
				}
			} catch (ParserException e) {
			}
		}
	}

}