package headrevision.BehatReporter.report;

import headrevision.BehatReporter.json.ParserException;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

public class OutlineParser extends ItemParser {

	public OutlineParser(JsonNode item) {
		super(item, new StepOrOutlineExampleParserFactory());
	}

	public boolean isOutline() throws ParserException {
		return parseBoolean("isOutline");
	}

	@Override
	public boolean hasTitle() throws ParserException {
		return parseTitle() != null;
	}

	@Override
	public String parseTitle() throws ParserException {
		return parseTextOrNull("title");
	}

	@Override
	public boolean hasSubItems() {
		return hasSubItems("steps") && hasSubItems("examples");
	}

	@Override
	public List<JsonNode> parseSubItems() throws ParserException {
		List<JsonNode> subItems = parseSubItems("steps");
		subItems.addAll(super.parseSubItems("examples"));
		return subItems;
	}

}