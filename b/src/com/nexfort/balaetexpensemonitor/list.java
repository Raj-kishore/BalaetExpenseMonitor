package com.nexfort.balaetexpensemonitor;


import java.util.Random;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class list extends ActionBarActivity {
	myDBClass myDb;
	public static Handler h;
	ImageView img;
	ImageView img2;
	
	private int lastPosition = -1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		MediaPlayer mp = MediaPlayer.create(getBaseContext(), R.raw.rotate);
	    mp.start();
		

		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		// Button button = (Button) findViewById(R.id.button1);
        // Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
         //button.startAnimation(animation);
		
		
		// This is a static handler
		//Now, from any activity after this one, such asACTIVITY_B, or ACTIVITY_C I can call

//ACTIVITY_A.h.sendEmptyMessage(0);
//which then calls finish() in ACTIVITY_A i.e here it is list class  and ta-da! ACTIVITY_A is finished from a different activity.
	//When I click back btn after delete from details, list class appears without refresh, so 
		//this methid helps to finish previous activity if current activity. 
		
		h = new Handler() {

	        public void handleMessage(Message msg) {
	            super.handleMessage(msg);

	            switch(msg.what) {

	            case 0:
	                finish();
	                break;

	            }
	        }

	    };
		
		
	 createlist();
	  myDb = new myDBClass(this);
	  Button button = (Button) findViewById(R.id.button1);
	  Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
	  button.startAnimation(animation);
	 
	 

	     LinearLayout emptys = (LinearLayout)findViewById(R.id.empty);
	     emptys.setVisibility(View.GONE);
	
	 
	 
	 
     ListView lisView1 = (ListView)findViewById(R.id.listView1); 
     
     Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zz);
     lisView1.startAnimation(animation1);
	 
     final Cursor c = myDb.joinerselect();
     
     
   //  String index = c.getString(c.getColumnIndex("Name"));
   
     
     if(c!=null && c.getCount()>0){
    	  emptys.setVisibility(View.GONE);
    	 
    	 lisView1.setAdapter(new CountryAdapter(this, c ));
		registerForContextMenu(lisView1);
		
		
		
	 lisView1.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
				// Do your action here
				//Toast.makeText(getApplicationContext(),"View..."+id,Toast.LENGTH_LONG).show();  
				c.moveToPosition(position);
                String iddx = c.getString(c.getColumnIndex("_id"));
				String str= Long.toString(id);
                  Intent startDayActivity = new Intent(list.this, details.class);
                  startDayActivity.putExtra("IDD", iddx);
                   startActivity(startDayActivity);
               
           };
		});
		
			
    }else{

	     emptys.setVisibility(View.VISIBLE);
     }

 Object anim = AnimationUtils.loadAnimation(this, R.anim.list);
	 
	 
	 
	}
	
	private void createlist() {
		// TODO Auto-generated method stub
		final Context context = this;
		 
		Button button = (Button) findViewById(R.id.button1);
 
		button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
			    Intent intent = new Intent(context, create.class);
                            startActivity(intent);  
                          //  finish();
 
			}
 
		});
	}
	
	

	
	public class CountryAdapter extends CursorAdapter 
    {   
    	    private Cursor cursor;
            public CountryAdapter(Context context, Cursor cur) {
    	    super(context, cur , 0);
    	    this.cursor = cur;
    	   // initializes all items value with false
    	    
                                                          }
            
    @Override
    public void bindView(View view, Context context, final Cursor cursor) {
    	
   	// int position = cursor.getPosition();
		
   	// Animation animation = AnimationUtils.loadAnimation(getBaseContext(), (position > lastPosition) ? R.anim.up_from_bttom : R.anim.down_from_top);
	 //   view.startAnimation(animation);
	  //  lastPosition = position;
	    
	    

    			// ColID
    		TextView txtID = (TextView) view.findViewById(R.id.textsv);
    		TextView txtIDD = (TextView) view.findViewById(R.id.textsvv);
    		ImageView txtIDDD = (ImageView) view.findViewById(R.id.textsvvv);
    		TextView txtIDDDD = (TextView) view.findViewById(R.id.textViewx);
    		TextView txtIDDDDD = (TextView) view.findViewById(R.id.textViewxy);
    		TextView txtIDDDDDD = (TextView) view.findViewById(R.id.textsvvvv);
    			txtID.setText(cursor.getString(cursor.getColumnIndex("Name")));
    			txtIDD.setText(cursor.getString(cursor.getColumnIndex("Amount")));
    	
    			txtIDDDD.setText(cursor.getString(cursor.getColumnIndex("Datte")));
    			txtIDDDDD.setText(cursor.getString(cursor.getColumnIndex("Timme")));
    			txtIDDDDDD.setText(cursor.getString(cursor.getColumnIndex("Day"))+"-"+cursor.getString(cursor.getColumnIndex("Month"))+"-"+cursor.getString(cursor.getColumnIndex("Year")));
    		//Use .equals() instead of =="string" in if/else 
    		
    			LinearLayout RLa =(LinearLayout) view.findViewById(R.id.textfull);
    		 //	  Random rnd = new Random(); 
	        	//	  int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));   
	       // 	int color = Color.rgb(95, 186, 125);
    		//	RLa.setBackgroundColor(color);		
    		    
	        		  
	        		  
	        		  String totalr = cursor.getString(cursor.getColumnIndex("Amount"));
	        		String Remainr=	cursor.getString(cursor.getColumnIndex("Amountx"));
	        		
	        		 img = (ImageView) view.findViewById(R.id.closed);
	        		 img2 = (ImageView) view.findViewById(R.id.opened);
	        		
	        	// Integer conversion and integers in bindview doesn't work. Only strings accepted	
	        	//	int t = Integer.parseInt(totalr);
	        	//	int r=  Integer.parseInt(Remainr);
	        		
	        	//	int f = t - r ;
	        		
	        	//	String fin = String.valueOf(f);
	        		 
	        		 
	        		 
	        		 //android:animateLayoutChanges="true" is used in activity_list closed and opened imageviews to compromise the delay between setvisibility and startanimation
	        		  if(totalr.equals(Remainr)){
    				
    			img.setVisibility(View.VISIBLE);
    			img2.setVisibility(View.GONE);
    		    Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move2);
    		    img.startAnimation(animation2);
    				
    			}else{
    				img.setVisibility(View.GONE);
    				img2.setVisibility(View.VISIBLE);
        		    Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move2);
        		    img2.startAnimation(animation2);
    				
    			}
    			
    			
    			
    			
    			final int sdk = android.os.Build.VERSION.SDK_INT;
    		
    			
    			if (cursor.getString(cursor.getColumnIndex("Spinpos")).equals("1")) {
    				if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
        			    txtIDDD.setBackgroundDrawable( getResources().getDrawable(R.drawable.to) );
        			} else {
        			    txtIDDD.setBackground( getResources().getDrawable(R.drawable.to));
        			} 
    			} else{
    				if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
        			    txtIDDD.setBackgroundDrawable( getResources().getDrawable(R.drawable.from1) );
        			} else {
        			    txtIDDD.setBackground( getResources().getDrawable(R.drawable.from1));
        			} 
    			}
    			
    		// txtChe.setChecked((cursor.getInt(cursor.getColumnIndex("ischecked"))==0? false:true));
    		
    			/*
    			 * 
    			 * CursorAdapter is a subclass of BaseAdapter.

    Android maintains a pool of views for a ListView which it will give
    to you so you can reuse it instead of creating a new view each time.
    In BaseAdapter, you will have a function called getView(),
    to which one of the parameters is a View object named convertView.
    Basically, this convertView will be null if the list is being loaded 
    for the first time, and it will not be null once you start sliding 
    the list. Therefore, in the getView() method of your BaseAdapter, 
    you will check if convertView is null. If yes, you will inflate it.
    Then you can use the view and set its elements as normal. 
    This will improve the scrolling performance of a listview tremendously.

    A CursorAdapter makes it easy to use when the data source of a listview
    is a database. In a cursor adapter, however, Android takes care of checking 
    whether the convertView is null or not. In the newView() method,
    you simply inflate the view and return it. 
    In the bindView() method, you set the elements of your view.

    As an example, imagine a listview on a device which can show upto
    11 list items on the screen. In this case, newView() will be 
    called upto 11 times. However, bindView() will be called many times
    whenever you scroll the list view. The 11 views you created in your
    newView method will be reused again and again as you scroll the list.
    			 * 
    			 */
    			
    						//Boolean bi = Boolean.valueOf(cursor.getString(cursor.getColumnIndex("ischecked")));
    						//txtChe.setChecked(bi);
    		   
    
    
    }

    		@Override
    		public View newView(Context context, Cursor cursor, ViewGroup parent) {
    			// TODO Auto-generated method stub
    			LayoutInflater inflater = LayoutInflater.from(context);
    			View view = inflater.inflate(R.layout.item_layout, parent, false);
    			bindView(view, context, cursor);
    			
    		
    			
    			return view;
    			/*
    			 * or   return LayoutInflater.from(context).inflate(R.layout.activity_column, parent, false);
    			 * */
    		}
    		



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
	
}
