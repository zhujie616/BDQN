package rjfsdo.sharoncn.android.BDQN.CoolVideoPlayer.Utils;

import java.io.File;
import java.io.FileFilter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Environment;
import android.util.Log;

public class Utils {
	public static Bitmap getTextureFromBitmapResource(Context context,int res){
		Bitmap bmp = null;
		
		Matrix myMatrix = new Matrix();
		myMatrix.postScale(1, -1);
		
		try{
			bmp = BitmapFactory.decodeResource(context.getResources(), res);
			return Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), myMatrix, false);
		}catch(Exception e){
			return null;
		}finally{
			if(bmp != null){
				bmp.recycle();
			}
		}
	}
	
	public static String getSdCardPath(){
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			return Environment.getExternalStorageDirectory().getPath();
		}
		Log.v("getSdCardPath", "��ÿ�ֵ");
		return null;
	}
	
	public static FileFilter getFilter(){
		return new FileFilter(){
			public boolean accept(File file) {
				return file.isDirectory() || file.getName().matches("^.*\\.(3gp|mp4|rmvb|rm|avi)$");
			}
		};
	}
	
	public static String toMB(long size){
		return (size / 1024 / 1024) + "MB";
	}
}
