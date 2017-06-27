package com.wishwell.microbankingonsmartphone;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MiniStatementView extends Activity{

	ListView lv;
	SQLiteDatabase db;
	TextView uname,accno,avilbal;
	Button home;
	public static ArrayList<String> arrlist,arrlist1,arrlist2=null;
	String key;

	String name,Date,Deposit,Transfer,Avilabule;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ministatementview);
		db=Main.db;

		home=(Button)findViewById(R.id.button1);
		uname=(TextView)findViewById(R.id.textView3);
		accno=(TextView)findViewById(R.id.textView5);
		avilbal=(TextView)findViewById(R.id.textView11);

		lv=(ListView)findViewById(R.id.listView1);
		key=getIntent().getExtras().getString("acno");
		arrlist=new ArrayList<String>();
		arrlist1=new ArrayList<String>();
		arrlist2=new ArrayList<String>();
		
		arrlist.clear();
		arrlist1.clear();
		arrlist2.clear();

		
		
		Cursor cs;
		cs= db.rawQuery("SELECT * FROM AmountDeposited WHERE accountno="+key,null);

		if (cs.moveToFirst())
		{
			do {
				name=cs.getString(0);
				Date=cs.getString(2);
				Deposit=cs.getString(3);
				Transfer=cs.getString(4);
				Avilabule=cs.getString(5);

				arrlist.add(Date);
				arrlist1.add(Deposit);
				arrlist2.add(Transfer);

				Log.v("@@@@@@@@@@@@@@$$$$$$$$$$$$$$$$$$", "Requestes"+key+name+arrlist+"and"+arrlist1+"and"+arrlist2+"@@@@"+Avilabule);

			} while (cs.moveToNext());
		}
		uname.setText(name);
		accno.setText(key);
		avilbal.setText(Avilabule);
		lv.setAdapter(new AdapterofMinistatement(this, arrlist, arrlist1, arrlist2));
		
		
		home.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				startActivity(new Intent(MiniStatementView.this,UserHome.class));
				finish();

			}
		});


	}

}
