package headrevision.BehatReporter.report;

import com.fasterxml.jackson.databind.JsonNode;

public class ItemParserImpl2 extends ItemParser {

	public ItemParserImpl2(JsonNode item, ItemParserFactory subItemParserFactory) {
		super(item, subItemParserFactory);
	}

	@Override
	public boolean hasTitle() {
		return false;
	}

}