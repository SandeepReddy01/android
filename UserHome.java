package com.wishwell.microbankingonsmartphone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class UserHome extends Activity  {

	Button request_for_account,money_transfer,apply_loan,ministatement,home;
	String uname,upass,uextra,passextra;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.userhome);

		request_for_account=(Button)findViewById(R.id.button1);
		money_transfer=(Button)findViewById(R.id.button2);
		apply_loan=(Button)findViewById(R.id.button3);
		ministatement=(Button)findViewById(R.id.button4);
		home=(Button)findViewById(R.id.button5);
		
		

		request_for_account.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			

				Intent i=new Intent(UserHome.this,RequestForAccount.class);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
			}
		});
		money_transfer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				

				Intent i=new Intent(UserHome.this,MoneyTransfer.class);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
			}
		});
		apply_loan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub


				Intent i=new Intent(UserHome.this,Apply_For_Loan.class);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
			}
		});
		ministatement.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				

				Intent i=new Intent(UserHome.this,MiniStatement.class);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
			}
		});
		home.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(UserHome.this,UserLogin.class);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
			}
		});




	}

}
