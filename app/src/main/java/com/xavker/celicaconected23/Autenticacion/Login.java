package com.xavker.celicaconected23.Autenticacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.xavker.celicaconected23.R;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;


public class Login extends AppCompatActivity {

    FirebaseAuth mfirebaseAuth;
    FirebaseAuth.AuthStateListener mauthListener;
    public static  final int REQUESTR_CODE=54563;
    List<AuthUI.IdpConfig > provider= Arrays.asList(
      new AuthUI.IdpConfig.FacebookBuilder().build(),
            new AuthUI.IdpConfig.GoogleBuilder().build(),
            new AuthUI.IdpConfig.PhoneBuilder().build()
    );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_login);
        mfirebaseAuth= FirebaseAuth.getInstance();
        mauthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if(user!=null){
                    Toast.makeText(Login.this,"Iniciaste Secion",Toast.LENGTH_LONG).show();
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

    public void botoncerrarCecion(View view) {
        AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(Login.this,"Secion Cerrada",Toast.LENGTH_LONG).show();
            }
        });
    }
}
