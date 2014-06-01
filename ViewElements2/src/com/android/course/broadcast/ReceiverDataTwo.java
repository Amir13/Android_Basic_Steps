package com.android.course.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ReceiverDataTwo extends BroadcastReceiver{

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		String result = getResultData() == null ? "ReceiverDataTwo ->" : getResultData() + " ReceiverDataTwo ->";
		setResultData(result);		
	}

}
