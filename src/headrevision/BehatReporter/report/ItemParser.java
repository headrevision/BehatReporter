package headrevision.BehatReporter.report;

import headrevision.BehatReporter.json.Parser;
import headrevision.BehatReporter.json.ParserException;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import com.fasterxml.jackson.databind.JsonNode;

public abstract class ItemParser extends Parser {

	private ItemParserFactory subItemParserFactory;

	public ItemParser(JsonNode item, ItemParserFactory subItemParserFactory) {
		super(item);
		this.subItemParserFactory = subItemParserFactory;
	}

	public abstract boolean hasTitle() throws ParserException;

	public String parseTitle() throws ParserException {
		throw (new ParserException(new UnsupportedOperationException()));
	}

	public Result parseResult() throws ParserException {
		if (has("result")) {
			return parseResultDirectly(parseText("result"));
		} else {
			return parseResultRecursively();
		}
	}

	public boolean hasSubItems() {
		String subItemsKey;
		try {
			subItemsKey = subItemParserFactory.getItemsKey();
		} catch (NullPointerException e) {
			return false;
		}
		return hasSubItems(subItemsKey);
	}

	public List<JsonNode> parseSubItems() throws ParserException {
		String subItemsKey;
		try {
			subItemsKey = subItemParserFactory.getItemsKey();
		} catch (NullPointerException e) {
			throw(new ParserException(e));
		}
		return parseSubItems(subItemsKey);
	}

	protected boolean hasSubItems(String subItemsKey) {
		List<JsonNode> subItems = null;
		try {
			subItems = parseSubItems(subItemsKey);
		} catch (ParserException e) {
			return false;
		}
		return subItems.size() > 0;
	}

	protected List<JsonNode> parseSubItems(String subItemsKey) throws ParserException {
		return parseArray(subItemsKey);
	}

	private Result parseResultDirectly(String resultText)
			throws ParserException {
		try {
			return Result.valueOf(resultText.toUpperCase(Locale.ENGLISH));
		} catch (IllegalArgumentException e) {
			throw (new ParserException(e));
		}
	}

	private Result parseResultRecursively() throws ParserException {
		Result result = Result.PASSED;

		if (hasSubItems()) {
			List<JsonNode> subItems = parseSubItems();
			Iterator<JsonNode> subItemIterator = subItems.iterator();
			while (subItemIterator.hasNext()) {
				result = addSubItemResult(subItemIterator.next(), result);
			}
		}

		return result;
	}

	private Result addSubItemResult(JsonNode subItem, Result itemResult) throws ParserException {
		ItemParser subItemParser = subItemParserFactory.getItemParser(subItem);
		Result subItemResult = subItemParser.parseResult();
		if (itemResult.getPriority() < subItemResult.getPriority()) {
			itemResult = subItemResult;
		}
		return itemResult;
	}

}