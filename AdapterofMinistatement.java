package com.wishwell.microbankingonsmartphone;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterofMinistatement extends BaseAdapter {

	Context myct;
	ArrayList<String>Date;
	ArrayList<String>Deposit;
	ArrayList<String>Transefered;
	LayoutInflater lf;

	public AdapterofMinistatement(Context ct,ArrayList<String> arrlist,ArrayList<String> arrlist1,ArrayList<String> arrlist2) {
		// TODO Auto-generated constructor stub
		myct=ct;
		Date=arrlist;
		Deposit=arrlist1;
		Transefered=arrlist2;
		lf=(LayoutInflater)myct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Date.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		View v=convertView;
		if(convertView==null)
		{
			v=lf.inflate(R.layout.adapter_of_ministatement, null);
			TextView tv1=(TextView)v.findViewById(R.id.textView1);
			TextView tv2=(TextView)v.findViewById(R.id.textView2);
			TextView tv3=(TextView)v.findViewById(R.id.textView3);
			tv1.setText(Date.get(position));
			tv2.setText(Deposit.get(position));
			tv3.setText(Transefered.get(position));
			
			/*tv1.setText(mynames.indexOf(arg0));
			tv2.setText(String.valueOf(account.indexOf(arg0)));*/
		}
		return v;
		
	}

}
