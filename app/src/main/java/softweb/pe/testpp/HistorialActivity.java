package softweb.pe.testpp;

import android.app.FragmentManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import utils.Constants;
import utils.Httparty;

public class HistorialActivity extends AppCompatActivity implements OnChartGestureListener, OnChartValueSelectedListener, FechaFragment.OnFragmentInteractionListener{
    private LineChart mChart;
    private int ideSensor;
    private LinearLayout linearFechaInicio;

    public int getIdeSensor() {
        return ideSensor;
    }

    public void setIdeSensor(int ideSensor) {
        this.ideSensor = ideSensor;
    }

    public void btnSetFechaInicio(View v) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(HistorialActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.fragment_fecha, null);
        Log.d("btnSetFechaInicio","ANTES");

        FragmentManager fm = getFragmentManager();
        FechaFragment dialogFragment = new FechaFragment();
        dialogFragment.show(fm, "Sample Fragment");
    }

    public void btnSetFechaFin(View v) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(HistorialActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.fragment_fecha, null);
        Log.d("btnSetFechaInicio","ANTES");

        FragmentManager fm = getFragmentManager();
        FechaFragment dialogFragment = new FechaFragment();
        dialogFragment.show(fm, "Sample Fragment");
    }

    private ArrayList<String> setXAxisValues(JSONArray historicosJSON){
        ArrayList<String> xVals = new ArrayList<String>();
        //xVals.add("10");
        //xVals.add("20");
        //xVals.add("30");
        //xVals.add("30.5");
        //xVals.add("40");

        for (int i=0; i < historicosJSON.length(); i++) {
            try {
                JSONObject historicoJSON = historicosJSON.getJSONObject(i);
                String temp = historicoJSON.getString("anio") + "/" + historicoJSON.getString("mes") + "/" + historicoJSON.getString("dia");
                xVals.add(temp);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return xVals;
    }

    private ArrayList<Entry> setYAxisValues(JSONArray historicosJSON){
        ArrayList<Entry> yVals = new ArrayList<Entry>();
        //yVals.add(new Entry(60, 0));
        //yVals.add(new Entry(48, 1));
        //yVals.add(new Entry(70.5f, 2));
        //yVals.add(new Entry(100, 3));
        //yVals.add(new Entry(180.9f, 4));

        for (int i=0; i < historicosJSON.length(); i++) {
            try {
                JSONObject historicoJSON = historicosJSON.getJSONObject(i);
                String temp = historicoJSON.getString("anio") + "/" + historicoJSON.getString("mes") + "/" + historicoJSON.getString("dia");
                yVals.add(new Entry((float)historicoJSON.getDouble("medicion"), i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return yVals;
    }

    private void setData() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        JSONArray historiosJSON = null;
        ArrayList<String> xVals = null;
        ArrayList<Entry> yVals = null;

        try{
            String urlHistorico = Constants.BASE_URL + "sensor/historico/" + this.getIdeSensor() + "?fecha_inicio=2017-01-01&fecha_fin=2017-08-10";
            Httparty httpartyHistorico = new Httparty(urlHistorico, "GET");
            httpartyHistorico.action();

            historiosJSON = new JSONArray(httpartyHistorico.getRpta());
            xVals = setXAxisValues(historiosJSON);
            yVals = setYAxisValues(historiosJSON);
        } catch(Exception e){
            e.printStackTrace();
        }

        LineDataSet set1;

        // create a dataset and give it a type
        set1 = new LineDataSet(yVals, "YYYY/MM/DD");
        set1.setFillAlpha(110);
        // set1.setFillColor(Color.RED);

        // set the line to be drawn like this "- - - - - -"
        // set1.enableDashedLine(10f, 5f, 0f);
        // set1.enableDashedHighlightLine(10f, 5f, 0f);
        set1.setColor(Color.BLACK);
        set1.setCircleColor(Color.BLACK);
        set1.setLineWidth(1f);
        set1.setCircleRadius(3f);
        set1.setDrawCircleHole(false);
        set1.setValueTextSize(9f);
        set1.setDrawFilled(true);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(set1); // add the datasets

        // create a data object with the datasets
        LineData data = new LineData(xVals, dataSets);

        // set data
        mChart.setData(data);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setIdeSensor(Integer.parseInt(getIntent().getStringExtra("ide_sensor")));

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_historial);

        mChart = (LineChart) findViewById(R.id.linechart);
        this.setData();
        Legend l = mChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);
        mChart.setOnChartGestureListener(this);
        mChart.setOnChartValueSelectedListener(this);
    }

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i("Gesture", "START, x: " + me.getX() + ", y: " + me.getY());
    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i("Gesture", "END, lastGesture: " + lastPerformedGesture);

        // un-highlight values after the gesture is finished and no single-tap
        if(lastPerformedGesture != ChartTouchListener.ChartGesture.SINGLE_TAP)
            // or highlightTouch(null) for callback to onNothingSelected(...)
            mChart.highlightValues(null);
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        Log.i("LongPress", "Chart longpressed.");
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
        Log.i("DoubleTap", "Chart double-tapped.");
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
        Log.i("SingleTap", "Chart single-tapped.");
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
        Log.i("Fling", "Chart flinged. VeloX: " + velocityX + ", VeloY: " + velocityY);
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
        Log.i("Scale / Zoom", "ScaleX: " + scaleX + ", ScaleY: " + scaleY);
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
        Log.i("Translate / Move", "dX: " + dX + ", dY: " + dY);
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
        Log.i("Entry selected", e.toString());
        Log.i("LOWHIGH", "low: " + mChart.getLowestVisibleXIndex()
                + ", high: " + mChart.getHighestVisibleXIndex());

        //+ ", ymin: " + mChart.getYChartMin()
        Log.i("MIN MAX", "xmin: " + mChart.getXChartMin()
                + ", xmax: " + mChart.getXChartMax()
                + ", ymax: " + mChart.getYChartMax());
    }

    @Override
    public void onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.");
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
