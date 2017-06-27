package com.wishwell.microbankingonsmartphone;

import java.util.Calendar;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AmountDeposite extends Activity {
	SQLiteDatabase db;
	EditText amount,accountno;
	public static String Tablename="AmountDeposited";
	public static final String CREATE_TABLE="create table if not exists "+Tablename+"(username text not null,accountno INTEGER not null,Date text,DepositAmount INTEGER,TransferAmount INTEGER,AvailableBalance INTEGER)";

	Button btn,home;
	String un,ac;
	String date;
	int csmnt,csdate,csyear;
	Integer avlbal,deposit,chk;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.amountdeposit);
		db = Main.db;

		db.execSQL(CREATE_TABLE);

		accountno=(EditText)findViewById(R.id.editText1);
		amount=(EditText)findViewById(R.id.editText2);

		btn = (Button)findViewById(R.id.button1);
		home = (Button)findViewById(R.id.button2);

		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
								
				if(accountno.length() > 0&& amount.length() > 0)
				{
					chk=Integer.parseInt(amount.getText().toString());
					deposit=Integer.parseInt(amount.getText().toString());
						
						Cursor cs;

						cs = db.query("ACCOUNT_CREATED", new String[]{"username","accountno"}, "accountno = ?", new String[]{accountno.getText().toString()}, null, null, null);

						if(cs.moveToFirst())
						{
							do {

								un = cs.getString(0);
								ac = cs.getString(1);

							} while (cs.moveToNext());
						}

						Cursor cs1;

						cs1 = db.query("AmountDeposited", new String[]{"AvailableBalance"}, "accountno = ?", new String[]{accountno.getText().toString()}, null, null, null);

						if(cs1.moveToFirst())
						{
							do {

								avlbal =Integer.parseInt(cs1.getString(0));

							} while (cs1.moveToNext());
						}
						
						
						if(avlbal==null)
						{
							avlbal=deposit;
						}else
						{
							avlbal=avlbal+deposit;	
						}

						Calendar cal=Calendar.getInstance();


						csmnt=cal.get(Calendar.MONTH)+1;
						csdate=cal.get(Calendar.DAY_OF_MONTH);
						csyear=cal.get(Calendar.YEAR);

						date=String.valueOf(csdate)+"/"+String.valueOf(csmnt)+"/"+String.valueOf(csyear);

						Log.v("@@@@@@@@@@@@@@@@@@@@@@@@@@@", "date is"+date+avlbal+chk+deposit);

						if(ac.equals(accountno.getText().toString()))
						{
							if(deposit >= 100)
							{
								ContentValues cv = new ContentValues();
								cv.put("username", un);
								cv.put("accountno", accountno.getText().toString());
								cv.put("Date", date);
								cv.put("DepositAmount", amount.getText().toString());
								cv.put("TransferAmount", 0);
								cv.put("AvailableBalance",avlbal);

								long result=db.insert("AmountDeposited", null, cv);
								if(result==-1)
								{
									Toast.makeText(AmountDeposite.this, "Amount Deposited Failuered", Toast.LENGTH_LONG).show();
								}
								else
								{
									Toast.makeText(AmountDeposite.this, "Amount Deposited Successfull", Toast.LENGTH_LONG).show();
									amount.setText("");
									accountno.setText("");
									
								}

							}else {
								Toast.makeText(AmountDeposite.this, "Amount Should be RS 100 are above", 5000).show();
							}
						
						}else {
							Toast.makeText(AmountDeposite.this, "Enter Valid AccountNumber", Toast.LENGTH_LONG).show();
						}
				}else {
					Toast.makeText(AmountDeposite.this, "Please fill all", Toast.LENGTH_LONG).show();

				}
			}
		});

		home.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getApplicationContext(), AdminHome.class);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
					
			}
		});


	}

}
