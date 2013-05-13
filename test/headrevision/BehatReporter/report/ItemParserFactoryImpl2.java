package headrevision.BehatReporter.report;

import com.fasterxml.jackson.databind.JsonNode;

public class ItemParserFactoryImpl2 extends ItemParserFactory {

	@Override
	public ItemParser getItemParser(JsonNode item) {
		return new ItemParserImpl2(item, null);
	}

	@Override
	public String getItemsKey() {
		return "subItems";
	}

}