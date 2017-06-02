package com.example.sko.wip;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class About extends AppCompatActivity {
    private static final String TAG = About.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitle("About");

        if (savedInstanceState != null) {
            Log.d(TAG, "onCreate() Restoring previous state");
            /* restore state */
        } else {
            Log.d(TAG, "onCreate() No saved state available");
            /* initialize app */
        }

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        assert getSupportActionBar() != null;
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
