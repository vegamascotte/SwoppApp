package nl.creationinc.swopp;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

public class DataBase extends AsyncTask<String, Void, String> {
	
	private Activity activity;
	private ProgressDialog dialog;
	private AsyncTaskCompleteListener callback;
	private String endResult;
	
	public DataBase(Activity act) {
		this.activity = act;
		this.callback = (AsyncTaskCompleteListener)act;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();

		dialog = new ProgressDialog(activity);
		dialog.setMessage("Loading...");
		dialog.show();
	}
	
	@Override
	protected String doInBackground(String... params) {
		try {
			//Create the HTTP request
			HttpParams httpParameters = new BasicHttpParams();

			//Setup timeouts
			HttpConnectionParams.setConnectionTimeout(httpParameters, 15000);
			HttpConnectionParams.setSoTimeout(httpParameters, 15000);			

			HttpClient httpclient = new DefaultHttpClient(httpParameters);
			HttpPost httppost = new HttpPost("http://ilker.teamdevit.com/android/getproducts.php");      
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();

			endResult = EntityUtils.toString(entity);

			// Create a JSON object from the request response
			//JSONObject jsonObject = new JSONObject(result);

			//j = jsonObject;
			//Retrieve the data from the JSON object
			//var retreived = jsonObject.getInt("retreived data");
			//var retreived2 = jsonObject.getString("retreived data2");
			
			/*JSONArray jsonMainNode = jsonObject.optJSONArray("products");
			
			for(int i = 0; i<jsonMainNode.length();i++){
				try{
					JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
					p = jsonChildNode.optString("name");
				} catch(Exception e) {
					//lol
				}
			}*/
		} catch (Exception e) {
			Log.e("Login", "Error:", e);
		}
		return endResult;
	}
	
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		if (null != dialog && dialog.isShowing()) {
			dialog.dismiss();
		}
		callback.onTaskComplete(result);
	}
}
