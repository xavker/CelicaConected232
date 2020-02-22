package com.xavker.celicaconected23;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    String fotocelica="http://gadcelica.gob.ec/wp-content/uploads/2018/03/escudo-celica-300x300.jpeg";
    Button especifico,atopico;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseMessaging.getInstance().subscribeToTopic("enviaratodos").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(MainActivity.this,"suscrito a topico",Toast.LENGTH_LONG).show();
            }
        });

        especifico=findViewById(R.id.especifico);
        atopico=findViewById(R.id.button2);

        especifico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notificacionespecifica();
            }
        });
        atopico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notificacionatopica();
            }
        });
    }
    private void Notificacionespecifica() {
        RequestQueue  myrequest= Volley.newRequestQueue(getApplicationContext());
        JSONObject json=new JSONObject();
        try{
            String token="cqct-XWZ7sQ:APA91bHi024aL7hjA5Gl6gt2c56suxLnm-ngRIggsfSlUFM1rkT2k40LWJlUkLWcaHjYU8keriAkPrOQtYTh8YN0ghEVWA9a3Gq0MRytMzOMxBCE6j0a6XMNgSO2UPPeKTBxN8FJBFjW";
            json.put("to",token);
            JSONObject notificacion=new JSONObject();
            notificacion.put("titulo","soy un titulo");
            notificacion.put("detalle","soy un detalle");
            notificacion.put("foto",fotocelica);

            json.put("data",notificacion);

            String urlfirebase="https://fcm.googleapis.com/fcm/send";

            JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST,urlfirebase,json,null,null){
                @Override
                public Map<String, String> getHeaders() {
                  Map<String,String> header=new HashMap<>();
                  header.put("content-type","application/json");
                  header.put("authorization","key=AAAAgrTacWo:APA91bELnEU2bwfZ4r1AVFUcYzENX8cSirhZn_Ivx90jAr1clDDktkgP72DI3h-yxTvNgZ8KrbdSLG3BCReXci4XpzMJFKGMVWBK-7hQO_XP01H5-0QjTMZCyaVQ9BOCZ2dokWkNui-N");
                  return header;
                }
            };
            myrequest.add(request);



        }catch (JSONException e){
            e.printStackTrace();
        }


    }
    private void Notificacionatopica() {

        RequestQueue  myrequest= Volley.newRequestQueue(getApplicationContext());
        JSONObject json=new JSONObject();

        try{
           // String token="cqct-XWZ7sQ:APA91bHi024aL7hjA5Gl6gt2c56suxLnm-ngRIggsfSlUFM1rkT2k40LWJlUkLWcaHjYU8keriAkPrOQtYTh8YN0ghEVWA9a3Gq0MRytMzOMxBCE6j0a6XMNgSO2UPPeKTBxN8FJBFjW";
            json.put("to","/topics/"+"enviaratodos");
            JSONObject notificacion=new JSONObject();
            notificacion.put("titulo","soy un titulo");
            notificacion.put("detalle","soy un detalle");
            notificacion.put("foto",fotocelica);
            json.put("data",notificacion);

            String urlfirebase="https://fcm.googleapis.com/fcm/send";

            JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST,urlfirebase,json,null,null){
                @Override
                public Map<String, String> getHeaders() {
                    Map<String,String> header=new HashMap<>();
                    header.put("content-type","application/json");
                    header.put("authorization","key=AAAAgrTacWo:APA91bELnEU2bwfZ4r1AVFUcYzENX8cSirhZn_Ivx90jAr1clDDktkgP72DI3h-yxTvNgZ8KrbdSLG3BCReXci4XpzMJFKGMVWBK-7hQO_XP01H5-0QjTMZCyaVQ9BOCZ2dokWkNui-N");
                    return header;
                }
            };
            myrequest.add(request);



        }catch (JSONException e){
            e.printStackTrace();
        }


    }
}
