package headrevision.BehatReporter.json;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.fasterxml.jackson.core.JsonParseException;

public class ReaderTest extends TestCase {

	public void testReadingMalformedJson() {
		InputStream jsonStream = new ByteArrayInputStream("{\"foo\": }".getBytes());
		Reader sut = new Reader(jsonStream);

		try {
			sut.read();

			Assert.fail("expected ReaderException");
		} catch (ReaderException e) {
			Assert.assertTrue(e.getCause() instanceof JsonParseException);
		}
	}

	public void testReadingWellformedJson() {
		InputStream jsonStream = new ByteArrayInputStream("{\"foo\": \"bar\"}".getBytes());
		Reader sut = new Reader(jsonStream);

		try {
			sut.read();
		} catch (ReaderException e) {
			Assert.fail(e.getMessage());
		}
	}

}