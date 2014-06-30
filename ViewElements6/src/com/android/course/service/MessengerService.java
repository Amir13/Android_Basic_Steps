package com.android.course.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;

public class MessengerService extends Service {

	// Messenger that receives messages from clients
	final Messenger messenger = new Messenger(new IncomingMessagesHanlder());
	public static final int LOG_MSG = 1;
	private static String MESSAGE_KEY = "Key";

	private static String receivedMessage = null;

	// Hanlder for incoming messages
	static class IncomingMessagesHanlder extends Handler {

		@Override
		public void handleMessage(Message msg) {

			switch (msg.what) {
			case LOG_MSG:
				receivedMessage = msg.getData().getString(MESSAGE_KEY);
				Log.i("IncomingMessagesHanlder", receivedMessage);
				break;

			default:
				break;
			}

			super.handleMessage(msg);
		}

	}

	// Returns the messenger binder to allow the client send messages
	@Override
	public IBinder onBind(Intent intent) {
		return messenger.getBinder();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(getApplicationContext(), receivedMessage,
				Toast.LENGTH_LONG).show();
		return super.onStartCommand(intent, flags, startId);
	}

}
