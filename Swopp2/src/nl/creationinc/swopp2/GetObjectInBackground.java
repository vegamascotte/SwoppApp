package nl.creationinc.swopp2;

import android.os.AsyncTask;

import com.parse.ParseObject;
import com.parse.ParseQuery;

public class GetObjectInBackground extends AsyncTask<Integer, Void, ParseObject>
{
	@Override
	protected ParseObject doInBackground(Integer... params) {
		try {
			ParseQuery<ParseObject> query = ParseQuery.getQuery("Products")
					.whereEqualTo("stack", params[0]);
			return query.getFirst();
		}
		catch (Exception e) {
			return null;
		}
	}
}
