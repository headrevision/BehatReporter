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

public class StepsAdapter extends ItemsAdapter {

	public StepsAdapter(Activity activity, int itemViewId, List<JsonNode> items) {
		super(activity, itemViewId, items);
	}

	@Override
	public ItemParserFactory getItemParserFactory() {
		return new StepParserFactory();
	}

	@Override
	public ItemsAdapterFactory getSubItemsAdapterFactory(JsonNode item) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected List<String> getGroupNames() {
		List<String> groupNames = new ArrayList<String>();
		groupNames.add(getGroupHeading("background"));
		groupNames.add(getGroupHeading(new StepsAdapterFactory()));
		return groupNames;
	}

	@Override
	protected List<List<JsonNode>> getGroupedItems(List<JsonNode> items) {
		List<List<JsonNode>> groupedItems = new ArrayList<List<JsonNode>>();
		List<JsonNode> backgroundSteps = new ArrayList<JsonNode>();
		List<JsonNode> steps = new ArrayList<JsonNode>();
		groupedItems.add(backgroundSteps);
		groupedItems.add(steps);
		groupItems(items, backgroundSteps, steps);
		return groupedItems;
	}

	@Override
	protected void setItemViewContent(View itemView, int groupPosition, int childPosition) {
		JsonNode step = getChild(groupPosition, childPosition);
		StepParser stepParser = new StepParser(step);
		try {
			Result result = stepParser.parseResult();
			setTypeAndText(stepParser.parseType(), stepParser.parseText(), result, itemView);
			if (stepParser.hasMultilineArgs()) {
				setMultilineArgs(stepParser.parseMultilineArgs(), result, itemView);
			}
		} catch (ParserException e) {
			Message.getInstance(activity).showError(e);
		}
	}

	protected void setResultColor(Result result, View itemView, TextView textView) {
		ResultColor.getInstance(activity).set(result, itemView, textView, false);
	}

	private void setTypeAndText(String type, String text, Result result, View itemView) {
		TextView textView = (TextView) itemView.findViewById(R.id.stepTypeAndText);
		textView.setText(type + " " + text);
		setResultColor(result, itemView, textView);
	}

	private void setMultilineArgs(String multilineArgs, Result result, View itemView) {
		TextView textView = (TextView) itemView.findViewById(R.id.stepMultilineArgs);
		textView.setText(multilineArgs);
		setResultColor(result, itemView, textView);
		textView.setVisibility(View.VISIBLE);
	}

	private void groupItems(List<JsonNode> items, List<JsonNode> backgroundSteps, List<JsonNode> steps) {
		Iterator<JsonNode> iterator = items.iterator();
		while (iterator.hasNext()) {
			JsonNode item = iterator.next();
			StepParser stepParser = new StepParser(item);
			try {
				if (stepParser.isBackground()) {
					backgroundSteps.add(item);
				} else {
					steps.add(item);
				}
			} catch (ParserException e) {
			}
		}
	}


}