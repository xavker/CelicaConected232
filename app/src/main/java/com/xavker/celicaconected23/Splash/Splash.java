package com.xavker.celicaconected23.Splash;

import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.xavker.celicaconected23.Autenticacion.Login;
import com.xavker.celicaconected23.Navegador.Navegador;
import com.xavker.celicaconected23.R;

import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;

public class Splash extends Activity {

    FirebaseAuth mfirebaseAuth;
    FirebaseAuth.AuthStateListener mauthListener;
    private AnimatorSet animatorSet;
    public static  final int REQUESTR_CODE=54563;
    List<AuthUI.IdpConfig > provider= Arrays.asList(
            new AuthUI.IdpConfig.FacebookBuilder().build(),
            new AuthUI.IdpConfig.GoogleBuilder().build(),
            new AuthUI.IdpConfig.PhoneBuilder().build()
    );
ImageView loja,nube1,nube2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        loja=findViewById(R.id.loja);
        nube1=findViewById(R.id.nube1);
        nube2=findViewById(R.id.nube2);


        mfirebaseAuth= FirebaseAuth.getInstance();
        mauthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if(user!=null){
                    Toast.makeText(Splash.this,"Iniciaste Secion",Toast.LENGTH_LONG).show();
                }else {
                    startActivityForResult(AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(provider)
                            .setIsSmartLockEnabled(false)
                            .setTheme(R.style.LoginUIStyle)
                            .setLogo(R.mipmap.ic_escudo_round)
                            .build(),REQUESTR_CODE
                    );
                }
            }
        };


        Animation anim= AnimationUtils.loadAnimation(this, R.anim.loja);
        loja.startAnimation(anim);
        anim= AnimationUtils.loadAnimation(this, R.anim.nube1);
        nube1.startAnimation(anim);
        anim= AnimationUtils.loadAnimation(this, R.anim.nube2);
        nube2.startAnimation(anim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               Intent intent=new Intent(Splash.this, Navegador.class);
               startActivity(intent);
            }
        },3000);


    }

    @Override
    protected void onResume() {
        super.onResume();
        mfirebaseAuth.addAuthStateListener(mauthListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mfirebaseAuth.removeAuthStateListener(mauthListener);
    }

    public void botoncerrarCecion() {
        AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(Splash.this,"Secion Cerrada",Toast.LENGTH_LONG).show();
            }
        });
    }
}
