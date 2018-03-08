package tluan.restauranthygienechecker;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailActivity extends FragmentActivity implements OnMapReadyCallback {

    public static final String JSON_OBJECT_STR = "JsonObjectStr";

    public static final int DEFAULT_ZOOM = 10;

    private JSONObject jObj;
    private Establishment est;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        String JsonObjectStr = intent.getStringExtra(JSON_OBJECT_STR);
        try {

            jObj = new JSONObject(JsonObjectStr);
            est = new Establishment(jObj);
            setupTextView();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        addMarker();
    }

    private void addMarker() {
        mMap.clear();
        if (est.hasLatLng()) {
            LatLng pos = new LatLng(est.getLatitude(), est.getLongitude());
            mMap.addMarker(new MarkerOptions().position(pos).title(est.getBusinessName()));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom( pos, DEFAULT_ZOOM));
        } else {
            Toast.makeText(this, "No Lat Lng Data", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupTextView() {
        TextView detail = (TextView)findViewById(R.id.detailTextView);
        String text ="Name: " +est.getBusinessName()+"\n"+
                "Business Type: "+est.getBusinessType()+"\n"+
                "Rating: "+est.getRatingValue()+"\n"+
                "Address"+est.getAddressLine()+"\n"+
                "Authority"+ est.getLocalAuthorityName() +"\n"+
                "Authority Email: "+est.getLocalAuthorityEmailAddress();


        detail.setText(text);
    }

    public void onShare(View view) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        String shareBody = est.getAddressLine();
        String shareSub = "Share: "+ est.getBusinessName();
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(shareIntent, "share using"));
    }

}
