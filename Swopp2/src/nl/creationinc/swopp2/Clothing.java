package nl.creationinc.swopp2;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

public class Clothing extends Activity
{
	int stackIndex = 0;
	int index = -1;
	swopObject[] swopObjects = new swopObject[10];

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clothing);
		swopObjects[9] = new swopObject();

		ParseQuery<ParseObject> query = ParseQuery.getQuery("Products");
		try
		{
			while (true)
				FillArray(query);
		} catch (Exception e)
		{
			Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG)
					.show();
		}
	}

	protected void FillArray(ParseQuery<ParseObject> query)
	{
		try
		{
			if (index != -1)
				SetImage(swopObjects[0].getImgUrl(), "Products");

			if (HasEmptySpot())
			{
				query.whereEqualTo("stack", stackIndex);
				query.findInBackground(new FindCallback<ParseObject>()
				{
					@Override
					public void done(List<ParseObject> objects, ParseException e)
					{
						stackIndex++;
						index = 0;

						for (int i = 0; i < swopObjects.length || i < objects.size(); i++)
						{
							try
							{
								swopObjects[i] = new swopObject(objects.get(i));
							} catch (Exception ex)
							{
								Toast.makeText(getBaseContext(), ex.toString(),
										Toast.LENGTH_LONG).show();
							}
						}
					}
				});
			}
		} catch (Exception e)
		{
			Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG)
					.show();
		}
	}

	protected boolean HasEmptySpot()
	{
		for (swopObject cur : swopObjects)
		{
			if (cur == null)
				return true;
		}
		return false;
	}

	protected void SetImage(String url, String className)
	{
		String[] params =
		{ url, className };
		try
		{
			Drawable image = ((Utility) new Utility().execute(params)).get();
			((ImageView) findViewById(R.id.mainImgView))
					.setImageDrawable(image);
		} catch (InterruptedException e)
		{
			Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG)
					.show();
		} catch (ExecutionException e)
		{
			Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG)
					.show();
		}
	}
}
