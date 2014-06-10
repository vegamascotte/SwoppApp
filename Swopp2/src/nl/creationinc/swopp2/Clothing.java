package nl.creationinc.swopp2;

import java.util.concurrent.ExecutionException;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Clothing extends Activity
{
	int stackIndex = 0;
	swopObject next = null;
	GetObjectInBackground nObj = null;
	GetObjectInBackground Obj = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clothing);

		try {
			findViewById(R.id.next_button).setOnClickListener(
					new View.OnClickListener()
					{
						@Override
						public void onClick(View v) {
							try {
								Obj = ((GetObjectInBackground) new GetObjectInBackground()
										.execute(stackIndex));
								nObj = ((GetObjectInBackground) new GetObjectInBackground()
										.execute(++stackIndex));
								swopObject obj = (next == null) ? Obj.get()
										: next;

								SetSwopObject(obj.getImgUrl(), obj.getClass()
										.getName());
								((TextView) findViewById(R.id.textView1))
										.setText(obj.getName());

								next = nObj.get();
							}
							catch(NullPointerException e){
								stackIndex = 0;
								findViewById(R.id.next_button).performClick();
							}
							catch (Exception e) {
								Toast.makeText(getBaseContext(), e.toString(),
										Toast.LENGTH_LONG).show();
							}
						}
					});
		}
		catch (Exception e) {
			Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG)
					.show();
		}

		findViewById(R.id.next_button).performClick();
	}

	protected void SetSwopObject(String url, String className) {
		String[] params = { url, className };
		try {
			Drawable image = ((SetDrawable) new SetDrawable().execute(params))
					.get();
			((ImageView) findViewById(R.id.mainImgView))
					.setImageDrawable(image);
		}
		catch (InterruptedException e) {
			Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG)
					.show();
		}
		catch (ExecutionException e) {
			Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG)
					.show();
		}
		catch (Exception e) {
			Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG)
					.show();
		}
	}
}
