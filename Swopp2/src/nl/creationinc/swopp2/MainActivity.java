package nl.creationinc.swopp2;

import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.PushService;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
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
		
		//database connection
		Parse.initialize(this, "MAqOnM2QUws3xLjyNsTtxxXchOLW2yCEnZlZmOcO", "GAyWjnwHSJ1rjtbfiaWTFVpiLbxiJCVhFuPa9GFw");
        ParseAnalytics.trackAppOpened(getIntent());
        //PushService.setDefaultPushCallback(this, MainActivity.class);
        
        
        
		String[] items = {
	        "User",
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
        
        findViewById(R.id.clothing).setOnClickListener(new View.OnClickListener() {
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
}
