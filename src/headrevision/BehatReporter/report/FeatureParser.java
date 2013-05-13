package headrevision.BehatReporter.report;

import headrevision.BehatReporter.json.ParserException;

import com.fasterxml.jackson.databind.JsonNode;

public class FeatureParser extends ItemParser {

	public FeatureParser(JsonNode item) {
		super(item, new ScenarioOrOutlineParserFactory());
	}

	@Override
	public boolean hasTitle() throws ParserException {
		return true;
	}

	@Override
	public String parseTitle() throws ParserException {
		return parseText("title");
	}

	public String parseDesc() throws ParserException {
		return parseTextOrNull("desc");
	}

}