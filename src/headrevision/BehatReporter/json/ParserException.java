package headrevision.BehatReporter.json;

@SuppressWarnings("serial")
public class ParserException extends Exception {

	public ParserException(Throwable throwable) {
		super(throwable);
	}

	public ParserException(String message) {
		super(message);
	}

}