package rjfsdo.sharoncn.android.BDQN.CoolVideoPlayer.adapter;

import java.util.List;

import rjfsdo.sharoncn.android.BDQN.CoolVideoPlayer.Models.VideoInfo;

import cn.com.jbit.coolvideoplayer.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class VideoListAdapter extends BaseAdapter {
	private LayoutInflater videoListInflater;
	private List<VideoInfo> videoList;
	
	static class ViewHolder{
		ImageView ivVideoThumbnail;
		TextView tvVideoName;
		TextView tvVideoSize;
	}
	
	public VideoListAdapter(Context context,
			List<VideoInfo> data) {
		this.videoListInflater = LayoutInflater.from(context);
		this.videoList = data;
	}

	public int getCount() {
		if(videoList != null){
			return videoList.size();
		}else{
			return 0;
		}
	}

	public Object getItem(int position) {
		return videoList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		VideoInfo video = videoList.get(position);
		ViewHolder holder = null;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = videoListInflater.inflate(R.layout.list_item_videoinfo, null);
			holder.ivVideoThumbnail = (ImageView) convertView.findViewById(R.id.iv_video_thunmnail);
			holder.tvVideoName = (TextView) convertView.findViewById(R.id.tv_video_name);
			holder.tvVideoSize = (TextView) convertView.findViewById(R.id.tv_video_size);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.ivVideoThumbnail.setImageBitmap(video.getThumbnail());
		holder.tvVideoName.setText(video.getName());
		holder.tvVideoSize.setText(video.getSize());
		return convertView;
	}

}
