package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.acme.a3csci3130.Business;

/**
 * Activity allows creation of business contacts which are stored in Firebase.
 *
 * @author  Dryden Pick
 */
public class CreateBusinessAcitivity extends Activity {

    private Button submitButton;
    private EditText numberField, nameField, primaryBusinessField, addressField, provinceField;
    private MyApplicationData appState;

    /**
     * Sets up each EditText field and gets application references like the Firebase reference.
     *
     * @param Bundle savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_business_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);
        numberField = (EditText) findViewById(R.id.number);
        nameField = (EditText) findViewById(R.id.name);
        primaryBusinessField = (EditText) findViewById(R.id.primaryBusiness);
        addressField = (EditText) findViewById(R.id.address);
        provinceField =(EditText) findViewById(R.id.province);
    }

    /**
     * Gets data from each field and then writes it to database under a new, unique id requested from Firebase.
     *
     * @param View V
     */
    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String businessID = appState.firebaseReference.push().getKey();
        String number = ""+numberField.getText().toString();
        String name = nameField.getText().toString();
        String primaryBusiness = primaryBusinessField.getText().toString();
        String address = addressField.getText().toString();
        String province = provinceField.getText().toString();
        Business newBusiness= new Business(businessID, number, name, primaryBusiness, address, province);

        appState.firebaseReference.child(businessID).setValue(newBusiness);

        finish();

    }
}
