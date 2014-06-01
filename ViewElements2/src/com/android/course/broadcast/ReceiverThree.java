package com.android.course.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ReceiverThree extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent arg1) {
		Toast.makeText(context, "INTENT RECEIVED by ReceiverThree", Toast.LENGTH_LONG).show();		
	}

}
