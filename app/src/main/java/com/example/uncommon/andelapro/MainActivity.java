package com.example.uncommon.andelapro;

import android.content.Intent;
import android.app.LoaderManager;
import android.os.Bundle;
//import android.support.v4.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Loader;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Javalagos>>{

    public static final String Lagos_Java_Url = "https://api.github.com/search/users?q=location:%22lagos%22+language:%22java%22";
    public static final String Avatar_Url = " Avatar_Url";
    public static final String FirstName = " FirstName";
    public static final String Profile = "Profile";


    public static final int JAVALAG = 1;

    public MyAdapter javalag;

    protected TextView mEmptyStateTextView;

    public static final String LOG_TAGS = QueryLagos.class.getSimpleName();
   // public static final String LOG_TAG = MainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(JAVALAG, null, this);




        //Initializing Adapter Class for javaLagos...
        javalag = new MyAdapter(this, new ArrayList<Javalagos>());
        ListView mylist = (ListView) findViewById(R.id.list_name);
        mylist.setAdapter(javalag);


        //Initializing an Empty View of text to show on list when network fails
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        mylist.setEmptyView(mEmptyStateTextView);

        // Setting up the OnItemClickListener for each item that will be cliked..
        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Javalagos lad = javalag.getItem(position);
                String imaged = lad.getmAvater();
                String firstName = lad.getmNames();
                String git_profile = lad.getmUrllinks();

                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                intent.putExtra(FirstName, firstName);
                intent.putExtra(Avatar_Url, imaged);
                intent.putExtra(Profile, git_profile);

                startActivity(intent);


            }
        });




    }



    @Override
    public Loader<List<Javalagos>> onCreateLoader(int i, Bundle bundle) {
        return new JavaLagosLoader(this, Lagos_Java_Url);
    }

    @Override
    public void onLoadFinished(Loader<List<Javalagos>> loader, List<Javalagos> javalagoses) {
        // Adding up a Progress Spin for this
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        //Indicating A text to be shown when Network Connection Fails
        mEmptyStateTextView.setText(" This page needs an Internet connection.....");


        // When there is a good network checks to to see if values is empty
        if (javalagoses != null && !javalagoses.isEmpty()){
            javalag.addAll(javalagoses);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Javalagos>> loader) {
        javalag.clear();
    }



    /**
     //A nested Class that extends the AsyncTask to run a background thread...
     private class LagosJavaTask extends AsyncTask<String, Void, List<Lads>> {

    @Override
    protected List<Lads> doInBackground(String... urls) {
    // Don't perform the request if there are no URLs, or the first URL is null.
    if (urls.length < 1 || urls[0] == null) {
    return null;
    }

    List<Lads> result = QueryUtils.fetchJavaDevLag(urls[0]);
    return result;


    }

    //Updating the UI with this method below
    @Override
    protected void onPostExecute(List<Lads> data) {
    // Clear the adapter of previous earthquake data
    javalag.clear();

    // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
    // data set. This will trigger the ListView to update.
    if (data != null && !data.isEmpty()) {
    javalag.addAll(data);
    }
    }
    }

     **/
}
