package tluan.restauranthygienechecker;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FilterActivity extends AppCompatActivity {

     ArrayList<Integer> businessTypesIDList = new ArrayList<>();
     ArrayList<Integer> ratingIDList = new ArrayList<>();
     ArrayList<Integer> countryIDList = new ArrayList<>();


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
        onDone();
        Intent resultIntent = new Intent();
        resultIntent.putExtra("some_key", "String data");

        setResult(RESULT_OK, resultIntent);


        //finish();
    }

    private void setupOptions() {
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





        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        //final String businessTypesSuffix = "BusinessTypes/basic";
        MyJsonObjectRequest objRequest1 = new MyJsonObjectRequest(
                "BusinessTypes/basic", "businessTypes", "BusinessTypeName",
                "BusinessTypeId", businessTypeAdapter, businessTypesIDList);
        requestQueue.add(objRequest1);

        MyJsonObjectRequest objRequest2 = new MyJsonObjectRequest(
                "Ratings", "ratings", "ratingName",
                "ratingId", ratingAdapter, ratingIDList);
        requestQueue.add(objRequest2);
/*
        final String businessTypesSuffix = "BusinessTypes/basic";
        MyJsonObjectRequest objRequest1 = new MyJsonObjectRequest(businessTypesSuffix,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray array = response.getJSONArray("businessTypes");
                            for (int i=0; i<array.length(); i++) {
                                String type = array.getJSONObject(i).getString("BusinessTypeName");
                                int id = array.getJSONObject(i).getInt("BusinessTypeId");
                                businessTypeAdapter.add(type);
                                businessTypesIDList.add(id);
                            }
                            businessTypeAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        requestQueue.add(objRequest1);


        final String ratingSuffix = "Ratings";
        MyJsonObjectRequest objRequest2 = new MyJsonObjectRequest(ratingSuffix,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray array = response.getJSONArray("ratings");
                            for (int i=0; i<array.length(); i++) {
                                String ratingName = array.getJSONObject(i).getString("ratingName");
                                int id = array.getJSONObject(i).getInt("ratingId");

                                ratingAdapter.add(ratingName);
                                ratingIDList.add(id);
                            }
                            ratingAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
*/

    }


    public void onDone() {
        Spinner businessTypeSpinner = (Spinner)findViewById(R.id.businessType);

        businessTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Log.d("text", parentView.getItemAtPosition(position).toString());
                Log.d("id", String.valueOf(businessTypesIDList.get(position)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        Spinner ratingSpinner = (Spinner)findViewById(R.id.rating);

        ratingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Log.d("text", parentView.getItemAtPosition(position).toString());
                Log.d("id", String.valueOf(ratingIDList.get(position)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

    }


}
