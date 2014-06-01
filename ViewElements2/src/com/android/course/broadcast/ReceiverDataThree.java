package com.android.course.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ReceiverDataThree extends BroadcastReceiver{

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		String result = getResultData() == null ? "ReceiverDataThree ->" : getResultData() + " ReceiverDataThree ->";
		setResultData(result);		
	}

}
