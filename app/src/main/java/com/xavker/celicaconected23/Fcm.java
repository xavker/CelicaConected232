package com.xavker.celicaconected23;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

public class Fcm extends FirebaseMessagingService {
    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.e("token","mi tocken es:"+s);
        guardarToken(s);
    }

    private void guardarToken(String s) {
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("token");
        ref.child("usuario").setValue(s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        String from=remoteMessage.getFrom();
        Log.e("tag","mensaje recibido de "+ from );

       /* if(remoteMessage.getNotification()!=null){
            Log.e("tag","el titulo es"+remoteMessage.getNotification().getTitle());
            Log.e("tag","el cuerpo es"+remoteMessage.getNotification().getBody());

        }*/
        if(remoteMessage.getData().size()>0){
           // Log.e("tag","el titulo 2 es: "+remoteMessage.getData().get("titulo"));
           // Log.e("tag","el detalle 2 es: "+remoteMessage.getData().get("detalle"));
           // Log.e("tag","el color es 2: "+remoteMessage.getData().get("color"));
            String titulo=remoteMessage.getData().get("titulo");
            String detalle=remoteMessage.getData().get("detalle");

            notificaciondependelaversionoreo(titulo,detalle);


        }



    }

    private void notificaciondependelaversionoreo(String titulo,String detalle) {
        String id="mensaje";
        NotificationManager nm=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,id);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel nc=new NotificationChannel(id,"nuevo",NotificationManager.IMPORTANCE_HIGH);
            nc.setShowBadge(true);
            assert  nm!=null;
            nm.createNotificationChannel(nc);
        }
        builder.setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setContentTitle(titulo)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(IngresaralaApp())
                .setContentText(detalle);

        Random random=new Random();
        int idNotify=random.nextInt(8000);
        assert nm!=null;
        nm.notify(idNotify,builder.build());




    }
    public PendingIntent IngresaralaApp(){
        Intent i=new Intent(getApplicationContext(),MainActivity.class);
        i.putExtra("color","rojo");
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return PendingIntent.getActivity(this,0,i,0);
    }

}
