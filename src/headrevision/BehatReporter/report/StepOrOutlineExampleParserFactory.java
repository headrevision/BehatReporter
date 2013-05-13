package headrevision.BehatReporter.report;

import headrevision.BehatReporter.json.ParserException;

import com.fasterxml.jackson.databind.JsonNode;

public class StepOrOutlineExampleParserFactory extends ItemParserFactory {

	@Override
	public ItemParser getItemParser(JsonNode item) {
		StepParser stepParser = new StepParser(item);
		OutlineExampleParser outlineExampleParser = new OutlineExampleParser(item);
		try {
			if (stepParser.isStep()) {
				return stepParser;
			} else if (outlineExampleParser.isOutlineExample()) {
				return outlineExampleParser;
			}
		} catch (ParserException e) {
		}

		return null;
	}

	@Override
	public String getItemsKey() {
		throw(new UnsupportedOperationException());
	}

}