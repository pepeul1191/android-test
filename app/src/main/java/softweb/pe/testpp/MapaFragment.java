package softweb.pe.testpp;

import android.os.Bundle;
import android.os.StrictMode;
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
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import utils.Constants;
import utils.Httparty;

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
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try{
            String urlEstaciones = Constants.BASE_URL + "estacion/listar";
            Httparty httpartyEstacion = new Httparty(urlEstaciones, "GET");
            httpartyEstacion.action();

            JSONArray estacionesJsonArray = new JSONArray(httpartyEstacion.getRpta());

            for(int i = 0; i < estacionesJsonArray.length(); i++){
                JSONObject estacionJson = estacionesJsonArray.getJSONObject(i);
                double latitud = estacionJson.getDouble("latitud");
                double longitud = estacionJson.getDouble("longitud");
                String descripcion = estacionJson.getString("descripcion");

                LatLng estacionMapa = new LatLng(latitud, longitud);
                googleMap.getUiSettings().setZoomControlsEnabled(true);
                googleMap.addMarker(new MarkerOptions().position(estacionMapa).title(descripcion));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(estacionMapa, 5));
            }
        }catch (Exception e){
            Log.d("TRY1", e.toString());
        }
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