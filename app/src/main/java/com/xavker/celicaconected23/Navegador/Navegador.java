package com.xavker.celicaconected23.Navegador;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import android.view.View;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.xavker.celicaconected23.Navegador.Menu.ConsultarLuz.Luz;
import com.xavker.celicaconected23.Navegador.Menu.ConsultarTelefono.Telefono;
import com.xavker.celicaconected23.Navegador.Menu.EnviarSugerencias.Sugerencias;
import com.xavker.celicaconected23.Navegador.Menu.Home;
import com.xavker.celicaconected23.Navegador.Menu.SalirCerrarSecion;
import com.xavker.celicaconected23.NoConexion.Noconexion;
import com.xavker.celicaconected23.R;

public class Navegador extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //public Probar_conexion probar_conexion;
    public ImageView screenshot;
    private String sharePath="no";
    private FloatingActionButton fab;
    View navegacion;
    FragmentManager fragmentManager=getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navegador);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FragmentManager fragmentManager=getSupportFragmentManager();
        //probar_conexion=null;
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Gracias por usar Celica Conected", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //takescreenshot();//Capturar pantalla;
            }
        });

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //screenshot=navegacion.findViewById(R.id.captura);
        //screenshot.setVisibility(View.INVISIBLE);

        fragmentManager.beginTransaction().replace(R.id.contenedor,new Home()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
            drawer.openDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navegador, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.noticias) {
            //fragmentManager.beginTransaction().replace(R.id.contenedor,new ReadRSS()).commit();
        } else if (id == R.id.sugerencia) {
            fragmentManager.beginTransaction().replace(R.id.contenedor,new Sugerencias()).commit();

        } else if (id == R.id.planilla_luz) {
            fragmentManager.beginTransaction().replace(R.id.contenedor,new Luz()).commit();
        }
        else if (id == R.id.planilla_telefono) {
            fragmentManager.beginTransaction().replace(R.id.contenedor,new Telefono()).commit();
        }
//        else if (id == R.id.multa_transito) {
//            fragmentManager.beginTransaction().replace(R.id.contenedor,new MultasTransito()).commit();
//        }
        else if (id == R.id.licencia) {
            //fragmentManager.beginTransaction().replace(R.id.contenedor,new Licencia()).commit();
        }
        else if (id == R.id.ser_bachiller) {
            //fragmentManager.beginTransaction().replace(R.id.contenedor,new SerBachiller()).commit();
        }
        else if (id == R.id.conversor) {
            //fragmentManager.beginTransaction().replace(R.id.contenedor,new Conversor()).commit();
        }
        else if (id == R.id.campeonato) {
            //fragmentManager.beginTransaction().replace(R.id.contenedor,new CampeonatoFutbol()).commit();
        }else if (id == R.id.turismo) {
            //fragmentManager.beginTransaction().replace(R.id.contenedor,new Turismo()).commit();
        }
        else if (id == R.id.salir) {
           fragmentManager.beginTransaction().replace(R.id.contenedor,new SalirCerrarSecion()).commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
