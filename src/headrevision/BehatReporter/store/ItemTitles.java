package headrevision.BehatReporter.store;

import java.util.Stack;

public class ItemTitles {

	private static ItemTitles instance;

	private Stack<String> titles;

	private ItemTitles() {
		titles = new Stack<String>();
	}

	public static ItemTitles getInstance() {
		if (instance == null) {
			instance = new ItemTitles();
		}
		return instance;
	}

	public void push(String title) {
		titles.push(title);
	}

	public String pop() {
		return titles.pop();
	}

	public String clear() {
		String title = titles.firstElement();
		titles.removeAllElements();
		return title;
	}

}