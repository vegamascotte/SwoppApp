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
		
		//Button references
		Button wal1 = (Button) findViewById(R.id.kleding);
		Button wal2 = (Button) findViewById(R.id.sieraden);
		Button wal3 = (Button) findViewById(R.id.accessoires);
		wal1.setOnClickListener(new View.OnClickListener() {
			
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