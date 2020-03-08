package com.xavker.celicaconected23.Navegador.Menu.Turismo.Listener;


import com.xavker.celicaconected23.Navegador.Menu.Turismo.Colecciones.Lugares;

import java.util.List;

public interface IFirebaseturismo {

    void onFirebaseloadSuccesss(List<Lugares> lugaresList);
    void onFirebaseLoadFailed(String mensage);

}
