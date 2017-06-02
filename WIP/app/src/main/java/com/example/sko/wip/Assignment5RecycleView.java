package com.example.sko.wip;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.widget.*;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Assignment5RecycleView extends AppCompatActivity {
    private static final String TAG = Assignment5RecycleView.class.getSimpleName();

    //Timeout in ms.
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private android.support.v7.widget.RecyclerView mRVNext;
    private Assignment5Network mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment5_recycle_view);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        assert getSupportActionBar() != null;
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        new AsyncFetch().execute();
        if (!wifiConnectivity()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(Assignment5RecycleView.this);
            builder.setIcon(android.R.drawable.sym_def_app_icon);
            builder.setMessage("Can't connect to internet. :(");
            builder.setTitle("Connectivity Issues");

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    Log.d(TAG, "User clicked OK dialog box");
                    dialogInterface.dismiss();
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();


        }

        if (savedInstanceState != null) {
            Log.d(TAG, "onCreate() Restoring previous state");
            /* restore state */
        } else {
            Log.d(TAG, "onCreate() No saved state available");
            /* initialize app */
        }




    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    private class AsyncFetch extends AsyncTask<String, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(Assignment5RecycleView.this);
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }

        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your json file resides
                // Even you can make call to php file which returns json data
                url = new URL("http://souchi.icoolshow.net/android/Android.json");

            } catch (MalformedURLException e) {

                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }


            try {


                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("GET");

                // setDoOutput to true as we recieve data from json file
                conn.setDoOutput(true);

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return e1.toString();
            }
            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return (result.toString());

                }

                else {


                    AlertDialog.Builder builder = new AlertDialog.Builder(Assignment5RecycleView.this);

                    // 2. Chain together various setter methods to set the dialog characteristics
                    builder.setIcon(android.R.drawable.sym_def_app_icon);
                    builder.setMessage("Can't connect to internet. :(");
                    builder.setTitle("Connectivity Issues");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            // Log.d(TAG, "User clicked OK dialog box");
                            dialogInterface.dismiss();
                        }
                    });
                    builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Log.d(TAG, "User clicked Dismiss dialog box");
                            dialogInterface.dismiss();
                        }
                    });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();







                    return ("unsuccessful");
                }


            }
            catch (IOException e) {

                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }
        }



    @Override
    protected void onPostExecute(String result) {

        //this method will be running on UI thread

        pdLoading.dismiss();
        List<DataList> data = new ArrayList<>();

        pdLoading.dismiss();
        try {

            JSONArray jArray = new JSONArray(result);

            // Extract data from json and store into ArrayList as class objects
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);
                DataList numData = new DataList();
                numData.numImage = json_data.getString("num_img");
                numData.numName = json_data.getString("num_name");
                numData.numNext = json_data.getString("num_next");
                data.add(numData);
            }

            // Setup and Handover data to recyclerview
            mRVNext = (android.support.v7.widget.RecyclerView) findViewById(R.id.numNextList);
            mAdapter = new Assignment5Network(Assignment5RecycleView.this, data);
            mRVNext.setAdapter(mAdapter);
            mRVNext.setLayoutManager(new LinearLayoutManager(Assignment5RecycleView.this));

        } catch (JSONException e) {
            Toast.makeText(Assignment5RecycleView.this, e.toString(), Toast.LENGTH_LONG).show();
        }

        }
    }
    private boolean wifiConnectivity() {
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && (ConnectivityManager.TYPE_WIFI == networkInfo.getType()) && networkInfo.isConnected();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_about:
                Intent intent = new Intent(this, About.class);
                startActivity(intent);
                return true;

            case R.id.action_assignment3:
                Intent intent_assign3 = new Intent(this,Assignment3RecycleV.class);
                startActivity(intent_assign3);
                return true;

            case R.id.action_assign1:
                Intent intent_assign_1 = new Intent(this, MainActivity.class);
                startActivity(intent_assign_1);
                return true;

            case R.id.action_assign5:
                Intent intent_assign_5 = new Intent(this, Assignment5RecycleView.class);
                startActivity(intent_assign_5);
                return true;

            case R.id.action_assign7:
                Intent intent_assign_7 = new Intent(this, Assignment7.class);
                startActivity(intent_assign_7);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
