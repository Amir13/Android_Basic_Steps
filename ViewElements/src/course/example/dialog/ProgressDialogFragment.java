package course.example.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.os.Bundle;

public class ProgressDialogFragment extends DialogFragment{

	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final ProgressDialog progressdialog =  new ProgressDialog(getActivity());
		progressdialog.setMessage("Activity shutting down");
		progressdialog.setIndeterminate(true);
		return progressdialog;
	}
	
	
}
