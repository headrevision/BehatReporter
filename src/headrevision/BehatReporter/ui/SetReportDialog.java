package headrevision.BehatReporter.ui;

import headrevision.BehatReporter.R;
import headrevision.BehatReporter.store.ReportUrl;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class SetReportDialog extends DialogFragment {

	private static SetReportDialogListener listener;

	private AlertDialog.Builder builder;

	private View builderView;

	public static SetReportDialog newInstance(SetReportDialogListener listener) {
		SetReportDialog.listener = listener;

		return new SetReportDialog();
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.dialog_title);

		setView();
		setHandlers();

		return builder.create();
	}

	private void setView() {
		LayoutInflater inflater = getActivity().getLayoutInflater();
		builderView = inflater.inflate(R.layout.set_report_dialog, null);
		EditText urlField = (EditText) ((ViewGroup) builderView).getChildAt(0);
		urlField.setText(ReportUrl.getInstance(getActivity()).retrieve());

		builder.setView(builderView);
	}

	private void setHandlers() {
		builder.setPositiveButton(R.string.dialog_positive, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				String reportUrl = SetReportDialog.this.getReportUrl();
				SetReportDialog.listener.onSetReportDialogPositiveClick(reportUrl);
			}
		});

		builder.setNegativeButton(R.string.dialog_negative, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				SetReportDialog.this.getDialog().cancel();
				SetReportDialog.listener.onSetReportDialogNegativeClick();
			}
		});
	}

	private String getReportUrl() {
		EditText reportUrlField = (EditText) builderView.findViewById(R.id.url);
		Editable reportUrl = reportUrlField.getText();

		return reportUrl.toString();
	}

}