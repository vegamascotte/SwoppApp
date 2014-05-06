package nl.creationinc.swopp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class menu extends Activity {

	private String[] drawerListViewItems;
    private DrawerLayout drawerLayout;
    private ListView drawerListView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		String[] items = {
		        "Piet",
		        "About"
		    };
		drawerListViewItems = items;
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
        
  		findViewById(R.id.kleding).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent ("nl.creationinc.swopp.KLEDING"));
			}
		});
  		findViewById(R.id.sieraden).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent ("nl.creationinc.swopp.SIERADEN"));
			}
		});
  		findViewById(R.id.accessoires).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent ("nl.creationinc.swopp.ACCESSOIRES"));
			}
		});
  		findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent (menu.this, LoginActivity.class));
			}
		});
	}
	
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
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
}