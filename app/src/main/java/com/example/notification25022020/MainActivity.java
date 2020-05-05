package com.example.notification25022020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button mBtnTao,mBtnDong;
    String CHANNEL_ID ="chanel_01";
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
        NotificationCompat.Builder build =
                new NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Có thông báo mới")
                .setContentText("Bạn nhận được thông báo có version mới cho app")
                .setWhen(System.currentTimeMillis())
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(
                                BitmapFactory.decodeResource(
                                        null ,
                                        R.drawable.ic_launcher_background)))
    }

}
