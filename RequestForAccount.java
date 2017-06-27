package com.wishwell.microbankingonsmartphone;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class RequestForAccount extends Activity {

	EditText username,emailid,address,phoneno,alincome;
	RadioButton chkbookyes,chkbookno,onlinebankingyes,onlinebankingno,acsavings,accurrent;
	SQLiteDatabase db;
	Button submit,home,cancel;
	String sname,spwd;
	
	public  SharedPreferences sp = null;
	public  SharedPreferences.Editor spe = null;

	String chkbook,onlinebanking,accounttype,firstname,lastname;

	public static final String TABLE_REQUEST_NAME="REQUEST_FOR_ACCOUNT";
	public static final String CREATE_TABLE="create table if not exists "+TABLE_REQUEST_NAME+"(username text not null,email text not null,address text not null ,phoneno int(10) not null,annualincome text not null,checkbook text not null,onlinebanking text not null,accounttype text not null)";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.requestforaccount);

		sp = this.getSharedPreferences("UserDetails", MODE_PRIVATE);

		sname = sp.getString("susername", null);
		spwd =  sp.getString("spass", null);

		Log.v("@@@!!!@@@", " sname and spwd:"+sname +","+spwd);

		db=Main.db;
		
		
		//submit,home,cancel
		submit=(Button)findViewById(R.id.button1);
		home=(Button)findViewById(R.id.button3);
		cancel=(Button)findViewById(R.id.button2);

		username=(EditText)findViewById(R.id.editText1);
		emailid=(EditText)findViewById(R.id.editText2);
		address=(EditText)findViewById(R.id.editText3);
		phoneno=(EditText)findViewById(R.id.editText6);
		alincome=(EditText)findViewById(R.id.editText4);

		acsavings=(RadioButton)findViewById(R.id.savings);
		accurrent=(RadioButton)findViewById(R.id.current);

		chkbookyes=(RadioButton)findViewById(R.id.chcbyes);
		chkbookno=(RadioButton)findViewById(R.id.chcbno);

		
		onlinebankingyes=(RadioButton)findViewById(R.id.netyes);
		onlinebankingno=(RadioButton)findViewById(R.id.netno);

		db.execSQL(CREATE_TABLE);

		Cursor cs;
		cs=db.query("USER_REGISTRATION", new String[]{"username"}, "username=?", new String[]{sname}, null, null, null);

		if (cs.moveToFirst())
		{
			do {
				sname=cs.getString(0);
				
			} while (cs.moveToNext());
		}

		
		username.setText(sname);
		
		
		submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// TODO Auto-generated method stub

				if(chkbookyes.isChecked())
				{
					chkbook = chkbookyes.getText().toString();

				}else if (chkbookno.isChecked())
				{
					chkbook = chkbookno.getText().toString();
				}else
				{
					chkbook=null;
				}
				//onlinebankingyes,onlinebankingno

				if(onlinebankingyes.isChecked())
				{
					onlinebanking = onlinebankingyes.getText().toString();

				}else if (onlinebankingno.isChecked())
				{
					onlinebanking = onlinebankingno.getText().toString();
				}else
				{
					onlinebanking=null;
				}
				//acsavings,accurrent
				if(acsavings.isChecked())
				{
					accounttype = acsavings.getText().toString();

				}else if (accurrent.isChecked())
				{
					accounttype = accurrent.getText().toString();
				}else
				{
					accounttype=null;
				}


				if(username.length()>0 && emailid.length()>0 && address.length()>0 &&
						phoneno.length()>0&&chkbook.length()>0&& accounttype.length()>0 
						&& alincome.length()>0&&onlinebanking.length()>0)
				{
					ContentValues cv = new ContentValues();
					cv.put("username", username.getText().toString());
					cv.put("email",emailid.getText().toString());
					cv.put("address", address.getText().toString());
					cv.put("annualincome", alincome.getText().toString());
					cv.put("phoneno", phoneno.getText().toString());
					cv.put("checkbook", chkbook);
					cv.put("onlinebanking", onlinebanking);
					cv.put("accounttype", accounttype);

					long results = db.insert(RequestForAccount.this.TABLE_REQUEST_NAME, null, cv);	

					if(results == -1)
					{
						Toast.makeText(RequestForAccount.this, "Registration has been failed, please try again !!!", Toast.LENGTH_LONG).show();
					}else {
						Toast.makeText(RequestForAccount.this, "Registration has been done successuflly !!!", Toast.LENGTH_LONG).show();
						//It is to reset the values
						reSet();
					}


				}
				else {
					Toast.makeText(RequestForAccount.this, "Please Fill All The Fields !!!", Toast.LENGTH_LONG).show();

				}


			}
		});
		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				reSet();
			}
		});
		home.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(RequestForAccount.this,UserHome.class);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);

			}
		});




	}
	public void reSet()
	{
		username.setText("");
		emailid.setText("");
		address.setText("");
		phoneno.setText("");
		alincome.setText("");

		acsavings.setChecked(false);
		accurrent.setChecked(false);
		chkbookyes.setChecked(false);
		chkbookno.setChecked(false);
		onlinebankingyes.setChecked(false);
		onlinebankingno.setChecked(false);

	}



}
