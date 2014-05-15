package nl.creationinc.swopp2;

import nl.creationinc.swopp2.LoginActivity.Login;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.PushService;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private String[] mDrawerListViewItems;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerListView;
    private ActionBarDrawerToggle mDrawerToggle;
    
    //AppPreferences _appPrefs;
    int user_id;
	private String username;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Parse.initialize(this, "MAqOnM2QUws3xLjyNsTtxxXchOLW2yCEnZlZmOcO", "GAyWjnwHSJ1rjtbfiaWTFVpiLbxiJCVhFuPa9GFw");
		PushService.setDefaultPushCallback(this, LoginActivity.class);
		ParseInstallation.getCurrentInstallation().saveInBackground();
		PushService.subscribe(getApplicationContext(), "Giants", MainActivity.class);
		
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser == null) {
			Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
            return;
		} else {
			username = currentUser.getUsername();
			//user_id = currentUser.getObjectId();
			String[] items = {
			        username,
			        "About"
			};
        
	        mDrawerListViewItems = items;
			mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
	        mDrawerListView = (ListView) findViewById(R.id.left_drawer);
	        mDrawerListView.setAdapter(new ArrayAdapter<String>(this,
	                R.layout.drawer_listview_item, mDrawerListViewItems));
	        mDrawerToggle = new ActionBarDrawerToggle(
	                this,                  /* host Activity */
	                mDrawerLayout,         /* DrawerLayout object */
	                R.drawable.ic_drawer,  /* nav drawer icon to replace 'Up' caret */
	                R.string.drawer_open,  /* "open drawer" description */
	                R.string.drawer_close  /* "close drawer" description */
	                ){
	        	
	        	/** Called when a drawer has settled in a completely closed state. */
	            public void onDrawerClosed(View view) {
	                super.onDrawerClosed(view);
	                getActionBar().setTitle(R.string.drawer_close);
	            }
	
	            /** Called when a drawer has settled in a completely open state. */
	            public void onDrawerOpened(View drawerView) {
	                super.onDrawerOpened(drawerView);
	                getActionBar().setTitle(R.string.drawer_open);
	            }
	        };
        
	        mDrawerLayout.setDrawerListener(mDrawerToggle);
	        
	        getActionBar().setDisplayHomeAsUpEnabled(true);
	        getActionBar().setHomeButtonEnabled(true);
	        //drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
	        mDrawerListView.setOnItemClickListener(new DrawerItemClickListener());
		}
        
        findViewById(R.id.kleding).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent (MainActivity.this, Clothing.class));
			}
		});
  		findViewById(R.id.sieraden).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//startActivity(new Intent (MainActivity.this, Sieraden.class));
			}
		});
  		findViewById(R.id.accessoires).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//startActivity(new Intent (MainActivity.this, Accessoires.class));
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
		switch (item.getItemId()) {
		    case R.id.log_out:
		    	logout();
		        return true;
		    default:
		        return super.onOptionsItemSelected(item);
	    }
	}
	
	/** Start ActionBarDrawer */
	@Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }
	
	@Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
	
	/*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
          return true;
        }
        
        switch (item.getItemId()) {
		    case R.id.action_settings:
		    	//Intent settings = new Intent(this, SettingsActivity.class);
				//startActivity(settings);
		        return true;
		    case R.id.action_log_out:
		    	signOut();
		    	return true;
		    default:
		        return super.onOptionsItemSelected(item);
        }
    }*/
	
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @SuppressWarnings("rawtypes")
		@Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
        	switch (position) {
		    	case 0:
		    		/*Intent profile = new Intent(MainActivity.this, ProfileActivity.class);
		    		profile.putExtra("userid", user_id);
					startActivity(profile);*/
		    		Toast.makeText(getApplicationContext(), "Profile", Toast.LENGTH_LONG).show();
					break;
		    	case 1:
		    		/*Intent about = new Intent(MainActivity.this, AboutActivity.class);
					startActivity(about);*/
		    		Toast.makeText(getApplicationContext(), "About", Toast.LENGTH_LONG).show();
		    		break;
        	}
        }
    }
	/** End ActionBarDrawer */
	
	public void logout() {
		AlertDialog.Builder alert_box = new AlertDialog.Builder(this);
		alert_box.setMessage("Are you sure that you want to log out?");
		alert_box.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
		   @Override
		   public void onClick(DialogInterface dialog, int which) {
			   LogoutTask lt = new LogoutTask();
			   lt.execute((Void) null);
		   }
		});
		alert_box.setNegativeButton("No", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			    //Toast.makeText(getApplicationContext(), "No Button Clicked", Toast.LENGTH_LONG).show();
			}
		});
		alert_box.show();
	}
	
	public class LogoutTask extends AsyncTask<Void, Void, Boolean> {
		
		@Override
		protected Boolean doInBackground(Void... params) {
			ParseUser.logOut();
			ParseUser currentUser = ParseUser.getCurrentUser(); // this will now be null
			
			if(currentUser == null){
				return true;
			} else {
				return false;
			}
		}
		
		@Override
		protected void onPostExecute(final Boolean success) {

			if (success) {
				Toast.makeText(getApplicationContext(), "Logged out", Toast.LENGTH_LONG).show();
				Intent i = new Intent(MainActivity.this, LoginActivity.class);
	            startActivity(i);
	            finish();
				startActivity(getIntent());
			} else {
				Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
			} 
		}
	}
}
