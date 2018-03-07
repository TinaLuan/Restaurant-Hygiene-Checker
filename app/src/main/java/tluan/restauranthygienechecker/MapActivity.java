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

//    private Location mLastKnownLocation;
//    private FusedLocationProviderClient mFusedLocationProviderClient;
//    private boolean mLocationPermissionGranted = false;
//    private final LatLng mDefaultLocation = new LatLng(-33.8523341, 151.2106085);
//    private static final int DEFAULT_ZOOM = 10;
//    private static final String TAG = MapActivity.class.getSimpleName();
//
//    private final int NUM_LOCAL_SEARCH_RESULTS = 15;

    private ArrayList<Establishment> establishments = new ArrayList<>();

//    private final int FILTER_ACTIVITY_REQ_CODE = 1;
//
//    private int businessTypeSelectedID;
//    private int ratingSelectedID;
//    private int countrySelectedID;
//    private int authoritySelectedID;
//
//    public final int MY_PERMISSIONS_ACCESS_FINE_LOCATION = 1;

    public static final String RESPONSE_STRING = "response_string";
    String responseStr = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        //mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        Intent intent = getIntent();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Intent intent = getIntent();
        //intent.getBooleanExtra(LOCATION_PERMISSION, permission);
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


        //permission();
        try {
            parseResponse(new JSONObject(responseStr));
            addMarkers(establishments);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
//
//    /**
//     * Gets the current location of the device, and positions the map's camera.
//     */
//    public void onLocalSearch(View view) {
//        mMap.clear();
//
//        /*
//         * Get the best and most recent location of the device, which may be null in rare
//         * cases when a location is not available.
//         */
//        try {
//            if (mLocationPermissionGranted) {
//                Task<Location> locationResult = mFusedLocationProviderClient.getLastLocation();
//                locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Location> task) {
//                        if (task.isSuccessful()) {
//                            // Set the map's camera position to the current location of the device.
//                            mLastKnownLocation = task.getResult();
//                            double lat = mLastKnownLocation.getLatitude();
//                            double lng = mLastKnownLocation.getLongitude();
//                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom( new LatLng(lat, lng), DEFAULT_ZOOM));
//
//
//                            String URLsuffix = "Establishments?longitude=" + lng + "&latitude=" + lat +
//                                    "&sortOptionKey=Distance&pageNumber=1&pageSize=" + String.valueOf(NUM_LOCAL_SEARCH_RESULTS);
//                            queryFSA(URLsuffix);
//
//                        } else {
//                            Log.d(TAG, "Current location is null. Using defaults.");
//                            Log.e(TAG, "Exception: %s", task.getException());
//                            mMap.moveCamera(CameraUpdateFactory
//                                    .newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
//                            mMap.getUiSettings().setMyLocationButtonEnabled(false);
//                        }
//                    }
//                });
//            }
//        } catch (SecurityException e)  {
//            Log.e("Exception: %s", e.getMessage());
//        }
//    }
//
//    public void onSimpleSearch(View view) {
//        String input = ((EditText)findViewById(R.id.input)).getText().toString();
//        if (input != null && input.length() > 0) {
//            String URLsuffix = "Establishments?name="+ input +
//                    "&sortOptionKey=Rating&pageNumber=1&pageSize=5";
//            queryFSA(URLsuffix);
//        }
//
//    }
//
//    public void onAdvancedSearch(View view) {
//        Intent intent = new Intent(this, FilterActivity.class);
//
//        startActivityForResult(intent, FILTER_ACTIVITY_REQ_CODE);
//
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch(requestCode) {
//            case (FILTER_ACTIVITY_REQ_CODE) : {
//                if (resultCode == RESULT_OK) {
//                    businessTypeSelectedID = data.getIntExtra("businessTypeSelectedID" , businessTypeSelectedID);
//                    ratingSelectedID = data.getIntExtra("ratingSelectedID", ratingSelectedID);
//                    countrySelectedID = data.getIntExtra("countrySelectedID", countrySelectedID);
//                    authoritySelectedID = data.getIntExtra("authoritySelectedID", authoritySelectedID);
//                    Log.d("!!!!return", String.valueOf(businessTypeSelectedID) + " "+ String.valueOf(ratingSelectedID)
//                            + " "+ String.valueOf(countrySelectedID) + " "+String.valueOf(authoritySelectedID));
//
//                    String URLsuffix = "Establishments?";
//                    String input = ((EditText)findViewById(R.id.input)).getText().toString();
//                    if (input != null && input.length() > 0) {
//                        URLsuffix += "name=" + input + "&";
//                    }
//                    URLsuffix += "businessTypeId="+businessTypeSelectedID+"&ratingKey="+ratingSelectedID+
//                            "&localAuthorityId="+authoritySelectedID+"&countryId="+ countrySelectedID+
//                            "&sortOptionKey=Rating&pageNumber=1&pageSize=5";
//                    Log.d("suffix", URLsuffix);
//                    queryFSA(URLsuffix);
//                }
//                break;
//            }
//        }
//    }
//    private void queryFSA(String URLsuffix) {
//
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//
//        String URL = "http://api.ratings.food.gov.uk/" + URLsuffix;
//        System.out.println("URL:"+URL);
//        JsonObjectRequest objRequest = new JsonObjectRequest(URL, null,
//                new Response.Listener<JSONObject>() {
//                    // Called when a response is received.
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Log.d("response", String.valueOf(response));
//
//                        parseResponse(response);
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.e("errorQuery" , "!");
//                    }
//                }
//        ) {
//            @Override
//            public Map<String, String> getHeaders() {
//                Map<String, String>  params = new HashMap<String, String>();
//                params.put("Accept", "application/json");
//                params.put("x-api-version", "2");
//                return params;
//            }
//        };
//        requestQueue.add(objRequest);
//
//    }
//
    private void parseResponse(JSONObject response) {
        establishments.clear();
        try {
            JSONArray array = response.getJSONArray("establishments");
            if (array.length() == 0)
                Toast.makeText(getApplicationContext(), "No Establishments satisfies the criteria", Toast.LENGTH_LONG).show();
            for (int i=0; i<array.length(); i++) {
                JSONObject jObj = array.getJSONObject(i);

                //Log.d("one", (String.valueOf(jObj)));

//                Establishment est = new Establishment(jObj.getString("FHRSID"), jObj.getString("BusinessName"),
//                        jObj.getString("BusinessType"), jObj.getString("AddressLine1") +
//                        jObj.getString("AddressLine2")+jObj.getString("AddressLine3")+ jObj.getString("AddressLine4"),
//                        jObj.getString("LocalAuthorityName"),jObj.getString("LocalAuthorityEmailAddress"),
//                        jObj.getString("RatingValue"), jObj.getJSONObject("geocode").getString("longitude"),
//                        jObj.getJSONObject("geocode").getString("latitude"), jObj);
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
//
//    // Check and ask for permission
//    private void permission() {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//                == PackageManager.PERMISSION_GRANTED) {
//
//            mMap.setMyLocationEnabled(true);
//            mLocationPermissionGranted = true;
//
//        } else {
//            // Should we show an explanation?
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                    Manifest.permission.ACCESS_FINE_LOCATION)) {
//
//                // Show an explanation to the user *asynchronously* -- don't block
//                // this thread waiting for the user's response! After the user
//                // sees the explanation, try again to request the permission.
//                ActivityCompat.requestPermissions(this,
//                        new String[] {Manifest.permission.ACCESS_FINE_LOCATION },
//                        MY_PERMISSIONS_ACCESS_FINE_LOCATION);
//            } else {
//
//                // No explanation needed, we can request the permission.
//
//                ActivityCompat.requestPermissions(this,
//                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                        MY_PERMISSIONS_ACCESS_FINE_LOCATION);
//
//                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
//                // app-defined int constant. The callback method gets the
//                // result of the request.
//            }
//        }
//
//
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case MY_PERMISSIONS_ACCESS_FINE_LOCATION:
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    // permission was granted
//                    if (ContextCompat.checkSelfPermission(this,
//                            Manifest.permission.ACCESS_FINE_LOCATION)
//                            == PackageManager.PERMISSION_GRANTED) {
//
//                        mMap.setMyLocationEnabled(true);
//                        mLocationPermissionGranted = true;
//                    }
//                } else {
//
//                    // permission denied, boo! Disable the
//                    // functionality that depends on this permission.
//                    Toast.makeText(this,"Permission Denied" , Toast.LENGTH_LONG).show();
//                    mMap.setMyLocationEnabled(false);
//                }
//        }
//    }
}
