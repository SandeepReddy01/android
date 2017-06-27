package com.wishwell.microbankingonsmartphone;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.drm.DrmStore.Action;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ApproveLoan extends Activity{
	
	public static final String LOAN_CREATED="LOAN_CREATED";
	public static final String CREATE_TABLE="create table if not exists "+LOAN_CREATED+"(accountno INTEGER not null,username text not null,loanamount INTEGER not null)";

	SQLiteDatabase db;
	TextView laccount,loamount;
	String ukey,uname,uamount;
	Button ok;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.approveloan);
		db=Main.db;
		db.execSQL(CREATE_TABLE);
		
		laccount=(TextView)findViewById(R.id.laccount);
		loamount=(TextView)findViewById(R.id.lamount);
		ok=(Button)findViewById(R.id.ok);
		
		ukey=getIntent().getExtras().getString("lacno");
		uname=getIntent().getExtras().getString("lname");
		uamount=getIntent().getExtras().getString("lamount");
		
		
		ContentValues cv = new ContentValues();
		cv.put("accountno",ukey);
		cv.put("username",uname );
		cv.put("loanamount",uamount);
		long results = db.insert(ApproveLoan.this.LOAN_CREATED, null, cv);	
		
		if(results == -1)
		{
			Toast.makeText(ApproveLoan.this, "Loan not approved !!!", Toast.LENGTH_LONG).show();
		}else {
			Toast.makeText(ApproveLoan.this, "Loan Approved!!!", Toast.LENGTH_LONG).show();
		
		laccount.setText(ukey);
		loamount.setText(uamount);
		
		ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent i=new Intent(getApplicationContext(), AdminHome.class);
				startActivity(i);
				
				long res=db.delete("APPLY_FOR_LOAN", "username=?", new String[]{uname});
				if(res==-1)
				{
					Toast.makeText(ApproveLoan.this, "Not Deleted", 5000).show();
				}
				else
				{
					Toast.makeText(ApproveLoan.this, "Deleted", 5000).show();
				}
				finish();

			}
		});

		}
		
	}

}
