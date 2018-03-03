package tluan.restauranthygienechecker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

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



        Intent resultIntent = new Intent();
        resultIntent.putExtra("some_key", "String data");
        setResult(RESULT_OK, resultIntent);


        //finish();
    }

    private void setupOptions() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());


        String businessTypesSuffix = "BusinessTypes/basic";
        /*
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", "!");
            }
        };
        */
        MyJsonObjectRequest objRequest = new MyJsonObjectRequest(businessTypesSuffix,
                new Response.Listener<JSONObject>() {
                    // Called when a response is received.
                    @Override
                    public void onResponse(JSONObject response) {

                        //parseResponse(response);
                        Log.d("response", String.valueOf(response));
                    }
                }

        );
        /*
        JsonObjectRequest objRequest = new JsonObjectRequest(MapActivity.ROOT_URL, null,
                new Response.Listener<JSONObject>() {
                    // Called when a response is received.
                    @Override
                    public void onResponse(JSONObject response) {

                        parseResponse(response);
                    }
                },
                errorListener
        ) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Accept", "application/json");
                params.put("x-api-version", "2");
                return params;
            }
        };
        */
        requestQueue.add(objRequest);

    }


    public void onDone(View view) {
        Spinner businessType = (Spinner)findViewById(R.id.businessType);


    }


}
