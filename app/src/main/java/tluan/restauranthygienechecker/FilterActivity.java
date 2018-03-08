package tluan.restauranthygienechecker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;


import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;


public class FilterActivity extends AppCompatActivity {

    ArrayList<Integer> businessTypesIDList = new ArrayList<>();
    ArrayList<Integer> ratingIDList = new ArrayList<>();
    ArrayList<Integer> regionIDList = new ArrayList<>();
    ArrayList<Integer> authorityIDList = new ArrayList<>();
    ArrayList<Integer> radiusList = new ArrayList<>();

    Intent resultIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

//        DisplayMetrics dm = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(dm);
//
//        int width = dm.widthPixels;
//        int height = dm.heightPixels;
//
//        getWindow().setLayout( (int)(width*.7), (int)(height*.5) );


        setupOptions();
        setupListeners();

        resultIntent = new Intent();

    }

    private void setupOptions() {

        // Set up adapters for all spinners
        Spinner businessTypeSpinner = (Spinner)findViewById(R.id.businessType);
        final ArrayAdapter<String> businessTypeAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, android.R.id.text1);
        businessTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        businessTypeSpinner.setAdapter(businessTypeAdapter);

        Spinner ratingSpinner = (Spinner)findViewById(R.id.rating);
        final ArrayAdapter<String> ratingAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, android.R.id.text1);
        ratingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ratingSpinner.setAdapter(ratingAdapter);

        Spinner authoritySpinner = (Spinner)findViewById(R.id.authority);
        final ArrayAdapter<String> authorityAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, android.R.id.text1);
        authorityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        authoritySpinner.setAdapter(authorityAdapter);

        Spinner regionSpinner = (Spinner)findViewById(R.id.region);
        final ArrayAdapter<String> regionAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, android.R.id.text1);
        regionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        regionSpinner.setAdapter(regionAdapter);

//        Spinner radiusSpinner = (Spinner)findViewById(R.id.radius);
//        final ArrayAdapter<String> radiusAdapter = ArrayAdapter.createFromResource();
//        regionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        regionSpinner.setAdapter(regionAdapter);


        // Make GET requests for spinners
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        MyJsonObjectRequest objRequest1 = new MyJsonObjectRequest(
                "BusinessTypes/basic", "businessTypes", "BusinessTypeName",
                "BusinessTypeId", businessTypeAdapter, businessTypesIDList);
        requestQueue.add(objRequest1);

        MyJsonObjectRequest objRequest2 = new MyJsonObjectRequest(
                "Ratings", "ratings", "ratingName",
                "ratingId", ratingAdapter, ratingIDList);
        requestQueue.add(objRequest2);

        MyJsonObjectRequest objRequest3 = new MyJsonObjectRequest(
                "Countries", "countries", "nameKey",
                "id", regionAdapter, regionIDList);
        requestQueue.add(objRequest3);

        MyJsonObjectRequest objRequest4 = new MyJsonObjectRequest(
                "Authorities/basic", "authorities", "Name",
                "LocalAuthorityId", authorityAdapter, authorityIDList);
        requestQueue.add(objRequest4);

    }

    /* Listeners for all spinners. Put selected item into intent
    */
    public void setupListeners() {

        Spinner businessTypeSpinner = (Spinner)findViewById(R.id.businessType);

        businessTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Log.d("text", parentView.getItemAtPosition(position).toString());
                Log.d("id", String.valueOf(businessTypesIDList.get(position)));
                resultIntent.putExtra("businessTypeSelectedID",businessTypesIDList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                Toast.makeText(getApplicationContext(), "Please make selections", Toast.LENGTH_LONG).show();            }

        });

        Spinner ratingSpinner = (Spinner)findViewById(R.id.rating);

        ratingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Log.d("text", parentView.getItemAtPosition(position).toString());
                Log.d("id", String.valueOf(ratingIDList.get(position)));
                resultIntent.putExtra("ratingSelectedID",ratingIDList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                Toast.makeText(getApplicationContext(), "Please make selections", Toast.LENGTH_LONG).show();            }

        });

        final Spinner authoritySpinner = (Spinner)findViewById(R.id.authority);

        authoritySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Log.d("text", parentView.getItemAtPosition(position).toString());
                Log.d("id", String.valueOf(authorityIDList.get(position)));
                resultIntent.putExtra("authoritySelectedID",authorityIDList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                Toast.makeText(getApplicationContext(), "Please make selections", Toast.LENGTH_LONG).show();            }

        });

        final Spinner regionSpinner = (Spinner)findViewById(R.id.region);

        regionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Log.d("text", parentView.getItemAtPosition(position).toString());
                Log.d("id", String.valueOf(regionIDList.get(position)));
                resultIntent.putExtra("countrySelectedID",regionIDList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                Toast.makeText(getApplicationContext(), "Please make selections", Toast.LENGTH_LONG).show();
            }

        });


        final EditText radius = (EditText)findViewById(R.id.radius);


        ToggleButton toggle = (ToggleButton) findViewById(R.id.toggle);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //radiusSpinner.setClickable(true);
                    radius.setFocusable(true);
                    radius.setClickable(true);
                    resultIntent.putExtra("radiusStr",radius.getText().toString());
                    resultIntent.putExtra("toggleChecked", true);
                    authoritySpinner.setClickable(false);
                    authoritySpinner.setEnabled(false);
                    regionSpinner.setClickable(false);
                    regionSpinner.setEnabled(false);
                } else {
                    authoritySpinner.setClickable(true);
                    authoritySpinner.setEnabled(true);
                    regionSpinner.setEnabled(true);
                    regionSpinner.setClickable(true);
                    radius.setFocusable(false);
                    radius.setClickable(false);
                    resultIntent.putExtra("toggleChecked", false);
                }
            }
        });

    }

    /* Put results and Close current activity
     */
    public void onDone(View view) {
        setResult(RESULT_OK, resultIntent);
        finish();
    }

}
