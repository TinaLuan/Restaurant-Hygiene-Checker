package tluan.restauranthygienechecker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;


public class FilterActivity extends AppCompatActivity {

    ArrayList<Integer> businessTypesIDList = new ArrayList<>();
    ArrayList<Integer> ratingIDList = new ArrayList<>();
    ArrayList<Integer> countryIDList = new ArrayList<>();
    ArrayList<Integer> authorityIDList = new ArrayList<>();

    Intent resultIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout( (int)(width*.7), (int)(height*.5) );

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

        Spinner countrySpinner = (Spinner)findViewById(R.id.country);
        final ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, android.R.id.text1);
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setAdapter(countryAdapter);


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
                "Countries/basic", "countries", "name",
                "id", countryAdapter, countryIDList);
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

        Spinner authoritySpinner = (Spinner)findViewById(R.id.authority);

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

        final Spinner countrySpinner = (Spinner)findViewById(R.id.country);

        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Log.d("text", parentView.getItemAtPosition(position).toString());
                Log.d("id", String.valueOf(countryIDList.get(position)));
                resultIntent.putExtra("countrySelectedID",countryIDList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                Toast.makeText(getApplicationContext(), "Please make selections", Toast.LENGTH_LONG).show();
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
