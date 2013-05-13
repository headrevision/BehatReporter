package headrevision.BehatReporter.report;

import headrevision.BehatReporter.json.ParserException;

import com.fasterxml.jackson.databind.JsonNode;

public class ScenarioParser extends ItemParser {

	public ScenarioParser(JsonNode item) {
		super(item, new StepParserFactory());
	}

	@Override
	public boolean hasTitle() throws ParserException {
		return parseTitle() != null;
	}

	@Override
	public String parseTitle() throws ParserException {
		return parseTextOrNull("title");
	}

}