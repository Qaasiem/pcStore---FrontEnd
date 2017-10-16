package org.qaasiems.pcStore.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.qaasiems.pcStore.R;

public class MainActivity extends Activity {

    private Button login;
    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (Button)findViewById(R.id.btnLogin);
        email = (EditText)findViewById(R.id.txtEmail);
        password = (EditText)findViewById(R.id.txtPassword);

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (email.getText().toString().equals("") && password.getText().toString().equals("")) {

                    email.requestFocus();
                    email.setError("Cannot be empty.");
                    password.requestFocus();
                    password.setError("Cannot be empty!");
                }

                else if (email.getText().toString().equals("")) {

                    email.requestFocus();
                    email.setError("Cannot be empty!");
                }

                else if (password.getText().toString().equals("")) {

                    password.requestFocus();
                    password.setError("Cannot be empty!");
                }

                else if (!email.getText().toString().equals("faizeljabaar@gmail.com")) {

                    email.requestFocus();
                    email.setError("Incorrect Email!");
                }

                else if (!password.getText().toString().equals("asd123")) {

                    password.requestFocus();
                    password.setError("Incorrect password!");
                }

                else {
                    Intent i = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(i);
                }
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
