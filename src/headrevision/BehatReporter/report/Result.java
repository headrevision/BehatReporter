package headrevision.BehatReporter.report;

import java.util.Locale;

public enum Result implements Comparable<Result> {

	PASSED(0), SKIPPED(1), PENDING(2), UNDEFINED(3), FAILED(4);

	private int priority;

	private Result(int priority) {
		this.priority = priority;
	}

	public int getPriority() {
		return priority;
	}

	public String getName() {
		return this.name().toLowerCase(Locale.ENGLISH);
	}

}