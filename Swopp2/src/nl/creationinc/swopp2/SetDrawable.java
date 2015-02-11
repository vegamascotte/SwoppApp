package nl.creationinc.swopp2;

import java.io.InputStream;
import java.net.URL;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import com.parse.ParseObject;

public class SetDrawable extends AsyncTask<Object, Void, Drawable>
{
	@Override
	protected Drawable doInBackground(Object... params)
	{
		try
		{
			ParseObject obj = (ParseObject) params[0];
			String url = obj.getParseFile("image").getUrl();
			String className = obj.getClassName();
			InputStream is = (InputStream) new URL(url).getContent();
			return Drawable.createFromStream(is, className);
		} catch (Exception e)
		{
			return null;
		}
	}
}