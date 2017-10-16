package org.qaasiems.pcStore.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.qaasiems.pcStore.R;
import org.qaasiems.pcStore.model.Hardware;
import org.qaasiems.pcStore.services.implementation.HardwareServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class DeleteHardware extends AppCompatActivity {

    private List<Hardware> hardwareList;
    private ArrayList<String> strHardList;
    private ListView hardwareListview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_hardware);

        deleteHardware();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_delete_hardware, menu);
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

        Intent i = new Intent(DeleteHardware.this, HomeActivity.class);
        finish();
        startActivity(i);
    }

    public void deleteHardware() {

        hardwareListview = (ListView) findViewById(R.id.listViewHardware);

        Thread thread = new Thread (new Runnable() {

            @Override
            public void run() {

                HardwareServiceImpl service = new HardwareServiceImpl();

                hardwareList = service.getAllHardwareItems();
                strHardList = new ArrayList<>();

                for (Hardware hardware : hardwareList) {

                    strHardList.add(hardware.getId() + "\n\n" + hardware.getManufacturer() + " - " + hardware.getName() + " - " + hardware.getCategory() + " - R" + hardware.getPrice() + "\n");
                }
            }
        });

        thread.start();

        try {

            thread.join();
        }
        catch (Exception ex){

            ex.printStackTrace();
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(DeleteHardware.this, android.R.layout.simple_list_item_1, android.R.id.text1, strHardList);
        hardwareListview.setAdapter(adapter);

        hardwareListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> a, final View v, final int position, long id) {

                AlertDialog.Builder adb = new AlertDialog.Builder(DeleteHardware.this);
                adb.setTitle("DELETE");
                adb.setMessage("Are you sure you want to delete the selected item?");
                final int positionToRemove = position;
                adb.setNegativeButton("No", null);

                adb.setPositiveButton("Yes", new AlertDialog.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        Thread thread1 = new Thread(new Runnable() {

                            @Override
                            public void run() {

                                TextView text = (TextView) v;

                                Long hID = new Long(text.getText().toString().substring(0, text.getText().toString().indexOf(" ")));

                                HardwareServiceImpl service = new HardwareServiceImpl();
                                Hardware hardware = service.getHardwareItem(hID);
                                service.deleteHardwareItem(hardware);
                            }
                        });

                        thread1.start();

                        try {

                            thread1.join();
                        }
                        catch (InterruptedException e) {

                            e.printStackTrace();
                        }

                        strHardList.remove(position);

                        adapter.notifyDataSetChanged();
                    }
                });

                adb.show();
            }
        });
    }
}
