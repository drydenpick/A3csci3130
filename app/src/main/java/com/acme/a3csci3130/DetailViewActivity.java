package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Activity displays details of a Business in the database and allows users to update or delete that business
 *
 * @author  Dryden Pick
 */
public class DetailViewActivity extends Activity {

    private EditText numberField, nameField, primaryBusinessField, addressField, provinceField;
    Business receivedBusinessInfo;
    private MyApplicationData appState;


    /**
     * onCreate gets application state and fills each field with data from Firebase entry.
     *
     * @param Bundle savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedBusinessInfo = (Business)getIntent().getSerializableExtra("Business");
        appState = ((MyApplicationData) getApplicationContext());

        numberField = (EditText) findViewById(R.id.number);
        nameField = (EditText) findViewById(R.id.name);
        primaryBusinessField = (EditText) findViewById(R.id.primaryBusiness);
        addressField = (EditText) findViewById(R.id.address);
        provinceField = (EditText) findViewById(R.id.province);

        if(receivedBusinessInfo != null){
            numberField.setText(""+receivedBusinessInfo.number);
            nameField.setText(receivedBusinessInfo.name);
            primaryBusinessField.setText(receivedBusinessInfo.primaryBusiness);
            addressField.setText(receivedBusinessInfo.address);
            provinceField.setText(receivedBusinessInfo.province);
        }
    }

    /**
     * Gets data from each field and then writes it to database under the ID selected from the database earlier.
     *
     * @param View V
     */
    public void updateBusiness(View v){
        String number = ""+numberField.getText().toString();
        String name = nameField.getText().toString();
        String primaryBusiness = primaryBusinessField.getText().toString();
        String address = addressField.getText().toString();
        String province = provinceField.getText().toString();
        Business updateBusiness= new Business(receivedBusinessInfo.businessID, number, name, primaryBusiness, address, province);

        appState.firebaseReference.child(receivedBusinessInfo.businessID).setValue(updateBusiness);
        finish();
    }

    /**
     * Users BusinessId from Firebase to delete Firebase entry.
     *
     * @param View V
     */
    public void eraseBusiness(View v) {
        appState.firebaseReference.child(receivedBusinessInfo.businessID).removeValue();
        finish();
    }
}
