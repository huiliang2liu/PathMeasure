package com.xh;

import java.util.ArrayList;
import java.util.List;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

/**
 * PathMeasure com.xh 2018 2018-2-5 下午3:24:39 instructions： author:liuhuiliang
 * email:825378291@qq.com
 **/

public class PathView extends ImageView {
	List<Path> pathSources;
	List<Path> pathDraw;
	List<PathMeasure> measures;
	Paint paint;
	LayoutParams params;
	final static String TAG = "PathView";

	public PathView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}

	@SuppressLint("DrawAllocation")
	@Override
	public void layout(int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		Log.e("layout", "l=" + l + " t=" + t + " r=" + r + " b=" + b);
//		RectF rect = new RectF();
//		for (int i = 0; i < pathSources.size(); i++) {
//			pathSources.get(i).computeBounds(rect, true);
//			l = (int) (l > rect.left ? rect.left : l);
//			t = (int) (t > rect.top ? rect.top : t);
//			r = (int) (r > rect.right ? r : rect.right);
//			b = (int) (b > rect.bottom ? b : rect.bottom);
//		}
		super.layout(l, t, r, b);
	}

	public PathView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	public PathView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		pathSources = new ArrayList<Path>();
		pathDraw = new ArrayList<Path>();
		paint = new Paint();
		paint.setStrokeWidth(2);
		paint.setAntiAlias(true);
		paint.setColor(Color.RED);
		paint.setStyle(Style.STROKE);
		measures = new ArrayList<PathMeasure>();
		params = getLayoutParams();
	}

	public void addPath(Path path) {
		pathSources.add(path);
	}

	@SuppressLint("NewApi")
	public void start() {
		for (int i = 0; i < pathSources.size(); i++) {
			pathDraw.add(new Path());
			measures.add(new PathMeasure(pathSources.get(i), false));
		}
		ValueAnimator mAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
		mAnimator.setInterpolator(new LinearInterpolator());
		mAnimator.setDuration(3000);
		mAnimator.setRepeatCount(ValueAnimator.INFINITE);
		mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				// Log.i("TAG", "onAnimationUpdate");
				// 增加一个callback 便于子类重写搞事情
				for (int i = 0; i < measures.size(); i++) {
					onPathAnimCallback(pathDraw.get(i), measures.get(i),
							animation);
				}
				// 通知View刷新自己
				PathView.this.invalidate();
				Log.e(TAG, "addUpdateListener");
			}
		});

		mAnimator.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationRepeat(Animator animation) {
				// Log.w("TAG", "onAnimationRepeat: ");
				// 每段path走完后，要补一下 某些情况会出现 animPath不满的情况
				boolean end = true;
				for (int i = 0; i < measures.size(); i++) {
					PathMeasure pathMeasure = measures.get(i);
					pathMeasure.getSegment(0, pathMeasure.getLength(),
							pathDraw.get(i), true);
					pathMeasure.nextContour();
					end &= pathMeasure.getLength() == 0;
				}

				// 绘制完一条Path之后，再绘制下一条
				Log.e(TAG, "onAnimationRepeat");
				// 长度为0 说明一次循环结束
				if (end) {
					// if (isInfinite) {// 如果需要循环动画
					// animPath.reset();
					// animPath.lineTo(0, 0);
					// pathMeasure.setPath(sourcePath, false);
					// } else {// 不需要就停止（因为repeat是无限 需要手动停止）
					animation.end();
					// listen.end();
					// }
				}
			}

		});
		mAnimator.start();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		for (int i = 0; i < pathDraw.size(); i++) {
			canvas.drawPath(pathDraw.get(i), paint);
		}
	}

	/**
	 * 用于子类继承搞事情，对animPath进行再次操作的函数
	 * 
	 * @param view
	 * @param sourcePath
	 * @param animPath
	 * @param pathMeasure
	 */
	@SuppressLint("NewApi")
	public void onPathAnimCallback(Path animPath, PathMeasure pathMeasure,
			ValueAnimator animation) {
		float value = (float) animation.getAnimatedValue();
		// 获取一个段落
		pathMeasure.getSegment(0, pathMeasure.getLength() * value, animPath,
				true);
	}
}
