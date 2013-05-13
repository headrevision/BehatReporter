package headrevision.BehatReporter.report;

import headrevision.BehatReporter.json.ParserException;

import com.fasterxml.jackson.databind.JsonNode;

public class StepParser extends ItemParser {

	public StepParser(JsonNode item) {
		super(item, null);
	}

	public boolean isStep() throws ParserException {
		return has("text") && has("type");
	}

	public boolean isBackground() throws ParserException {
		return has("isBackground") && parseBoolean("isBackground");
	}

	@Override
	public boolean hasTitle() throws ParserException {
		return false;
	}

	public String parseText() throws ParserException {
		try {
			return parseRawText()[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw (new ParserException(e));
		}
	}

	public boolean hasMultilineArgs() throws ParserException {
		return parseRawText().length == 2;
	}

	public String parseMultilineArgs() throws ParserException {
		try {
			return parseRawText()[1];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw (new ParserException(e));
		}
	}

	public String parseType() throws ParserException {
		return parseText("type");
	}

	private String[] parseRawText() throws ParserException {
		return parseText("text").split("\n", 2);
	}

}