package headrevision.BehatReporter;

import headrevision.BehatReporter.report.LoaderException;
import headrevision.BehatReporter.report.LoaderTaskListener;
import headrevision.BehatReporter.store.ReportUrl;
import headrevision.BehatReporter.ui.ItemDepth;
import headrevision.BehatReporter.ui.Message;
import headrevision.BehatReporter.ui.OptionsMenu;
import headrevision.BehatReporter.ui.SetReportDialog;
import headrevision.BehatReporter.ui.SetReportDialogListener;
import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

import com.fasterxml.jackson.databind.JsonNode;

public class MainActivity extends Activity implements SetReportDialogListener, LoaderTaskListener, OnChildClickListener {

	private DialogFragment dialog;

	private boolean loaderTaskExecuted = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		dialog = SetReportDialog.newInstance(this);

		String reportUrl = ReportUrl.getInstance(this).retrieve();
		if (reportUrl.equals("")) {
			showSetReportDialog();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		OptionsMenu.getInstance(this).create(menu);

		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		OptionsMenu optionsMenu = OptionsMenu.getInstance(this);
		if (loaderTaskExecuted) {
			optionsMenu.show(menu);
		} else {
			optionsMenu.hide(menu);
		}

		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			return onNavigateUp();
		} else if (item.getItemId() == R.id.menu_url) {
			showSetReportDialog();
			return true;
		} else if (item.getItemId() == R.id.menu_refresh) {
			String reportUrl = ReportUrl.getInstance(this).retrieve();
			ReportHandler.getInstance(this).load(reportUrl, this);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void onBackPressed() {
		ItemDepth itemDepth = ItemDepth.getInstance(this);
		if (itemDepth.isAtStart()) {
			super.onBackPressed();
		} else {
			itemDepth.stepBack();
		}
	}

	@Override
	public boolean onNavigateUp() {
		ItemDepth itemDepth = ItemDepth.getInstance(this);
		if (!itemDepth.isAtStart()) {
			itemDepth.restart();
		}

		return true;
	}

	@Override
	public void onSetReportDialogPositiveClick(String reportUrl) {
		if (reportUrl.equals("")) {
			onSetReportDialogNegativeClick();
		} else {
			ReportUrl.getInstance(this).save(reportUrl);
			Message.getInstance(this).showInfo(R.string.info_url_stored);
			ReportHandler.getInstance(this).load(reportUrl, this);
		}
	}

	@Override
	public void onSetReportDialogNegativeClick() {
		// ignore
	}

	@Override
	public void onLoaderTaskExecutionBegin() {
		getActionBar().setTitle(R.string.heading_loading);
		loaderTaskExecuted = false;
		invalidateOptionsMenu();
	}

	@Override
	public void onLoaderTaskExecutionCompletion(JsonNode reportJson) {
		ReportHandler.getInstance(this).show(reportJson, this);
		Message.getInstance(this).showInfo(R.string.info_report_loaded);
		loaderTaskExecuted = true;
		invalidateOptionsMenu();
	}

	@Override
	public void onLoaderTaskExecutionFailure(LoaderException exception) {
		Message.getInstance(this).showError(exception);
		loaderTaskExecuted = true;
		invalidateOptionsMenu();
	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
		ItemHandler.getInstance(this).show(parent, groupPosition, childPosition, this);

		return true;
	}

	private void showSetReportDialog() {
		if (!dialog.isVisible()) {
			dialog.show(getFragmentManager(), "SetReportNoticeDialog");
		}
	}

}
