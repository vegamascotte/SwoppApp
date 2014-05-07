package nl.creationinc.swopp;

import nl.creationinc.swopp.R;
import nl.creationinc.swopp.menu;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class menu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		
<<<<<<< HEAD
		// Check if user is logged in
		/*_appPrefs = new AppPreferences(getApplicationContext());
		String userName = _appPrefs.getUserName();
	   
		if(userName == null) {
			Intent i = new Intent(menu.this, LoginActivity.class);
            startActivity(i);
            finish();
            return;
		} else {
			username = userName;
			user_id = _appPrefs.getUserId();
			String[] items = {
			        username,
			        "About"
			    };*/
		
		String[] items = {
		        "Gebruiker",
		        "About"
		    };
		
			drawerListViewItems = items;
		
			setContentView(R.layout.activity_main);
			
	        drawerListView = (ListView) findViewById(R.id.left_drawer);
	        drawerListView.setAdapter(new ArrayAdapter<String>(this,
	                R.layout.drawer_listview_item, drawerListViewItems));
	        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
	        actionBarDrawerToggle = new ActionBarDrawerToggle(
	                this,                  /* host Activity */
	                drawerLayout,         /* DrawerLayout object */
	                R.drawable.ic_drawer,  /* nav drawer icon to replace 'Up' caret */
	                R.string.drawer_open,  /* "open drawer" description */
	                R.string.drawer_close  /* "close drawer" description */
	                );
	        drawerLayout.setDrawerListener(actionBarDrawerToggle);
	        getActionBar().setDisplayHomeAsUpEnabled(true);
	        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
	        drawerListView.setOnItemClickListener(new DrawerItemClickListener());
		//}
		
  		findViewById(R.id.kleding).setOnClickListener(new View.OnClickListener() {
=======
		//Button references
		Button wal1 = (Button) findViewById(R.id.kleding);
		Button wal2 = (Button) findViewById(R.id.sieraden);
		Button wal3 = (Button) findViewById(R.id.accessoires);
		wal1.setOnClickListener(new View.OnClickListener() {
>>>>>>> origin/Design
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent ("nl.creationinc.swopp.KLEDING"));
			}
		});
		wal2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent ("nl.creationinc.swopp.SIERADEN"));
			}
		});
		wal3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent ("nl.creationinc.swopp.ACCESSOIRES"));
			}
		});
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
}