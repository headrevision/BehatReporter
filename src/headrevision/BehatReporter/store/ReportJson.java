package headrevision.BehatReporter.store;

import headrevision.BehatReporter.json.Reader;
import headrevision.BehatReporter.json.ReaderException;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.fasterxml.jackson.databind.JsonNode;

public class ReportJson {

	private static ReportJson instance;

	private Activity activity;

	private SharedPreferences preferences;

	private ReportJson(Activity activity) {
		this.activity = activity;
		preferences = activity.getPreferences(Activity.MODE_PRIVATE);
	}

	public static ReportJson getInstance(Activity activity) {
		if (instance == null || instance.activity != activity) {
			instance = new ReportJson(activity);
		}
		return instance;
	}

	public JsonNode retrieve() {
		String reportJson = preferences.getString("report_json", "");
		Reader jsonReader = new Reader(reportJson);
		try {
			return jsonReader.read();
		} catch (ReaderException e) {
			return null;
		}
	}

	public void save(JsonNode reportJson) {
		Editor editor = preferences.edit();
		editor.putString("report_json", reportJson.toString());
		editor.commit();
	}

}