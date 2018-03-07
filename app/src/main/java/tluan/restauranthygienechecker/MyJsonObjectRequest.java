package tluan.restauranthygienechecker;

import android.util.Log;
import android.widget.ArrayAdapter;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MyJsonObjectRequest extends JsonObjectRequest {

    public static final String ROOT_URL = "http://api.ratings.food.gov.uk/";


    public MyJsonObjectRequest(String urlSuffix, final String arrayName, final String stringName, final String intName,
                               final ArrayAdapter<String> adapter, final ArrayList<Integer> idList) {

        super(Method.GET, ROOT_URL + urlSuffix, null,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray array = response.getJSONArray(arrayName);
                    for (int i=0; i<array.length(); i++) {
                        String type = array.getJSONObject(i).getString(stringName);
                        int id = array.getJSONObject(i).getInt(intName);
                        adapter.add(type);
                        idList.add(id);
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", "!");
            }
        });
    }


    @Override
    public Map<String, String> getHeaders() {
        Map<String, String>  params = new HashMap<String, String>();
        params.put("Accept", "application/json");
        params.put("x-api-version", "2");
        return params;
    }
}
