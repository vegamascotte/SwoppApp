package nl.creationinc.swopp2;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Clothing extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clothing);//database connection
		//Parse.initialize(this, "MAqOnM2QUws3xLjyNsTtxxXchOLW2yCEnZlZmOcO", "GAyWjnwHSJ1rjtbfiaWTFVpiLbxiJCVhFuPa9GFw");
        
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Products");
        query.getInBackground("5nVfgKvqkM", new GetCallback<ParseObject>() {
        	public void done(ParseObject object, ParseException e) {
        		if (e == null) {
        		 // object will be your game score
        		} else {
        			Toast.makeText(getBaseContext(), e.toString(), 5000);
        		 // something went wrong
        		}
        	}
        });
	}
}
