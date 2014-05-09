package nl.creationinc.swopp;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Kleding extends Activity implements AsyncTaskCompleteListener {

	//private TextView textview;
	private ListView listview;
	List<String> listContent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kleding);
        
		listContent = new ArrayList<String>();
		
		new DataBase(Kleding.this).execute();
		
		//textview = (TextView)findViewById(R.id.textView);
		listview = (ListView)findViewById(R.id.productsListView);
	}
	
	@Override
	public void onTaskComplete(String result) {
		System.out.println("calling....");
		System.out.println("result :: "+ result);
		
		
		try {
			JSONObject jsonObject = new JSONObject(result);
			
			JSONArray jsonMainNode = jsonObject.optJSONArray("products");
			
			for(int i = 0; i < jsonMainNode.length(); i++){
				JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
				String id = jsonChildNode.optString("id");
				String name = jsonChildNode.optString("name");
				String description = jsonChildNode.optString("description");
				listContent.add(name + " - " + description);
			}
		} catch (Exception e){
			
		}
		
		String[] simpleArray = new String[ listContent.size() ];
		listContent.toArray( simpleArray );
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		        android.R.layout.simple_list_item_1,
		        simpleArray);
		listview.setAdapter(adapter);
		//textview.setText(result);
	}

}
