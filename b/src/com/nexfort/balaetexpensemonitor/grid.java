package com.nexfort.balaetexpensemonitor;




import android.support.v7.app.ActionBarActivity;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class grid extends ActionBarActivity {
//	Button buttonbhai;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.material);

		

		 LinearLayout lay11 = (LinearLayout) findViewById(R.id.linearLayoutt2);
		 lay11.setVisibility(View.GONE);
		//Animation while activity open
	     overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
		 final LinearLayout button3 = (LinearLayout) findViewById(R.id.linearLayoutt);

			this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
			

         Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.gridmove);
        button3.startAnimation(animation);
        
     // Stackoverflow green color #5fba7d
		

	clickerlist();
	name();
	date();
topay();	
tobepaid();
settings();
flip();
flip2();
	
// to pay
      myDBClass myDb = new myDBClass(this);


	  int tota = myDb.topay();
	  int deductions = myDb.topayidinsta();
	  int rem = tota - deductions ;
	  String thats  = Integer.toString(rem);
	  
	  // tobe paid
	  int tota1 = myDb.tobepaid();
	  int deductions1 = myDb.topayidinsta2();
	  int rem1 = tota1 - deductions1 ;
	  String thats1  = Integer.toString(rem1);
	  
	  
	  
	 if(rem > rem1){
		  
		  TextView taf1 = (TextView) findViewById(R.id.textView7);
		  int remrem1 = rem - rem1;

		  String thatsok  = Integer.toString(remrem1);
		  taf1.setText(thatsok);
		  taf1.setTextColor(Color.parseColor("#303f9f"));
		  ImageView me = (ImageView) findViewById(R.id.imageView8);
		  me.setImageResource(R.drawable.too);
	  }else if(rem < rem1){
	  TextView taf1 = (TextView) findViewById(R.id.textView7);
	  int rem1rem = rem1 - rem;

	  String thatsok1  = Integer.toString(rem1rem);
	  taf1.setText(thatsok1);
	  taf1.setTextColor(Color.parseColor("#B71C1C"));
	  ImageView me = (ImageView) findViewById(R.id.imageView8);
	  me.setImageResource(R.drawable.from1);
	  }else{
		  TextView taf1 = (TextView) findViewById(R.id.textView7);
		  taf1.setText("Equilibrium");

		  taf1.setTextColor(Color.BLACK);
		  
		  ImageView me = (ImageView) findViewById(R.id.imageView8);
		  me.setImageResource(R.drawable.justice);
	  }
	  

	//  }


	}
	



	private void flip() {
		// 		// TODO Auto-generated method stub
		final Context context = this;
		 

		LinearLayout button1 = (LinearLayout) findViewById(R.id.Lays4);
 
		button1.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				
				MediaPlayer mp = MediaPlayer.create(context, R.raw.flip);
			    mp.start();
				
 LinearLayout lay1 = (LinearLayout) findViewById(R.id.linearLayoutt);

 LinearLayout lay12 = (LinearLayout) findViewById(R.id.linearLayoutt2);
 lay12.setVisibility(View.VISIBLE);
 lay1.setVisibility(View.GONE);
 


 Animation flipin = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.flipin);
lay12.startAnimation(flipin);
Animation flipout = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.flipout);
lay1.startAnimation(flipout);

 
			}
 
		});
		
	}

	private void flip2() {
		// 		// TODO Auto-generated method stub
		final Context context = this;
		 

		LinearLayout button1 = (LinearLayout) findViewById(R.id.Lays42);
 
		button1.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				
				MediaPlayer mp = MediaPlayer.create(context, R.raw.flip);
			    mp.start();
				
 LinearLayout lay1 = (LinearLayout) findViewById(R.id.linearLayoutt);

 LinearLayout lay12 = (LinearLayout) findViewById(R.id.linearLayoutt2);
 lay12.setVisibility(View.GONE);
 lay1.setVisibility(View.VISIBLE);
	
 
 

 Animation flipin = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.flipin);
lay1.startAnimation(flipin);
Animation flipout = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.flipout);
lay12.startAnimation(flipout);

 
			}
 
		});
		
	}




	private void settings() {
		// TODO Auto-generated method stub
		final Context context = this;
		 

		LinearLayout button1 = (LinearLayout) findViewById(R.id.lay6);
 
		button1.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
			    Intent intent = new Intent(context, settings.class);
                            startActivity(intent);   
 
			}
 
		});
	}
	

	private void clickerlist() {
		// TODO Auto-generated method stub
		final Context context = this;
		 

		LinearLayout button1 = (LinearLayout) findViewById(R.id.LinearLayoutx);
 
		button1.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
			    Intent intent = new Intent(context, list.class);
                            startActivity(intent);   
 
			}
 
		});
	}
	private void name() {
		// TODO Auto-generated method stub
		final Context context = this;
		 
		LinearLayout button2 = (LinearLayout) findViewById(R.id.Lay4);
 
		button2.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
			    Intent intent = new Intent(context, byname.class);
                            startActivity(intent);   
 
			}
 
		});
	}
	private void date() {
		// TODO Auto-generated method stub
		final Context context = this;
		 

		LinearLayout button3 = (LinearLayout) findViewById(R.id.Lay5);
 
		button3.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
			    Intent intent = new Intent(context, listbydate.class);
                            startActivity(intent);   
 
			}
 
		});
	}
	private void topay() {
		// TODO Auto-generated method stub
		final Context context = this;
		 

		LinearLayout button3 = (LinearLayout) findViewById(R.id.Lay2);
 
		button3.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
			    Intent intent = new Intent(context, topay.class);
                            startActivity(intent);   
 
			}
 
		});
	}
	private void tobepaid() {
		// TODO Auto-generated method stub
		final Context context = this;
		 

		LinearLayout button4 = (LinearLayout) findViewById(R.id.Lay3);
 
		button4.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
			    Intent intent = new Intent(context, tobepaid.class);
                            startActivity(intent);   
 
			}
 
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)  {
	    if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.ECLAIR
	            && keyCode == KeyEvent.KEYCODE_BACK
	            && event.getRepeatCount() == 0) {
	        // Take care of calling this method on earlier versions of
	        // the platform where it doesn't exist.
	      
	    }

	    
	    
        if (keyCode == KeyEvent.KEYCODE_MENU)
            return true;
       
	    return super.onKeyDown(keyCode, event);
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
