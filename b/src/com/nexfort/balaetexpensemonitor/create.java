package com.nexfort.balaetexpensemonitor;



import java.text.ParseException;
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
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
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
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class create extends ActionBarActivity {
	 LinearLayout yhp;
	 LinearLayout ihp;
	 AutoCompleteTextView et;
	 EditText ett;
	 EditText details;

	 myDBClass myDb;
	    private Button btnSubmit;
	    
	    int pass;
	    
	    
	    
		private TextView tvDisplayDate;
		private DatePicker dpResult;
		private Button btnChangeDate;

		private int year;
		private int month;
		private int day;
		
		

		static final int DATE_DIALOG_ID = 999;
		
		

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		
		   
		//Animation while activity open
	     overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
   myDb = new myDBClass(this);
   
   
DatePicker datepicker = (DatePicker) findViewById(R.id.datePicker1);
datepicker.setVisibility(View.GONE);

details=(EditText)findViewById(R.id.details);
et=(AutoCompleteTextView)findViewById(R.id.textView1);
ett=(EditText)findViewById(R.id.textView3);



        
	backclick();

       
   

           LinearLayout pass0 = (LinearLayout) findViewById(R.id.pass0);
           pass0.setVisibility(View.GONE);

           LinearLayout pass1 = (LinearLayout) findViewById(R.id.pass1);
           pass1.setVisibility(View.GONE);  
    

// Spinner item selection Listener  
//addListenerOnSpinnerItemSelection();

// Button click Listener 
addListenerOnButton();




setCurrentDateOnView();
addListenerOnButton2();

edit();
dio();
lay1();
lay2();



et.addTextChangedListener(new TextWatcher() {

    public void afterTextChanged(Editable s) {

      // you can call or do what you want with your EditText here
        et.showDropDown();
        et.requestFocus();
    

    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    	
    	   et.showDropDown();
           et.requestFocus();
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
    	
    	   et.showDropDown();
           et.requestFocus();
    }
    
 });





final Cursor c = myDb.SelectDatanames();
et.setAdapter(new CountryAdapter(this, c ));

//et.setOnTouchListener(new OnTouchListener() {

  //  @SuppressLint("ClickableViewAccessibility")
  //  @Override
   // public boolean onTouch(View paramView, MotionEvent paramMotionEvent) {
        // TODO Auto-generated method stub
     //   et.showDropDown();
       // et.requestFocus();
       // return false;
    //}
//});
et.setOnItemClickListener(new OnItemClickListener() {
	public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
		// Do your action here
		//Toast.makeText(getApplicationContext(),"View..."+id,Toast.LENGTH_LONG).show();  
		c.moveToPosition(position);
       
		String str= Long.toString(id);
		 String iddx = c.getString(c.getColumnIndex("Name"));
		et.setText(iddx);
		et.requestFocus();
     
           
   };
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
		TextView txtID = (TextView) view.findViewById(R.id.auto);
        txtID.setText(cursor.getString(cursor.getColumnIndex("Name")));
}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			// TODO Auto-generated method stub
			LayoutInflater inflater = LayoutInflater.from(context);
			View view = inflater.inflate(R.layout.autocomplete, parent, false);
			bindView(view, context, cursor);
			return view;
			/*
			 * or   return LayoutInflater.from(context).inflate(R.layout.activity_column, parent, false);
			 * */
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







	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)  {
	    if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.ECLAIR
	            && keyCode == KeyEvent.KEYCODE_BACK
	            && event.getRepeatCount() == 0) {
	        // Take care of calling this method on earlier versions of
	        // the platform where it doesn't exist.
	        onBackPressed();
	    }

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
	 

	                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	                String datte = sdf.format(new Date());
	                
	                SimpleDateFormat sdff = new SimpleDateFormat("HH:mm:ss");
	                String timme  = sdff.format(new Date());
	                
	                
	 	        	String note=et.getText().toString();
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
	 			 
	 			 
	 			 

	 	        	
	 	       //Concatenating int Values 	
	 	      //int datemine= Integer.valueOf(String.valueOf(day)+String.valueOf(mini) + String.valueOf(month) +String.valueOf(mini)+ String.valueOf(year));
	 	        	
	 	        	// int converted=Integer.parseInt(amount);
	 	    		
	 	    		
	 	    	
	 	      	if (note != null && !note.isEmpty()) {
	 	           if (amount != null && !amount.isEmpty()) {
	 	    		
	 	    				
	 	          
	 	           String passint = Integer.toString(pass);
	 	           
			myDb.InsertData(note,amount,passint,datte,timme,dayy,monthh,yearr,detaill,milisec);
Intent intent = new Intent(getBaseContext(), list.class);
                        startActivity(intent);   
                        finish();
                        list.h.sendEmptyMessage(0);
	 	           }else{
	 	        	  Toast.makeText(create.this,
		                        "Amount field missing" ,Toast.LENGTH_LONG).show();
	 	        	   
	 	           }
	 	      	}else{
	 	        	  Toast.makeText(create.this,
		                        "Name field missing" ,Toast.LENGTH_LONG).show();
                        
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
	
	
	

	private void lay1() {
		// TODO Auto-generated method stub
		final Context context = this;
		 
		LinearLayout button = (LinearLayout) findViewById(R.id.pass0);
 
		button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
				// custom dialog
				final Dialog dialog = new Dialog(context);
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before 
				dialog.setContentView(R.layout.chooser);

		//	dialog.setTitle("Choose Method");

				// set the custom dialog components - text, image and button
			//	TextView text = (TextView) dialog.findViewById(R.id.text);
		//		text.setText("Android custom dialog example!");
			//	ImageView image = (ImageView) dialog.findViewById(R.id.image);
				//image.setImageResource(R.drawable.ic_launcher);

				LinearLayout dialogButton = (LinearLayout) dialog.findViewById(R.id.lay1);
				// if button is clicked, close the custom dialog
				dialogButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();

			//		Toast.makeText(getApplicationContext(),"Pass 0",Toast.LENGTH_SHORT).show();  
					pass = 0;
					
				      LinearLayout pass0 = (LinearLayout) findViewById(R.id.pass0);
			           pass0.setVisibility(View.VISIBLE);

			           LinearLayout pass1 = (LinearLayout) findViewById(R.id.pass1);
			           pass1.setVisibility(View.GONE);
			           
			           Button choose = (Button) findViewById(R.id.btnSubmit2);
			           choose.setVisibility(View.GONE);
		
					
					}
				});
				
				LinearLayout dialogButton1 = (LinearLayout) dialog.findViewById(R.id.lay2);
				// if button is clicked, close the custom dialog
				dialogButton1.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
pass=1;
				//	Toast.makeText(getApplicationContext(),"Pass 1",Toast.LENGTH_SHORT).show();  
					   
				     
				           LinearLayout pass0 = (LinearLayout) findViewById(R.id.pass0);
				           pass0.setVisibility(View.GONE);

				           LinearLayout pass1 = (LinearLayout) findViewById(R.id.pass1);
				           pass1.setVisibility(View.VISIBLE);
				           
				           Button choose = (Button) findViewById(R.id.btnSubmit2);
				           choose.setVisibility(View.GONE);
			
					}
				});

				dialog.show();

 
			     
			}
 
		});
	}



	private void lay2() {
		// TODO Auto-generated method stub
		final Context context = this;
		 
		LinearLayout button = (LinearLayout) findViewById(R.id.pass1);
 
		button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				// custom dialog
				final Dialog dialog = new Dialog(context);
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before 
				dialog.setContentView(R.layout.chooser);

		//	dialog.setTitle("Choose Method");

				// set the custom dialog components - text, image and button
			//	TextView text = (TextView) dialog.findViewById(R.id.text);
		//		text.setText("Android custom dialog example!");
			//	ImageView image = (ImageView) dialog.findViewById(R.id.image);
				//image.setImageResource(R.drawable.ic_launcher);

				LinearLayout dialogButton = (LinearLayout) dialog.findViewById(R.id.lay1);
				// if button is clicked, close the custom dialog
				dialogButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();

			//		Toast.makeText(getApplicationContext(),"Pass 0",Toast.LENGTH_SHORT).show();  
					pass = 0;
					
				      LinearLayout pass0 = (LinearLayout) findViewById(R.id.pass0);
			           pass0.setVisibility(View.VISIBLE);

			           LinearLayout pass1 = (LinearLayout) findViewById(R.id.pass1);
			           pass1.setVisibility(View.GONE);
			           
			           Button choose = (Button) findViewById(R.id.btnSubmit2);
			           choose.setVisibility(View.GONE);
		
					
					}
				});
				
				LinearLayout dialogButton1 = (LinearLayout) dialog.findViewById(R.id.lay2);
				// if button is clicked, close the custom dialog
				dialogButton1.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
pass=1;
			//		Toast.makeText(getApplicationContext(),"Pass 1",Toast.LENGTH_SHORT).show();  
					   
				     
				           LinearLayout pass0 = (LinearLayout) findViewById(R.id.pass0);
				           pass0.setVisibility(View.GONE);

				           LinearLayout pass1 = (LinearLayout) findViewById(R.id.pass1);
				           pass1.setVisibility(View.VISIBLE);
				           
				           Button choose = (Button) findViewById(R.id.btnSubmit2);
				           choose.setVisibility(View.GONE);
			
					}
				});

				dialog.show();

  
			     
 
			}
 
		});
	}



	
	
	private void dio() {
		// TODO Auto-generated method stub
		final Context context = this;
		 
		Button button = (Button) findViewById(R.id.btnSubmit2);
 
		button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {

				// custom dialog
				final Dialog dialog = new Dialog(context);
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before 
				dialog.setContentView(R.layout.chooser);

		//	dialog.setTitle("Choose Method");

				// set the custom dialog components - text, image and button
			//	TextView text = (TextView) dialog.findViewById(R.id.text);
		//		text.setText("Android custom dialog example!");
			//	ImageView image = (ImageView) dialog.findViewById(R.id.image);
				//image.setImageResource(R.drawable.ic_launcher);

				LinearLayout dialogButton = (LinearLayout) dialog.findViewById(R.id.lay1);
				// if button is clicked, close the custom dialog
				dialogButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();

				//	Toast.makeText(getApplicationContext(),"Pass 0",Toast.LENGTH_SHORT).show();  
					pass = 0;
					
				      LinearLayout pass0 = (LinearLayout) findViewById(R.id.pass0);
			           pass0.setVisibility(View.VISIBLE);

			           LinearLayout pass1 = (LinearLayout) findViewById(R.id.pass1);
			           pass1.setVisibility(View.GONE);
			           
			           Button choose = (Button) findViewById(R.id.btnSubmit2);
			           choose.setVisibility(View.GONE);
		
					
					}
				});
				
				LinearLayout dialogButton1 = (LinearLayout) dialog.findViewById(R.id.lay2);
				// if button is clicked, close the custom dialog
				dialogButton1.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
						
						pass=1;

				//	Toast.makeText(getApplicationContext(),"Pass 1",Toast.LENGTH_SHORT).show();  
					   
				     
				           LinearLayout pass0 = (LinearLayout) findViewById(R.id.pass0);
				           pass0.setVisibility(View.GONE);

				           LinearLayout pass1 = (LinearLayout) findViewById(R.id.pass1);
				           pass1.setVisibility(View.VISIBLE);
				           
				           Button choose = (Button) findViewById(R.id.btnSubmit2);
				           choose.setVisibility(View.GONE);
			
					}
				});

				dialog.show();

 
			}
 
		});
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
