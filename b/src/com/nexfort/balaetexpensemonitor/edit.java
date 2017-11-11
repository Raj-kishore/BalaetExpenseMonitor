package com.nexfort.balaetexpensemonitor;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class edit extends ActionBarActivity {
	 LinearLayout yhp;
	 LinearLayout ihp;
	 EditText et;
	 EditText ett;
	 EditText details;

	 TextView tvDate;
		String s ;

	 myDBClass myDb;
	  private Spinner spinner1;
	    private Button btnSubmit;
	    
	    
	    
		private TextView tvDisplayDate;
		private DatePicker dpResult;
		private Button btnChangeDate;

		private int year;
		private int month;
		private int day;
		String strr ;
		
		

		static final int DATE_DIALOG_ID = 999;
		
		

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
	
		  this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
			


		//Animation while activity open
	     overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
		   
   myDb = new myDBClass(this);
	s = getIntent().getStringExtra("ID");
	int converted=Integer.parseInt(s);
	 final Cursor c = myDb.getRow(converted);
   
   
    DatePicker datepicker = (DatePicker) findViewById(R.id.datePicker1);
    datepicker.setVisibility(View.GONE);

    details=(EditText)findViewById(R.id.details);
    et=(EditText)findViewById(R.id.textView1);
    ett=(EditText)findViewById(R.id.textView3);
    tvDate=(TextView)findViewById(R.id.tvDate);
        
	backclick();
	
	
	  spinner1 = (Spinner) findViewById(R.id.spinner1);
	
	  
	  
	  
	  
	  
	  
	  
	  trigger();
	  
	  
	  
	  
	  
      List<String> list = new ArrayList<String>();
      list.add("You Have To Pay Me");
      list.add("I Have To Pay You");
    
      
ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
      (this, android.R.layout.simple_spinner_item,list);
       
dataAdapter.setDropDownViewResource
      (android.R.layout.simple_spinner_dropdown_item);
       
spinner1.setAdapter(dataAdapter);

String position = c.getString(c.getColumnIndex("Spinpos"));
		int pos = Integer.parseInt(position);	  
	  spinner1.setSelection( pos );

// Spinner item selection Listener  
//addListenerOnSpinnerItemSelection();

// Button click Listener 
addListenerOnButton();
setCurrentDateOnView();
addListenerOnButton2();
edit();






	et.setText(c.getString(c.getColumnIndex("Name")));
// strr is total deduction
strr = String.valueOf(myDb.getTotalOfAllMedLevels(s));

	String si = getIntent().getStringExtra("ID");
	int convertedsi=Integer.parseInt(si);
	 final Cursor csi = myDb.getRow(convertedsi);
String totall = csi.getString(csi.getColumnIndex("Amount"));
	ett.setText(totall);
	details.setText(c.getString(c.getColumnIndex("Details")));
	tvDate.setText(c.getString(c.getColumnIndex("Day"))+"-"+c.getString(c.getColumnIndex("Month"))+"-"+c.getString(c.getColumnIndex("Year")));

	
	//How to set focus to right of text in EditText for android
	et.setSelection(et.getText().length());

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










	
	private void trigger() {
		// TODO Auto-generated method stub
		final Context context = this;
		 
		ImageView button = (ImageView) findViewById(R.id.imageView1);
 
		button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
				spinner1.performClick();
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

	      spinner1 = (Spinner) findViewById(R.id.spinner1);
	         
	        btnSubmit = (Button) findViewById(R.id.btnSubmit);
	 
	        btnSubmit.setOnClickListener(new OnClickListener() {
	 
	            @Override
	            public void onClick(View v) {
	 
	            //    Toast.makeText(edit.this,
	              //          "On Button Click : " + 
	               //         "\n" + String.valueOf(spinner1.getSelectedItemPosition()) ,
	                 //       Toast.LENGTH_LONG).show();
	                

	      
	                
	                int spinposition=spinner1.getSelectedItemPosition();
	                String spins = Integer.toString(spinposition);
	 	        	String name=et.getText().toString();
	 	       	String detaill=details.getText().toString();
	 	           
	 	        	String amount=ett.getText().toString();
	 	        	
	 	           String dayy = Integer.toString(day);
	 	          String monthh = Integer.toString(month+1);
	 	         String yearr = Integer.toString(year);

		 			String strThatDay = ""+yearr+"/"+monthh+"/"+dayy+"";
		 			  SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		 			  Date d = null;
		 			  try {
		 			   d = formatter.parse(strThatDay);//catch exception
		 			  } catch (ParseException e) {
		 			   // TODO Auto-generated catch block
		 			   e.printStackTrace();
		 			  } 

		 			  Calendar thatDay = Calendar.getInstance();
		 			  thatDay.setTime(d);
		 			  
		 			  // A reference date to substract from it.
		 				String strToDay = "2016/12/10";
		 				  SimpleDateFormat formatterr = new SimpleDateFormat("yyyy/MM/dd");
		 				// String strToDay = formatterr.format(new Date());
		 				  Date da = null;
		 				  try {
		 				   da = formatterr.parse(strToDay);//catch exception
		 				  } catch (ParseException e) {
		 				   // TODO Auto-generated catch block
		 				   e.printStackTrace();
		 				  } 

		 				  Calendar toDay = Calendar.getInstance();
		 				  toDay.setTime(da);
		 			  
		 			  
		 			  
		 			//For today give a reference day instead of current date  
		 			  long diff = toDay.getTimeInMillis() - thatDay.getTimeInMillis(); //result in millis
		 	        	
		 			 String milise = String.valueOf(diff);
		 			
		 			long m= Long.parseLong(milise);
		 			long div = 24*60*60*10*10*10;
		 			 long mn= m/div;

		 	           String milisec = Long.toString(mn);	
		 	           
		 	           
		 	           int amoun = Integer.parseInt(amount);
		 	           int str =  Integer.parseInt(strr);
		 	           
		 	           
		 	          
		                	
	 	        	
	 	       //Concatenating int Values 	
	 	      //int datemine= Integer.valueOf(String.valueOf(day)+String.valueOf(mini) + String.valueOf(month) +String.valueOf(mini)+ String.valueOf(year));
	 	        	
	 	        	// int converted=Integer.parseInt(amount);
		 	           
		 	           if(amoun >= str){
		 	        	   
		 	        	  if(amoun == 0){
		 	        		  
		 	        		  
			 	        	  Toast.makeText(edit.this,
				                        "Enter a valid amount",
				                        Toast.LENGTH_LONG).show(); 
			 	           
			 	           
			 	           }else{
			 	        	   
			 	        	   
			 	          	
			 		 	      	if (name != null && !name.isEmpty()) {
			 		 	      		
			 	      		
			 		 	    		
			myDb.updateRow(s,name,amount,dayy,monthh,yearr,detaill,milisec,spins);

		      Intent startDayActivity = new Intent(edit.this, details.class);
              startDayActivity.putExtra("IDD", s);
               startActivity(startDayActivity);
                        finish();
 	     
                        
			 		 	           
			 		 	     }else{
			 		 	     	    Toast.makeText(edit.this,
					                       "Name Field Is Missing.",
					                        Toast.LENGTH_LONG).show(); 	 
			 		 	    	 
			 		 	     }
			 	           }
		 	           }else{
		 	        	 //   Toast.makeText(edit.this,
			               //         "Editted Amount Should Be Same As Or Bigger Than Total Deduction Amount "+strr+" ,Or Reduce Total Deduction Amount Same As Or Below The Editted Amount",
			                 //       Toast.LENGTH_LONG).show(); 
		 	        	   
	new AlertDialog.Builder(edit.this)
			     		  //.setTitle("Close the deal!")
	.setMessage(Html.fromHtml("<b><font color=#ff0000>Total Deduction Amount is "+strr+".</font></b><br/> <i><b>Oops</b>, Editted Amount Should Be At Least An Amount <b> "+strr+"</b><i/> ,<br/>Or, First Reduce Total Deduction Amount To Less Than Or Equal To An Amount That You Want To Edit."))
	.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
			     		        public void onClick(DialogInterface dialog, int which) { 
			     		            // continue with closing
			     		 }
			     		     })
			     		
			     		    .setIcon(android.R.drawable.ic_dialog_alert)
			     		     .show();
			     		 

								
				
		 	        	   
		 	        	   
		 	        	   
		 	       
		 	           }
	           
		 	           
	            }
	 
	        });
	 
	    
}
	




	private void addListenerOnSpinnerItemSelection() {
		// TODO Auto-generated method stub
	       spinner1.setOnItemSelectedListener(new spinme());
		
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
       	     overridePendingTransition(R.anim.slidebackin, R.anim.slidebackout);

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
