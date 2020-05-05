package com.example.notification25022020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button mBtnTao, mBtnDong;
    String CHANNEL_ID = "chanel_01";
    int mNotificationId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnDong = findViewById(R.id.buttonDongNotification);
        mBtnTao = findViewById(R.id.buttonTaoNotification);
        // 1 : Giao dien notifcation : NotificationCompat.Builder
        // 2 : PendingIntent : Sau khi xu ly xong muon ket noi voi intent de xu ly cho viec gi
        // 3 : Quan ly tat ca notification : NotificationManager

        mBtnTao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNotification();
            }
        });
    }

    private void createNotification() {
        // Intent : Thao tac voi notification
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder build =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Có thông báo mới")
                        .setContentText("Bạn nhận được thông báo có version mới cho app")
                        .setWhen(System.currentTimeMillis())
                        .setContentIntent(pendingIntent)
                        .setStyle(new NotificationCompat.BigPictureStyle()
                                .bigPicture(
                                        BitmapFactory.decodeResource(
                                                null,
                                                R.drawable.ic_launcher_background)));

        // Hien thi ra notification
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Notifcation";
            NotificationChannel notificationChannel =
                    new NotificationChannel(
                            CHANNEL_ID,
                            name,
                            NotificationManager.IMPORTANCE_LOW);
            // che độ rung
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        build.setSound(alarmSound);
        notificationManager.notify(mNotificationId, build.build());
    }

}
