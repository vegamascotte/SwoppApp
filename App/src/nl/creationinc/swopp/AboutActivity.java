package nl.creationinc.swopp;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.os.Build;

public class AboutActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		}
	public void openBrowser(View view){

	    //Get url from tag
	    String url = (String)view.getTag();

	    Intent intent = new Intent();
	    intent.setAction(Intent.ACTION_VIEW);
	    intent.addCategory(Intent.CATEGORY_BROWSABLE);

	    //pass the url to intent data
	    intent.setData(Uri.parse(url));

	    startActivity(intent);
	}

}
