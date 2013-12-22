package com.epicness.auscience;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
=======

import org.json.JSONArray;
import org.json.JSONObject;

>>>>>>> d9f5f5eb1d70935f8de029271bc741f8740055de
import android.app.ListActivity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
<<<<<<< HEAD
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
=======
import android.util.Log;
import android.view.Menu;
>>>>>>> d9f5f5eb1d70935f8de029271bc741f8740055de
import android.widget.Toast;

public class MainListActivity extends ListActivity {
	
	protected String[] mBlogPostTitles;
	public static final int NUMBER_OF_POSTS = 20;
	public static final String TAG = MainListActivity.class.getSimpleName();
<<<<<<< HEAD
	protected JSONObject mBlogData;
	protected ProgressBar mProgressBar;
	
	private final String KEY_TITLE = "title";
	private final String  KEY_AUTHOR = "author";
	
=======

>>>>>>> d9f5f5eb1d70935f8de029271bc741f8740055de
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
        
<<<<<<< HEAD
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar1);
        
        if(isNetworkAvailable()){
        	mProgressBar.setVisibility(View.VISIBLE);
=======
        if(isNetworkAvailable()){
>>>>>>> d9f5f5eb1d70935f8de029271bc741f8740055de
        	GetBlogPostsTask getBlogPostsTask = new GetBlogPostsTask();
        	getBlogPostsTask.execute();
        }
        else{
        	Toast.makeText(this, "Network is unavailable!", Toast.LENGTH_LONG).show();
        }
        //String message = getString(R.string.no_items);
        //Toast.makeText(this, message, Toast.LENGTH_LONG);
    }


    private boolean isNetworkAvailable() {
		ConnectivityManager manager = (ConnectivityManager) 
				getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = manager.getActiveNetworkInfo();
		
		boolean isAvailable = false;
		if(networkInfo != null && networkInfo.isConnected()){
			isAvailable = true;
		}
		return isAvailable;
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_list, menu);
        return true;
    }

	public void handleBlogResponse() {
		mProgressBar.setVisibility(View.INVISIBLE);
		
		if(mBlogData==null){
			updateDisplayForError();
		}
		else{
			try {
				JSONArray jsonPosts = mBlogData.getJSONArray("posts");
				ArrayList<HashMap<String,String>> blogPosts = 
						new ArrayList<HashMap<String,String>>();
				for(int i=0; i<jsonPosts.length();++i){
					JSONObject post = jsonPosts.getJSONObject(i);
					String title = post.getString(KEY_TITLE);
					title = Html.fromHtml(title).toString();
					String author = post.getString(KEY_AUTHOR);
					author = Html.fromHtml(author).toString();
					
					HashMap<String, String> blogPost = new HashMap<String, String>();
					blogPost.put(KEY_TITLE, title);
					blogPost.put(KEY_AUTHOR, author);
					
					blogPosts.add(blogPost);
				}
				
				String[] keys = { KEY_TITLE, KEY_AUTHOR };
				int[] ids = { android.R.id.text1, android.R.id.text2 };
				SimpleAdapter adapter = new SimpleAdapter(this, blogPosts, 
						android.R.layout.simple_expandable_list_item_2, keys, ids);
				setListAdapter(adapter);
			} catch (JSONException e) {
				Log.e(TAG, "Exception caught!", e);
			}
		}
	}


	private void updateDisplayForError() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(getString(R.string.error_title));
		builder.setMessage(getString(R.string.error_message));
		builder.setPositiveButton(android.R.string.ok, null);
		AlertDialog dialog = builder.create();
		dialog.show();
		
		TextView emptyTextView = (TextView) getListView().getEmptyView();
		emptyTextView.setText(getString(R.string.no_items));
	}
    
<<<<<<< HEAD
    private class GetBlogPostsTask extends AsyncTask<Object, Void, JSONObject>{

		@Override
		protected JSONObject doInBackground(Object... arg0) {
			int responseCode = -1;
			JSONObject jsonResponse = null;
			
			try{
	        	URL blogFeedUrl = new URL("http://blog.teamtreehouse.com/api/get_recent_summary/?count=" + NUMBER_OF_POSTS); //http://ausm.org.uk/u56NR5iwMrDb9vXXUK1cUPCTPSC0X4jEld1K8snfbmjYtBZgUUzHJ9NpP2YmdOL/get_recent_posts/?count=
=======
    private class GetBlogPostsTask extends AsyncTask<Object, Void, String>{

		@Override
		protected String doInBackground(Object... arg0) {
			int responseCode = -1;
			try{
	        	URL blogFeedUrl = new URL("http://blog.teamtreehouse.com/api/get_recent_summary/?count=" + NUMBER_OF_POSTS);
>>>>>>> d9f5f5eb1d70935f8de029271bc741f8740055de
	        	HttpURLConnection connection = (HttpURLConnection) blogFeedUrl.openConnection();
	        	connection.connect();
	        	responseCode = connection.getResponseCode();
	        	if(responseCode == HttpURLConnection.HTTP_OK){
	        		InputStream inputStream = connection.getInputStream();
	        		Reader reader = new InputStreamReader(inputStream);
<<<<<<< HEAD
	        		int nextCharacter;
	        		String responseData ="";
	        		while(true){
	        			nextCharacter = reader.read();
	        			if(nextCharacter == -1)
	        				break;
	        			responseData += (char) nextCharacter;
	        		}
	        		
	        		jsonResponse = new JSONObject(responseData);	        		
=======
	        		int contentLength = connection.getContentLength();
	        		char[] charArray = new char[contentLength];
	        		reader.read(charArray);
	        		String responseData = new String(charArray);
//	        		Log.v(TAG, responseData);
	        		
	        		JSONObject jsonResponse = new JSONObject(responseData);
	        		String status = jsonResponse.getString("status");
	        		Log.v(TAG, status);
	        		
	        		JSONArray jsonPosts = jsonResponse.getJSONArray("posts");
	        		for (int i = 0; i < jsonPosts.length(); ++i){
	        			JSONObject jsonPost = jsonPosts.getJSONObject(i);
	        			String title = jsonPost.getString("title");
	        			Log.v(TAG, "Post " + i + ": " + title);
	        		}
>>>>>>> d9f5f5eb1d70935f8de029271bc741f8740055de
	        	}
	        	else{
	        		Log.i(TAG, "Unsuccessful HTTP Response Code: " + responseCode);
	        	}
	        	Log.i(TAG, "Code: " + responseCode);
	        }
	        catch (MalformedURLException e){
	        	Log.e(TAG, "Exception caught: ", e);
	        }
	        catch (IOException e){
	        	Log.e(TAG, "Exception caught: ", e);
	        }
	        catch (Exception e){
	        	Log.e(TAG, "Exception caught: ", e);
	        }
			
<<<<<<< HEAD
			return jsonResponse;
		}
		
		@Override
		protected void onPostExecute(JSONObject result){
			mBlogData = result;
			handleBlogResponse();
=======
			return "Code " + responseCode;
>>>>>>> d9f5f5eb1d70935f8de029271bc741f8740055de
		}
    	
    }
}
