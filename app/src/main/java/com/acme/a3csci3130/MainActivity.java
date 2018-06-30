package com.acme.a3csci3130;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Main Activity displays a list of Businesses stored in Firebase, sets up firebase references for the
 * app and lets users go to create, and update/delete/read pages for each business.
 *
 * @author  Dryden Pick
 */
public class MainActivity extends Activity {


    private ListView businessListView;
    private FirebaseListAdapter<Business> firebaseAdapter;

    /**
     * Method Sets up Firebase connection and storing it. Also displays List of database entries and attaches
     * an event listener to each list item.
     *
     * @param Bundle savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get the app wide shared variables
        MyApplicationData appData = (MyApplicationData)getApplication();

        //Set-up Firebase
        appData.firebaseDBInstance = FirebaseDatabase.getInstance();
        appData.firebaseReference = appData.firebaseDBInstance.getReference("businesses");

        //Get the reference to the UI contents
        businessListView = (ListView) findViewById(R.id.listView);

        //Set up the List View
       firebaseAdapter = new FirebaseListAdapter<Business>(this, Business.class,
                android.R.layout.simple_list_item_1, appData.firebaseReference) {
            @Override
            protected void populateView(View v, Business model, int position) {
                TextView businessName = (TextView)v.findViewById(android.R.id.text1);
                businessName.setText(model.name);
            }
        };
        businessListView.setAdapter(firebaseAdapter);
        businessListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // onItemClick method is called everytime a user clicks an item on the list
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Business nBusiness = (Business) firebaseAdapter.getItem(position);
                showDetailView(nBusiness);
            }
        });
    }

    /**
     * Method starts the Create activity when the create button is selected.
     * @param View V
     */
    public void createBusinessButton(View v) {
        Intent intent = new Intent(this, CreateBusinessAcitivity.class);
        startActivity(intent);
    }

    /**
     * Method starts the detail activity when a list item is selected.
     * Method also passes selected business to the detail activity.
     * @param Business nBusiness
     */
    private void showDetailView(Business nBusiness) {
        Intent intent = new Intent(this, DetailViewActivity.class);
        intent.putExtra("Business", nBusiness);
        startActivity(intent);
    }



}
