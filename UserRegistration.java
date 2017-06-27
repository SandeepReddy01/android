package com.wishwell.microbankingonsmartphone;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class UserRegistration extends Activity {

	static SQLiteDatabase db;
	EditText fname,lname,address,email,phoneno,qualification,username,password,conformpass;
	RadioButton gmale,gfemale;
	Button submit,cancel,home;
	String s1,s2;
	public static final String TABLE_NAME="USER_REGISTRATION";
	public static final String CREATE_TABLE="create table if not exists "+TABLE_NAME+"(firstname text not null,lastname text not null,address text not null,email text not null,phoneno int(10) not null,gender text not null,qualification text not null,username text primery key,password text not null)";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.userregistration);

		db = Main.db;

		submit=(Button)findViewById(R.id.button1);
		fname=(EditText)findViewById(R.id.editText1);
		lname=(EditText)findViewById(R.id.editText2);
		address=(EditText)findViewById(R.id.editText3);
		email=(EditText)findViewById(R.id.editText4);
		phoneno=(EditText)findViewById(R.id.editText5);
		gmale=(RadioButton)findViewById(R.id.gmale);
		gfemale=(RadioButton)findViewById(R.id.gfemale);

		qualification=(EditText)findViewById(R.id.editText6);
		username=(EditText)findViewById(R.id.editText7);
		password=(EditText)findViewById(R.id.editText8);
		conformpass=(EditText)findViewById(R.id.editText9);
		submit=(Button)findViewById(R.id.button1);
		cancel=(Button)findViewById(R.id.button2);
		home=(Button)findViewById(R.id.button3);

		db.execSQL(CREATE_TABLE);

		submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if(gmale.isChecked())
				{
					s1 = gmale.getText().toString();
				}else if (gfemale.isChecked())
				{
					s1 = gfemale.getText().toString();
				}
				
				if(fname.length()>0 && lname.length()>0 && address.length()>0 &&
						email.length()>0 && phoneno.length()>0&&s1.length()>0
						&& qualification.length()>0 && username.length()>0 && password.length()>0&&conformpass.length()>0 )
				{
					if(password.getText().toString().equals(conformpass.getText().toString()))
					{

						ContentValues cv = new ContentValues();
						cv.put("firstname", fname.getText().toString());
						cv.put("lastname", lname.getText().toString());
						cv.put("address", address.getText().toString());
						cv.put("email", email.getText().toString());
						cv.put("phoneno", phoneno.getText().toString());
						cv.put("gender", s1);
						cv.put("qualification", qualification.getText().toString());
						cv.put("username", username.getText().toString());
						cv.put("password", password.getText().toString());

						long results = db.insert(UserRegistration.this.TABLE_NAME, null, cv);	

						if(results == -1)
						{
							Toast.makeText(UserRegistration.this, "Registration has been failed, please try again !!!", Toast.LENGTH_LONG).show();
						}else {
							Toast.makeText(UserRegistration.this, "Registration has been done successuflly !!!", Toast.LENGTH_LONG).show();
							//It is to reset the values
							reSet();
						}

					}
				}else {
					Toast.makeText(UserRegistration.this, "Please Fill All The Fields !!!", Toast.LENGTH_LONG).show();
									
				}

			}
		});
		
		//cancel,home
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
				
				Intent i=new Intent(UserRegistration.this, UserLogin.class);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
				
				
			}
		});


	}
	public void reSet()
	{

		fname.setText("");
		lname.setText("");
		address.setText("");
		email.setText("");
		phoneno.setText("");
		gmale.setChecked(false);
		gfemale.setChecked(false);
		qualification.setText("");
		username.setText("");
		password.setText("");
		conformpass.setText("");

	}

}
