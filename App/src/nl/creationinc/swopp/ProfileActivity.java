package nl.creationinc.swopp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends Activity {

	AppPreferences _appPrefs;
	private int mUserId;
	private String mUserName;
	private String mPhone; 
	TextView userName;
	TextView phone;
	ImageView pic;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		
		userName = (TextView) findViewById(R.id.tvUsername);
		phone = (TextView) findViewById(R.id.tvUsername2);
		pic = (ImageView) findViewById(R.id.ivProfilePicture);
		
		//getUserPrefs();
	}
	
	private void getUserPrefs(){
		Intent myIntent = getIntent();
		
		_appPrefs = new AppPreferences(getApplicationContext());
		int id = _appPrefs.getUserId();
		
		if(myIntent.getIntExtra("userid", 0) == id) {
			mUserId = id;
			//mUserName.setText(_appPrefs.getUserName());
			phone.setText(_appPrefs.getUserEmail());
			//user_id = Integer.parseInt(myIntent.getStringExtra("userid"));
			//new SummaryAsyncTask().execute((Void) null);
		} else {
			
		}
	}
}
