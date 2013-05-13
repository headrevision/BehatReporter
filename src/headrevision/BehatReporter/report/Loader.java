package headrevision.BehatReporter.report;

import headrevision.BehatReporter.json.Reader;
import headrevision.BehatReporter.json.ReaderException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;

public class Loader {

	private LoaderException occurredException;

	public JsonNode load(String url) {
		JsonNode reportJson = null;

		InputStream reportJsonStream = null;
		try {
			reportJsonStream = getJsonStream(url);
		} catch (LoaderException e) {
			occurredException = e;
			return reportJson;
		}

		Reader jsonReader = new Reader(reportJsonStream);
		try {
			reportJson = jsonReader.read();
		} catch (ReaderException e) {
			occurredException = new LoaderException(e);
		}

		return reportJson;
	}

	public boolean hasExceptionOccurred() {
		return (occurredException != null);
	}

	public LoaderException getException() {
		return occurredException;
	}

	private InputStream getJsonStream(String url) throws LoaderException {
		InputStream jsonStream = null;

		try {
			jsonStream = (new URL(url)).openStream();
		} catch (MalformedURLException e) {
			throw (new LoaderException(e));
		} catch (IOException e) {
			throw (new LoaderException(e));
		}

		return jsonStream;
	}

}