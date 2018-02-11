package com.xh;

import android.content.Context;
import android.graphics.Path;
import android.util.AttributeSet;

/**
 * PathMeasure com.xh 2018 2018-2-11 上午10:20:20 instructions： author:liuhuiliang
 * email:825378291@qq.com
 **/

public class BrokenLineView extends PathView {

	public BrokenLineView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public BrokenLineView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public BrokenLineView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public void setPoint(float[] points) {
		reset();
		if (points == null)
			return;
		int len = points.length;
		if (len % 2 == 1)
			len -= 1;
		if (len == 0)
			return;
		Path path = new Path();
		for (int i = 0; i < len; i += 2) {
			if (i == 0) {
				path.moveTo(points[i], points[i + 1]);
			} else
				path.lineTo(points[i], points[i + 1]);
		}
		addPath(path);
	}
}
