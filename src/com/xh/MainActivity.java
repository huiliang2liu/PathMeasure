package com.xh;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
	BrokenLineView pathView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		try {
			pathView = (BrokenLineView) findViewById(R.id.path_view);
			// Path path = new Path();
			// path.moveTo(600, 600);
			// path.lineTo(600, 10);
			// path.lineTo(10, 10);
			// path.lineTo(10, 600);
			// path.close();
			// pathView.addPath(path);
			// Path path1 = new Path();
			// path1.moveTo(550, 60);
			// path1.lineTo(550, 550);
			// path1.lineTo(60, 550);
			// path1.lineTo(60, 60);
			// path1.close();
			// path.addPath(path1);
			// // pathView.addPath(path1);
			// Path path2 = new Path();
			// path2.moveTo(110, 110);
			// path2.lineTo(110, 500);
			// path2.lineTo(500, 500);
			// path2.lineTo(500, 110);
			// path2.close();
			// path.addPath(path2);
			// Path path3 = new Path();
			// path3.moveTo(160, 450);
			// path3.lineTo(160, 160);
			// path3.lineTo(450, 160);
			// path3.lineTo(450, 450);
			// path3.close();
			// pathView.addPath(path3);
			// Path path4 = new Path();
			// path4.moveTo(400, 400);
			// path4.lineTo(400, 210);
			// path4.lineTo(210, 210);
			// path4.lineTo(210, 400);
			// path4.close();
			// path3.addPath(path4);
			// Path path5 = new Path();
			// path5.moveTo(350, 260);
			// path5.lineTo(350, 350);
			// path5.lineTo(260, 350);
			// path5.lineTo(260, 260);
			// path5.close();
			// path3.addPath(path5);
			float[] points = new float[70];
			for (int i = 0; i < 70; i += 2) {
				points[i] = i * 20;
				points[i + 1] = (float) (Math.random() * 700);
			}
			pathView.setPoint(points);
			pathView.start();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
