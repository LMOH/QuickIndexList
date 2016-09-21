package com.lmoh.quickindex;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class QuickIndexBar extends View {

	private String[] indexArr = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
			"R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
	private Paint paint;
	private int width;
	private float cellHeight;

	public QuickIndexBar(Context context) {
		super(context);
		init();
	}

	public QuickIndexBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public QuickIndexBar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		//
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);// 设置抗锯齿
		paint.setColor(Color.WHITE);
		paint.setTextSize(20);
		paint.setTextAlign(Align.CENTER);// 设置文本的起点是文字边框底边的中心

		// 获取x，y起点坐标（text从矩形框的右下角开始绘制）

	}

	/**
	 * onSizeChanged方法运行在onMeasure后，可获取测量长宽
	 */
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		width = getMeasuredWidth();
		// 获取每个字母所在格子的高度
		cellHeight = getMeasuredHeight() * 1f / 26;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		for (int i = 0; i < indexArr.length; i++) {
			float x = width / 2;
			// 每个字母的Y值 = 第一个格子高度/2 + 文字矩形框高度/2 + i个格子高度
			float y = cellHeight / 2 + getTextHeight(indexArr[i]) / 2 + i * cellHeight;
			//文字变色
			paint.setColor(lastIndex == i ? Color.BLACK : Color.WHITE);
			canvas.drawText(indexArr[i], x, y, paint);
		}
	}

	/**
	 * 获取文本的高度
	 * 
	 * @param text
	 * @return
	 */
	private int getTextHeight(String text) {
		// 获取文本的高度
		Rect bounds = new Rect();
		paint.getTextBounds(text, 0, text.length(), bounds);
		return bounds.height();
	}
	private int lastIndex = -1;
	// 获取触摸位置字母
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:		//去掉break，两个事件同时处理
	
		case MotionEvent.ACTION_MOVE:
			float y = event.getY();
			int index = (int) (y / cellHeight);
			if (lastIndex != index) {
				//Log.e("tag", indexArr[index]);
				if (index >= 0 && index < indexArr.length) {
					if (listener != null) {
						listener.onTouchLetter(indexArr[index]);
					}
				}
			}
			//赋值上一次位置
			lastIndex = index;
			break;
		case MotionEvent.ACTION_UP:
			//重置lastIndex
			lastIndex = -1;
			break;
		}
		//引起重绘
		invalidate();
		return true; // return true 自己消费事件
	}
	
	private OnTouchLetterListener listener;
	public void setOnTouchLetterListener(OnTouchLetterListener listener){
		this.listener = listener;
	}
	public interface OnTouchLetterListener{
		public void onTouchLetter(String letter);
	}
}
