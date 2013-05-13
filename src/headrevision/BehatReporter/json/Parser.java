package headrevision.BehatReporter.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.TextNode;

public abstract class Parser {

	private JsonNode rootNode;

	public Parser(JsonNode rootNode) {
		this.rootNode = rootNode;
	}

	protected String parseText(String name) throws ParserException {
		JsonNode textNode = parse(name);

		if (!textNode.isTextual()) {
			throw (new ParserException("value is not textual"));
		}

		return ((TextNode) textNode).asText();
	}

	protected String parseTextOrNull(String name) throws ParserException {
		JsonNode textOrNullNode = parse(name);

		if (textOrNullNode.isNull()) {

			return null;

		} else {

			if (!textOrNullNode.isTextual()) {
				throw (new ParserException("value is neither null nor textual"));
			}

			return ((TextNode) textOrNullNode).asText();

		}
	}

	protected boolean parseBoolean(String name) throws ParserException {
		JsonNode booleanNode = parse(name);

		if (!booleanNode.isBoolean()) {
			throw (new ParserException("value is not boolean"));
		}

		return booleanNode.asBoolean();
	}

	protected Map<String, String> parseProperties(String name)
			throws ParserException {
		JsonNode objectNode = rootNode.get(name);

		if (!objectNode.isObject()) {
			throw (new ParserException("value is not object"));
		}

		Map<String, String> properties = new HashMap<String, String>();

		Iterator<Entry<String, JsonNode>> propertyIterator = objectNode.fields();
		while (propertyIterator.hasNext()) {
			Entry<String, JsonNode> property = propertyIterator.next();
			properties.put(property.getKey(), ((TextNode) property.getValue()).asText());
		}

		return properties;
	}

	protected List<JsonNode> parseNonEmptyArray(String name)
			throws ParserException {
		List<JsonNode> nodes = parseArray(name);

		if (nodes.size() == 0) {
			throw (new ParserException("value is empty array"));
		}

		return nodes;
	}

	protected List<JsonNode> parseArray(String name) throws ParserException {
		JsonNode arrayNode = parse(name);

		if (!arrayNode.isArray()) {
			throw (new ParserException("value is not array"));
		}

		return getList(arrayNode);
	}

	protected JsonNode parse(String name) throws ParserException {
		JsonNode node = rootNode.get(name);

		if (node == null) {
			throw (new ParserException("property with name " + name + " does not exist"));
		}

		return node;
	}

	protected boolean has(String name) throws ParserException {
		JsonNode node = rootNode.get(name);

		return node != null;
	}

	protected List<JsonNode> getList(JsonNode arrayNode) {
		List<JsonNode> nodes = new ArrayList<JsonNode>();
		Iterator<JsonNode> nodeIterator = ((ArrayNode) arrayNode).iterator();
		while (nodeIterator.hasNext()) {
			nodes.add(nodeIterator.next());
		}
		return nodes;
	}

}