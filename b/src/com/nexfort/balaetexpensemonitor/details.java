package com.nexfort.balaetexpensemonitor;
//Selling property as taking commissions makes lots of profit 
// I mean services like IT and management consultancy too
// I mean becoming a salesperson between brokers and consumers who is negotiating, n taking commissions from them. It takes zero investment and 100% profit...ha haXD. Al it needs is hiring awesome sales persons. 




import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class details extends ActionBarActivity {
//	Button buttonbhai;
	myDBClass myDb;
	String s;
	long long_var;
	TextView Amountzero;
	TextView Amounttone;
	
	
	TextView Amountthree;
	TextView Amounttwo;
	
	public static Handler h;
	
	String strr;
	int final_;
	
	
	String totall;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		  myDb = new myDBClass(this);
		  this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
			

		s = getIntent().getStringExtra("IDD");
		int converted=Integer.parseInt(s);
		 final Cursor c = myDb.getRow(converted);
		// final Cursor crow = myDb.getRowx(converted);
		 
		 
			// Not used anywhere
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
		 

		  long_var= Long.parseLong(s);
		 
		 
		TextView tex = (TextView) findViewById(R.id.textView1);
		String chk = c.getString(c.getColumnIndex("Details"));
		
		if (chk != null && !chk.isEmpty()) {
	//	tex.setText(c.getString(c.getColumnIndex("Details")));
			tex.setText(c.getString(c.getColumnIndex("Details")));
		}else{
			tex.setText("- n/a -");
		
			
		}
		
		LinearLayout layzero = (LinearLayout) findViewById(R.id.linearLayout3);
		LinearLayout layone = (LinearLayout) findViewById(R.id.linearLayout2);
		LinearLayout laytwo = (LinearLayout) findViewById(R.id.linearLayout4);	
		LinearLayout laythree = (LinearLayout) findViewById(R.id.linearLayout5);	
		layone.setVisibility(View.GONE);
		layzero.setVisibility(View.GONE);
		laytwo.setVisibility(View.GONE);
		laythree.setVisibility(View.GONE);
		
		
		TextView namezero = (TextView) findViewById(R.id.textView5);
	    Amountzero = (TextView) findViewById(R.id.textView6);
		namezero.setText(c.getString(c.getColumnIndex("Name")));
	//	Amountzero.setText(c.getString(c.getColumnIndex("Amount")));
		
		TextView nameone = (TextView) findViewById(R.id.textView8);
		 Amounttone = (TextView) findViewById(R.id.textView10);
		nameone.setText(c.getString(c.getColumnIndex("Name")));
	//	Amountone.setText(c.getString(c.getColumnIndex("Amount")));
		
		TextView nametwo = (TextView) findViewById(R.id.textView84);
		 Amounttwo = (TextView) findViewById(R.id.textView104);
		nametwo.setText(c.getString(c.getColumnIndex("Name")));
		
		TextView namethree = (TextView) findViewById(R.id.textView85);
		 Amountthree = (TextView) findViewById(R.id.textView105);
		namethree.setText(c.getString(c.getColumnIndex("Name")));
		
		
		// Total amount
		 strr = String.valueOf(myDb.getTotalOfAllMedLevels(s));

		 totall = c.getString(c.getColumnIndex("Amount"));

	 final_ = Integer.parseInt(totall) - Integer.parseInt(strr);

		String tmpStr10 = String.valueOf(final_);
// Remained values
		Amounttone.setText(tmpStr10);
		Amountzero.setText(tmpStr10);
		
// Closed values
		Amounttwo.setText(totall);
		Amountthree.setText(totall);
		
		
		TextView txtID = (TextView) findViewById(R.id.textView3);
		txtID.setText(c.getString(c.getColumnIndex("Day"))+"-"+c.getString(c.getColumnIndex("Month"))+"-"+c.getString(c.getColumnIndex("Year")));
		
		if(final_!=0){
		if (c.getString(c.getColumnIndex("Spinpos")).equals("1")) {
			//  android:id="@+id/linearLayout3" 0
			// android:id="@+id/linearLayout2"  1
		
			
			layone.setVisibility(View.VISIBLE);
			layzero.setVisibility(View.GONE);
	} else{
		layone.setVisibility(View.GONE);
		layzero.setVisibility(View.VISIBLE);
	}
		
	}else{	if (c.getString(c.getColumnIndex("Spinpos")).equals("1")) {
		//  android:id="@+id/linearLayout3" 0
		// android:id="@+id/linearLayout2"  1
	
		
		laythree.setVisibility(View.VISIBLE);
		laytwo.setVisibility(View.GONE);
} else{
	laythree.setVisibility(View.GONE);
	laytwo.setVisibility(View.VISIBLE);
}
	}
		
		
		

		backclick();
		editclick();
		del();
		if(final_ != 0){
		close();
		LinearLayout Line7 = (LinearLayout) findViewById(R.id.line7); 
		Line7.setVisibility(View.GONE);
		}else{
			LinearLayout Line7 = (LinearLayout) findViewById(R.id.line7); 
			Line7.setVisibility(View.VISIBLE);
			
		}
		installs();
		
		
		
	}
	  


	private void installs() {
		// TODO Auto-generated method stub
		final Context context = this;

		LinearLayout button5 = (LinearLayout) findViewById(R.id.line1);
 
		button5.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				
				

			    Intent intent = new Intent(context, installs.class);

			//	 s = getIntent().getStringExtra("IDD");
			    intent.putExtra("ID", s);
                            startActivity(intent);   
 
 
			}
 
		});
		
	}



	private void close() {
		// TODO Auto-generated method stub

		// Delete..........
		final Context context = this;
		 
				LinearLayout button6 = (LinearLayout) findViewById(R.id.line2);
		 
				button6.setOnClickListener(new OnClickListener() {
		 
					@Override
					public void onClick(View arg0) {

						 new AlertDialog.Builder(details.this)
		     		  .setTitle("Close the deal!")
		     		    //.setMessage(Html.fromHtml("Delete! Are you sure?"))
		     		    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
		     		        public void onClick(DialogInterface dialog, int which) { 
		     		            // continue with closing
		     		
		     		        	//1. Picks the current date in......................."date"
		     		        	final Calendar c = Calendar.getInstance();
		     		   		
		     		   		
		     					int year = c.get(Calendar.YEAR);
		     		         	int month = c.get(Calendar.MONTH);
		     			        int day = c.get(Calendar.DAY_OF_MONTH);
		     		           String dayy = Integer.toString(day);
				   	 	       String monthh = Integer.toString(month+1);
				   	 	       String yearr = Integer.toString(year);
		     		        	
		     		        	
		     		        	//2. Add 'closed' to details in red bold letter in......................"details"
		     		       	String detaill="-Closed-";
		     		        	
		     		        	//3. Put remained value in the deduction amount in.. "Amount"
		     		       String amount = Integer.toString(final_);
		     		       
		   	 	        	
		   	 	      
		   	 	         
		   	 	         
		   	 	         

		   	 	        	
		   	 	        	
		   	 	        	
		   	 	       //Concatenating int Values 	
		   	 	      //int datemine= Integer.valueOf(String.valueOf(day)+String.valueOf(mini) + String.valueOf(month) +String.valueOf(mini)+ String.valueOf(year));
		   	 	        	
		   	 	        	// int converted=Integer.parseInt(amount);

		    // Integer.parseInt(totall) - Integer.parseInt(strr);
		   	 	         
		   	 	         int amn= Integer.parseInt(amount);
		   	 	      
		   	 	    myDb.InsertData2(amount,dayy,monthh,yearr,detaill,s);

		   	 	// 2 lines of code to refresh the page after delete 
		        	finish();
		        	startActivity(getIntent()); 
		                       
		   	 	    
		     		        	
		     		        	
		     		        	
		     		        	
		     		        	
		     		        }
		     		     })
		     		    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
		     		        public void onClick(DialogInterface dialog, int which) { 
		     		            // do nothing
		     		        }
		     		     })
		     		    .setIcon(android.R.drawable.ic_dialog_alert)
		     		     .show();
		     		 

							
					}
		 
				});
					
	
		
		
		
		
	}

	private void del() {
		// TODO Auto-generated method stub

		// Delete..........
		final Context context = this;
		 
				LinearLayout button7 = (LinearLayout) findViewById(R.id.line3);
		 
				button7.setOnClickListener(new OnClickListener() {
		 
					@Override
					public void onClick(View arg0) {

						 new AlertDialog.Builder(details.this)
		     		  .setTitle("Delete! Are you sure?")
		     		    //.setMessage(Html.fromHtml("Delete! Are you sure?"))
		     		    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
		     		        public void onClick(DialogInterface dialog, int which) { 
		     		            // continue with delete
		     		        	 
		     		   		
		     		

						 
				        		
				                 	 myDb.deleterow(s);
				                 	Intent intent = new Intent(context, list.class); 
		                            startActivity(intent);    
		                            finish();
		                            
		                            // it helps finish() list after content deletes which is a static h in list class
		                            list.h.sendEmptyMessage(0);
		     		        }
		     		     })
		     		    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
		     		        public void onClick(DialogInterface dialog, int which) { 
		     		            // do nothing
		     		        }
		     		     })
		     		    .setIcon(android.R.drawable.ic_dialog_alert)
		     		     .show();
		     		 

							
					}
		 
				});
					
	
		
		
		
		
	}




	private void backclick() {
		// TODO Auto-generated method stub
		final Context context = this;
		 
		ImageView button = (ImageView) findViewById(R.id.listingx);
 
		button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
			    Intent intent = new Intent(context, list.class);
                            startActivity(intent);   
                        //    finish();
 
			}
 
		});
	}
	
	private void editclick() {
		// TODO Auto-generated method stub
		final Context context = this;
		 
		LinearLayout button = (LinearLayout) findViewById(R.id.line4);
 
		button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
			    Intent intent = new Intent(context, edit.class);

				String s = getIntent().getStringExtra("IDD");
			    intent.putExtra("ID", s);
                            startActivity(intent);   
                      //      finish();
 
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
	        onBackPressed();
	    }

	    
	    
        if (keyCode == KeyEvent.KEYCODE_MENU)
            return true;
       
	    return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onBackPressed() {
	    // This will be called either automatically for you on 2.0
	    // or later, or by the code above on earlier versions of the
	    // platform.
		
          //     Toast.makeText(create.this,
            //        "On Button Click " ,
              //       Toast.LENGTH_LONG).show();

       		//Animation while activity open
		 super.onBackPressed();
       	     overridePendingTransition(R.anim.backin, R.anim.backout);

			 //   Intent intent = new Intent(getBaseContext(), list.class);
               //          startActivity(intent);   
	 //   return;
	}


	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return false;
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
