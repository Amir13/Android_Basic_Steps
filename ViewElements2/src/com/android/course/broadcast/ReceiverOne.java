package com.android.course.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.widget.Toast;

public class ReceiverOne extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent arg1) {
		
		Vibrator v = (Vibrator) context
				.getSystemService(Context.VIBRATOR_SERVICE);
		v.vibrate(500);

		Toast.makeText(context, "INTENT RECEIVED by ReceiverOne, was added programatically", Toast.LENGTH_LONG).show();
		
	}

}