package rjfsdo.sharoncn.android.CartoonReaderWeb.CartoonReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import rjfsdo.sharoncn.android.CartoonReaderWeb.CartoonReader.Utils.Constants;
import rjfsdo.sharoncn.android.CartoonReaderWeb.CartoonReader.Utils.Utils;
import rjfsdo.sharoncn.android.CartoonReaderWeb.Data.BookInfoDao;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

public class BaseActivity extends Activity {
	private static final String TAG = "CartoonReaderBaseActivity";
	protected static Map<String,String> imagePosition = new HashMap<String,String>();
	protected static LinkedList<String> imageList = new LinkedList<String>();
	protected static ArrayList<Activity> allActivity = new ArrayList<Activity>();
	protected static String bookId = null;//����bookId�ǲ��ǿ��ж���SDcard���ݹ�����picPath��������涯���Ķ������ݹ�����picPath
	protected static BookInfoDao dao;
	
	public static Activity getActivityByName(String name){
		return null;
	}
	
	/**
	 * �˳�
	 * @param con
	 */
	public static void exitApp(Context con){
		//ѭ���ر�Activity
		Iterator<Activity> it = allActivity.iterator();
		while(it.hasNext()){
			Activity act = it.next();
			if(act != null){
				act.finish();
			}
		}
		saveReaderState();
	}
	
	/**
	 * ���浱ǰ�Ķ���������Ϣ
	 */
	public static void saveReaderState(){
		saveReadedInfo();
		try{
			String picPath = Utils.getImagePath(imagePosition, imageList);
			if(picPath != null){
				//false ��ʾ����Դ�ļ���׷�ӣ������ļ��е�����
				Utils.saveFile(Constants.HISTORY, picPath, false);
			}
		}catch(Exception e){
			Log.i(TAG,e.getMessage());
		}
	}
	
	//�����Ķ���¼�����ݿ�
	private static void saveReadedInfo(){
		if(bookId != null && dao != null){
			Log.i(TAG,"��������Ķ�λ��:" + imagePosition.get("positionId"));
			dao.setLastReadedPosition(bookId, imagePosition.get("positionId"));
		}
	}
	
	/**
	 * �Զ����˳��Ի���
	 * @param context
	 */
	public static void promptExit(final Context context){
		exitApp(context);
//		//�Զ��岼��
//		View view = LayoutInflater.from(context).inflate(R.layout.layout_logout_cartoonreader, null);
//		//������ʾ�Ի���
//		AlertDialog.Builder dialog = new AlertDialog.Builder(context);
//		dialog.setTitle(R.string.logout_title);
//		dialog.setView(view);
//		//ȷ����ť
//		dialog.setPositiveButton(R.string.logout_submit, new DialogInterface.OnClickListener(){
//
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				//�����˳��Ի����˳��Ķ���
//				exitApp(context);
//			}
//		});
//		
//		//ȡ����ť
//		dialog.setNegativeButton(R.string.logout_cancel, new DialogInterface.OnClickListener(){
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				//�ر���ʾ�Ի���
//				dialog.dismiss();
//			}
//		});
//		dialog.show();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	//��back����ʾ�Ƿ��˳�
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(KeyEvent.KEYCODE_BACK == keyCode && event.getRepeatCount() == 0){
			promptExit(this);
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
}