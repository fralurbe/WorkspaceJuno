package com.fracarlu.runjumprun;

import android.content.Context;
import android.content.Intent;

public class ReceptorBandaAncha extends  android.content.BroadcastReceiver
{	
	@Override
	public void onReceive(Context context, Intent intent)
	{	
		Intent startupBootIntent = new Intent(context, PruebaJuegoAndroid.class);
		startupBootIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      context.startActivity(startupBootIntent);
	}
}

