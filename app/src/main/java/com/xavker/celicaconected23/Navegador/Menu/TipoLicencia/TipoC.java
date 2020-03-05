package com.xavker.celicaconected23.Navegador.Menu.TipoLicencia;


import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xavker.celicaconected23.R;

import androidx.fragment.app.Fragment;

public class TipoC extends Fragment {


    public TipoC() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tipo_c, container, false);
        TextView textView = view.findViewById(R.id.tipoc);
        textView.setMovementMethod(new ScrollingMovementMethod());
        return view;

    }
}
