package nl.creationinc.swopp2;

import android.app.Activity;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseUser;

public class Clothing extends Activity
{
	int						stackIndex			= 0;
	ParseObject				next				= null;
	GetObjectInBackground	nextObjectLoader	= null;
	GetObjectInBackground	firstObjectLoader	= null;
	SetDrawable				imageLoader			= null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clothing);

		try
		{
			findViewById(R.id.next_button).setOnClickListener(new View.OnClickListener()
				{
					@Override
					public void onClick(View v)
					{
						try
						{
							GetMainImageView().setImageDrawable(null);
							SetLoading();

							// Checks for the next object.
							if (nextObjectLoader != null)
								next = (nextObjectLoader.getStatus() != Status.PENDING) ? nextObjectLoader.get() : next;

							// Checks witch objectLoader to use.
							if (next == null)
								firstObjectLoader = ((GetObjectInBackground) new GetObjectInBackground().execute(stackIndex));
							nextObjectLoader = ((GetObjectInBackground) new GetObjectInBackground().execute(++stackIndex));

							// Gets the object for displaying.
							ParseObject obj = (next == null) ? firstObjectLoader.get() : next;

							// Displays the image in a async task.
							imageLoader = ((SetDrawable) new SetDrawable().execute(obj));

							// Displays the name and the amount of likes on
							// the screen.
							((TextView) findViewById(R.id.textView1)).setText(obj.getString("name"));
							((TextView) findViewById(R.id.likes)).setText("Likes: " + obj.getInt("likes"));
						} catch (NullPointerException e)
						{
							/*
							 * If this is called it means that the objectLoader
							 * was unable to fetch the next object. This is
							 * mainly called when the database is empty or has
							 * no more items.
							 */
							Toast.makeText(getBaseContext(), "These were all of the items", Toast.LENGTH_LONG).show();
						} catch (Exception e)
						{
							/*
							 * If this is called it means that there has been an
							 * unexpected exception in the obejct loading
							 * process.
							 */
							Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
						}
					}
				});

			findViewById(R.id.like_button).setOnClickListener(new View.OnClickListener()
				{
					@Override
					public void onClick(View v)
					{
						try
						{
							// Gets the ParseObject to like.
							ParseObject obj = (next == null) ? firstObjectLoader.get() : next;

							// Gets the last amount of likes adds 1 and
							// sends it to the database.
							int lastAmount = obj.getInt("likes");
							obj.put("likes", lastAmount + 1);
							obj.saveInBackground();

							// Gets the users id.
							String objId = obj.getObjectId();
							String userId = ParseUser.getCurrentUser().getObjectId();

							// Sends the users information to the
							// liked_product table.
							ParseObject Lobj = ParseObject.create("Likes");
							Lobj.put("liked_product", objId);
							Lobj.put("user", userId);
							Lobj.saveInBackground();

							findViewById(R.id.next_button).performClick();
						} catch (Exception e)
						{
							/*
							 * if this is called it means that there has been an
							 * unexpected exception in the obejct likeing
							 * process.
							 */
							Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
						}
					}
				});

			findViewById(R.id.dislike_button).setOnClickListener(new View.OnClickListener()
				{
					@Override
					public void onClick(View v)
					{
						try
						{
							// Gets the ParseObject to like.
							ParseObject obj = (next == null) ? firstObjectLoader.get() : next;

							// Gets the last amount of likes subtracts 1 and
							// sends it to the database.
							int lastAmount = obj.getInt("likes");
							obj.put("likes", lastAmount - 1);
							obj.saveInBackground();

							findViewById(R.id.next_button).performClick();
						} catch (Exception e)
						{
							/*
							 * If this is called it means that there has been an
							 * unexpected exception in the obejct dislikeing
							 * process.
							 */
							Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
						}
					}
				});
		} catch (Exception e)
		{
			/*
			 * * If this is called it means that there has been an unexpected
			 * exception in the making of Clothing.java
			 */
			Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
		}

		final Handler imageStachHandler = new Handler();
		Runnable naampje = new Runnable()
			{
				@Override
				public void run()
				{
					try
					{
						if (imageLoader.getStatus() == Status.FINISHED)
						{
							GetMainImageView().setImageDrawable(imageLoader.get());
							SetDone();
						}
					} catch (NullPointerException nulle)
					{
					} catch (Exception e)
					{
						Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
					}

					imageStachHandler.postDelayed(this, 500);
				}
			};

		findViewById(R.id.next_button).performClick();
		naampje.run();
	}

	private final ImageView GetMainImageView()
	{
		return (ImageView) findViewById(R.id.mainImgView);
	}

	private final void SetLoading()
	{
		findViewById(R.id.progressBar1).setVisibility(1);
		findViewById(R.id.mainImgView).setVisibility(0);
	}

	private final void SetDone()
	{
		findViewById(R.id.progressBar1).setVisibility(View.GONE);
		findViewById(R.id.mainImgView).setVisibility(1);
	}
}
