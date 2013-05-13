package headrevision.BehatReporter.report;

import com.fasterxml.jackson.databind.JsonNode;

public class FeatureParserFactory extends ItemParserFactory {

	@Override
	public ItemParser getItemParser(JsonNode item) {
		return new FeatureParser(item);
	}

	@Override
	public String getItemsKey() {
		return "features";
	}

}
