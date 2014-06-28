package com.android.course.contentProvider;

import java.io.FileNotFoundException;
import java.io.InputStream;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract.Contacts;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import com.android.course.main.R;

public class ContactListAdapter extends ResourceCursorAdapter {

	private Bitmap defaultPicture;

	public ContactListAdapter(Context context, int layout, Cursor c, int flags) {
		super(context, layout, c, flags);

		// default thumbnail photo
		defaultPicture = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.default_image);
	}

	// Create and return a new contact data view
	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		return inflater.inflate(R.layout.activity_content_provider, parent,
				false);
	}

	// Update and return a contact data view
	@Override
	public void bindView(View view, Context context, Cursor cursor) {

		TextView textView = (TextView) view.findViewById(R.id.name);
		textView.setText(cursor.getString(cursor
				.getColumnIndex(Contacts.DISPLAY_NAME)));

		// default thumbnail photo
		ImageView imageView = (ImageView) view.findViewById(R.id.photo);
		Bitmap photoBitmap = defaultPicture;

		// Get actual thumbnail photo if it exists
		String photoUri = cursor.getString(cursor
				.getColumnIndex(Contacts.PHOTO_THUMBNAIL_URI));

		if (photoUri != null) {
			InputStream is = null;

			try {
				is = context.getContentResolver().openInputStream(
						Uri.parse(photoUri));
				
				if (is != null) {
					photoBitmap = BitmapFactory.decodeStream(is);
				}
				
			} catch (FileNotFoundException e) {
				Log.e("ContactListAdapter", e.getMessage());
			}
		}

		imageView.setImageBitmap(photoBitmap);
		
	}

}
