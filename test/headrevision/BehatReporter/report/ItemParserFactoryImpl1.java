package headrevision.BehatReporter.report;

import com.fasterxml.jackson.databind.JsonNode;

public class ItemParserFactoryImpl1 extends ItemParserFactory {

	@Override
	public ItemParser getItemParser(JsonNode item) {
		return new ItemParserImpl1(item, new ItemParserFactoryImpl2());
	}

	@Override
	public String getItemsKey() {
		return "items";
	}

}