package headrevision.BehatReporter.report;

import com.fasterxml.jackson.databind.JsonNode;

public abstract class ItemParserFactory {

	public abstract ItemParser getItemParser(JsonNode item);

	public abstract String getItemsKey();

}