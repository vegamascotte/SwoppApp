package nl.creationinc.swopp2;

import android.os.AsyncTask;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseQuery;

public class GetObjectInBackground extends AsyncTask<Integer, Void, swopObject>
{
	@Override
	protected swopObject doInBackground(Integer... params) {
		try {
			ParseQuery<ParseObject> query = ParseQuery.getQuery("Products")
					.whereEqualTo("stack", params[0]);
			return new swopObject(query.getFirst());
		}
		catch (Exception e) {
			return null;
		}
	}
}
