package com.wishwell.microbankingonsmartphone;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ViewAccountRequestes extends Activity {
	
	ListView lv;
	SQLiteDatabase db;
	String selected;
	public static ArrayList<String> arrlist=null;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.viewaccountrequestes);
		lv=(ListView)findViewById(R.id.listView1);
		arrlist=new ArrayList<String>();
		db=Main.db;
		Cursor cs;
		cs= db.rawQuery("select username from REQUEST_FOR_ACCOUNT",null);
		if (cs.moveToFirst())
		{
			do {
				String uname=cs.getString(cs.getColumnIndex("username"));
			if(uname.length()<0)
			{
				arrlist.add("No Requestes Found");
				Log.v("@@@@@@@@@@@@@@##############", "Requestes"+arrlist);
			}
			else
			{
			  arrlist.add(uname);
			  Log.v("@@@@@@@@@@@@@@$$$$$$$$$$$$$$$$$$", "Requestes"+arrlist);
			}
			} while (cs.moveToNext());
		}
		lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrlist));
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				selected=arrlist.get(arg2);
				Intent i=new Intent(ViewAccountRequestes.this,CreateAccount.class);
				i.putExtra("myname",selected);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
				
				
			}
		});
		
	}

}
