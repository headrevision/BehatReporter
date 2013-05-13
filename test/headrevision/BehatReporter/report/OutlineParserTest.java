package headrevision.BehatReporter.report;

import headrevision.BehatReporter.json.ParserException;
import headrevision.BehatReporter.test.AbstractParserTest;

import java.util.List;

import junit.framework.Assert;

import com.fasterxml.jackson.databind.JsonNode;

public class OutlineParserTest extends AbstractParserTest {

	public void testCheckingInexistentSubItems() {
		JsonNode item = getJsonNode("{}");
		OutlineParser sut = new OutlineParser(item);

		Assert.assertFalse(sut.hasSubItems());
	}

	public void testCheckingZeroSubItems() {
		JsonNode item = getJsonNode("{\"steps\": [], \"examples\": []}");
		OutlineParser sut = new OutlineParser(item);

		Assert.assertFalse(sut.hasSubItems());
	}

	public void testCheckingIncompleteSubItems() {
		JsonNode item = getJsonNode("{\"steps\": [{}]}");
		OutlineParser sut = new OutlineParser(item);

		Assert.assertFalse(sut.hasSubItems());

		item = getJsonNode("{\"examples\": [{}]}");
		sut = new OutlineParser(item);

		Assert.assertFalse(sut.hasSubItems());
	}

	public void testCheckingOneSubItem() {
		JsonNode item = getJsonNode("{\"steps\": [{}], \"examples\": [{}]}");
		OutlineParser sut = new OutlineParser(item);

		Assert.assertTrue(sut.hasSubItems());
	}

	public void testParsingSubItem() throws ParserException {
		JsonNode item = getJsonNode("{\"steps\": [{\"text\": \"<foo>\", \"type\": \"Given\"}], \"examples\": [{\"values\": {\"foo\": \"bar\"}, \"result\": \"passed\"}]}");
		OutlineParser sut = new OutlineParser(item);
		List<JsonNode> subItems = sut.parseSubItems();

		Assert.assertEquals(2, subItems.size());
		Assert.assertTrue(new StepParser(subItems.get(0)).isStep());
		Assert.assertTrue(new OutlineExampleParser(subItems.get(1)).isOutlineExample());
	}

}