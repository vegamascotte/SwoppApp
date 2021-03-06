package nl.creationinc.swopp2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class SplashActivity extends Activity
{

	private static int	SPLASH_TIME_OUT	= 2000;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		getActionBar().hide();

		setContentView(R.layout.activity_splash);

		new Handler().postDelayed(new Runnable()
			{

				@Override
				public void run()
				{
					Intent i = new Intent(SplashActivity.this, MainActivity.class);
					startActivity(i);

					finish();
				}
			}, SPLASH_TIME_OUT);
	}
}
