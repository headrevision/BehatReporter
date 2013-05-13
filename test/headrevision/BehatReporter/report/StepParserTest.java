package headrevision.BehatReporter.report;

import headrevision.BehatReporter.json.ParserException;
import headrevision.BehatReporter.test.AbstractParserTest;
import junit.framework.Assert;

public class StepParserTest extends AbstractParserTest {

	public void testCheckingInexistentBackgroundStepProperty() {
		StepParser sut = new StepParser(getJsonNode("{\"foo\": \"bar\"}"));
		try {
			boolean stepIsBackground = sut.isBackground();

			Assert.assertFalse(stepIsBackground);
		} catch (ParserException e) {
			Assert.fail(e.getMessage());
		}
	}

	public void testCheckingBackgroundStepPropertySetFalse() {
		StepParser sut = new StepParser(getJsonNode("{\"isBackground\": false}"));
		try {
			boolean stepIsBackground = sut.isBackground();

			Assert.assertFalse(stepIsBackground);
		} catch (ParserException e) {
			Assert.fail(e.getMessage());
		}
	}

	public void testCheckingBackgroundStepPropertySetTrue() {
		StepParser sut = new StepParser(getJsonNode("{\"isBackground\": true}"));
		try {
			boolean stepIsBackground = sut.isBackground();

			Assert.assertTrue(stepIsBackground);
		} catch (ParserException e) {
			Assert.fail(e.getMessage());
		}
	}

	public void testParsingEmptyText() {
		StepParser sut = new StepParser(getJsonNode("{\"text\": \"\"}"));
		try {
			Assert.assertEquals("", sut.parseText());
			Assert.assertFalse(sut.hasMultilineArgs());
		} catch (ParserException e) {
			Assert.fail(e.getMessage());
		}
	}

	public void testParsingOneLineText() {
		StepParser sut = new StepParser(getJsonNode("{\"text\": \"foo\"}"));
		try {
			Assert.assertEquals("foo", sut.parseText());
			Assert.assertFalse(sut.hasMultilineArgs());
		} catch (ParserException e) {
			Assert.fail(e.getMessage());
		}
	}

	public void testParsingTwoLinesText() {
		StepParser sut = new StepParser(getJsonNode("{\"text\": \"foo\\nbar\"}"));
		try {
			Assert.assertEquals("foo", sut.parseText());
			Assert.assertTrue(sut.hasMultilineArgs());
			Assert.assertEquals("bar", sut.parseMultilineArgs());
		} catch (ParserException e) {
			Assert.fail(e.getMessage());
		}
	}

	public void testParsingThreeLinesText() {
		StepParser sut = new StepParser(getJsonNode("{\"text\": \"foo\\nbar\\nbaz\"}"));
		try {
			Assert.assertEquals("foo", sut.parseText());
			Assert.assertTrue(sut.hasMultilineArgs());
			Assert.assertEquals("bar\nbaz", sut.parseMultilineArgs());
		} catch (ParserException e) {
			Assert.fail(e.getMessage());
		}
	}

}