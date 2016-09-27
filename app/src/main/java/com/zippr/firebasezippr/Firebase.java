package com.zippr.firebasezippr;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Firebase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        SharedPreferences f = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String savedToken = f.getString("token", null);
        if (savedToken != null) {
            ((EditText) findViewById(R.id.textView2)).setText(savedToken);
        }


        IntentFilter i = new IntentFilter();
        i.addAction("my.local.intent");
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, i);
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ((EditText) findViewById(R.id.textView2)).setText( intent.getStringExtra("token"));
        }
    };
}
