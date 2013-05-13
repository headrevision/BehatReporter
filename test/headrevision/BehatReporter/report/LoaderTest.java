package headrevision.BehatReporter.report;

import java.io.IOException;
import java.net.MalformedURLException;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.fasterxml.jackson.databind.JsonNode;
import com.harlap.test.http.MockHttpServer;

public class LoaderTest extends TestCase {

	private Loader sut;

	private MockHttpServer httpServer;

	@Override
	protected void setUp() {
		sut = new Loader();

		startHttpServer();
		httpServer.expect(MockHttpServer.Method.GET, "/report.json").respondWith(200, "application/json", "{ }");
	}

	@Override
	protected void tearDown() {
		stopHttpServer();
	}

	public void testLoadingByInvalidUrl() {
		sut.load("invalid url");

		Assert.assertTrue(sut.hasExceptionOccurred());
		Assert.assertTrue(sut.getException().getCause() instanceof MalformedURLException);
	}

	public void testLoadingByNonResolvableUrl() {
		sut.load("http://localhost:8080/non_existing_report.json");

		Assert.assertTrue(sut.hasExceptionOccurred());
		Assert.assertTrue(sut.getException().getCause() instanceof IOException);
	}

	public void testLoadingByResolvableUrl() {
		JsonNode report = sut.load("http://localhost:8080/report.json");

		Assert.assertFalse(sut.hasExceptionOccurred());
		Assert.assertNotNull(report);
		Assert.assertTrue(report.isObject());
	}

	private void startHttpServer() {
		httpServer = new MockHttpServer(8080);
		try {
			httpServer.start();
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	private void stopHttpServer() {
		try {
			httpServer.stop();
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

}