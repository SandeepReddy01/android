package com.wishwell.microbankingonsmartphone;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Apply_For_Loan extends Activity{

	EditText acno,loan_amount,address,phoneno,username;
	Button submit,home;
	Spinner sp1;
	SQLiteDatabase db;
	String loantype,sname;
	String amount,phone,accno;
	String account,uname;
	
	public  SharedPreferences sp = null;
	public  SharedPreferences.Editor spe = null;

	String types[]={"select","HomeLoan","VehicalLoan","EducationLoan","GoldLoan","AgriculturalLoans","Others"};

	public static final String TABLE_APPLYLOAN="APPLY_FOR_LOAN";

	public static final String CREATE_TABLE_APPLYFORLOAN="create table if not exists "+TABLE_APPLYLOAN+"(accountno int(12) not null,username text not null,loantype text not null,loanamount int(10) not null,address text not null,phoneno int(10) not null)";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.apply_for_loan);
		sp = this.getSharedPreferences("UserDetails", MODE_PRIVATE);
		sname = sp.getString("susername", null);
		
		Log.v("@@@!!!@@@", " sname and spwd:"+sname);

		

		db=Main.db;

		submit=(Button)findViewById(R.id.button1);

		home=(Button)findViewById(R.id.button2);

		acno=(EditText)findViewById(R.id.editText1);
		sp1=(Spinner)findViewById(R.id.spinner1);
		loan_amount=(EditText)findViewById(R.id.editText2);
		address=(EditText)findViewById(R.id.editText4);
		phoneno=(EditText)findViewById(R.id.editText5);
		username=(EditText)findViewById(R.id.editText3);
		
		db.execSQL(CREATE_TABLE_APPLYFORLOAN);
		sp1.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, types));
		sp1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub

				loantype=types[arg2];

				if(loantype.equals("select"))
				{
					
					loantype="select";
					Log.v("@@@@@@@@@@@@@@@@", "value"+loantype);
				}
				else
				{
					loantype=types[arg2];
					Log.v("@@@@@@@@@@@@@@@@", "value"+loantype);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
		Cursor cs;
		cs=db.query("ACCOUNT_CREATED", new String[]{"accountno","username"}, "username=?", new String[]{sname}, null, null, null);

		if (cs.moveToFirst())
		{
			do {
				 account=cs.getString(0);
				 uname=cs.getString(1);
				 
			} while (cs.moveToNext());

		}
		
		acno.setText(account);
		username.setText(uname);

		submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				// TODO Auto-generated method stub
				
				
				if(loantype=="select")
				{
					Toast.makeText(getApplicationContext(), "Please Select Valid LoanType", 5000).show();
				}
				else
				{	
					Log.v("@@@@@@@@@@@@@@@@", "@@@@@@@@@Before#############");
					
					accno=acno.getText().toString();
					amount=loan_amount.getText().toString();
					phone=phoneno.getText().toString();
					
					Log.v("@@@@@@@@@@@@@@@@", "Values"+amount+accno+phone);
					
					if(acno.length()>0 &&loantype.length()>0 && address.length()>0 && loan_amount.length()>0 && phoneno.length()>0)
					{
						Log.v("@@@@@@@@@@@@@@@@", "@@@@@@@@@after#############");
						
						ContentValues cv = new ContentValues();
						cv.put("accountno",accno);
						cv.put("username", uname);
						cv.put("loantype", loantype);
						cv.put("loanamount", amount);
						cv.put("address", address.getText().toString());
						cv.put("phoneno", phone);

						long results = db.insert(Apply_For_Loan.this.TABLE_APPLYLOAN, null, cv);	

						if(results == -1)
						{
							Toast.makeText(Apply_For_Loan.this, "Registration has been failed, please try again !!!", Toast.LENGTH_LONG).show();
						}else {
							Toast.makeText(Apply_For_Loan.this, "Registration has been done successuflly !!!", Toast.LENGTH_LONG).show();
							//It is to reset the values

						}
						acno.setText("");
						address.setText("");
						phoneno.setText("");
						loan_amount.setText("");

					}
					else 
					{
						Toast.makeText(Apply_For_Loan.this, "Please Enter All Values !!!", Toast.LENGTH_LONG).show();
						//It is to reset the values

					}
				}
			}
		});

		home.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent i=new Intent(Apply_For_Loan.this,UserHome.class);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);



			}
		});

	}

}
