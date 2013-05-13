package headrevision.BehatReporter.report;

import com.fasterxml.jackson.databind.JsonNode;

public class StepParserFactory extends ItemParserFactory {

	@Override
	public ItemParser getItemParser(JsonNode item) {
		return new StepParser(item);
	}

	@Override
	public String getItemsKey() {
		return "steps";
	}

}
