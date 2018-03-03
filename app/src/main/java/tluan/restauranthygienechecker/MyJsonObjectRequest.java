package tluan.restauranthygienechecker;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MyJsonObjectRequest extends JsonObjectRequest {

    public static final String ROOT_URL = "http://api.ratings.food.gov.uk/";


    public MyJsonObjectRequest(String urlSuffix, Response.Listener<JSONObject> listener) {
        super(Method.GET, ROOT_URL + urlSuffix, null, listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", "!");
            }
        });
    }


//        public MyJsonObjectRequest(String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
//            super(url, jsonRequest, listener, errorListener);
//        }

    @Override
    public Map<String, String> getHeaders() {
        Map<String, String>  params = new HashMap<String, String>();
        params.put("Accept", "application/json");
        params.put("x-api-version", "2");
        return params;
    }
}
