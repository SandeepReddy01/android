package com.wishwell.microbankingonsmartphone;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class AdminHome extends Activity{

	Button viewaccountrequestes,viewloanrequestes,DepostAmount,Home,showall;
	SQLiteDatabase db;
	public static  String Tablename;
	String uname;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.admin_home);


		Tablename=RequestForAccount.TABLE_REQUEST_NAME;

		db=Main.db;


		viewaccountrequestes=(Button)findViewById(R.id.button1);
		viewloanrequestes=(Button)findViewById(R.id.button2);
		DepostAmount=(Button)findViewById(R.id.button3);
		showall=(Button)findViewById(R.id.button4);
		Home=(Button)findViewById(R.id.button5);

		viewaccountrequestes.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//startActivity(new Intent(AdminHome.this,ViewAccountRequestes.class));
				Intent i=new Intent(getApplicationContext(), ViewAccountRequestes.class);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
			}
		});
		viewloanrequestes.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//startActivity(new Intent(AdminHome.this,ViewLoanRequestes.class));
				Intent i=new Intent(getApplicationContext(), ViewLoanRequestes.class);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);

			}
		});
		
		DepostAmount.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//startActivity(new Intent(AdminHome.this,AmountDeposite.class));
				Intent i=new Intent(getApplicationContext(), AmountDeposite.class);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
			}
		});
		showall.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//startActivity(new Intent(AdminHome.this,ShowAllUsers.class));
				Intent i=new Intent(getApplicationContext(), ShowAllUsers.class);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);

			}
		});

		Home.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//startActivity(new Intent(AdminHome.this,Main.class));
				Intent i=new Intent(getApplicationContext(), Main.class);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
				
			}
		});
	}
}
