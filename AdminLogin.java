package com.wishwell.microbankingonsmartphone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends Activity{
	
	EditText adminid,password;
	Button submit,home;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.admin);
		
		adminid=(EditText)findViewById(R.id.editText1);
		password=(EditText)findViewById(R.id.editText2);
		
		submit=(Button)findViewById(R.id.button1);
		home=(Button)findViewById(R.id.button2);
		
		
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if (adminid.getText().toString().equals("admin")&&password.getText().toString().equals("admin")) 
				{
					Intent i=new Intent(getApplicationContext(),AdminHome.class);
					i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
		
				}
				else
				{
					Toast.makeText(getApplicationContext(), "Please Fill Correct Fields", 5000).show();
				}
				
			}
		});
		home.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getApplicationContext(),AdminLogin.class);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
				
			}
		});
	}

}
