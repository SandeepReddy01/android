package com.wishwell.microbankingonsmartphone;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserLogin extends Activity {

	EditText uname,upass;
	Button submit,newuser;
	SQLiteDatabase db;

	public static  String Tablename;

	public static SharedPreferences sp = null;
	public static SharedPreferences.Editor spe = null;
	String us,ps;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.userlogin);
		Tablename=UserRegistration.TABLE_NAME;
		db=Main.db;

		sp = this.getSharedPreferences("UserDetails", MODE_PRIVATE);


		submit=(Button)findViewById(R.id.button1);
		newuser=(Button)findViewById(R.id.button2);
		uname=(EditText)findViewById(R.id.editText1);
		upass=(EditText)findViewById(R.id.editText2);

		submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub


				if(uname.length()>0&&upass.length()>0)
				{  

					String key=uname.getText().toString();
					Cursor cs;
					cs=db.query(Tablename, new String[]{"username","password"}, "username=?", new String[]{key}, null, null, null);

					if (cs.moveToFirst())
					{
						do {
							us=cs.getString(0);
							ps=cs.getString(1);

						} while (cs.moveToNext());
						if(uname.getText().toString().equals(us)&& upass.getText().toString().equals(ps))
						{
							//go to userhome
							Intent i=new Intent(UserLogin.this,UserHome.class);
							startActivity(i);
						}
						else
						{
							//login failed
							Toast.makeText(UserLogin.this,"Login Failed Enter Valid Details", 5000).show();
						}
					}
					else
					{
						Toast.makeText(UserLogin.this,"No User Data Found", 5000).show();
					}
				}
				else
				{
					Toast.makeText(getApplicationContext(), "Please Fill All The Details", 5000).show();
				}

				spe = sp.edit();
				spe.putString("susername", us);
				spe.putString("spass", ps);
				spe.commit();
			}
		});


		newuser.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent i=new Intent(getApplicationContext(),UserRegistration.class);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);

			}
		});


	}

}
