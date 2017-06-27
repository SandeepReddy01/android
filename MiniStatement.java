package com.wishwell.microbankingonsmartphone;

import java.util.ArrayList;

import android.app.Activity;
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
import android.widget.ListView;
import android.widget.TextView;

public class MiniStatement extends Activity{

	String facc;
	EditText accountno;
	Button submit,home;
	SQLiteDatabase db;
	public static SharedPreferences sp = null;
	public static SharedPreferences.Editor spe = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ministatement);

		db=Main.db;

		sp = this.getSharedPreferences("UserDetails", MODE_PRIVATE);
		String sname = sp.getString("susername", null);

		accountno=(EditText)findViewById(R.id.editText1);
		submit=(Button)findViewById(R.id.button1);
		home=(Button)findViewById(R.id.button2);

		Cursor cs;
		cs=db.query("AmountDeposited", new String[]{"accountno"}, "username=?", new String[]{sname}, null, null, null);
		if (cs.moveToFirst())
		{
			do {

				facc=cs.getString(0);

			} while (cs.moveToNext());
			accountno.setText(facc);
		}

		submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(MiniStatement.this,MiniStatementView.class);
				i.putExtra("acno", facc);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
			}
		});
		home.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(MiniStatement.this,UserHome.class);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
			}
		});
	}
}
