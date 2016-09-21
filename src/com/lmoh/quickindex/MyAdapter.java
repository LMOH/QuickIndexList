package com.lmoh.quickindex;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<Friend> list;
	
	public MyAdapter(Context context, ArrayList<Friend> list) {
		super();
		this.list = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.adapter_friend, null);
		} 
		ViewHolder holder = ViewHolder.getViewHolder(convertView);
		Friend friend = list.get(position);
		holder.name.setText(friend.getName());
		String currentWord = friend.getPinyin().charAt(0)+"";
		
		if (position > 0 ) {
			//不为第一个条目position才能减一
			String lastWord = list.get(position-1).getPinyin().charAt(0)+"";
			if (currentWord.equals(lastWord)) {
				//与上一条目同一字母,则隐藏
				holder.first_word.setVisibility(View.GONE);
			}else{
				//与上一条目不同一字母,则显示
				holder.first_word.setVisibility(View.VISIBLE);
				holder.first_word.setText(currentWord);
			}
		}else{
			//第一个条目直接设置
			holder.first_word.setVisibility(View.VISIBLE);
			holder.first_word.setText(currentWord);
		}
		return convertView;
	}

	static class ViewHolder {
		TextView name, first_word;

		public ViewHolder(View view) {
			name = (TextView) view.findViewById(R.id.name);
			first_word = (TextView) view.findViewById(R.id.first_word);
		}
		public static ViewHolder getViewHolder(View view){
			ViewHolder holder = (ViewHolder) view.getTag();
			if(holder==null){
				holder = new ViewHolder(view);
				view.setTag(holder);
			}
			return holder;
		}
	}
}
