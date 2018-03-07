package tluan.restauranthygienechecker;
//https://www.youtube.com/watch?v=_Oljjn1fIAc&t=759s
//https://www.youtube.com/watch?v=dr0zEmuDuIk&t=144s
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

//import com.google.android.gms.location.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


    private ArrayList<Establishment> establishments = new ArrayList<>();


    public static final String RESPONSE_STRING = "response_string";
    String responseStr = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Intent intent = getIntent();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        responseStr = intent.getStringExtra(RESPONSE_STRING);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        try {
            parseResponse(new JSONObject(responseStr));
            addMarkers(establishments);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseResponse(JSONObject response) {
        establishments.clear();
        try {
            JSONArray array = response.getJSONArray("establishments");
            if (array.length() == 0)
                Toast.makeText(getApplicationContext(), "No Establishments satisfies the criteria", Toast.LENGTH_LONG).show();
            for (int i=0; i<array.length(); i++) {
                JSONObject jObj = array.getJSONObject(i);

                Establishment est = new Establishment(jObj);

                establishments.add(est);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void addMarkers(ArrayList<Establishment> estList) {
        mMap.clear();
        for (Establishment est : estList) {
            if (est.hasLatLng()) {
                LatLng marker = new LatLng(est.getLatitude(), est.getLongitude());
                mMap.addMarker(new MarkerOptions().position(marker)
                        .title(est.getBusinessName()));
            }
        }

    }

}
