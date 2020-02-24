package com.xavker.celicaconected23.Navegador.Menu;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xavker.celicaconected23.R;
import com.xavker.celicaconected23.Splash.Splash;

/**
 * A simple {@link Fragment} subclass.
 */
public class SalirCerrarSecion extends Fragment {

    Splash cerrarSecion;
    ImageView ImgCerrarSecion;

    public SalirCerrarSecion() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_salir_cerrar_secion, container, false);
        ImgCerrarSecion=v.findViewById(R.id.cerrars);
        ImgCerrarSecion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cerrarSecion.botoncerrarCecion();
            }
        });
        return v;
    }

}
