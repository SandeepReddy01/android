package com.wishwell.microbankingonsmartphone;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ViewLoanRequestes extends Activity {
	
	ListView lv;
	SQLiteDatabase db;
	String uname,selected;
	public static ArrayList<String> arrlist=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.viewloanrequestes);
		
		lv=(ListView)findViewById(R.id.listView1);
		arrlist=new ArrayList<String>();
		db=Main.db;
		Cursor cs;
		
		
		// here i have to change table name and keyvalue..bcz i dont have a/c no upto now
		
		cs= db.rawQuery("select username from APPLY_FOR_LOAN",null);
		if (cs.moveToFirst())
		{
			do {
				 uname=cs.getString(cs.getColumnIndex("username"));
				
			  arrlist.add(uname);
				
			} while (cs.moveToNext());
		}
		lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrlist));
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				selected=arrlist.get(arg2);
				Intent i=new Intent(ViewLoanRequestes.this,CreateLoan.class);
				i.putExtra("myloan",selected);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
				
			}
		});
		
	}

}
