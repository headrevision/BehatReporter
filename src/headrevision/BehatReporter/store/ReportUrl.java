package headrevision.BehatReporter.store;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class ReportUrl {

	private static ReportUrl instance;

	private Activity activity;

	private SharedPreferences preferences;

	private ReportUrl(Activity activity) {
		this.activity = activity;
		preferences = activity.getPreferences(Activity.MODE_PRIVATE);
	}

	public static ReportUrl getInstance(Activity activity) {
		if (instance == null || instance.activity != activity) {
			instance = new ReportUrl(activity);
		}
		return instance;
	}

	public String retrieve() {
		return preferences.getString("report_url", "");
	}

	public void save(String reportUrl) {
		Editor editor = preferences.edit();
		editor.putString("report_url", reportUrl);
		editor.commit();
	}

}