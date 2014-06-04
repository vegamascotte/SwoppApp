package nl.creationinc.swopp2;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;
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
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Clothing extends Activity
{
	int stackIndex = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clothing);

		try {
			findViewById(R.id.next_button).setOnClickListener(
					new View.OnClickListener()
					{
						@Override
						public void onClick(View v) {
							try {
								swopObject obj = UpdateStack();
								SetSwopObject(obj.getImgUrl(), obj.getClass()
										.getName());
								((TextView) findViewById(R.id.textView1))
										.setText(obj.getName());
								stackIndex++;
							}
							catch (Exception e) {
								Toast.makeText(getBaseContext(), e.toString(),
										Toast.LENGTH_LONG).show();
							}
						}
					});
		}
		catch (Exception e) {
			Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG)
					.show();
		}

		findViewById(R.id.next_button).performClick();
	}

	protected swopObject UpdateStack() throws ParseException {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Products").whereEqualTo("stack", stackIndex);
		return new swopObject(query.getFirst());
	}

	protected void SetSwopObject(String url, String className) {
		String[] params = { url, className };
		try {
			Drawable image = ((Utility) new Utility().execute(params)).get();
			((ImageView) findViewById(R.id.mainImgView))
					.setImageDrawable(image);
		}
		catch (InterruptedException e) {
			Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG)
					.show();
		}
		catch (ExecutionException e) {
			Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG)
					.show();
		}
	}
}
