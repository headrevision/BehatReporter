package headrevision.BehatReporter;

import headrevision.BehatReporter.json.ParserException;
import headrevision.BehatReporter.report.ItemParser;
import headrevision.BehatReporter.report.ItemsAdapter;
import headrevision.BehatReporter.report.ItemsAdapterFactory;
import headrevision.BehatReporter.report.ReportParser;
import headrevision.BehatReporter.store.ItemTitles;
import headrevision.BehatReporter.ui.Message;
import headrevision.HumanTimeApproximation.Duration;

import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.base.CaseFormat;

public class ListHandler {

	private static ListHandler instance;

	private Activity activity;

	private ListHandler(Activity activity) {
		this.activity = activity;
	}

	public static ListHandler getInstance(Activity activity) {
		if (instance == null || instance.activity != activity) {
			instance = new ListHandler(activity);
		}
		return instance;
	}

	public void show(ItemParser superItemParser, List<JsonNode> items, ItemsAdapterFactory adapterFactory, OnChildClickListener listener) {
		ExpandableListView listView = getListView(adapterFactory);

		showTitle(superItemParser, adapterFactory);
		showItems(items, adapterFactory, listView);

		listView.setOnChildClickListener(listener);
	}

	private void showTitle(ItemParser superItemParser, ItemsAdapterFactory adapterFactory) {
		try {
			if (superItemParser instanceof ReportParser) {
				showReportTitle(superItemParser);
			} else {
				showSuperItemTitle(superItemParser, adapterFactory);
			}
		} catch (ParserException e) {
			Message.getInstance(activity).showError(e);
		}
	}

	private void showReportTitle(ItemParser superItemParser) throws ParserException {
		Date reportDate = ((ReportParser) superItemParser).parseDate();
		long reportAge = (new Date().getTime() - reportDate.getTime()) / 1000;
		String approximatedReportAge = new Duration().fromSeconds(reportAge);

		String reportTitle = String.format(activity.getString(R.string.heading_report), approximatedReportAge);
		activity.getActionBar().setTitle(reportTitle);
	}

	private void showSuperItemTitle(ItemParser superItemParser, ItemsAdapterFactory adapterFactory) throws ParserException {
		ItemTitles.getInstance().push((String) activity.getActionBar().getTitle());
		if (superItemParser.hasTitle()) {
			activity.getActionBar().setTitle(superItemParser.parseTitle());
		} else {
			String defaultTitleName = "heading_" + adapterFactory.getItemType(CaseFormat.LOWER_UNDERSCORE);
			int defaultTitleId = activity.getResources().getIdentifier(defaultTitleName, "string", activity.getPackageName());
			activity.getActionBar().setTitle(defaultTitleId);
		}
	}

	private void showItems(List<JsonNode> items, ItemsAdapterFactory adapterFactory, ExpandableListView listView) {
		String itemViewName = adapterFactory.getItemType(CaseFormat.LOWER_UNDERSCORE) + "_list_item";
		int itemViewId = activity.getResources().getIdentifier(itemViewName, "layout", activity.getPackageName());
		ItemsAdapter adapter = adapterFactory.getItemsAdapter(activity, itemViewId, items);

		listView.setAdapter(adapter);
	}

	private ExpandableListView getListView(ItemsAdapterFactory adapterFactory) {
		String listViewName = "itemList" + adapterFactory.getItemDepth();
		int listViewId = activity.getResources().getIdentifier(listViewName, "id", activity.getPackageName());
		return (ExpandableListView) activity.findViewById(listViewId);
	}

}