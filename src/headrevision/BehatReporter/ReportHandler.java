package headrevision.BehatReporter;

import headrevision.BehatReporter.json.ParserException;
import headrevision.BehatReporter.report.FeaturesAdapterFactory;
import headrevision.BehatReporter.report.ItemsAdapterFactory;
import headrevision.BehatReporter.report.LoaderTask;
import headrevision.BehatReporter.report.LoaderTaskListener;
import headrevision.BehatReporter.report.ReportParser;
import headrevision.BehatReporter.ui.ItemDepth;
import headrevision.BehatReporter.ui.Message;

import java.util.List;

import android.app.Activity;
import android.widget.ExpandableListView.OnChildClickListener;

import com.fasterxml.jackson.databind.JsonNode;

public class ReportHandler {

	private static ReportHandler instance;

	private Activity activity;

	private ReportHandler(Activity activity) {
		this.activity = activity;
	}

	public static ReportHandler getInstance(Activity activity) {
		if (instance == null || instance.activity != activity) {
			instance = new ReportHandler(activity);
		}
		return instance;
	}

	public void load(String reportUrl, LoaderTaskListener listener) {
		LoaderTask loaderTask = new LoaderTask(listener);
		loaderTask.execute(reportUrl);
	}

	public void show(JsonNode reportJson, OnChildClickListener listener) {
		ReportParser reportParser = new ReportParser(reportJson);

		List<JsonNode> features = null;
		try {
			features = reportParser.parseSubItems();
		} catch (ParserException e) {
			Message.getInstance(activity).showError(e);
			return;
		}
		ItemsAdapterFactory featuresAdapterFactory = new FeaturesAdapterFactory();

		ListHandler.getInstance(activity).show(reportParser, features, featuresAdapterFactory, listener);
		ItemDepth.getInstance(activity).jumpForwardToStart();
	}

}