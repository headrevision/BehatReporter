package headrevision.BehatReporter.report;

import junit.framework.Assert;
import junit.framework.TestCase;

public class ResultTest extends TestCase {

	public void testResultName() {
		Result sut = Result.PASSED;

		Assert.assertEquals("passed", sut.getName());
	}

}