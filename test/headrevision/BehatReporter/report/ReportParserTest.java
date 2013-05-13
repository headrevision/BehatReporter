package headrevision.BehatReporter.report;

import headrevision.BehatReporter.json.ParserException;
import headrevision.BehatReporter.test.AbstractParserTest;

import java.util.Date;

import junit.framework.Assert;

public class ReportParserTest extends AbstractParserTest {

	@SuppressWarnings("deprecation")
	public void testParsingDate() {
		ReportParser sut = new ReportParser(getJsonNode("{\"date\": \"2012-11-30 17:23:58\"}"));
		try {
			Date date = sut.parseDate();

			Assert.assertEquals(112, date.getYear());
			Assert.assertEquals(10, date.getMonth());
			Assert.assertEquals(5, date.getDay());
			Assert.assertEquals(17, date.getHours());
			Assert.assertEquals(23, date.getMinutes());
			Assert.assertEquals(58, date.getSeconds());
		} catch (ParserException e) {
			Assert.fail(e.getMessage());
		}
	}

}