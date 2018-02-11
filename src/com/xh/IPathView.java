package com.xh;

import java.util.List;

import android.graphics.Path;

/**
 * PathMeasure com.xh 2018 2018-2-6 上午10:01:57 instructions： author:liuhuiliang
 * email:825378291@qq.com
 **/

public interface IPathView {
	/**
	 * 
	 * 2018 2018-2-6 上午10:02:35 annotation：画路径 author：liuhuiliang email
	 * ：825378291@qq.com
	 * 
	 * @param paths
	 *            void
	 */
	public void draw(List<Path> paths);

	/**
	 * 
	 * 2018 2018-2-6 上午10:11:42 annotation：获取需要动画的路径 author：liuhuiliang email
	 * ：825378291@qq.com
	 * 
	 * @return List<Path>
	 */
	public List<Path> getPaths();
}
