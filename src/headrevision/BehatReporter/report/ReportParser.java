package headrevision.BehatReporter.report;

import headrevision.BehatReporter.json.ParserException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.fasterxml.jackson.databind.JsonNode;

public class ReportParser extends ItemParser {

	public ReportParser(JsonNode item) {
		super(item, new FeatureParserFactory());
	}

	@Override
	public boolean hasTitle() throws ParserException {
		return false;
	}

	public Date parseDate() throws ParserException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
		Date date;
		try {
			date = dateFormat.parse(parseText("date"));
		} catch (ParseException e) {
			throw (new ParserException(e));
		}

		return date;
	}

}