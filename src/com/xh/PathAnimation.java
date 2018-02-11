package com.xh;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

/**
 * PathMeasure com.xh 2018 2018-2-6 上午10:00:44 instructions： author:liuhuiliang
 * email:825378291@qq.com
 **/

public class PathAnimation extends Animation implements AnimationListener {
	private IPathView pathView;
	private List<PathEnty> measures;
	private List<Path> paths;

	public PathAnimation(IPathView pathView) {
		// TODO Auto-generated constructor stub
		this.pathView = pathView;
		setAnimationListener(this);
		setInterpolator(new LinearInterpolator());
	}

	@Override
	protected void applyTransformation(float interpolatedTime, Transformation t) {
		// TODO Auto-generated method stub
		if (measures == null) {
			measures = new ArrayList<>();
			paths = new ArrayList<Path>();
			List<Path> pList = pathView.getPaths();
			for (int i = 0; i < pList.size(); i++) {
				measures.add(new PathEnty(pList.get(i)));
				paths.add(new Path());
			}
		}
		draw(interpolatedTime);
	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub
		Log.e("onAnimationEnd", "onAnimationEnd");
		draw(1);
		for (int i = 0; i < paths.size(); i++) {
			Path path = paths.get(i);
			path.reset();
			path.lineTo(0, 0);
		}
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub

	}

	public void draw(float percent) {
		for (int i = 0; i < measures.size(); i++) {
			PathEnty pathMeasure = measures.get(i);
			pathMeasure.setPath(paths.get(i), percent);
			// pathMeasure.getSegment(0, pathMeasure.getLength() * percent,
			// paths.get(i), true);
		}
		pathView.draw(paths);
	}

	private class PathEnty {
		PathMeasure measure;
		int counts;

		public PathEnty(Path path) {
			// TODO Auto-generated constructor stub
			measure = new PathMeasure();
			measure.setPath(path, false);
			while (measure.getLength() != 0) {
				measure.nextContour();
				counts++;
			}
			measure.setPath(path, false);
		}

		public void setPath(Path path, float percent) {
			percent = percent * counts;
			if (percent > 1) {
				counts--;
				percent -= 1;
				measure.nextContour();
			}
			Log.e("PathEnty", "percent=" + percent);
			measure.getSegment(0, measure.getLength() * percent, path, true);
		}
	}
}
