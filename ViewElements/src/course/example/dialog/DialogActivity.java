package course.example.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import course.example.main.R;

public class DialogActivity extends Activity {
	private static final int ALERTTAG = 0, PROGRESSTAG = 1;
	private Button button = null;
	private DialogFragment dialog = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialog);

		button = (Button) findViewById(R.id.buttonUiDialog);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				showDialogFragment(ALERTTAG);
			}
		});
	}

	protected void showDialogFragment(int dialogFragment) {
		switch (dialogFragment) {
		case ALERTTAG:
			dialog = AlertDialogFragment.newInstance();
			dialog.show(getFragmentManager(), "Alert");
			break;
		case PROGRESSTAG:
			dialog = new ProgressDialogFragment();
			dialog.show(getFragmentManager(), "Shutdown");
			break;
		}
	}

	protected void continueShutdown(boolean continuation) {
		if (continuation) {
//			button.setEnabled(false);
			showDialogFragment(PROGRESSTAG);
			finishShutdown();
		} else {
			dialog.dismiss();
		}
	}

	private void finishShutdown() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(5000);
				} catch (Exception e) {
					Log.i("DialogActivity: ", e.toString());
				} finally {
					finish();
				}
			}
		});
	}

	public static class AlertDialogFragment extends DialogFragment {

		public static AlertDialogFragment newInstance() {
			return new AlertDialogFragment();
		}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {

			return new AlertDialog.Builder(getActivity())
					.setMessage("Do you really want to exti")
					.setCancelable(false)
					.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface arg0,
										int arg1) {
									((DialogActivity) getActivity())
											.continueShutdown(true);
								}

							})
					.setNegativeButton("No",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface arg0,
										int arg1) {
									((DialogActivity) getActivity())
											.continueShutdown(false);
								}

							}).create();
		}
	}

}
