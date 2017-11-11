package com.nexfort.balaetexpensemonitor;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;


import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class settings extends ActionBarActivity {
    boolean bool;
    Spinner spinner;

	 myDBClass myDb;
	 
	 
	 public static String PACKAGE_NAME;
	 String dbname;
	 
	 
//   AlertDialog.Builder alert = new AlertDialog.Builder(this); 
// #cfd8dc for setting items background
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);

	    PACKAGE_NAME = getApplicationContext().getPackageName();

	 
	   myDb = new myDBClass(this);
	 
	 //DATABASE_NAME

		//Animation while activity open
	     overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	
		
	
	      
		
		
		
	    versions();
	    rateapp();
	    symbol();
	    about();
	    terms();
	    suggestions();
	    backup();
	   restore();
	   
	   guide();
	   
	   share();
	   reset();
	    
	    
	//    addListenerOnSpinnerItemSelection();
	    
	    

	}
	

	private void reset() {
		// TODO Auto-generated method stub
		 
		LinearLayout button = (LinearLayout) findViewById(R.id.lay2x);

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
			    // Get the layout inflater
		       	
	            LayoutInflater inflater = settings.this.getLayoutInflater();
	        				new AlertDialog.Builder(settings.this)
	        	

	        			    // Inflate and set the layout for the dialog
	        			    // Pass null as the parent view because its going in the dialog layout
	        			    .setView(inflater.inflate(R.layout.reset, null))
	        			//	  .setTitle("About The App")
	        		//.setMessage(Html.fromHtml("<b><font color=#303f9f>Send Mail</font></b>"))
	        		.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
	        				        public void onClick(DialogInterface dialog, int which) { 
	        				            // continue with closing
	        				 
	        				        	
	        				        	
	        				        	
	        				 }
	        				     })
	        				     	.setNegativeButton("Reset", new DialogInterface.OnClickListener() {
	        				        public void onClick(DialogInterface dialog, int which) { 
	        				            // continue with closing
	        			
	        				        	myDb.removeAll();
	        				        	   Toast.makeText(getApplicationContext(), "All Data Earsed", Toast.LENGTH_SHORT).show();

	        							    Intent intent = new Intent(getBaseContext(), grid.class);
	        				                            startActivity(intent);   
	        				        	
	        				 }
	        				     })
	        				
	        				    .setIcon(android.R.drawable.ic_dialog_info)
	        				     .show();
	        	
			}

		});
		
	}

		
	


	private void share() {
		// TODO Auto-generated method stub
		
		LinearLayout button = (LinearLayout) findViewById(R.id.lay2s);
		 
		button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
			  	Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND); 
		    	sharingIntent.setType("text/plain");
		    	String shareBody = "Here is the share content body";
		    	sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
		    	sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
		    	startActivity(Intent.createChooser(sharingIntent, "Share via"));
  	
			}
 
		});
		
  
	}


	private void suggestions() {
		// TODO Auto-generated method stub
		final Context context = this;
		 
		LinearLayout button = (LinearLayout) findViewById(R.id.lay8);
 
		button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
			    // Get the layout inflater
		       	
	            LayoutInflater inflater = settings.this.getLayoutInflater();
	        				new AlertDialog.Builder(settings.this)
	        	

	        			    // Inflate and set the layout for the dialog
	        			    // Pass null as the parent view because its going in the dialog layout
	        			    .setView(inflater.inflate(R.layout.dialog_signin, null))
	        			//	  .setTitle("About The App")
	        		//.setMessage(Html.fromHtml("<b><font color=#303f9f>Send Mail</font></b>"))
	        		.setPositiveButton("Proceed to mail", new DialogInterface.OnClickListener() {
	        				        public void onClick(DialogInterface dialog, int which) { 
	        				            // continue with closing
	        				        	
	        				        	Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
	        				        	emailIntent.setData(Uri.parse("mailto: quickroutes@gmail.com"));
	        				        	startActivity(Intent.createChooser(emailIntent, "Send in Gmail(Recommended)"));        	
	        				        	
	        				        	
	        				        	
	        				        	
	        				 }
	        				     })
	        				
	        				    .setIcon(android.R.drawable.ic_dialog_info)
	        				     .show();
	        	
			}
 
		});
		
	}

	
	
	private void about() {
		// TODO Auto-generated method stub
		final Context context = this;
		 
		LinearLayout button = (LinearLayout) findViewById(R.id.lay6);
 
		button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
			      LayoutInflater inflater = settings.this.getLayoutInflater();
  				new AlertDialog.Builder(settings.this)
  	

  			    // Inflate and set the layout for the dialog
  			    // Pass null as the parent view because its going in the dialog layout
  			    .setView(inflater.inflate(R.layout.aboutd, null))
  			//	  .setTitle("About The App")
  		//.setMessage(Html.fromHtml("<b><font color=#303f9f>Send Mail</font></b>"))
  		.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
  				        public void onClick(DialogInterface dialog, int which) { 
  				            // continue with closing
  				        	
  				     
  				        	
  				 }
  				     })
  				
  				    .setIcon(android.R.drawable.ic_dialog_info)
  				     .show();
  	
			}
 
		});
		
	}


	private void symbol() {
	// TODO Auto-generated method stub
		final Context context = this;
		 
		LinearLayout button = (LinearLayout) findViewById(R.id.lay5);
 
		button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
			    
                String spin=spinner.getSelectedItem().toString();
              
			          Toast.makeText(settings.this,
		                        "On Button Click : " + spin ,
		                       Toast.LENGTH_LONG).show();
			  
			    
			    //      myDb.InsertDatacur("ok");
			}
 
		});
	
}
	

	

	private void backup() {
	// TODO Auto-generated method stub
		final Context context = this;
		 
		LinearLayout button = (LinearLayout) findViewById(R.id.lay3);
 
		button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				
				
				 try {
				        File sd = Environment.getExternalStorageDirectory();
				        File f=new File(sd+"/balaetem");
				        f.mkdir();
				        File data = Environment.getDataDirectory();

				        if (sd.canWrite()) {
				       //    String currentDBPath = "//data//PACKAGE_NAME//databases//balaet";
				            String backupDBPath = "/balaetem/balaet.db";
				        //   File currentDB = new File(data, currentDBPath);
				            File currentDB = getDatabasePath("balaet.db");
				         //   File currentDB = context.getDatabasePath("balaet");
				            File backupDB = new File(sd, backupDBPath);

				            if (currentDB.exists()) {
				                FileChannel src = new FileInputStream(currentDB).getChannel();
				                FileChannel dst = new FileOutputStream(backupDB).getChannel();
				                dst.transferFrom(src, 0, src.size());
				                src.close();
				                dst.close();

				                Toast.makeText(getApplicationContext(), "Backup is successful to SD card", Toast.LENGTH_SHORT).show();
				            }
				        }
				    } catch (Exception e) {
				    }  

			               
			}
 
		});
	
}
	
	
	
	

	private void restore() {
	// TODO Auto-generated method stub
		final Context context = this;
		 
		LinearLayout button = (LinearLayout) findViewById(R.id.lay10);
 
		button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				
				try {
				    File sd = Environment.getExternalStorageDirectory();
				    File f=new File(sd+"/balaetem");
			        f.mkdir();
				    File data = Environment.getDataDirectory();

				    if (sd.canWrite()) {
				        //    String currentDBPath = "//data//PACKAGE_NAME//databases//balaet";
			            String backupDBPath = "/balaetem/balaet.db";
			        //   File currentDB = new File(data, currentDBPath);
			            File currentDB = getDatabasePath("balaet.db");
			         //   File currentDB = context.getDatabasePath("balaet");
			            File backupDB = new File(sd, backupDBPath);
				        if (currentDB.exists()) {
				            FileChannel src = new FileInputStream(backupDB).getChannel();
				            FileChannel dst = new FileOutputStream(currentDB).getChannel();
				            dst.transferFrom(src, 0, src.size());
				            src.close();
				            dst.close();
				            Toast.makeText(getApplicationContext(), "Database Restored successfully", Toast.LENGTH_SHORT).show();

						    Intent intent = new Intent(context, grid.class);
			                            startActivity(intent);   
				        }
				    }
				} catch (Exception e) {
				}
			
			               
			}
 
		});
	
}


	private void addListenerOnSpinnerItemSelection() {
		// TODO Auto-generated method stub
	       spinner.setOnItemSelectedListener(new spinme());
		
	}



	private void terms() {
	// TODO Auto-generated method stub
		final Context context = this;
		 
		LinearLayout button = (LinearLayout) findViewById(R.id.lay7);
 
		button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
			    Intent intent = new Intent(context, terms.class);
                startActivity(intent);   
			}
 
		});
	
}
	
	


	private void guide() {
	// TODO Auto-generated method stub
		final Context context = this;
		 
		LinearLayout button = (LinearLayout) findViewById(R.id.lay9);
 
		button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
			    Intent intent = new Intent(context, guide.class);
                startActivity(intent);   
			}
 
		});
	
}



	private void rateapp() {
	// TODO Auto-generated method stub
		final Context context = this;
		 
		LinearLayout button = (LinearLayout) findViewById(R.id.lay2);
 
		button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=PackageName"))); 
				   //       Toast.makeText(create.this,
		           //             "On Button Click : " + 
		             //           "\n" + String.valueOf(spinner1.getSelectedItemPosition()) ,
		               //         Toast.LENGTH_LONG).show();
			}
 
		});
	
}

	private void versions() {
		// TODO Auto-generated method stub
		final Context context = this;
		 
		LinearLayout button = (LinearLayout) findViewById(R.id.lay4);
 
		button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
				new AlertDialog.Builder(settings.this)
				  //.setTitle("Close the deal!")
		.setMessage(Html.fromHtml("<b><font color=#303f9f>Version 0.1</font></b> <br/>Beta release : 30 Dec 2016"))
		.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
				        public void onClick(DialogInterface dialog, int which) { 
				            // continue with closing
				 }
				     })
				
				    .setIcon(android.R.drawable.ic_dialog_alert)
				     .show();
				 
 
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
