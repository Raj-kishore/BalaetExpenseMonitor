package com.nexfort.balaetexpensemonitor;


import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class stats extends ActionBarActivity {
//	Button buttonbhai;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stats);

		clickerlist();
		clickerlist1();
		clickerlist3();
		clickerlist4();
	}
	
	private void clickerlist() {
		// TODO Auto-generated method stub
		final Context context = this;
		 
		TextView button = (TextView) findViewById(R.id.textView1);
 
		button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
			    Intent intent = new Intent(context, topay.class);
                            startActivity(intent);   
 
			}
 
		});
	}
	private void clickerlist3() {
		// TODO Auto-generated method stub
		final Context context = this;
		 
		TextView button = (TextView) findViewById(R.id.textView2);
 
		button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
			    Intent intent = new Intent(context, tobepaid.class);
                            startActivity(intent);   
 
			}
 
		});
	}
	private void clickerlist4() {
		// TODO Auto-generated method stub
		final Context context = this;
		 
		TextView button = (TextView) findViewById(R.id.textView5);
 
		button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
			    Intent intent = new Intent(context, byname.class);
                            startActivity(intent);   
 
			}
 
		});
	}
	
	private void clickerlist1() {
		// TODO Auto-generated method stub
		final Context context = this;
		 
		TextView button = (TextView) findViewById(R.id.textView4);
 
		button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
			    Intent intent = new Intent(context, listbydate.class);
                            startActivity(intent);   
 
			}
 
		});
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	
	
}
