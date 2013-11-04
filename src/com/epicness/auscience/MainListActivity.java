package com.epicness.auscience;

import java.net.MalformedURLException;
import java.net.URL;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class MainListActivity extends ListActivity {
	
	protected String[] mBlogPostTitles;
	public static final int NUMBER_OF_POSTS = 20;
	public static final String TAG = MainListActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
        try{
        	URL blogFeedUrl = new URL("http://blog.teamtreehouse.com/api/get_recent_summary/?count=" + NUMBER_OF_POSTS);
        }
        catch(MalformedURLException e){
        	Log.e(TAG, "Exception caught: ", e);
        }
        //String message = getString(R.string.no_items);
        //Toast.makeText(this, message, Toast.LENGTH_LONG);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_list, menu);
        return true;
    }
    
}
