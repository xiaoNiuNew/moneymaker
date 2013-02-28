package org.leonlong.android.moneymaker.util;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class BitmapUtil {

//	public static void goToGitHub(Context context) {
//		Uri uriUrl = Uri.parse("http://github.com/jfeinstein10/slidingmenu");
//		Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
//		context.startActivity(launchBrowser);
//	}

	public static Bitmap getBitmapByWidthAndHeight(Bitmap bm, int new_width,
			int new_height) {
		// 获得图片的宽高
		int width = bm.getWidth();
		int height = bm.getHeight();
		// 设置想要的大小
		int newWidth = new_width;
		int newHeight = new_height;
		// 计算缩放比例
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// 取得想要缩放的matrix参数
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		// 得到新的图片
		Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix,
				true);

		return newbm;
	}

}
