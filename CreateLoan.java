package com.wishwell.microbankingonsmartphone;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CreateLoan extends Activity{


	TextView acountno,username,loantype,loanamount,address,phone;
	
	String acno,uname,ltype,lamount,adrs,phno;

	Button approve,cancel,home;
	
	SQLiteDatabase db;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.createloan);

		acountno=(TextView)findViewById(R.id.textView10);
		username=(TextView)findViewById(R.id.textView11);
		loantype=(TextView)findViewById(R.id.textView12);
		loanamount=(TextView)findViewById(R.id.textView13);
		address=(TextView)findViewById(R.id.textView14);
		phone=(TextView)findViewById(R.id.textView15);
		approve=(Button)findViewById(R.id.button1);
		cancel=(Button)findViewById(R.id.button2);
		home=(Button)findViewById(R.id.button3);


		db=Main.db;
		
		String key=getIntent().getExtras().getString("myloan");

		Cursor cs=db.query("APPLY_FOR_LOAN", new String[]{"accountno","username","loantype","loanamount","address","phoneno"},"username=?",new String[]{key},null,null,null);

		// acno,uname,ltype,lamount,adrs,phno
		if (cs.moveToFirst())
		{
			do {
				acno=cs.getString(0);
				uname=cs.getString(1);
				ltype=cs.getString(2);
				lamount=cs.getString(3);
				adrs=cs.getString(4);
				phno=cs.getString(5);
				
			} while (cs.moveToNext());
		}else
		{
			Toast.makeText(getApplicationContext(), "No Data Found", 5000).show();
		}

		username.setText(uname);
		acountno.setText(acno);
		address.setText(adrs);
		phone.setText(phno);
		loantype.setText(ltype);
		loanamount.setText(lamount);
		
		approve.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent i =new Intent(getApplicationContext(), ApproveLoan.class);
				i.putExtra("lacno", acno);
				i.putExtra("lname", uname);
				i.putExtra("lamount", lamount);
				startActivity(i);
				finish();
				
			}
		});
		
	}

}
