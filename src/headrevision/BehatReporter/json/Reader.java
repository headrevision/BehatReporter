package headrevision.BehatReporter.json;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Closeables;

public class Reader {

	private InputStream jsonStream;

	public Reader(InputStream jsonStream) {
		this.jsonStream = jsonStream;
	}

	@SuppressWarnings("deprecation")
	public JsonNode read() throws ReaderException {
		JsonNode jsonNode = null;

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			jsonNode = objectMapper.readTree(jsonStream);
		} catch (JsonParseException e) {
			throw (new ReaderException(e));
		} catch (JsonProcessingException e) {
			throw (new ReaderException(e));
		} catch (IOException e) {
			throw (new ReaderException(e));
		} finally {
			Closeables.closeQuietly(jsonStream);
		}

		return jsonNode;
	}

}