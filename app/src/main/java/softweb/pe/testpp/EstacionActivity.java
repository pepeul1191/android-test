package softweb.pe.testpp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class EstacionActivity extends AppCompatActivity {
    private TextView txtNombre;
    private TextView txtDescipcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_estacion);

        this.txtNombre = (TextView) findViewById(R.id.txtNombre);
        this.txtDescipcion = (TextView) findViewById(R.id.txtDescipcion);

        String detalleEstacionString = getIntent().getStringExtra("detalleEstacion");
        Log.d("onMarkerClick - EstacionActivity", detalleEstacionString);

        JsonElement jelement = new JsonParser().parse(detalleEstacionString);
        JsonObject jobject = jelement.getAsJsonObject();

        Log.d("onMarkerClick - EstacionActivity JSON", jobject.toString());
        Log.d("DESCRIPCION", jobject.get("descripcion").getAsString());
        Log.d("NOMBRE_ESTACION", jobject.get("nombre_estacion").getAsString());
        this.txtNombre.setText(jobject.get("nombre_estacion").getAsString());
        this.txtDescipcion.setText(jobject.get("descripcion").getAsString());
    }
}
