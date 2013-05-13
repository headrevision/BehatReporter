package headrevision.BehatReporter.report;

import com.fasterxml.jackson.databind.JsonNode;

public interface LoaderTaskListener {

	public void onLoaderTaskExecutionBegin();

	public void onLoaderTaskExecutionCompletion(JsonNode reportJson);

	public void onLoaderTaskExecutionFailure(LoaderException exception);

}