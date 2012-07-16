package com.fracarlu.runjumprun;

//import android.app.FragmentManager;
//import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.fracarlu.runjumprun.Engine.*;

public class PruebaJuegoAndroid extends AndroidApplication
{
	private final static int SHOW_WEB = 0;
	private final static int HIDE_WEB = 1;
	private boolean inWebView;
	RelativeLayout layout;
	WebView webView = null;
	View gameView;
	 
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		try
		{
			super.onCreate(savedInstanceState);
			layout = new RelativeLayout(this);			
			setWebView();
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			getWindow().setFlags(
					WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN);
			getWindow().clearFlags(
					WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
			this.gameView = initializeForView(new Juego(), false);
			layout.addView(this.gameView);
			setContentView(layout);
		} 
		catch (Exception ex)
		{
			Gdx.app.log("errror", ex.toString());
		}
	}

	// Intento para ver si puedo cargar la configuracion de la webview desde un xml 
	private void setWebView2()
	{
		try
		{
			this.webView = new WebView(this);
			this.webView = (WebView) findViewById(R.id.idwebview);
			//aqui falla porque no esta asignado el content
			// this.webView.getSettings().setJavaScriptEnabled(true);
			if (this.webView != null)
			{
				this.webView.loadUrl("http://www.androidpeople.com");
				this.webView.setWebViewClient(new WebViewClient());
			}
					
		}
		catch (Exception ex)
		{
			Gdx.app.log("Error", ex.toString());
		}
	}

	private void setWebView()
	{
		try
		{
			if (checkConnection())
			{
				this.webView = new WebView(this);
				this.webView.getSettings().setJavaScriptEnabled(true);
				this.webView.getSettings().setUseWideViewPort(false);
				
				this.webView.requestFocus(View.FOCUS_DOWN);
				this.webView.setOnTouchListener(new View.OnTouchListener()
				{
					public boolean onTouch(View v, MotionEvent event)
					{
						switch (event.getAction())
						{
						case MotionEvent.ACTION_DOWN:
						case MotionEvent.ACTION_UP:
							if (!v.hasFocus())
							{
								v.requestFocus();
							}
							break;
						}
						return false;
					}
				});
	
				this.webView.setWebViewClient(new WebViewClient());				
	
				RelativeLayout.LayoutParams wParams = new RelativeLayout.LayoutParams(320, 240);							
				
				// wParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
				// wParams
				// params.addRule(RelativeLayout.ALIGN_PARENT_LEFT,
				// RelativeLayout.TRUE);
				
				layout.addView(this.webView, wParams);
	
				showWeb(false);				
			}
		}
		catch (Exception ex)
		{
			Gdx.app.log("error", ex.toString());
		}
	}

	public Handler webHandler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			switch (msg.what)
			{
			case HIDE_WEB:
				PruebaJuegoAndroid.this.webView.setVisibility(View.GONE);
				PruebaJuegoAndroid.this.gameView.setVisibility(View.VISIBLE);
				PruebaJuegoAndroid.this.inWebView = false;
				break;
			case SHOW_WEB:
			{
				try
				{
					openUri("http://slashdot.org/");
					PruebaJuegoAndroid.this.webView.setVisibility(View.VISIBLE);
					PruebaJuegoAndroid.this.gameView.setVisibility(View.GONE);
					PruebaJuegoAndroid.this.inWebView = true;
				} 
				catch (Exception ex)
				{
					Gdx.app.log("error", ex.toString());
				}
			}
				break;
			default:
				break;
			}
		}
	};

	public boolean checkConnection()
	{
		try
		{
			ConnectivityManager conMgr = (ConnectivityManager) this
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo i = conMgr.getActiveNetworkInfo();
			if (i == null)
				return false;
			if (!i.isConnected())
				return false;
			if (!i.isAvailable())
				return false;
		} catch (Exception ex)
		{
			Gdx.app.log("error", ex.toString());
		}
		return true;
	}

	public void showWeb(boolean show)
	{
		this.webHandler.sendEmptyMessage(show ? PruebaJuegoAndroid.SHOW_WEB
				: PruebaJuegoAndroid.HIDE_WEB);
	}

	public void openUri(String uri)
	{
		Uri _uri = Uri.parse(uri);
		Intent intent = new Intent(Intent.ACTION_VIEW, _uri);
		this.startActivity(intent);
		intent = null;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_0)
		{
			if (this.inWebView)
			{
				showWeb(false);
			} 
			else
			{
				showWeb(true);
			}
			return super.onKeyDown(keyCode, event);
		}

		return super.onKeyDown(keyCode, event);
	}

}

/*
 
*/