package softweb.pe.testpp;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

public class FechaFragment extends DialogFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button btnSelcionarFecha;
    private DatePicker datePicker;
    private String fechaSeleccionadaString;
    Activity activity;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FechaFragment() {
        // Required empty public constructor
    }

    public String getFechaSeleccionadaString() {
        return fechaSeleccionadaString;
    }

    public static FechaFragment newInstance(String param1, String param2) {
        FechaFragment fragment = new FechaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fecha, container, false);
        //getDialog().setTitle("Simple Dialog");
        btnSelcionarFecha = (Button) view.findViewById(R.id.btnSelcionarFecha);
        datePicker = (DatePicker) view.findViewById(R.id.datePicker);
        btnSelcionarFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity = getActivity();
                int dia = datePicker.getDayOfMonth();
                int mes = datePicker.getMonth() + 1;
                int anio = datePicker.getYear();
                //Toast.makeText(v.getContext(), "Fecha selccionada : " + dia + "/" + mes + "/" + anio, Toast.LENGTH_SHORT).show();
                //Log.d("FRAGMENT CLICK", txtCorreo.getText().toString());
                fechaSeleccionadaString = dia + "/" + mes + "/" + anio;
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("onDetach", fechaSeleccionadaString);
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
