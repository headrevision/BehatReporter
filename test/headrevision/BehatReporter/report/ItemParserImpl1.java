package headrevision.BehatReporter.report;

import com.fasterxml.jackson.databind.JsonNode;

public class ItemParserImpl1 extends ItemParser {

	public ItemParserImpl1(JsonNode item, ItemParserFactory subItemParserFactory) {
		super(item, subItemParserFactory);
	}

	@Override
	public boolean hasTitle() {
		return false;
	}

}