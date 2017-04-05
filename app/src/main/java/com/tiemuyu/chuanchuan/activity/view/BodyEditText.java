package com.tiemuyu.chuanchuan.activity.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by 梁文硕 on 2017/4/3.
 */

public class BodyEditText extends EditText {
	public BodyEditText(Context context) {
		super(context);
	}

	public BodyEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public BodyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}
	private boolean isShow = true;

	@Override
	protected void onTextChanged(CharSequence text, int start, int before, int count) {
		if (text.toString().contains(".")) {
			if (text.length() - 1 - text.toString().indexOf(".") > 2  ) {
				text = text.toString().subSequence(0,
						text.toString().indexOf(".") + 3);
				this.setText(text);
				this.setSelection(text.length());
			}
		}
		if (text.toString().trim().substring(0).equals(".")) {
			text = "0" + text;
			this.setText(text);
			this.setSelection(2);
		}

		if (text.toString().startsWith("0")
				&& text.toString().trim().length() > 1) {
			if (!text.toString().substring(1, 2).equals(".")) {
				this.setText(text.subSequence(0, 1));
				this.setSelection(1);
				return;
			}
		}
	}

	public Double getUserInput(){
		if (!String.valueOf(this.getText()).equals("")){
			return Double.parseDouble(String.valueOf(this.getText()));
		}
		return 0.0;
	}

	public void setUnderLine(boolean isShow) {
		this.isShow = isShow;
		this.invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Paint mPaint = new Paint();
		mPaint.setStyle(Paint.Style.STROKE);
		// 你可以根据自己的具体需要在此处对画笔做更多个性化设置
		mPaint.setColor(Color.BLACK);
		if (isShow) {
			canvas.drawLine(0, this.getHeight(), this.getWidth() - 1,
					this.getHeight(), mPaint);
		}
	}
}
