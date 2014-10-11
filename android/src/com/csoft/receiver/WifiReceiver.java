package com.csoft.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

public class WifiReceiver extends BroadcastReceiver
{
    static String SSID="csoft";
	@Override
	public void onReceive(Context context, Intent intent) 
	{
		String action=intent.getAction();
		if (ConnectivityManager.CONNECTIVITY_ACTION.equals(action)) 
		{
            NetworkInfo ni = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
            if (ni.getState()==State.CONNECTED&&ni.getType()==ConnectivityManager.TYPE_WIFI) 
            {
                    WifiManager wifi=(WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                    WifiInfo wifiInfo=wifi.getConnectionInfo();
                    String ssid=wifiInfo.getSSID();
                    Log.e("test", "wifi connected!ssid:"+ssid);
            } 
        }
		
	}
	

}
