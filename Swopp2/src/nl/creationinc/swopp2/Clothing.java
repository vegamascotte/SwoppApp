package nl.creationinc.swopp2;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.PushService;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.os.Build;

public class Clothing extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clothing);
		
		//database connection
		Parse.initialize(this, "MAqOnM2QUws3xLjyNsTtxxXchOLW2yCEnZlZmOcO", "GAyWjnwHSJ1rjtbfiaWTFVpiLbxiJCVhFuPa9GFw");
        ParseAnalytics.trackAppOpened(getIntent());
        //PushService.setDefaultPushCallback(this, MainActivity.class);
        
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Products");
        query.getInBackground("5nVfgKvqkM", new GetCallback<ParseObject>() {
        	  public void done(ParseObject object, ParseException e) {
        		    if (e == null) {
        		    	Toast.makeText(getApplicationContext(), e.toString(), 5000);
        		      // object will be your game score
        		    } else {
        		    	Toast.makeText(getApplicationContext(), e.toString(), 5000);
        		      // something went wrong
        		    }
        		  }
        		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.clothing, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
