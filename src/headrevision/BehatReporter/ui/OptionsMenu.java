package headrevision.BehatReporter.ui;

import headrevision.BehatReporter.R;
import headrevision.BehatReporter.store.ReportUrl;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;

public class OptionsMenu {

	private static OptionsMenu instance;

	private Activity activity;

	private OptionsMenu(Activity activity) {
		this.activity = activity;
	}

	public static OptionsMenu getInstance(Activity activity) {
		if (instance == null || instance.activity != activity) {
			instance = new OptionsMenu(activity);
		}
		return instance;
	}

	public void create(Menu menu) {
		MenuInflater menuInflater = activity.getMenuInflater();
		menuInflater.inflate(R.menu.main, menu);
	}

	public void show(Menu menu) {
		menu.findItem(R.id.menu_url).setVisible(true);
		String reportUrl = ReportUrl.getInstance(activity).retrieve();
		menu.findItem(R.id.menu_refresh).setVisible(!reportUrl.equals(""));
	}

	public void hide(Menu menu) {
		menu.findItem(R.id.menu_url).setVisible(false);
		menu.findItem(R.id.menu_refresh).setVisible(false);			
	}

}