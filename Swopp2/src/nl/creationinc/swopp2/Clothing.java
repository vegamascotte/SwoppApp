package nl.creationinc.swopp2;

import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class Clothing extends Activity
{
	int stackIndex = 0;
	ParseObject next = null;
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
								ParseObject obj = (next == null) ? Obj.get()
										: next;

								SetSwopObject(obj.getParseFile("image")
										.getUrl(), obj.getClass().getName());
								((TextView) findViewById(R.id.textView1))
										.setText(obj.getString("name"));
								((TextView) findViewById(R.id.likes))
										.setText( "Likes: " + obj.getInt("likes"));

								next = nObj.get();
							}
							catch (NullPointerException e) {
								Toast.makeText(getBaseContext(),
										"These where all of the items",
										Toast.LENGTH_LONG).show();
							}
							catch (Exception e) {
								Toast.makeText(getBaseContext(), e.toString(),
										Toast.LENGTH_LONG).show();
							}
						}
					});

			findViewById(R.id.like_button).setOnClickListener(
					new View.OnClickListener()
					{
						@Override
						public void onClick(View v) {
							try {
								ParseObject obj = Obj.get();
								int lastAmount = obj.getInt("likes");
								obj.put("likes", lastAmount + 1);
								obj.saveInBackground();

								String objId = obj.getObjectId();
								String userId = ParseUser.getCurrentUser()
										.getObjectId();

								ParseObject Lobj = ParseObject.create("Likes");
								Lobj.put("liked_product", objId);
								Lobj.put("user", userId);
								Lobj.saveInBackground();

								findViewById(R.id.next_button).performClick();
							}
							catch (Exception e) {
								Toast.makeText(getBaseContext(), e.toString(),
										Toast.LENGTH_LONG).show();
							}
						}
					});

			findViewById(R.id.dislike_button).setOnClickListener(
					new View.OnClickListener()
					{
						@Override
						public void onClick(View v) {
							try {
								ParseObject obj = Obj.get();
								int lastAmount = obj.getInt("likes");
								obj.put("likes", lastAmount - 1);
								obj.saveInBackground();

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
