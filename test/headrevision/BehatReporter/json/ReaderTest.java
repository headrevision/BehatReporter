package headrevision.BehatReporter.json;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class ReaderTest extends TestCase {

	public void testReadingEmptyJson() {
		Reader sut = new Reader("");

		try {
			sut.read();

			Assert.fail("expected ReaderException");
		} catch (ReaderException e) {
			Assert.assertTrue(e.getCause() instanceof JsonMappingException);
		}		
	}

	public void testReadingMalformedJson() {
		Reader sut = new Reader("{\"foo\": }");

		try {
			sut.read();

			Assert.fail("expected ReaderException");
		} catch (ReaderException e) {
			Assert.assertTrue(e.getCause() instanceof JsonParseException);
		}
	}

	public void testReadingWellformedJson() {
		Reader sut = new Reader("{\"foo\": \"bar\"}");

		try {
			sut.read();
		} catch (ReaderException e) {
			Assert.fail(e.getMessage());
		}
	}

}