package com.zippr.firebasezippr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;
import android.widget.TextView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdReceiver;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by nagachaitanya on 25/9/16.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public void onTokenRefresh() {

        //Getting registration token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        System.out.print(FirebaseInstanceId.getInstance().getToken());



        SharedPreferences f = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String savedToken = f.getString("token", null);
        if (savedToken==null){
            SharedPreferences.Editor editor = f.edit();
            editor.putString("token", refreshedToken);
            editor.commit();
        }
        Intent i = new Intent("my.local.intent");
        i.putExtra("token", refreshedToken==null ? savedToken :refreshedToken);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(i);

        //FirebaseInstanceId.getInstance().getToken();
        //Toast.makeText(getApplicationContext(), "Refreshed token:" + refreshedToken, Toast.LENGTH_LONG).show();

        //((TextView)findViewById(R.id.textView)).setText("Token: " + refreshedToken);
        //Displaying token on logcat
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        Log.i(TAG,"Refreshed token:");

    }

    private void sendRegistrationToServer(String token) {
        //You can implement this method to store the token on your server
        //Not required for current project
    }
}
