package nl.creationinc.swopp2;

import java.io.InputStream;
import java.net.URL;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.Toast;

public class Utility extends AsyncTask<String, Void, Drawable>
{
	private Drawable d;
	
	@Override
	protected Drawable doInBackground(String... params)
	{
		try
		{
			InputStream is = (InputStream) new URL(params[0]).getContent();
			d = Drawable.createFromStream(is, params[1]);
			return d;
		} catch (Exception e)
		{
			Toast.makeText(null, e.toString(), Toast.LENGTH_LONG).show();
			return null;
		}
	}
}
