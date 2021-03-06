package com.wbasheer.testapplication.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.wbasheer.testapplication.AppController;
import com.wbasheer.testapplication.R;
import com.wbasheer.testapplication.model.Image;


public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
	private LayoutInflater inflater;
	private List<Image> imageItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public CustomListAdapter(Activity activity, List<Image> imageItems) {
		this.activity = activity;
		this.imageItems = imageItems;
	}

	@Override
	public int getCount() {
		return imageItems.size();
	}

	@Override
	public Object getItem(int location) {
		return imageItems.get(location);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (inflater == null)
			inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		if (convertView == null)
			convertView = inflater.inflate(R.layout.listitemlayout, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        NetworkImageView thumbNail = (NetworkImageView) convertView.findViewById(R.id.imageView);
		TextView imageTitle = (TextView) convertView.findViewById(R.id.titleTextView);

		// getting image object for the row
		Image i = imageItems.get(position);

		// download thumbnail image
        thumbNail.setErrorImageResId(R.drawable.ic_launcher);
		thumbNail.setImageUrl(AppController.imageUrlBase + i.getName(), imageLoader);

		// image title
		imageTitle.setText(i.getTitle());

		return convertView;
	}

}