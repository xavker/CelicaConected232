package com.xavker.celicaconected23.Splash;

import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.xavker.celicaconected23.Navegador.Navegador;
import com.xavker.celicaconected23.R;

public class Splash extends Activity {

private AnimatorSet animatorSet;
ImageView loja,nube1,nube2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        loja=findViewById(R.id.loja);
        nube1=findViewById(R.id.nube1);
        nube2=findViewById(R.id.nube2);

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
}
