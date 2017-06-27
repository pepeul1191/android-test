package softweb.pe.testpp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by pepe on 27/06/17.
 */

public class MapaFragment extends Fragment implements OnMapReadyCallback {
    private static final String TAG = "MapaFrgment";
    MapView mapView;
    GoogleMap googleMap;

    private WebView contactWebView;

    public MapaFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mapa, container, false);
        mapView = (MapView) v.findViewById(R.id.mapaEstaciones);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this); //this is important

        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng peru = new LatLng(-10.569220973686791, -75.20462410000005);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.addMarker(new MarkerOptions().position(peru).title("Hola desde Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(peru, 5));
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
