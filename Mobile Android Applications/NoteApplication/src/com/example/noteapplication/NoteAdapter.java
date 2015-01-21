package com.example.noteapplication;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.v7.internal.widget.AdapterViewCompat.OnItemClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class NoteAdapter extends ArrayAdapter<BasicNote> {
	private Context mContext;
	private int viewResId;
	private List<BasicNote> noteList;
	
	private static class ViewHolder{
		private TextView itemView1;
	}
	
	public NoteAdapter(Context context, int viewResId, List<BasicNote> noteList){
		super(context, viewResId, noteList);
		this.viewResId = viewResId;
		this.mContext = context;
		this.noteList = noteList;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View v  = convertView;
		
		if (v == null){
			LayoutInflater vi;
			vi = LayoutInflater.from(getContext());
			v = vi.inflate(R.layout.list_item, null);
		}
		
		BasicNote note = getItem(position);
		
		if( note != null){
			TextView tv1 = (TextView) v.findViewById(R.id.rowDataView1);
			if(tv1 != null){
				tv1.setText(note.getNoteVal());
			}
		}
		
		return v;
		
		
//		ViewHolder viewHolder = null;
//		
//		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		View dataView = inflater.inflate(R.layout.list_item, parent, false);
//		
//		if (dataView == null){
//			TextView textView = (TextView) dataView.findViewById(R.id.rowDataView1);
//			BasicNote note = noteList.get(position);
//			textView.setText(note.getNoteVal());
//		}else{
//			
//		}
		
//		if (convertView == null){
//			convertView = LayoutInflater.from(this.getContext())
//					.inflate(R.id.noteList, parent, false);
//			
//			viewHolder = new ViewHolder();
//			viewHolder.itemView1 = (TextView) convertView.findViewById(R.id.list_item);
//			
//			convertView.setTag(viewHolder);
//		}else{
//			viewHolder = (ViewHolder) convertView.getTag();
//		}
//		
//		BasicNote note = getItem(position);
//		
//		if(note != null){
//			viewHolder.itemView1.setText(note.getNoteVal());
//		}
//		return convertView;
	}
}
