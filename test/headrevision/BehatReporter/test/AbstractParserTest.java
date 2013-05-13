package headrevision.BehatReporter.test;

import java.io.IOException;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractParserTest extends TestCase {

	protected JsonNode getJsonNode(String json) {
		JsonNode jsonNode = null;

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			jsonNode = objectMapper.readTree(json);
		} catch (IOException e) {
			Assert.fail(e.getMessage());
		}

		return jsonNode;
	}

}