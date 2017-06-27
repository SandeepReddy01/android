package com.wishwell.microbankingonsmartphone;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CreateAccount extends Activity {
	SQLiteDatabase db;
	TextView username,email,address,phoneno,annualincome,checkbook,onlinebanking,accounttype;
	Button genrate,cancel,adhome;
	String user,uemail,uaddress,uphoneno,uannualincome,ucheckbook,uonlinebanking,uaccounttype;

	//username,email,address,phoneno,annualincome,checkbook,onlinebanking,accounttype
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.createaccount);
		username=(TextView)findViewById(R.id.textView10);
		email=(TextView)findViewById(R.id.textView11);
		address=(TextView)findViewById(R.id.textView12);
		phoneno=(TextView)findViewById(R.id.textView13);
		annualincome=(TextView)findViewById(R.id.textView14);
		accounttype=(TextView)findViewById(R.id.textView15);
		checkbook=(TextView)findViewById(R.id.textView16);
		onlinebanking=(TextView)findViewById(R.id.textView17);

		genrate=(Button)findViewById(R.id.button1);
		cancel=(Button)findViewById(R.id.button2);
		adhome=(Button)findViewById(R.id.button3);

		db=Main.db;
		String key=getIntent().getExtras().getString("myname");

		Cursor cs=db.query("REQUEST_FOR_ACCOUNT", new String[]{"username","email","address","phoneno","annualincome","checkbook","onlinebanking","accounttype"},"username=?",new String[]{key},null,null,null);

		//user,email,address,phoneno,annualincome,checkbook,onlinebanking,accounttype
		if (cs.moveToFirst())
		{
			do {
				user=cs.getString(0);
				uemail=cs.getString(1);
				uaddress=cs.getString(2);
				uphoneno=cs.getString(3);
				uannualincome=cs.getString(4);
				ucheckbook=cs.getString(5);
				uonlinebanking=cs.getString(6);
				uaccounttype=cs.getString(7);


			} while (cs.moveToNext());
		}else
		{
			Toast.makeText(getApplicationContext(), "No Data Found", 5000).show();
		}//username,email,address,phoneno,annualincome,accounttype,checkbook,onlinebanking;

		username.setText(user);
		email.setText(uemail);
		address.setText(uaddress);
		phoneno.setText(uphoneno);
		annualincome.setText(uannualincome);
		accounttype.setText(uaccounttype);
		checkbook.setText(ucheckbook);
		onlinebanking.setText(uonlinebanking);

		genrate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent i=new Intent(getApplicationContext(), Account_Created.class);
				i.putExtra("uname", user);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
				
			}
		});




		adhome.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getApplicationContext(), AdminHome.class);
				startActivity(i);
				finish();
			}
		});
		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getApplicationContext(), AdminHome.class);
				startActivity(i);
				finish();
			}
		});

	}


}
