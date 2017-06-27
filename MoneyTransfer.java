package com.wishwell.microbankingonsmartphone;

import java.util.Calendar;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MoneyTransfer extends Activity {

	EditText toaccount,fromaccount,amount;
	Button transfer,home;

	public  SharedPreferences sp = null;
	public  SharedPreferences.Editor spe = null;
	String sname,date,toacusername,toaccountno;

	Integer toavl,fdavil,amountforto,freamount,toreamount;

	int csmnt,csdate,csyear;

	SQLiteDatabase db;
	String Tablename;

	String fname,facc,fdeposite,ftrans,favil;
	String tname,tacc,tdeposite,ttrans,tavil;

	public static final String TABLE_MONEY_TRANSFER="MONEY_TRANSFER_DETAILS";
	public static final String CREATE_TABLE="create table if not exists "+TABLE_MONEY_TRANSFER+"(Date text not null,toaccount int(12) not null,fromaccount int(12) not null,amount int(10) not null)";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.moneytransfer);

		Tablename=AmountDeposite.Tablename;
		toaccount=(EditText)findViewById(R.id.editText1);
		fromaccount=(EditText)findViewById(R.id.editText2);
		amount=(EditText)findViewById(R.id.editText3);

		transfer=(Button)findViewById(R.id.button1);
		home=(Button)findViewById(R.id.button2);

		sp = this.getSharedPreferences("UserDetails", MODE_PRIVATE);
		sname = sp.getString("susername", null);

		Log.v("@@@@@@@@@@@@@@@@@@@@@@@", " sname:"+sname);
		db=Main.db;
		db.execSQL(CREATE_TABLE);

		Cursor cs;
		cs=db.query("AmountDeposited", new String[]{"username","accountno","AvailableBalance"}, "username=?", new String[]{sname}, null, null, null);
		//fname,facc,fdeposite,ftrans,favil
		if (cs.moveToFirst())
		{
			do {
				fname=cs.getString(0);
				facc=cs.getString(1);
				favil=cs.getString(2);

			} while (cs.moveToNext());
			fromaccount.setText(facc);
			amount.setText(favil);
		}

		transfer.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if(toaccount.length()>0 && fromaccount.length()>0 && amount.length()>0)
				{	
					//here i have problem toaccountno checkup
					
						Cursor cs1;
						cs1 = db.query("AmountDeposited", new String[]{"username","accountno","AvailableBalance"}, "accountno = ?", new String[]{toaccount.getText().toString()}, null, null, null);
						if(cs1.moveToFirst())
						{
							do {

								toacusername=cs1.getString(0);
								toaccountno=cs1.getString(1);
								toavl=Integer.parseInt(cs1.getString(2));

							} while (cs1.moveToNext());
						}

					
					fdavil=Integer.parseInt(favil);
					amountforto=Integer.parseInt(amount.getText().toString());
					
					if(amountforto>=100)
					{
							
						if(amountforto<=fdavil)
						{
							freamount=fdavil-amountforto;

							toreamount=amountforto;
							//.........................catch toaccount problem

							toavl=toavl+amountforto;

							Calendar cal=Calendar.getInstance();
							csmnt=cal.get(Calendar.MONTH)+1;
							csdate=cal.get(Calendar.DAY_OF_MONTH);
							csyear=cal.get(Calendar.YEAR);

							date=String.valueOf(csdate)+"/"+String.valueOf(csmnt)+"/"+String.valueOf(csyear);

							ContentValues cv = new ContentValues();
							cv.put("username", toacusername);
							cv.put("accountno", toaccountno);
							cv.put("Date", date);
							cv.put("DepositAmount",toreamount);
							cv.put("TransferAmount", 0);
							cv.put("AvailableBalance",toavl);

							long result1=db.insert("AmountDeposited", null, cv);
							if(result1==-1)
							{
								Toast.makeText(MoneyTransfer.this, "Deposite Failuered", Toast.LENGTH_LONG).show();
							}
							else
							{
								Toast.makeText(MoneyTransfer.this, "Deposite Successfull", Toast.LENGTH_LONG).show();


								ContentValues cv1 = new ContentValues();
								cv1.put("username", fname);
								cv1.put("accountno", facc);
								cv1.put("Date", date);
								cv1.put("DepositAmount",0);
								cv1.put("TransferAmount",amountforto);
								cv1.put("AvailableBalance",freamount);

								long results2 = db.insert("AmountDeposited", null, cv1);	

								if(results2 == -1)
								{
									Toast.makeText(MoneyTransfer.this, "Transaction Failed...", Toast.LENGTH_LONG).show();
								}
								else
								{
									Toast.makeText(MoneyTransfer.this, "Transfored Successfully....", Toast.LENGTH_LONG).show();
									/*toaccount.setText("");
									fromaccount.setText(facc);
									amount.setText(freamount);*/
								}
							}	

						}else
						{
							Toast.makeText(getApplicationContext(),"You Don't have Sufficent Amount", 5000).show();
						}
						
					}
					else
					{
						Toast.makeText(getApplicationContext(), "Transfore RS:100 are above", 5000).show();
					}
					
				}else
				{
					Toast.makeText(getApplicationContext(),"Please fill all", 5000).show();
				}
			}
		});
		
		
		home.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(MoneyTransfer.this, UserHome.class);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);

			}
		});


	}

}

 

