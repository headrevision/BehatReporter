package headrevision.BehatReporter.json;

import headrevision.BehatReporter.test.AbstractParserTest;

import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;

public class ParserTest extends AbstractParserTest {

	public void testParsingInexistentProperty() {
		Parser sut = new ParserImpl(getJsonNode("{}"));
		try {
			boolean hasProperty = sut.has("foo");

			Assert.assertFalse(hasProperty);
		} catch (ParserException e) {
			Assert.fail(e.getMessage());
		}
		try {
			sut.parse("foo");

			Assert.fail("expected ParserException");
		} catch (ParserException e) {
		}
	}

	public void testParsingExistentProperty() {
		Parser sut = new ParserImpl(getJsonNode("{\"foo\": null}"));
		try {
			boolean hasProperty = sut.has("foo");

			Assert.assertTrue(hasProperty);

			sut.parse("foo");
		} catch (ParserException e) {
			Assert.fail(e.getMessage());
		}
	}

	public void testParsingNullAsText() {
		Parser sut = new ParserImpl(getJsonNode("{\"foo\": null}"));
		try {
			sut.parseText("foo");

			Assert.fail("expected ParserException");
		} catch (ParserException e) {
		}
	}

	public void testParsingTextAsText() {
		Parser sut = new ParserImpl(getJsonNode("{\"foo\": \"bar\"}"));
		try {
			String text = sut.parseText("foo");

			Assert.assertEquals("bar", text);
		} catch (ParserException e) {
			Assert.fail(e.getMessage());
		}
	}

	public void testParsingNullAsTextOrNull() {
		Parser sut = new ParserImpl(getJsonNode("{\"foo\": null}"));
		try {
			String textOrNullNode = sut.parseTextOrNull("foo");

			Assert.assertNull(textOrNullNode);
		} catch (ParserException e) {
			Assert.fail(e.getMessage());
		}
	}

	public void testParsingTextAsTextOrNull() {
		Parser sut = new ParserImpl(getJsonNode("{\"foo\": \"bar\"}"));
		try {
			String textOrNullNode = sut.parseTextOrNull("foo");

			Assert.assertEquals("bar", textOrNullNode);
		} catch (ParserException e) {
			Assert.fail(e.getMessage());
		}
	}

	public void testParsingNullAsBoolean() {
		Parser sut = new ParserImpl(getJsonNode("{\"foo\": null}"));
		try {
			sut.parseText("foo");

			Assert.fail("expected ParserException");
		} catch (ParserException e) {
		}
	}

	public void testParsingBooleanAsBoolean() {
		Parser sut = new ParserImpl(
				getJsonNode("{\"foo\": true, \"bar\": false}"));
		try {
			boolean booleanNode = sut.parseBoolean("foo");

			Assert.assertTrue(booleanNode);

			booleanNode = sut.parseBoolean("bar");

			Assert.assertFalse(booleanNode);
		} catch (ParserException e) {
			Assert.fail(e.getMessage());
		}
	}

	public void testParsingEmptyObjectAsProperties() {
		Parser sut = new ParserImpl(getJsonNode("{\"foo\": {}}"));
		try {
			Map<String, String> properties = sut.parseProperties("foo");

			Assert.assertEquals(0, properties.size());
		} catch (ParserException e) {
			Assert.fail(e.getMessage());
		}
	}

	public void testParsingNonEmptyObjectAsProperties() {
		Parser sut = new ParserImpl(getJsonNode("{\"foo\": {\"bar\": \"baz\"}}"));
		try {
			Map<String,String> properties = sut.parseProperties("foo");

			Assert.assertEquals(1, properties.size());
			Assert.assertTrue(properties.containsKey("bar"));
			Assert.assertEquals("baz", properties.get("bar"));
		} catch (ParserException e) {
			Assert.fail(e.getMessage());
		}
	}

	public void testParsingNullAsArray() {
		Parser sut = new ParserImpl(getJsonNode("{\"foo\": null}"));
		try {
			sut.parseArray("foo");

			Assert.fail("expected ParserException");
		} catch (ParserException e) {
		}
	}

	public void testParsingEmptyArrayAsArray() {
		Parser sut = new ParserImpl(getJsonNode("{\"foo\": []}"));
		try {
			List<JsonNode> nodes = sut.parseArray("foo");

			Assert.assertEquals(0, nodes.size());
		} catch (ParserException e) {
			Assert.fail(e.getMessage());
		}
	}

	public void testParsingEmptyArrayAsNonEmptyArray() {
		Parser sut = new ParserImpl(getJsonNode("{\"foo\": []}"));
		try {
			sut.parseNonEmptyArray("foo");

			Assert.fail("expected ParserException");
		} catch (ParserException e) {
		}
	}

	public void testParsingNonEmptyArrayAsNonEmptyArray() {
		Parser sut = new ParserImpl(getJsonNode("{\"foo\": [\"bar\"]}"));
		try {
			List<JsonNode> nodes = sut.parseArray("foo");

			Assert.assertEquals(1, nodes.size());
			Assert.assertNotNull(nodes.get(0));
			Assert.assertTrue(nodes.get(0) instanceof TextNode);
			Assert.assertEquals("bar", nodes.get(0).asText());
		} catch (ParserException e) {
			Assert.fail(e.getMessage());
		}
	}

}