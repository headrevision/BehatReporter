package headrevision.BehatReporter.report;

import headrevision.BehatReporter.json.ParserException;
import headrevision.BehatReporter.test.AbstractParserTest;
import junit.framework.Assert;

import com.fasterxml.jackson.databind.JsonNode;

public class ItemParserTest extends AbstractParserTest {

	public void testCheckingInexistentSubItems() {
		JsonNode item = getJsonNode("{}");
		ItemParserImpl1 sut = new ItemParserImpl1(item, new ItemParserFactoryImpl2());

		Assert.assertFalse(sut.hasSubItems());
	}

	public void testCheckingZeroSubItems() {
		JsonNode item = getJsonNode("{\"subItems\": []}");
		ItemParserImpl1 sut = new ItemParserImpl1(item, new ItemParserFactoryImpl2());

		Assert.assertFalse(sut.hasSubItems());
	}

	public void testCheckingOneSubItem() {
		JsonNode item = getJsonNode("{\"subItems\": [{}]}");
		ItemParserImpl1 sut = new ItemParserImpl1(item, new ItemParserFactoryImpl2());

		Assert.assertTrue(sut.hasSubItems());
	}

	public void testParsingSingleSubItemResult() {
		JsonNode item = getJsonNode("{\"subItems\": [{\"result\": \"passed\"}]}");
		ItemParserImpl1 sut = new ItemParserImpl1(item, new ItemParserFactoryImpl2());
		try {
			Result result = sut.parseResult();

			Assert.assertEquals(Result.PASSED, result);
		} catch (ParserException e) {
			Assert.fail(e.getMessage());
		}
	}

	public void testParsingMultipleSubItemsResult() {
		JsonNode item = getJsonNode("{\"subItems\": [{\"result\": \"passed\"}, {\"result\": \"failed\"}]}");
		ItemParserImpl1 sut = new ItemParserImpl1(item, new ItemParserFactoryImpl2());
		try {
			Result result = sut.parseResult();

			Assert.assertEquals(Result.FAILED, result);
		} catch (ParserException e) {
			Assert.fail(e.getMessage());
		}
	}

	public void testParsingInvalidResult() {
		ItemParserImpl2 sut = new ItemParserImpl2(getJsonNode("{\"result\": \"pissed\"}"), null);
		try {
			sut.parseResult();

			Assert.fail("expected ParserException");
		} catch (ParserException e) {
		}
	}

	public void testParsingValidResult() {
		ItemParserImpl2 sut = new ItemParserImpl2(getJsonNode("{\"result\": \"passed\"}"), null);
		try {
			Result result = sut.parseResult();

			Assert.assertEquals(Result.PASSED, result);
		} catch (ParserException e) {
			Assert.fail(e.getMessage());
		}
	}

	public void testParsingInexistentResult() {
		ItemParserImpl2 sut = new ItemParserImpl2(getJsonNode("{\"foo\": \"bar\"}"), null);
		try {
			Result result = sut.parseResult();

			Assert.assertEquals(Result.PASSED, result);
		} catch (ParserException e) {
			Assert.fail(e.getMessage());
		}
	}

}