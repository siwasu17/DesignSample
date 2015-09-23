package com.example.yasu.designsample;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
    //通知系
    NotificationManager notificationManager;
    static final int NOTIFY_ID = 100;

    //アニメーション系
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //アニメーション画像
        ImageView animImg  = (ImageView)findViewById(R.id.anim_image);
        if(animImg != null) {
            animImg.setBackgroundResource(R.drawable.image_animation_list);
            animationDrawable = (AnimationDrawable) animImg.getBackground();
        }

        //Launch ViewPager
        findViewById(R.id.button_01).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itt = new Intent();
                itt.setClass(getApplicationContext(),ViewPagerSample.class);
                startActivity(itt);
            }
        });

        //WebView
        WebView webView = (WebView)findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://www.yahoo.co.jp/");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch(id){
            case R.id.action_add:
                showMyNotification();
                break;
            case R.id.action_remove:
                if(notificationManager != null) {
                    notificationManager.cancel(NOTIFY_ID);
                }
                break;
            case R.id.action_anim_start:
                if(animationDrawable != null) {
                    animationDrawable.start();
                }
                break;
            case R.id.action_anim_stop:
                if(animationDrawable != null) {
                    animationDrawable.stop();
                }
                break;
            case R.id.action_settings:
                Toast.makeText(getApplicationContext(),"設定します",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void showMyNotification(){
        //通知の設定
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(getApplicationContext());
        notifyBuilder.setContentTitle("お知らせです");
        /*
        notifyBuilder.setLargeIcon(
                BitmapFactory.decodeResource(getResources(), android.R.drawable.stat_notify_voicemail)
        );
        */
        notifyBuilder.setSmallIcon(android.R.drawable.stat_notify_more);
        notifyBuilder.setContentText("ノーティフィケーションサンプル");
        notifyBuilder.setTicker("ティッカー");
        //音も鳴らす
        //notifyBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));


        //通知をタップしたらアクティビティを起動するように設定
        Intent resultIntent = new Intent(this,MainActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(
                this,
                0,
                resultIntent,
                0
        );
        //Intentを予約して指定したタイミングで発行するような場合、PendingIntentを使うらしい(時間指定も可能とか)
        notifyBuilder.setContentIntent(resultPendingIntent);

        //表示
        if(notificationManager == null){
            notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        }
        notificationManager.notify(NOTIFY_ID, notifyBuilder.build());
    }


}
