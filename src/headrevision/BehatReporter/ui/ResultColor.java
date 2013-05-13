package headrevision.BehatReporter.ui;

import headrevision.BehatReporter.report.Result;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;

public class ResultColor {

	private static ResultColor instance;

	private Activity activity;

	private ResultColor(Activity activity) {
		this.activity = activity;
	}

	public static ResultColor getInstance(Activity activity) {
		if (instance == null || instance.activity != activity) {
			instance = new ResultColor(activity);
		}
		return instance;
	}

	public void set(Result result, View parentView, TextView textView, boolean selectable) {
		String resultName = result.getName();

		if (selectable) { 
			setSelector(parentView, resultName);
		} else {
			setBackground(parentView, resultName);
		}
		setText(textView, resultName);
	}

	@SuppressWarnings("deprecation")
	private void setSelector(View parentView, String resultName) {
		String selectorName = "selector_" + resultName;
		int selectorId = activity.getResources().getIdentifier(selectorName, "drawable", activity.getPackageName());
		parentView.setBackgroundDrawable(activity.getResources().getDrawable(selectorId));
	}

	private void setBackground(View parentView, String resultName) {
		String backgroundColorName = "result_" + resultName + "_light";
		int backgroundColorId = activity.getResources().getIdentifier(backgroundColorName, "color", activity.getPackageName());
		parentView.setBackgroundColor(activity.getResources().getColor(backgroundColorId));
	}

	private void setText(TextView textView, String resultName) {
		String textColorName = "result_" + resultName + "_dark";
		int textColorId = activity.getResources().getIdentifier(textColorName, "color", activity.getPackageName());
		textView.setTextColor(activity.getResources().getColor(textColorId));
	}

}