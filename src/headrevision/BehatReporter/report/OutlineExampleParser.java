package headrevision.BehatReporter.report;

import headrevision.BehatReporter.json.ParserException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.base.Joiner;

public class OutlineExampleParser extends ItemParser {

	public OutlineExampleParser(JsonNode item) {
		super(item, null);
	}

	public boolean isOutlineExample() throws ParserException {
		return has("values") && has("result");
	}

	@Override
	public boolean hasTitle() throws ParserException {
		return false;
	}

	public String parseValues() throws ParserException {
		List<String> values = new ArrayList<String>();

		Map<String,String> valueEntries = parseProperties("values");
		for (Entry<String,String> valueEntry: valueEntries.entrySet()) {
			values.add(valueEntry.getKey() + ": " + valueEntry.getValue());
		}

		return Joiner.on("\n").join(values);
	}

}