package org.qaasiems.pcStore.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.qaasiems.pcStore.R;
import org.qaasiems.pcStore.model.Hardware;
import org.qaasiems.pcStore.services.implementation.HardwareServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class ViewHardware extends AppCompatActivity {

    private List<Hardware> hardwareList;
    private ArrayList<String> strHardList;
    private ListView hardwareListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_hardware);
        viewHardware();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_hardware, menu);
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

        Intent i = new Intent(ViewHardware.this, HomeActivity.class);
        finish();
        startActivity(i);
    }

    public void viewHardware() {

        hardwareListView = (ListView) findViewById(R.id.listViewHardware);

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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ViewHardware.this, android.R.layout.simple_list_item_1, android.R.id.text1, strHardList);
        hardwareListView.setAdapter(adapter);
    }

}