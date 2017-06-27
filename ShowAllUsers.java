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

public class ShowAllUsers extends Activity{

	ListView lv;
	SQLiteDatabase db;
	public static ArrayList<String> arrlist,arrlist1=null;
	Button btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showallusers);
		
		btn=(Button)findViewById(R.id.button1);
		db=Main.db;
		arrlist=new ArrayList<String>();
		arrlist1=new ArrayList<String>();
		
		Cursor cs;
		cs= db.rawQuery("SELECT accountno, username FROM ACCOUNT_CREATED",null);

		if (cs.moveToFirst())
		{
			do {
				
				String accnt=cs.getString(cs.getColumnIndex("accountno"));
				String uname=cs.getString(cs.getColumnIndex("username"));
				
				arrlist1.add(accnt);
				arrlist.add(uname);
				Log.v("@@@@@@@@@@@@@@$$$$$$$$$$$$$$$$$$", "Requestes"+arrlist+"and"+arrlist1);

			} while (cs.moveToNext());
		}
		lv=(ListView)findViewById(R.id.listView1);
		lv.setAdapter(new ListAdapter(this, arrlist, arrlist1));
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(ShowAllUsers.this,AdminHome.class));
				finish();
			}
		});
		
	}

}
