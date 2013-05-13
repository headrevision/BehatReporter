package headrevision.BehatReporter.ui;

import android.app.Activity;
import android.widget.Toast;

public class Message {

	private static Message instance;

	private Activity activity;

	private Message(Activity activity) {
		this.activity = activity;
	}

	public static Message getInstance(Activity activity) {
		if (instance == null || instance.activity != activity) {
			instance = new Message(activity);
		}
		return instance;
	}

	public void showInfo(int resourceId) {
		String message = activity.getResources().getString(resourceId);
		show(message, Toast.LENGTH_SHORT);
	}

	public void showError(Exception exception) {
		String message = "ERROR: " + exception.getMessage();
		show(message, Toast.LENGTH_LONG);
	}

	private void show(String message, int duration) {
		Toast toast = Toast.makeText(activity, message, duration);
		toast.show();
	}
}