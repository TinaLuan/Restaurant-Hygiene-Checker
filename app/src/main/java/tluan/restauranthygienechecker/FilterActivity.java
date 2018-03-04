package tluan.restauranthygienechecker;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
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

        Intent resultIntent = new Intent();
        resultIntent.putExtra("some_key", "String data");
        setResult(RESULT_OK, resultIntent);


        //finish();
    }

    private void setupOptions() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());


        final String businessTypesSuffix = "BusinessTypes/basic";

        MyJsonObjectRequest objRequest = new MyJsonObjectRequest(businessTypesSuffix,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray array = response.getJSONArray("businessTypes");
                            //Log.d("response", String.valueOf(array));

                            Spinner businessTypeSpinner = (Spinner)findViewById(R.id.businessType);
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                                    android.R.layout.simple_spinner_item, android.R.id.text1);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            businessTypeSpinner.setAdapter(adapter);

                            //ArrayList<String> businessTypesArray = new ArrayList<String>();
                            for (int i=0; i<array.length(); i++) {
                                String type = array.getJSONObject(i).getString("BusinessTypeName");
                                Log.d("name", type);
                                //businessTypesArray.add(type);
                                adapter.add(type);

                            }

                            //Spinner businessTypeSpinner = (Spinner)findViewById(R.id.businessType);
                            //ArrayAdapter<String> adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                                    //businessTypesArray.toArray(), android.R.layout.simple_spinner_item);
                            //R.array.businessTypes
//                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
//                                    android.R.layout.simple_spinner_item, android.R.id.text1);
//                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                            //businessTypeSpinner.setAdapter(adapter);
                            //adapter.add("one");
                            //adapter.add("two");
                            adapter.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }
                }

        );

        requestQueue.add(objRequest);

    }


    public void onDone(View view) {
        Spinner businessType = (Spinner)findViewById(R.id.businessType);


    }


}
