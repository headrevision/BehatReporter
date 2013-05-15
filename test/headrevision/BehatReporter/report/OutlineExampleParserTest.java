package headrevision.BehatReporter.report;

import headrevision.BehatReporter.json.ParserException;
import headrevision.BehatReporter.test.AbstractParserTest;
import junit.framework.Assert;

public class OutlineExampleParserTest extends AbstractParserTest {

	public void testParsingOneOutlineExampleValue() {
		OutlineExampleParser sut = new OutlineExampleParser(getJsonNode("{\"values\": {\"foo\": \"bar\"}}"));
		try {
			Assert.assertEquals("foo: bar", sut.parseValues());
		} catch (ParserException e) {
			Assert.fail(e.getMessage());
		}
	}

	public void testParsingTwoOutlineExampleValues() {
		OutlineExampleParser sut = new OutlineExampleParser(getJsonNode("{\"values\": {\"foo\": \"bar\", \"bar\": \"baz\"}}"));
		try {
			Assert.assertEquals("foo: bar\nbar: baz", sut.parseValues());
		} catch (ParserException e) {
			Assert.fail(e.getMessage());
		}
	}

}