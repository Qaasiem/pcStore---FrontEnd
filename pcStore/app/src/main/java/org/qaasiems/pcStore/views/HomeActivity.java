package org.qaasiems.pcStore.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.qaasiems.pcStore.R;

public class HomeActivity extends Activity {

    private Button viewInventory;
    private Button addHardware;
    private Button order;
    private Button logout;
    private Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewInventory = (Button)findViewById(R.id.btnViewInventory);
        addHardware = (Button)findViewById(R.id.btnAddHardware);
        delete = (Button)findViewById(R.id.btnDelete);
        order = (Button)findViewById(R.id.btnOrder);
        logout = (Button)findViewById(R.id.btnLogout);

        viewInventory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent i = new Intent(HomeActivity.this, ViewHardware.class);
                startActivity(i);
            }
        });

        addHardware.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent i = new Intent(HomeActivity.this, AddHardware.class);
                startActivity(i);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent i = new Intent(HomeActivity.this, DeleteHardware.class);
                startActivity(i);
            }
        });

        order.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent i = new Intent(HomeActivity.this, PlaceOrder.class);
                startActivity(i);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent i = new Intent(HomeActivity.this, MainActivity.class);
                finish();
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        Intent i = new Intent(HomeActivity.this, MainActivity.class);
        finish();
        startActivity(i);
    }
}
