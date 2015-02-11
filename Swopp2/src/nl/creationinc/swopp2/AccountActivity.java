package nl.creationinc.swopp2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class AccountActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		try
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_account);

			ParseUser user = ParseUser.getCurrentUser();

			((TextView) findViewById(R.id.Name)).setText(user.getUsername());

			((TextView) findViewById(R.id.About)).setText(String.valueOf(user.getEmail()));
		} catch (Exception e)
		{
			Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}
}
