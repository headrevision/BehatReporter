package headrevision.BehatReporter;

import headrevision.BehatReporter.json.ParserException;
import headrevision.BehatReporter.report.ItemParser;
import headrevision.BehatReporter.report.ItemsAdapter;
import headrevision.BehatReporter.report.ItemsAdapterFactory;
import headrevision.BehatReporter.ui.ItemDepth;
import headrevision.BehatReporter.ui.Message;

import java.util.List;

import android.app.Activity;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

import com.fasterxml.jackson.databind.JsonNode;

public class ItemHandler {

	private static ItemHandler instance;

	private Activity activity;

	private ItemHandler(Activity activity) {
		this.activity = activity;
	}

	public static ItemHandler getInstance(Activity activity) {
		if (instance == null || instance.activity != activity) {
			instance = new ItemHandler(activity);
		}
		return instance;
	}

	public void show(ExpandableListView parent, int groupPosition, int childPosition, OnChildClickListener listener) {
		ItemsAdapter itemsAdapter = (ItemsAdapter) parent.getExpandableListAdapter();
		JsonNode item = itemsAdapter.getChild(groupPosition, childPosition);
		ItemParser itemParser = itemsAdapter.getItemParserFactory().getItemParser(item);

		showSubItems(itemsAdapter, item, itemParser, listener);
	}

	private void showSubItems(ItemsAdapter itemsAdapter, JsonNode item, ItemParser itemParser, OnChildClickListener listener) {
		if (itemParser.hasSubItems()) {
			List<JsonNode> subItems = getSubItems(itemParser);

			if (subItems != null) {
				ItemsAdapterFactory subItemsAdapterFactory = itemsAdapter.getSubItemsAdapterFactory(item);
				ListHandler.getInstance(activity).show(itemParser, subItems, subItemsAdapterFactory, listener);
				ItemDepth.getInstance(activity).stepForward();
			}
		}
	}

	private List<JsonNode> getSubItems(ItemParser itemParser) {
		List<JsonNode> subItems = null;

		try {
			subItems = itemParser.parseSubItems();
		} catch (ParserException e) {
			Message.getInstance(activity).showError(e);
		}

		return subItems;
	}

}