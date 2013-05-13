package headrevision.BehatReporter.ui;

import headrevision.BehatReporter.R;
import headrevision.BehatReporter.store.ItemTitles;
import android.app.ActionBar;
import android.app.Activity;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

public class ItemDepth {

	private static ItemDepth instance;

	private Activity activity;

	private ViewFlipper viewFlipper;

	private ActionBar actionBar;

	private ItemDepth(Activity activity) {
		this.activity = activity;
		viewFlipper = (ViewFlipper) activity.findViewById(R.id.viewFlipper);
		actionBar = activity.getActionBar();
	}

	public static ItemDepth getInstance(Activity activity) {
		if (instance == null || instance.activity != activity) {
			instance = new ItemDepth(activity);
		}
		return instance;
	}

	public void start() {
		animateForward();
		viewFlipper.setDisplayedChild(0);
		actionBar.setDisplayHomeAsUpEnabled(false);		
	}

	public void restart() {
		animateBack();
		viewFlipper.setDisplayedChild(0);
		actionBar.setTitle(ItemTitles.getInstance().clear());
		actionBar.setDisplayHomeAsUpEnabled(false);	
	}

	public void stepForward() {
		animateForward();
		viewFlipper.showNext();
		actionBar.setDisplayHomeAsUpEnabled(true);
	}

	public void stepBack() {
		animateBack();
		viewFlipper.showPrevious();
		actionBar.setTitle(ItemTitles.getInstance().pop());
		if (isAtStart()) {
			actionBar.setDisplayHomeAsUpEnabled(false);	
		}
	}

	public boolean isAtStart() {
		return viewFlipper.getDisplayedChild() == 0;
	}

	private void animateForward() {
		viewFlipper.setInAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_in_right));
		viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_out_left));		
	}

	private void animateBack() {
		viewFlipper.setInAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_in_left));
		viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_out_right));		
	}

}