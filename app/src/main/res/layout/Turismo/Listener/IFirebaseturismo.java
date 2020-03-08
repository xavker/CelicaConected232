package com.example.myapplication.Menu.Turismo.Listener;

import com.example.myapplication.Menu.Turismo.Colecciones.Lugares;

import java.util.List;

public interface IFirebaseturismo {

    void onFirebaseloadSuccesss(List<Lugares> lugaresList);
    void onFirebaseLoadFailed(String mensage);

}
