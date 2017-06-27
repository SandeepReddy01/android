package com.wishwell.microbankingonsmartphone;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter{

	Context myct;
	
	ArrayList<String>mynames;
	ArrayList<String>account;
	LayoutInflater lf;
	
	public ListAdapter(Context ct,ArrayList<String> arrlist,ArrayList<String> arrlist1) 
	{
	// TODO Auto-generated constructor stub
		
		myct=ct;
		mynames=arrlist;
		account=arrlist1;
		lf=(LayoutInflater)myct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mynames.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		View v=convertView;
		if(convertView==null)
		{
			v=lf.inflate(R.layout.listadapter, null);
			TextView tv1=(TextView)v.findViewById(R.id.textView1);
			TextView tv2=(TextView)v.findViewById(R.id.textView2);
			
			tv1.setText(mynames.get(arg0));
			tv2.setText(account.get(arg0));
			
			/*tv1.setText(mynames.indexOf(arg0));
			tv2.setText(String.valueOf(account.indexOf(arg0)));*/
		}
		return v;
		
	}

}
