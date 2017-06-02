package com.example.sko.wip;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class Assignment3Gridview extends AppCompatActivity {

    private static final String TAG = Assignment3Gridview.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment3_gridview);

        if (savedInstanceState != null) {
            Log.d(TAG, "onCreate() Restoring previous state");
            /* restore state */
        } else {
            Log.d(TAG, "onCreate() No saved state available");
            /* initialize app */
        }

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(Assignment3Gridview.this, "" + position,
                        Toast.LENGTH_SHORT).show();
                if (position == 0) {
                    //code specific to first list item
                    Intent intent = new Intent(Assignment3Gridview.this,Assignment3RecycleV.class);
                    startActivity(intent);
                }
            }
        });

        FloatingActionButton myFab = (FloatingActionButton)  findViewById(R.id.fab);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Assignment3Gridview.this);
                builder.setTitle("Pick an Animal");
                String[] animals = {"Horse", "Cow", "Camel", "Sheep", "Goat"};
                builder.setItems(animals, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        switch (which) {

                            case 0:
                                Log.d(TAG, "Horse");
                                break;
                            case 1:
                                Log.d(TAG, "Cow");
                                break;
                            case 2:
                                Log.d(TAG, "Camel");
                                break;
                            case 3:
                                Log.d(TAG, "Sheep");
                                break;
                            case 4:
                                Log.d(TAG, "Goat");
                                break;
                        }
                        }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

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