package nl.creationinc.swopp2;

import com.parse.Parse;

import android.app.Application;

public class BaseApp extends Application
{
	@Override
	public void onCreate()
	{
		super.onCreate();
		Parse.initialize(this, "MAqOnM2QUws3xLjyNsTtxxXchOLW2yCEnZlZmOcO", "GAyWjnwHSJ1rjtbfiaWTFVpiLbxiJCVhFuPa9GFw");
	}
}
