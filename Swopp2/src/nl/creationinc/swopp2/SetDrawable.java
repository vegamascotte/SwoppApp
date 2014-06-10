package nl.creationinc.swopp2;

import java.io.InputStream;
import java.net.URL;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.DownloadManager.Query;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.Toast;

public class SetDrawable extends AsyncTask<String, Void, Drawable>
{
	@Override
	protected Drawable doInBackground(String... params) {
		try {
			InputStream is = (InputStream) new URL(params[0]).getContent();
			return Drawable.createFromStream(is, params[1]);
		}
		catch (Exception e) {
			return null;
		}
	}
}