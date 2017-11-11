package com.nexfort.balaetexpensemonitor;

import java.io.File;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class myDBClass extends SQLiteOpenHelper {
	//Help

//String table = "table2";
//String[] columns = {"column1", "column3"};
//String selection = "column3 =?";
//String[] selectionArgs = {"apple"};
//String groupBy = null;
//String having = null;
//String orderBy = "column3 DESC";
//String limit = "10";

//Cursor cursor = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);

    // Database Version
    private static final int DATABASE_VERSION = 4;
    private SQLiteDatabase db;
 
    // Database Name
    private static final String DATABASE_NAME = "balaet.db";
  
    
 
    // Table Name
    private static final String TABLE_COUNTRY = "paylist";
    private static final String TABLE_INSTALLS = "installments";
    public static final String[] ALL_KEYS = new String[] {"ID","Name","Amount","Spinpos","Datte","Timme","Day","Month","Year","Details", "Milisec"};
    public static final String[] ALL_KEYS_INST = new String[] {"IDx","Amountx","Dayx","Monthx","Yearx","Detailsx","Ofidx"};


    
	public myDBClass(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		
		
		// Create Table Name
		//"I'm Not A Threat To You Unless You Are A Threat To Me"
	    db.execSQL("CREATE TABLE " + TABLE_COUNTRY +
		          "(ID INTEGER PRIMARY KEY," +
		        " Name TEXT(100),"+ 
		        " Amount TEXT(100),"+ 
		        " Spinpos TEXT(100),"+ 
		        " Datte TEXT(100),"+ 
		        " Timme TEXT(100),"+ 
		        " Day TEXT(100),"+ 
		        " Month TEXT(100),"+ 
		        " Year TEXT(100),"+ 
		        " Details TEXT(1000),"+ 
		        " Milisec INTEGER);");
	    
	    
	    // Another table
	    db.execSQL("CREATE TABLE " + TABLE_INSTALLS +
		          "(IDx INTEGER PRIMARY KEY," +
		       
		        " Amountx TEXT(100),"+ 
		    
		        " Dayx TEXT(100),"+ 
		        " Monthx TEXT(100),"+ 
		        " Yearx TEXT(100),"+ 
		        " Detailsx TEXT(1000),"+ 
		        " Ofidx TEXT(100));");
	    
	    
	    //currency table
	
	   
	    Log.d("TABLE_INSTALLS","Create currency Table Successfully.");
	}
	
	// Insert Data
	public long InsertData(String name, String amnt, String spinp, String datt, String timm, String dayy, String monthh, String yearr, String detaill, String milise) {
		// TODO Auto-generated method stub
		
		
		int milisec = Integer.parseInt(milise);
		
		 try {
			SQLiteDatabase db;
     		db = this.getWritableDatabase(); // Write Data
     		
     		/**
     		 *  for API 11 and above
			SQLiteStatement insertCmd;
			String strSQL = "INSERT INTO " + TABLE_MEMBER
					+ "(ID,Code,Country) VALUES (?,?,?)";
			
			insertCmd = db.compileStatement(strSQL);
			insertCmd.bindString(1, strMemberID);
			insertCmd.bindString(2, strName);
			insertCmd.bindString(3, strTel);
			return insertCmd.executeInsert();
			*/
			
     	   ContentValues Val = new ContentValues();
     	
     	  Val.put("Name", name);
     	 Val.put("Amount", amnt);
     	 Val.put("Spinpos", spinp);
     	 Val.put("Datte", datt);
     	 Val.put("Timme", timm);
     	 Val.put("Day", dayy);
     	 Val.put("Month", monthh);
     	 Val.put("Year", yearr);
     	 Val.put("Details", detaill);
     	Val.put("Milisec", milisec);
     	  
     	  
		   long rows = db.insert(TABLE_COUNTRY, null, Val);

		   db.close();
		   return rows; // return rows inserted.
           
		 } catch (Exception e) {
		    return -1;
		 }

	}
	
	// Insert Data2
		public long InsertData2( String amnt, String dayy, String monthh, String yearr, String detaill, String ofidx) {
			// TODO Auto-generated method stub
			
			 try {
				SQLiteDatabase db;
	     		db = this.getWritableDatabase(); // Write Data
	     		
	     		/**
	     		 *  for API 11 and above
				SQLiteStatement insertCmd;
				String strSQL = "INSERT INTO " + TABLE_MEMBER
						+ "(ID,Code,Country) VALUES (?,?,?)";
				
				insertCmd = db.compileStatement(strSQL);
				insertCmd.bindString(1, strMemberID);
				insertCmd.bindString(2, strName);
				insertCmd.bindString(3, strTel);
				return insertCmd.executeInsert();
				*/
				
	     	   ContentValues Val = new ContentValues();
	     	   
	     	   
	     	
	     
	     	 Val.put("Amountx", amnt);
	
	     	 Val.put("Dayx", dayy);
	     	 Val.put("Monthx", monthh);
	     	 Val.put("Yearx", yearr);
	     	 Val.put("Detailsx", detaill);
	    	 Val.put("Ofidx", ofidx);
	     	  
	     	  
			   long rows = db.insert(TABLE_INSTALLS, null, Val);

			   db.close();
			   return rows; // return rows inserted.
	           
			 } catch (Exception e) {
			    return -1;
			 }

		}

		
		
		
		

	
	
	// Select Data by descending ID
	public Cursor SelectData() {
		// TODO Auto-generated method stub
		
		  try{
			
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 Cursor cursor = db.query(TABLE_COUNTRY, new String[] { "ID AS _id, *" },null, null, null, null, "ID DESC");
			 
			 	if(cursor != null)
			 	{
			 		return cursor;
			 	}
			 	else
			 	{
			 		return null;
			 	}
				
		     } catch (Exception e) {
		       return null;
		     }

	}
	
	// Select Data by descending ID
	public Cursor SelectDatax(String i) {
		// TODO Auto-generated method stub
		String where =  "Ofidx" + "=" + i;
		
		 try {
			
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 Cursor cursor = db.query(TABLE_INSTALLS, new String[] { "IDx AS _id, *" },where, null, null, null, "IDx DESC");
			 
			 	if(cursor != null)
			 	{
			 		return cursor;
			 	}
			 	else
			 	{
			 		return null;
			 	}
				
		 } catch (Exception e) {
		    return null;
		 }

	}
	
	// Select Data by descending Year
	public Cursor SelectData2() {
		// TODO Auto-generated method stub
		String groupBy = "Month,Day,Year";
		 try {
			
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
			 
			 
			 
			 //Raw query
			 String query = "SELECT ID AS _id, Name, Day, Month, Year, GROUP_CONCAT(Name) AS Name FROM "+TABLE_COUNTRY+" GROUP BY Day, Month,Year ORDER BY Milisec ASC" ;


			    Cursor cursor = db.rawQuery(query, null);
			// DB query function	
		//	 Cursor cursor = db.query(TABLE_COUNTRY, new String[] { "ID AS _id, *" },null, null, groupBy, null,"Milisec ASC");
			 
			 	if(cursor != null)
			 	{
			 		return cursor;
			 	}
			 	else
			 	{
			 		return null;
			 	}
				
		 } catch (Exception e) {
		    return null;
		 }

	}
	//String table = "goal";
	//String[] columns = new String[] { "distinct id", "date", "sum(goal)" };
	//String selection = "id=? and date=?";
	//String[] arguments = new String[] { "0", "Sep 15, 2015" };
	//String groupBy = "id, date";
	//String having = null;
	//String orderBy = null;
	//db.query(table, columns, selection, arguments, groupBy, having, orderBy);
	
	//dataselect group by names
	public Cursor SelectDatanames() {
		// TODO Auto-generated method stub
		String groupBy = "Name";
		
		 try {
			
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 Cursor cursor = db.query(TABLE_COUNTRY, new String[] { "ID AS _id, *" },null, null, groupBy, null, "ID DESC");
			 
			 	if(cursor != null)
			 	{
			 		return cursor;
			 	}
			 	else
			 	{
			 		return null;
			 	}
				
		 } catch (Exception e) {
		    return null;
		 }
		
	

	}
	
	
	
	

	
	   // Method is called during an upgrade of the database, e.g. if you increase
    // the database version
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COUNTRY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INSTALLS);

 
        
        // Re Create on method  onCreate
        onCreate(db);
	}


// Extreme Vengeance Of The Cause Behind My Goal Frustration 
	
//	public boolean deleteRow(long l) {
		
	//	return db.delete(TABLE_COUNTRY, "ID" + "=" + l, null) > 0;
	//}
	
	
	 public void deleterow(String title) {
	        //Open the database
	        SQLiteDatabase database = this.getWritableDatabase();

	        //Execute sql query to remove from database
	        //NOTE: When removing by String in SQL, value must be enclosed with ''
	        database.execSQL("DELETE FROM " + TABLE_COUNTRY + " WHERE " + "ID" + "= '" + title + "'");

	        //Close the database
	        database.close();
	    }
	 
	 public void deleterowx(String title) {
	        //Open the database
	        SQLiteDatabase database = this.getWritableDatabase();

	        //Execute sql query to remove from database
	        //NOTE: When removing by String in SQL, value must be enclosed with ''
	        database.execSQL("DELETE FROM " + TABLE_INSTALLS + " WHERE " + "IDx" + "= '" + title + "'");

	        //Close the database
	        database.close();
	    }
	 
	 
	 
	 public void deleteallrows() {
	        //Open the database
	        SQLiteDatabase database = this.getWritableDatabase();

	        //Execute sql query to remove from database
	        //NOTE: When removing by String in SQL, value must be enclosed with ''
	        database.execSQL("DELETE * FROM " + TABLE_COUNTRY + "");
	        database.execSQL("DELETE * FROM " + TABLE_INSTALLS + "");

	        //Close the database
	        database.close();
	    }
	 
	 //Get milisec
	 //Total amount
	 public int milisecget(String tit){

		    SQLiteDatabase db = this.getWritableDatabase();

		    String query = "SELECT ID AS _id, Milisec FROM " + TABLE_COUNTRY +" WHERE ID ="+ "'"+tit+"'" ;


		    Cursor c = db.rawQuery(query, null);

		    //Add in the movetofirst etc here? see SO
		    c.moveToFirst(); 
		    int i=c.getInt(1);

		    return i;



		}
	 

	 
	 
	 
	 
	 //Total amount
	 public int getTotalOfAllMedLevels(String tit){

		    SQLiteDatabase db = this.getWritableDatabase();

		    String query = "SELECT SUM(Amountx) FROM " + TABLE_INSTALLS +" WHERE Ofidx ="+ "'"+tit+"'" ;


		    Cursor c = db.rawQuery(query, null);

		    //Add in the movetofirst etc here? see SO
		    c.moveToFirst(); 
		    int i=c.getInt(0);

		    return i;



		}
	 
	 
	 //topay sum total
	 public int topay(){

		    SQLiteDatabase db = this.getWritableDatabase();

		    String query = "SELECT SUM(Amount) FROM " + TABLE_COUNTRY +" WHERE Spinpos = '1'" ;


		    Cursor c = db.rawQuery(query, null);

		    //Add in the movetofirst etc here? see SO
		    c.moveToFirst(); 
		    int i=c.getInt(0);

		    return i;



		}
	 
	 //topay sum by id
	 public int topayid(String go){
		 
		 
		 
try{
		    SQLiteDatabase db = this.getWritableDatabase();

		    String query = "SELECT ID AS _id, SUM(Amount) AS Amount FROM " + TABLE_COUNTRY +" WHERE Spinpos = '1' AND Name = '"+go+"'" ;


		    Cursor c = db.rawQuery(query, null);

		    //Add in the movetofirst etc here? see SO
		    c.moveToFirst(); 
		    int i=c.getInt(1);

		    return i;

	 } catch (Exception e) {
    return 0;
 }


		}
	 
	 
	 //topay sum by idx from table_installs
	 public int topayidinsta(){
		 
		 
		 try{

		    SQLiteDatabase db = this.getWritableDatabase();

		    String query = "SELECT "+TABLE_INSTALLS+".IDx AS _id, SUM("+TABLE_INSTALLS+".Amountx) AS Amountx, "+TABLE_COUNTRY+".Spinpos FROM "+TABLE_INSTALLS+" LEFT JOIN "+TABLE_COUNTRY+" ON "+TABLE_COUNTRY+".ID = "+TABLE_INSTALLS+".Ofidx WHERE "+TABLE_COUNTRY+".Spinpos='1' GROUP BY "+TABLE_COUNTRY+".Spinpos" ;


		    Cursor c = db.rawQuery(query, null);

		    //Add in the movetofirst etc here? see SO
		    c.moveToFirst(); 
		    int i=c.getInt(1);

		    return i;

		 } catch (Exception e) {
			    return 0;
			 }
			

		}
	 
	 
	 
	 
	 //topay sum by idx from table_installs
	 public int topayidinsta2(){
		 
		 
		 try{

		    SQLiteDatabase db = this.getWritableDatabase();

		    String query = "SELECT "+TABLE_INSTALLS+".IDx AS _id, SUM("+TABLE_INSTALLS+".Amountx) AS Amountx, "+TABLE_COUNTRY+".Spinpos FROM "+TABLE_INSTALLS+" LEFT JOIN "+TABLE_COUNTRY+" ON "+TABLE_COUNTRY+".ID = "+TABLE_INSTALLS+".Ofidx WHERE "+TABLE_COUNTRY+".Spinpos='0' GROUP BY "+TABLE_COUNTRY+".Spinpos" ;


		    Cursor c = db.rawQuery(query, null);

		    //Add in the movetofirst etc here? see SO
		    c.moveToFirst(); 
		    int i=c.getInt(1);

		    return i;
		    
	 } catch (Exception e) {
		    return 0;
		 }
		



		}
	 
	 
	 
	 
	 //topay sum by idx from table_installs
	 public int cattopaymilideduction(String name){
		 
		 
		 try{

		    SQLiteDatabase db = this.getWritableDatabase();

		    String query = "SELECT "+TABLE_INSTALLS+".IDx AS _id, SUM("+TABLE_INSTALLS+".Amountx) AS Amountx, "+TABLE_COUNTRY+".Spinpos FROM "+TABLE_INSTALLS+" LEFT JOIN "+TABLE_COUNTRY+" ON "+TABLE_COUNTRY+".ID = "+TABLE_INSTALLS+".Ofidx WHERE "+TABLE_COUNTRY+".Spinpos='1' AND "+TABLE_COUNTRY+".Milisec='"+name+"' GROUP BY "+TABLE_COUNTRY+".Spinpos" ;


		    Cursor c = db.rawQuery(query, null);

		    //Add in the movetofirst etc here? see SO
		    c.moveToFirst(); 
		    int i=c.getInt(1);

		    return i;
		    
		// If query fails to execute
	    // Such as if spin pos 1 does not exist while it selects 1...then 
		// Query fails, unlike mysql in java we have to call catch exception 
		// here it sends 0 when query fails as desired.
	 } catch (Exception e) {
	    return 0;
	 }
	



		}
	 
	 
	 
	 //topay sum by idx from table_installs
	 public int cattobepaidmilideduction(String name){
		 
		 
		 try{

		    SQLiteDatabase db = this.getWritableDatabase();

		    String query = "SELECT "+TABLE_INSTALLS+".IDx AS _id, SUM("+TABLE_INSTALLS+".Amountx) AS Amountx, "+TABLE_COUNTRY+".Spinpos FROM "+TABLE_INSTALLS+" LEFT JOIN "+TABLE_COUNTRY+" ON "+TABLE_COUNTRY+".ID = "+TABLE_INSTALLS+".Ofidx WHERE "+TABLE_COUNTRY+".Spinpos='0' AND "+TABLE_COUNTRY+".Milisec='"+name+"' GROUP BY "+TABLE_COUNTRY+".Spinpos" ;


		    Cursor c = db.rawQuery(query, null);

		    //Add in the movetofirst etc here? see SO
		    c.moveToFirst(); 
		    int i=c.getInt(1);

		    return i;

	 } catch (Exception e) {
		    return 0;
	 }


		}
	 
	 
	 
	 
	 
	 
	 
	 
	 /**
	  * Remove all users and groups from database.
	  */
	 public void removeAll()
	 {
	     // db.delete(String tableName, String whereClause, String[] whereArgs);
	     // If whereClause is null, it will delete all rows.
	     SQLiteDatabase db = this.getWritableDatabase(); // helper is object extends SQLiteOpenHelper
	     db.delete(TABLE_INSTALLS, null, null);
	     db.delete(TABLE_COUNTRY, null, null);
	 }
	 
	 
	 
	 
	 
	 
	 //topay sum by idx from table_installs
	 public int cattopaydeduction(String name){
		 
		 
		 try{

		    SQLiteDatabase db = this.getWritableDatabase();

		    String query = "SELECT "+TABLE_INSTALLS+".IDx AS _id, SUM("+TABLE_INSTALLS+".Amountx) AS Amountx, "+TABLE_COUNTRY+".Spinpos FROM "+TABLE_INSTALLS+" LEFT JOIN "+TABLE_COUNTRY+" ON "+TABLE_COUNTRY+".ID = "+TABLE_INSTALLS+".Ofidx WHERE "+TABLE_COUNTRY+".Spinpos='1' AND "+TABLE_COUNTRY+".Name='"+name+"' GROUP BY "+TABLE_COUNTRY+".Spinpos" ;


		    Cursor c = db.rawQuery(query, null);

		    //Add in the movetofirst etc here? see SO
		    c.moveToFirst(); 
		    int i=c.getInt(1);

		    return i;
		    
		// If query fails to execute
	    // Such as if spin pos 1 does not exist while it selects 1...then 
		// Query fails, unlike mysql in java we have to call catch exception 
		// here it sends 0 when query fails as desired.
	 } catch (Exception e) {
	    return 0;
	 }
	



		}
	 
	 
	 
	 //topay sum by idx from table_installs
	 public int cattobepaiddeduction(String name){
		 
		 
		 try{

		    SQLiteDatabase db = this.getWritableDatabase();

		    String query = "SELECT "+TABLE_INSTALLS+".IDx AS _id, SUM("+TABLE_INSTALLS+".Amountx) AS Amountx, "+TABLE_COUNTRY+".Spinpos FROM "+TABLE_INSTALLS+" LEFT JOIN "+TABLE_COUNTRY+" ON "+TABLE_COUNTRY+".ID = "+TABLE_INSTALLS+".Ofidx WHERE "+TABLE_COUNTRY+".Spinpos='0' AND "+TABLE_COUNTRY+".Name='"+name+"' GROUP BY "+TABLE_COUNTRY+".Spinpos" ;


		    Cursor c = db.rawQuery(query, null);

		    //Add in the movetofirst etc here? see SO
		    c.moveToFirst(); 
		    int i=c.getInt(1);

		    return i;

	 } catch (Exception e) {
		    return 0;
	 }


		}
	 //topay sum by id
	 public int tobepaidid(String go){
		 
		 
try{
		    SQLiteDatabase db = this.getWritableDatabase();

		    String query = "SELECT ID AS _id, SUM(Amount) AS Amount FROM " + TABLE_COUNTRY +" WHERE Spinpos = '0' AND Name = '"+go+"'" ;


		    Cursor c = db.rawQuery(query, null);

		    //Add in the movetofirst etc here? see SO
		    c.moveToFirst(); 
		    int i=c.getInt(1);

		    return i;
	 } catch (Exception e) {
		    return 0;
		 }
		



		}
	 
	 
	 //topay sum by id
	 public int tobepaidmili(String go){
		 
		 

		    SQLiteDatabase db = this.getWritableDatabase();

		    String query = "SELECT ID AS _id, SUM(Amount) AS Amount FROM " + TABLE_COUNTRY +" WHERE Spinpos = '0' AND Milisec = '"+go+"'" ;


		    Cursor c = db.rawQuery(query, null);

		    //Add in the movetofirst etc here? see SO
		    c.moveToFirst(); 
		    int i=c.getInt(1);

		    return i;



		} 
	 //topay sum by id
	 public int topayidmili(String go){
		 
		 

		    SQLiteDatabase db = this.getWritableDatabase();

		    String query = "SELECT ID AS _id, SUM(Amount) AS Amount FROM " + TABLE_COUNTRY +" WHERE Spinpos = '1' AND Milisec = '"+go+"'" ;


		    Cursor c = db.rawQuery(query, null);

		    //Add in the movetofirst etc here? see SO
		    c.moveToFirst(); 
		    int i=c.getInt(1);

		    return i;



		}
	 
	 
	 //to be paid total
	 public int tobepaid(){

		    SQLiteDatabase db = this.getWritableDatabase();

		    String query = "SELECT SUM(Amount) FROM " + TABLE_COUNTRY +" WHERE Spinpos = '0'" ;


		    Cursor c = db.rawQuery(query, null);

		    //Add in the movetofirst etc here? see SO
		    c.moveToFirst(); 
		    int i=c.getInt(0);

		    return i;



		}
	 
	 //joiner
	 public Cursor joinerselect(){
		 
		 
		 try {
				
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			    String query = "SELECT "+
					    TABLE_COUNTRY+".ID AS _id, "+TABLE_COUNTRY+".Name, "+TABLE_COUNTRY+".Amount, "+TABLE_COUNTRY+".Spinpos, "+
					    TABLE_COUNTRY+".Datte, "+TABLE_COUNTRY+".Timme, "+TABLE_COUNTRY+".Day, "+ 
					    TABLE_COUNTRY+".Month, "+TABLE_COUNTRY+".Year, "+TABLE_COUNTRY+".Details, "+TABLE_COUNTRY+".Milisec, SUM("+ 
					    TABLE_INSTALLS+".Amountx ) AS Amountx FROM "+TABLE_COUNTRY+" LEFT JOIN "+ TABLE_INSTALLS+" ON "+
					    TABLE_COUNTRY+".ID = "+TABLE_INSTALLS+".Ofidx GROUP BY "+TABLE_COUNTRY+".ID ORDER BY "+TABLE_COUNTRY +".ID DESC " ;


					    Cursor c = db.rawQuery(query, null);

			 
			 	if(c != null)
			 	{
			 		return c;
			 	}
			 	else
			 	{
			 		return null;
			 	}
				
		 } catch (Exception e) {
		    return null;
		 }
		 
		 

	
		    //Add in the movetofirst etc here? see SO
		  //  c.moveToFirst(); 
		   // int i=c.getInt(0);

		   // return i;



		}
	 
	 
	 
	 //joiner
	 public Cursor joinerselectbyid(int idx){
		 
		 
		 try {
				
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			    String query = "SELECT "+
					    TABLE_COUNTRY+".ID AS _id, "+TABLE_COUNTRY+".Name, "+TABLE_COUNTRY+".Amount, "+TABLE_COUNTRY+".Spinpos, "+
					    TABLE_COUNTRY+".Datte, "+TABLE_COUNTRY+".Timme, "+TABLE_COUNTRY+".Day, "+ 
					    TABLE_COUNTRY+".Month, "+TABLE_COUNTRY+".Year, "+TABLE_COUNTRY+".Details, "+TABLE_COUNTRY+".Milisec, SUM("+ 
					    TABLE_INSTALLS+".Amountx ) AS Amountx FROM "+TABLE_COUNTRY+" LEFT JOIN "+ TABLE_INSTALLS+" ON "+
					    TABLE_COUNTRY+".ID = "+TABLE_INSTALLS+".Ofidx WHERE Milisec='"+idx+"' GROUP BY "+TABLE_COUNTRY+".ID ORDER BY "+TABLE_COUNTRY +".ID DESC " ;


					    Cursor c = db.rawQuery(query, null);

			 
			 	if(c != null)
			 	{
			 		return c;
			 	}
			 	else
			 	{
			 		return null;
			 	}
				
		 } catch (Exception e) {
		    return null;
		 }
	 }
		 
		 
		 
		 //joiner
		 public Cursor joinerselectbyname(String idx){
			 
			 
			 try {
					
				 SQLiteDatabase db;
				 db = this.getReadableDatabase(); // Read Data
					
				    String query = "SELECT "+
						    TABLE_COUNTRY+".ID AS _id, "+TABLE_COUNTRY+".Name, "+TABLE_COUNTRY+".Amount, "+TABLE_COUNTRY+".Spinpos, "+
						    TABLE_COUNTRY+".Datte, "+TABLE_COUNTRY+".Timme, "+TABLE_COUNTRY+".Day, "+ 
						    TABLE_COUNTRY+".Month, "+TABLE_COUNTRY+".Year, "+TABLE_COUNTRY+".Details, "+TABLE_COUNTRY+".Milisec, SUM("+ 
						    TABLE_INSTALLS+".Amountx ) AS Amountx FROM "+TABLE_COUNTRY+" LEFT JOIN "+ TABLE_INSTALLS+" ON "+
						    TABLE_COUNTRY+".ID = "+TABLE_INSTALLS+".Ofidx WHERE Name='"+idx+"' GROUP BY "+TABLE_COUNTRY+".ID ORDER BY "+TABLE_COUNTRY +".ID DESC " ;


						    Cursor c = db.rawQuery(query, null);

				 
				 	if(c != null)
				 	{
				 		return c;
				 	}
				 	else
				 	{
				 		return null;
				 	}
					
			 } catch (Exception e) {
			    return null;
			 }
			 
		 

	
		    //Add in the movetofirst etc here? see SO
		  //  c.moveToFirst(); 
		   // int i=c.getInt(0);

		   // return i;



		}
	 
		 
		 
		 
		 
		 //joiner
		 public Cursor joinerselectbyspin(){
			 
			 
			 try {
					
				 SQLiteDatabase db;
				 db = this.getReadableDatabase(); // Read Data
					
				    String query = "SELECT "+
						    TABLE_COUNTRY+".ID AS _id, "+TABLE_COUNTRY+".Name, "+TABLE_COUNTRY+".Amount, "+TABLE_COUNTRY+".Spinpos, "+
						    TABLE_COUNTRY+".Datte, "+TABLE_COUNTRY+".Timme, "+TABLE_COUNTRY+".Day, "+ 
						    TABLE_COUNTRY+".Month, "+TABLE_COUNTRY+".Year, "+TABLE_COUNTRY+".Details, "+TABLE_COUNTRY+".Milisec, SUM("+ 
						    TABLE_INSTALLS+".Amountx ) AS Amountx FROM "+TABLE_COUNTRY+" LEFT JOIN "+ TABLE_INSTALLS+" ON "+
						    TABLE_COUNTRY+".ID = "+TABLE_INSTALLS+".Ofidx WHERE Spinpos='1' GROUP BY "+TABLE_COUNTRY+".ID ORDER BY "+TABLE_COUNTRY +".ID DESC " ;


						    Cursor c = db.rawQuery(query, null);

				 
				 	if(c != null)
				 	{
				 		return c;
				 	}
				 	else
				 	{
				 		return null;
				 	}
					
			 } catch (Exception e) {
			    return null;
			 }
			 
		 

	
		    //Add in the movetofirst etc here? see SO
		  //  c.moveToFirst(); 
		   // int i=c.getInt(0);

		   // return i;



		}
		 
		 //joiner
		 public Cursor joinerselectbyspin2(){
			 
			 
			 try {
					
				 SQLiteDatabase db;
				 db = this.getReadableDatabase(); // Read Data
					
				    String query = "SELECT "+
						    TABLE_COUNTRY+".ID AS _id, "+TABLE_COUNTRY+".Name, "+TABLE_COUNTRY+".Amount, "+TABLE_COUNTRY+".Spinpos, "+
						    TABLE_COUNTRY+".Datte, "+TABLE_COUNTRY+".Timme, "+TABLE_COUNTRY+".Day, "+ 
						    TABLE_COUNTRY+".Month, "+TABLE_COUNTRY+".Year, "+TABLE_COUNTRY+".Details, "+TABLE_COUNTRY+".Milisec, SUM("+ 
						    TABLE_INSTALLS+".Amountx ) AS Amountx FROM "+TABLE_COUNTRY+" LEFT JOIN "+ TABLE_INSTALLS+" ON "+
						    TABLE_COUNTRY+".ID = "+TABLE_INSTALLS+".Ofidx WHERE Spinpos='0' GROUP BY "+TABLE_COUNTRY+".ID ORDER BY "+TABLE_COUNTRY +".ID DESC " ;


						    Cursor c = db.rawQuery(query, null);

				 
				 	if(c != null)
				 	{
				 		return c;
				 	}
				 	else
				 	{
				 		return null;
				 	}
					
			 } catch (Exception e) {
			    return null;
			 }
			 
		 

	
		    //Add in the movetofirst etc here? see SO
		  //  c.moveToFirst(); 
		   // int i=c.getInt(0);

		   // return i;



		}
	 
		 
		 
		 
		 
// namedetails counting sum of open n close
		 
		 //joiner
		 public int joinerselectbynamespinpos(String idx){
			 
			 
			
					
				 SQLiteDatabase db;
				 db = this.getReadableDatabase(); // Read Data
					
				    String query = "SELECT "+
						    TABLE_COUNTRY+".ID AS _id, "+TABLE_COUNTRY+".Spinpos, SUM("+TABLE_COUNTRY+".Amount ) AS Amount, "+
						    " SUM("+TABLE_INSTALLS+".Amountx ) AS Amountx FROM "+TABLE_COUNTRY+" LEFT JOIN "+ TABLE_INSTALLS+" ON "+
						    TABLE_COUNTRY+".ID = "+TABLE_INSTALLS+".Ofidx WHERE Name='"+idx+"' AND "+TABLE_COUNTRY+".Spinpos='1' GROUP BY "+TABLE_COUNTRY+".ID  ORDER BY "+TABLE_COUNTRY +".ID DESC " ;


						    Cursor c = db.rawQuery(query, null);

						    int i=c.getInt(2);

						    return i;
				
			
		 

	
		    //Add in the movetofirst etc here? see SO
		  //  c.moveToFirst(); 
		   // int i=c.getInt(0);

		   // return i;



		}
	 

	
	// Get a specific row (by rowId)
	public Cursor getRow(int i) {
		String where =  "ID" + "=" + i;
	
		
		 try {
			
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 Cursor cursor = db.query(TABLE_COUNTRY, new String[] { "ID AS _id, *" },where, null, null, null, null);
			 
			 	if(cursor != null)
			 	{
			 		cursor.moveToFirst();
			 		return cursor;
			 	}
			 	else
			 	{
			 		return null;
			 	}
				
		 } catch (Exception e) {
		    return null;
		 }
	}

	public Cursor getRow2(int i) {
		String where =  "Spinpos" + "=" + i;
	
		
		 try {
			
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 Cursor cursor = db.query(TABLE_COUNTRY, new String[] { "ID AS _id, *" },where, null, null, null, null);
			 
			 	if(cursor != null)
			 	{
			 		cursor.moveToFirst();
			 		return cursor;
			 	}
			 	else
			 	{
			 		return null;
			 	}
				
		 } catch (Exception e) {
		    return null;
		 }
	}
	
	public Cursor getRowx(int i) {
		String where =  "IDx" + "=" + i;
	
		
		 try {
			
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 Cursor cursor = db.query(TABLE_COUNTRY, new String[] { "ID AS _id, *" },where, null, null, null, null);
			 
			 	if(cursor != null)
			 	{
			 		cursor.moveToFirst();
			 		return cursor;
			 	}
			 	else
			 	{
			 		return null;
			 	}
				
		 } catch (Exception e) {
		    return null;
		 }
	}

	public boolean updateRow(String note, String name, String amount, String day, String month, String year, String details, String milisec, String spins)  {
		// TODO Auto-generated method stub
			String where = "ID" + "=" + note;

	        SQLiteDatabase db = this.getWritableDatabase();
			/*
			 * CHANGE 4:
			 */
			// TODO: Update data in the row with new fields.
			// TODO: Also change the function's arguments to be what you need!
			// Create row's data:
			ContentValues newValues = new ContentValues();
			newValues.put("Name", name);
		newValues.put("Amount", amount);
			newValues.put("Spinpos", spins);
			newValues.put("Day", day);
			newValues.put("Month", month);
		newValues.put("Year", year);
			newValues.put("Details", details);
			newValues.put("Milisec", milisec);
		
			
			// Insert it into the database.
			return db.update(TABLE_COUNTRY, newValues, where, null) != 0;
		
	}

	
	
	
	
	public boolean updatecat(String name, String nampre)  {
		// TODO Auto-generated method stub

//		String where = "Name" + "=" + name;


	        SQLiteDatabase db = this.getWritableDatabase();
			/*
			 * CHANGE 4:
			 */
			// TODO: Update data in the row with new fields.
			// TODO: Also change the function's arguments to be what you need!
			// Create row's data:

		    String query = "UPDATE " + TABLE_COUNTRY +" SET Name='"+name+"'  WHERE Name = '"+nampre+"'" ;


		    Cursor c = db.rawQuery(query, null);

		    //Add in the movetofirst etc here? see SO
		   c.moveToFirst(); 
		  //  int i=c.getInt(0);

		    return true;


			
			
		
	}

	
	
	

	
	
	
	
	
}