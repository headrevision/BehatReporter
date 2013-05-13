package headrevision.BehatReporter.report;

import android.os.AsyncTask;

import com.fasterxml.jackson.databind.JsonNode;

public class LoaderTask extends AsyncTask<String, Void, JsonNode> {

	private Loader loader;
	private LoaderTaskListener listener;

	public LoaderTask(LoaderTaskListener listener) {
		super();
		this.listener = listener;
	}

	@Override
	protected JsonNode doInBackground(String... urls) {
		String url = urls[0];
		loader = new Loader();

		return loader.load(url);
	}

	@Override
	protected void onPreExecute() {
		listener.onLoaderTaskExecutionBegin();
	}

	@Override
	protected void onPostExecute(JsonNode result) {
		if (loader.hasExceptionOccurred()) {
			listener.onLoaderTaskExecutionFailure(loader.getException());
		} else {
			listener.onLoaderTaskExecutionCompletion(result);
		}
	}

}