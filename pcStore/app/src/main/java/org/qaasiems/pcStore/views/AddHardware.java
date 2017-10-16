package org.qaasiems.pcStore.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.qaasiems.pcStore.R;
import org.qaasiems.pcStore.model.Hardware;
import org.qaasiems.pcStore.services.HardwareService;
import org.qaasiems.pcStore.services.implementation.HardwareServiceImpl;

public class AddHardware extends Activity {

    private Button save;
    private Button cancel;
    private EditText manufacturer;
    private EditText name;
    private EditText category;
    private EditText price;
    private Hardware hardware;
    private String hardwareExist = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hardware);

        addHardware();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_hardware, menu);
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

        Intent i = new Intent(AddHardware.this, HomeActivity.class);
        finish();
        startActivity(i);
    }

    public void addHardware() {

        save = (Button)findViewById(R.id.btnSave);
        cancel = (Button)findViewById(R.id.btnCancel);
        manufacturer = (EditText)findViewById(R.id.txtmanufacturer);
        name = (EditText)findViewById(R.id.txtName);
        category = (EditText)findViewById(R.id.txtCategory);
        price = (EditText)findViewById(R.id.txtPrice);

        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i = new Intent(AddHardware.this, HomeActivity.class);
                finish();
                startActivity(i);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(manufacturer.getText().toString().equals("")) {

                    manufacturer.requestFocus();
                    manufacturer.setError("Cannot be empty!");
                }
                else if (name.getText().toString().equals("")) {

                    name.requestFocus();
                    name.setError("Cannot be empty!");
                }
                else if (category.getText().toString().equals("")) {

                    category.requestFocus();
                    category.setError("Cannot be empty!");
                }
                else if (price.getText().toString().equals("")) {

                        price.requestFocus();
                        price.setError("Cannot be empty!");
                    }
                    else {

                        Thread thread = new Thread(new Runnable() {

                            @Override
                            public void run() {

                                HardwareService service = new HardwareServiceImpl();

                                String price1 = price.getText().toString();
                                final double hPrice = Double.parseDouble(price1);

                                hardware = new Hardware();

                                hardware.setManufacturer(manufacturer.getText().toString());
                                hardware.setName(name.getText().toString());
                                hardware.setCategory(category.getText().toString());
                                hardware.setPrice(hPrice);

                                hardwareExist = service.insertHardwareItem(hardware);
                            }
                        });

                        thread.start();

                        try {

                            thread.join();
                        }
                        catch (Exception e) {

                            e.printStackTrace();
                        }

                    }
                    if (hardwareExist == null) {

                        Toast.makeText(getApplicationContext(), "Hardware Item Successfully added.",
                                Toast.LENGTH_LONG).show();

                        Intent i = new Intent(AddHardware.this, ViewHardware.class);
                        finish();
                        startActivity(i);
                    }
                    else {

                        manufacturer.requestFocus();
                        manufacturer.setError("Already exists.");
                        name.requestFocus();
                        name.setError("Already exists.");
                    }
                }
        });
    }
}
