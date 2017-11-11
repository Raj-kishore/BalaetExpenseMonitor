package com.nexfort.balaetexpensemonitor;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;




import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.AdapterView.OnItemClickListener;

public class installs extends ActionBarActivity {
	 LinearLayout yhp;
	 LinearLayout ihp;
	 TextView et;
	 EditText ett;
	 EditText details;

	 myDBClass myDb;
	  private Spinner spinner1;
	    private Button btnSubmit;
	    TextView remain;
	    
	    
	    
		private TextView tvDisplayDate;
		private DatePicker dpResult;
		private Button btnChangeDate;
		ListView lisView1;

		private int year;
		private int month;
		private int day;
		String s;
		  long rowInserted;
		int final_ ;
		
		 ScrollView RLa;
		 RelativeLayout RLa2;

		static final int DATE_DIALOG_ID = 999;
		
		

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_install);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		MediaPlayer mp = MediaPlayer.create(getBaseContext(), R.raw.flip);
	    mp.start();
  	     overridePendingTransition(R.anim.flipin, R.anim.flipout);
   myDb = new myDBClass(this);
s = getIntent().getStringExtra("ID");


  
  RLa = (ScrollView) findViewById(R.id.sc1);
   RLa.setVisibility(View.GONE);
   RLa2 = (RelativeLayout) findViewById(R.id.linearLayout2);
 
   tog();
   showinst();
   
   
DatePicker datepicker = (DatePicker) findViewById(R.id.datePicker1);
datepicker.setVisibility(View.GONE);

   details=(EditText)findViewById(R.id.details);
   et=(TextView)findViewById(R.id.textView1);
ett=(EditText)findViewById(R.id.textView3);




        
	backclick();
	
	
	 

// Button click Listener 
addListenerOnButton();




setCurrentDateOnView();
addListenerOnButton2();

edit();

TextView amoun = (TextView) findViewById(R.id.tvam);
int converted=Integer.parseInt(s);
final Cursor c = myDb.getRow(converted);
//amoun.setText(c.getString(c.getColumnIndex("Amount")));



TextView ssize2g=(TextView)findViewById(R.id.ssize2g);

// Total amount
String strr = String.valueOf(myDb.getTotalOfAllMedLevels(s));
ssize2g.setText(strr);

String totall = c.getString(c.getColumnIndex("Amount"));

final_ = Integer.parseInt(totall) - Integer.parseInt(strr);
//remained
String tmpStr10 = String.valueOf(final_);
amoun.setText(tmpStr10);
  


 remain=(TextView)findViewById(R.id.datexv);
if(final_!=0){
	remain.setText("Remained");
	
}else{
	remain.setText("Deal Closed");

TextView amoun1 = (TextView) findViewById(R.id.tvam);
amoun1.setText("");
	
}



//Listview 

 lisView1 = (ListView)findViewById(R.id.listViewok); 
final Cursor cr = myDb.SelectDatax(s);
	lisView1.setAdapter(new CountryAdapter(this, cr ));
	registerForContextMenu(lisView1);

	final Context context = this;
lisView1.setOnItemClickListener(new OnItemClickListener() {
	
		public void onItemClick(AdapterView<?> arg0, View v, final int position, final long id) {
			// Do your action here
		//	Toast.makeText(getApplicationContext(),"View..."+id,Toast.LENGTH_LONG).show();  
			c.moveToPosition(position);
     //      String iddx = c.getString(c.getColumnIndex("_id"));
		//	String str= Long.toString(id);
			
			
			
			
		
			

			 new AlertDialog.Builder(installs.this)
		  .setTitle("Delete! Are you sure?")
		    //.setMessage(Html.fromHtml("Delete! Are you sure?"))
		    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) { 
		            // continue with delete
		        	
		        	
		        	// 2 lines of code to refresh the page after delete 
		        	finish();
		        	startActivity(getIntent()); 

					//Toast.makeText(getApplicationContext(),"View..."+id,Toast.LENGTH_LONG).show(); 

		            //  	Intent intent = new Intent(context, installs.class); 
                     // startActivity(intent);    
                     // finish();
					String str = String.valueOf(id);
	        		
	                 	 myDb.deleterowx(str);
	                 //	adapter.swapCursor(cr);
	                // 	lisView1.remove(position);
	                //notifyDataSetChanged();
	               //  	Intent intent = new Intent(context, list.class); 
                    //   startActivity(intent);    
                     //  finish();
                       
                       // it helps finish() list after content deletes which is a static h in list class
                     //  list.h.sendEmptyMessage(0);
		        }
		     })
		    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) { 
		            // do nothing
		        }
		     })
		    .setIcon(android.R.drawable.ic_dialog_alert)
		     .show();
		 

				
		

			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
          //   Intent startDayActivity = new Intent(installs.this, details.class);
          //   startDayActivity.putExtra("IDD", iddx);
            //  startActivity(startDayActivity);
              
      };
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
    			// ColID
    	
    	
    	
    		TextView txtIDD = (TextView) view.findViewById(R.id.amount);
    		TextView txtIDDDDDD = (TextView) view.findViewById(R.id.date);
    		TextView det = (TextView) view.findViewById(R.id.detail);
    		//	txtID.setText(cursor.getString(cursor.getColumnIndex("Name")));
    			txtIDD.setText(cursor.getString(cursor.getColumnIndex("Amountx")));
    			
    			String chk = cursor.getString(cursor.getColumnIndex("Detailsx"));
    		
    		
    		if (chk != null && !chk.isEmpty()) {
    			//	tex.setText(c.getString(c.getColumnIndex("Details")));
    					det.setText(cursor.getString(cursor.getColumnIndex("Detailsx")));
    				}else{
    					det.setText("- n/a -");
    				
    					
    				}
    				
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		//	txtIDDDD.setText(cursor.getString(cursor.getColumnIndex("Datte")));
    		//	txtIDDDDD.setText(cursor.getString(cursor.getColumnIndex("Timme")));
    			txtIDDDDDD.setText(cursor.getString(cursor.getColumnIndex("Dayx"))+"-"+cursor.getString(cursor.getColumnIndex("Monthx"))+"-"+cursor.getString(cursor.getColumnIndex("Yearx")));
    		//Use .equals() instead of =="string" in if/else 
    		//	if (cursor.getString(cursor.getColumnIndex("Spinpos")).equals("1")) {
    		//			txtIDDD.setText("To pay");   
    		//	} else{
    			//		txtIDDD.setText("To be paid");
    		//	}
    			
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
    			
    			
    			
    			
    			//LinearLayout RLad =(LinearLayout) view.findViewById(R.id.listd);
  		 	 // Random rnd = new Random(); 
	        	//	  int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));   
	        		//  RLad.setBackgroundColor(color);
    		}

    		@Override
    		public View newView(Context context, Cursor cursor, ViewGroup parent) {
    			// TODO Auto-generated method stub
    			LayoutInflater inflater = LayoutInflater.from(context);
    			View view = inflater.inflate(R.layout.list_items, parent, false);
    			bindView(view, context, cursor);
    			return view;
    			/*
    			 * or   return LayoutInflater.from(context).inflate(R.layout.activity_column, parent, false);
    			 * */
    		}
    		



    }
	
	
	
	
	
	
	

	
	
	



private void showinst() {
		// TODO Auto-generated method stub
	// TODO Auto-generated method stub
	
Button togg2 = (Button) findViewById(R.id.btnn2);

togg2.setOnClickListener(new OnClickListener() {

	@Override
	public void onClick(View v) {

		
		  RLa.setVisibility(View.GONE);
	      //   Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
	       //  RLa2.startAnimation(animation);
	       //  RLa.startAnimation(animation);
		  

TextView amoun = (TextView) findViewById(R.id.tvam);
int converted=Integer.parseInt(s);
final Cursor c = myDb.getRow(converted);
amoun.setText(c.getString(c.getColumnIndex("Amount")));

	      RLa2.setVisibility(View.VISIBLE);
	      Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
	      RLa2.startAnimation(animation2);
	}

});
	}






private void tog() {
		// TODO Auto-generated method stub
	
	Button togg = (Button) findViewById(R.id.togg);

	togg.setOnClickListener(new OnClickListener() {

		@Override
		public void onClick(View v) {

			  RLa2.setVisibility(View.GONE);
			 
			  RLa.setVisibility(View.VISIBLE);
			  //move previously used
			  Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
		   
		      RLa.startAnimation(animation);
		  	int converted=Integer.parseInt(s);
			 final Cursor c = myDb.getRow(converted);
				et.setText(c.getString(c.getColumnIndex("Name")));
		   

		}

	});
	
		
	}






//Costume ScrollView to scroll edittext inside scrollview layout
public class VerticalScrollview extends ScrollView{

    public VerticalScrollview(Context context) {
        super(context);
    }

     public VerticalScrollview(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public VerticalScrollview(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
        }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = ev.getAction();
        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                    Log.i("VerticalScrollview", "onInterceptTouchEvent: DOWN super false" );
                    super.onTouchEvent(ev);
                    break;

            case MotionEvent.ACTION_MOVE:
                    return false; // redirect MotionEvents to ourself

            case MotionEvent.ACTION_CANCEL:
                    Log.i("VerticalScrollview", "onInterceptTouchEvent: CANCEL super false" );
                    super.onTouchEvent(ev);
                    break;

            case MotionEvent.ACTION_UP:
                    Log.i("VerticalScrollview", "onInterceptTouchEvent: UP super false" );
                    return false;

            default: Log.i("VerticalScrollview", "onInterceptTouchEvent: " + action ); break;
        }

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        super.onTouchEvent(ev);
        Log.i("VerticalScrollview", "onTouchEvent. action: " + ev.getAction() );
         return true;
    }
}







	private void edit() {
		// TODO Auto-generated method stub
		EditText dwEdit = (EditText) findViewById(R.id.details);       
		dwEdit.setOnTouchListener(new OnTouchListener() {

		                public boolean onTouch(View view, MotionEvent event) {
		                    // TODO Auto-generated method stub
		                    if (view.getId() ==R.id.details) {
		                        view.getParent().requestDisallowInterceptTouchEvent(true);
		                        switch (event.getAction()&MotionEvent.ACTION_MASK){
		                        case MotionEvent.ACTION_UP:
		                            view.getParent().requestDisallowInterceptTouchEvent(false);
		                            break;
		                        }
		                    }
		                    return false;
		                }
		            });
	}














	private void addListenerOnButton2() {
		// TODO Auto-generated method stub
		tvDisplayDate = (TextView) findViewById(R.id.tvDate);
		//tvDisplayDate.setVisibility(View.GONE);
		dpResult = (DatePicker) findViewById(R.id.datePicker1);

		final Calendar c = Calendar.getInstance();
		
		
				year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);

		// set current date into textview
		tvDisplayDate.setText(new StringBuilder()
			// Month is 0 based, just add 1
			.append(day).append("-").append(month + 1).append("-")
			.append(year).append(" "));

		// set current date into datepicker
		dpResult.init(year, month, day, null);
		
	}




	private void setCurrentDateOnView() {
		// TODO Auto-generated method stub
		btnChangeDate = (Button) findViewById(R.id.btnChangeDate);

		btnChangeDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				showDialog(DATE_DIALOG_ID);

			}

		});
		
	}


	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
		   // set date picker as current date
		   return new DatePickerDialog(this, datePickerListener,
                         year, month,day);
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener datePickerListener
                = new DatePickerDialog.OnDateSetListener() {

		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;

			// set selected date into textview
			tvDisplayDate.setText(new StringBuilder().append(day).append("-").append(month + 1)
			   .append("-").append(year)
			   .append(" "));

			// set selected date into datepicker also
			dpResult.init(year, month, day, null);

		}
	};

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	private void addListenerOnButton() {
		// TODO Auto-generated method stub

	         
	        btnSubmit = (Button) findViewById(R.id.btnSubmit);
	 
	        btnSubmit.setOnClickListener(new OnClickListener() {
	 
	            @Override
	            public void onClick(View v) {
	 
	           
	            	ett=(EditText)findViewById(R.id.textView3);
	            	String ok = ett.getText().toString();
	            	if(ok.equals("") || ok.equals("0")){
	            		
	            		  Toast.makeText(getBaseContext(), "Invalid entry", Toast.LENGTH_SHORT).show();
		 		 	        	
	            	}else{
	                

	                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	                String datte = sdf.format(new Date());
	                
	                SimpleDateFormat sdff = new SimpleDateFormat("HH:mm:ss");
	                String timme  = sdff.format(new Date());
	                
	                
	 	        //	String note=et.getText().toString();
	 	       	String detaill=details.getText().toString();
	 	           
	 	        	String amount=ett.getText().toString();
	 	        	
	 	           String dayy = Integer.toString(day);
	 	          String monthh = Integer.toString(month+1);
	 	         String yearr = Integer.toString(year);
	 	         
	 	         
	 	         

	 	        	
	 	        	
	 	        	
	 	       //Concatenating int Values 	
	 	      //int datemine= Integer.valueOf(String.valueOf(day)+String.valueOf(mini) + String.valueOf(month) +String.valueOf(mini)+ String.valueOf(year));
	 	        	
	 	        	// int converted=Integer.parseInt(amount);

 // Integer.parseInt(totall) - Integer.parseInt(strr);
	 	         
	 	         int amn= Integer.parseInt(amount);
	 	         int fone = final_+1;
	 	       
	 	         
	 	         if(amn<=final_){
	 	    rowInserted =	myDb.InsertData2(amount,dayy,monthh,yearr,detaill,s);

	 	    //2 line of codes refreshes the page
	    	finish();
        	startActivity(getIntent()); 
	 	         }else{
	 	        	 
	 	         if(final_ == 0 ){
	 		 	        Toast.makeText(getBaseContext(), "The Amount is closed", Toast.LENGTH_SHORT).show();
	 		 	         }else{
	 	        	 Toast.makeText(getBaseContext(), "The Amount must be less than "+ fone, Toast.LENGTH_SHORT).show(); 
	 		 	         }
	 	         }
	 	         
		// This is to check weather a new row is inserted 	
		//	if(rowInserted != -1)
			//    Toast.makeText(getBaseContext(), "New row added, row id: " + rowInserted, Toast.LENGTH_SHORT).show();
		//	else
			//    Toast.makeText(getBaseContext(), "Something wrong", Toast.LENGTH_SHORT).show();
 
	            }
	            }
	 
	        });
	 
	    }
		
	








	private void backclick() {
		// TODO Auto-generated method stub
		final Context context = this;
		 
		ImageView button = (ImageView) findViewById(R.id.backbtn);
 
		button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
			    Intent intent = new Intent(context, list.class);
                            startActivity(intent);   
                            finish();
 
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
       	     overridePendingTransition(R.anim.flipin, R.anim.flipout);
       		MediaPlayer mp = MediaPlayer.create(getBaseContext(), R.raw.flip);
    	    mp.start();

			 //   Intent intent = new Intent(getBaseContext(), list.class);
               //          startActivity(intent);   
	 //   return;
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
