package com.wishwell.microbankingonsmartphone;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Account_Created extends Activity{

	TextView uname,accountno;
	Button ok;
	SQLiteDatabase db;
	Integer accounnofromDB;
	String unamekey;

	Integer accnumber=1000000000;

	public static final String TABLE_ACCOUNT_CREATED="ACCOUNT_CREATED";
	public static final String CREATE_TABLE="create table if not exists "+TABLE_ACCOUNT_CREATED+"(accountno INTEGER not null,username text not null)";

	public SharedPreferences sp = null;
	public SharedPreferences.Editor spe = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.account_genereted);
		
		sp = this.getSharedPreferences("FirstTimeInstallationFlag", MODE_PRIVATE);
		spe = sp.edit();
		
		spe.putInt("Key_accnumber", 10);
		spe.commit();

		db = Main.db;
		
		unamekey=getIntent().getExtras().getString("uname");

		uname = (TextView)findViewById(R.id.textView3);
		accountno = (TextView)findViewById(R.id.textView5);
		ok = (Button)findViewById(R.id.button1);

		try {

			db.execSQL(CREATE_TABLE);
			
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("Table creation problem ", "@@@@@@@@@@@@@@@@");
		}

		Cursor cs;
		cs= db.rawQuery("select accountno from ACCOUNT_CREATED",null);
		if (cs.moveToFirst())
		{
			do {
				
				Log.e("e", "@@@@@Convertion problem@@@@@@@@@@@");
				
				int uacountno=cs.getInt(cs.getColumnIndex("accountno"));
				spe.putInt("Key_accnumber", uacountno);
				spe.commit();
				Log.e("e", "@@@@@Convertion problem##############@@@@@@@@@@@"+uacountno);
				
				accounnofromDB=uacountno;
				
				Log.e("e", "@@@@@adding ######## problem@@@@@@@@@@@"+accounnofromDB);

			} while (cs.moveToNext());

		}
		

		Boolean bb = sp.getBoolean("Key_First", false);
		int csnumber = sp.getInt("Key_accnumber", 0);

		//if (accounnofromDB>0) 
		
		if(csnumber != 10)
		{
			
			accounnofromDB=accounnofromDB+1;
			ContentValues cv = new ContentValues();
			cv.put("accountno",accounnofromDB);
			cv.put("username",unamekey );
			long results = db.insert(Account_Created.this.TABLE_ACCOUNT_CREATED, null, cv);	
			
			uname.setText(unamekey);
			
			accountno.setText(accounnofromDB.toString());
			
			if(results == -1)
			{
				Toast.makeText(Account_Created.this, "Registration has been failed, please try again !!!", Toast.LENGTH_LONG).show();
			}else {
				Toast.makeText(Account_Created.this, "Registration has been done successuflly !!!", Toast.LENGTH_LONG).show();
				
				
			}			
		} 
		else 
		{
			
			ContentValues cv = new ContentValues();
			cv.put("accountno",accnumber);
			cv.put("username",unamekey );
			long results = db.insert(Account_Created.this.TABLE_ACCOUNT_CREATED, null, cv);	
			
			uname.setText(unamekey);
			accountno.setText(accnumber.toString());
			
			if(results == -1)
			{
				Toast.makeText(Account_Created.this, "Registration has been failed, please try again !!!", Toast.LENGTH_LONG).show();
			}else {
				Toast.makeText(Account_Created.this, "Registration has been done successuflly !!!", Toast.LENGTH_LONG).show();
				
			}
		}


		ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getApplicationContext(), AdminHome.class);
				startActivity(i);
				finish();
				
				
				long res=db.delete("REQUEST_FOR_ACCOUNT", "username=?", new String[]{unamekey});
				if(res==-1)
				{
					Toast.makeText(Account_Created.this, "Not Deleted", 5000).show();
				}
				else
				{
					Toast.makeText(Account_Created.this, "Deleted", 5000).show();
				}
				//cs1=db.delete("REQUEST_FOR_ACCOUNT", whereClause, whereArgs)
				
				
				
			}
		});
		
		
		
		
		
	}
	
}
