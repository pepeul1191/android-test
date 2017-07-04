package adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.json.JSONArray;

import org.json.JSONException;
import org.json.JSONObject;

import softweb.pe.testpp.R;


public class EstacionesAdapter extends BaseAdapter implements ListAdapter{
    private final Activity activity;
    private final JSONArray jsonArray;

    public EstacionesAdapter(Activity activity, JSONArray jsonArray) {
        this.activity = activity;
        this.jsonArray = jsonArray;
    }

    @Override
    public int getCount() {
        if(null==this.jsonArray){
            return 0;
        }else{
            return this.jsonArray.length();
        }
    }

    @Override
    public JSONObject getItem(int position) {
        if(null == this.jsonArray){
            return null;
        }else{
            try {
                return (JSONObject) jsonArray.get(position);
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    @Override
    public long getItemId(int position) {
        JSONObject jsonObject = (JSONObject) getItem(position);
        return jsonObject.optLong("id");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if(convertView == null){
            convertView = activity.getLayoutInflater().inflate(R.layout.adapter_estaciones, null);
        }

        TextView txtNombreSensor = (TextView)convertView.findViewById(R.id.txtNombreSensor);
        TextView txtDescripcionInstrumento = (TextView)convertView.findViewById(R.id.txtDescripcionInstrumento);
        TextView txtDescripcionTipo = (TextView)convertView.findViewById(R.id.txtDescripcionTipo);

        JSONObject sensorJson = getItem(position);
        if(null != sensorJson){
            try {
                txtNombreSensor.setText(sensorJson.getString("nombre_sensor"));
                txtDescripcionInstrumento.setText(sensorJson.getString("desc_instrumento"));
                txtDescripcionTipo.setText(sensorJson.getString("des_tipo"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return convertView;
    }
}
