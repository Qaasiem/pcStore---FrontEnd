package org.qaasiems.pcStore.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.qaasiems.pcStore.R;

public class HardwareExDrive extends AppCompatActivity {

    private Button back;
    private Button order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hardware_ex_drive);

        back = (Button) findViewById(R.id.btnBack);
        order = (Button) findViewById(R.id.btnOrder);

        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i = new Intent(HardwareExDrive.this, PlaceOrder.class);
                finish();
                startActivity(i);
            }
        });

        order.setOnClickListener(new View.OnClickListener() {

            public void onClick(final View v) {

                AlertDialog.Builder adb = new AlertDialog.Builder(HardwareExDrive.this);
                adb.setTitle("ORDER SUCCESSFUL");
                adb.setMessage("Your order has been placed. Delivery will be in within 3 days, have you cash ready.");

                adb.setPositiveButton("OK", new AlertDialog.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        TextView text = (TextView) v;
                        Intent i = new Intent(HardwareExDrive.this, HomeActivity.class);
                        finish();
                        startActivity(i);
                    }
                });

                adb.show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hardware_ex_drive, menu);
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

        Intent i = new Intent(HardwareExDrive.this, HomeActivity.class);
        finish();
        startActivity(i);
    }
}
