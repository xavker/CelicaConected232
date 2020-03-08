package com.example.myapplication.Menu.Turismo;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.Menu.Turismo.Adaptadores.AdaptadorTurismo;
import com.example.myapplication.Menu.Turismo.Colecciones.Lugares;
import com.example.myapplication.Menu.Turismo.Listener.IFirebaseturismo;
import com.example.myapplication.Menu.Turismo.Transformer.DepthPageTransformer;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Turismo extends Fragment implements IFirebaseturismo {

ViewPager viewPager;
AdaptadorTurismo adaptadorTurismo;
DatabaseReference databaseReference;
IFirebaseturismo iFirebaseturismo;
CollectionReference lugares;
    public Turismo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //inicio firebase

        //inicializoevento
        iFirebaseturismo=this;
        lugares= FirebaseFirestore.getInstance().collection("turismo");
        View view=inflater.inflate(R.layout.fragment_turismo, container, false);
        viewPager=view.findViewById(R.id.viewpageturismos);

        cargarLugares();

        viewPager=view.findViewById(R.id.viewpageturismos);
        viewPager.setPageTransformer(true,new DepthPageTransformer());
        return  view;
    }

    private void cargarLugares() {
        lugares.get()
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        iFirebaseturismo.onFirebaseLoadFailed(e.getMessage());
                    }
                })
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                           List<Lugares>lugar=new ArrayList<>();
                            for(QueryDocumentSnapshot qs1:task.getResult()){
                                Lugares l=qs1.toObject(Lugares.class);
                                lugar.add(l);
                            }
                            iFirebaseturismo.onFirebaseloadSuccesss(lugar);
                    }
                }
                });
    }

    @Override
    public void onFirebaseloadSuccesss(List<Lugares> lugaresList) {
        adaptadorTurismo=new AdaptadorTurismo(getContext(),lugaresList);
        viewPager.setAdapter(adaptadorTurismo);
    }

    @Override
    public void onFirebaseLoadFailed(String mensage) {
        Toast.makeText(getContext(),mensage,Toast.LENGTH_SHORT).show();
    }
}
