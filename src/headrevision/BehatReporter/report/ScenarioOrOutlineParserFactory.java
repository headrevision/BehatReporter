package headrevision.BehatReporter.report;

import headrevision.BehatReporter.json.ParserException;

import com.fasterxml.jackson.databind.JsonNode;

public class ScenarioOrOutlineParserFactory extends ItemParserFactory {

	@Override
	public ItemParser getItemParser(JsonNode item) {
		OutlineParser outlineParser = new OutlineParser(item);
		try {
			if (outlineParser.isOutline()) {
				return outlineParser;
			}
		} catch (ParserException e) {
			return null;
		}

		return new ScenarioParser(item);
	}

	@Override
	public String getItemsKey() {
		return "scenarios";
	}

}