package com.lmoh.quickindex;

import java.util.ArrayList;
import java.util.Collections;

import com.lmoh.quickindex.QuickIndexBar.OnTouchLetterListener;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private QuickIndexBar quickIndexBar;
	private ArrayList<Friend> friends = new ArrayList<Friend>();
	private ListView listview;
	private TextView currentWord;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initData();
		initView();
	}



	private void initView() {
		currentWord = (TextView) findViewById(R.id.currentWord);
		listview = (ListView) findViewById(R.id.listview);
		quickIndexBar = (QuickIndexBar) findViewById(R.id.quickIndexBar);
		quickIndexBar.setOnTouchLetterListener(new OnTouchLetterListener() {
			
			@Override
			public void onTouchLetter(String letter) {
				//Log.e("MainActivity", "letter:"+letter);
				for (int i = 0; i < friends.size(); i++) {
					String firstWord = friends.get(i).getPinyin().charAt(0)+"";
					if (letter.equals(firstWord)) {
						//字母和头字母相同,listview设置到该位置
						listview.setSelection(i);
						break;	//找到一个就行
					}
				}
				//显示中间textview，并隔时间隐藏
				showTextView(letter);
			}
		});
		listview.setAdapter(new MyAdapter(this, friends));
	}
	
	private Handler handler = new Handler();
	
	protected void showTextView(String letter) {
		currentWord.setVisibility(View.VISIBLE);
		currentWord.setText(letter);
		//handler先移除上次的任务
		handler.removeCallbacksAndMessages(null);
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				currentWord.setVisibility(View.GONE);
			}
		}, 1000);
	}



	private void initData() {
		fillList();
		//对集合数据进行排序
		Collections.sort(friends);
	}
	
	private void fillList() {
		// 虚拟数据
		friends.add(new Friend("李伟"));
		friends.add(new Friend("张三"));
		friends.add(new Friend("阿三"));
		friends.add(new Friend("阿四"));
		friends.add(new Friend("段誉"));
		friends.add(new Friend("段正淳"));
		friends.add(new Friend("张三丰"));
		friends.add(new Friend("陈坤"));
		friends.add(new Friend("林俊杰1"));
		friends.add(new Friend("陈坤2"));
		friends.add(new Friend("王二a"));
		friends.add(new Friend("林俊杰a"));
		friends.add(new Friend("张四"));
		friends.add(new Friend("林俊杰"));
		friends.add(new Friend("王二"));
		friends.add(new Friend("王二b"));
		friends.add(new Friend("赵四"));
		friends.add(new Friend("杨坤"));
		friends.add(new Friend("赵子龙"));
		friends.add(new Friend("杨坤1"));
		friends.add(new Friend("李伟1"));
		friends.add(new Friend("宋江"));
		friends.add(new Friend("宋江1"));
		friends.add(new Friend("李伟3"));
	}
}
