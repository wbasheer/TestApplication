package com.wbasheer.testapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.wbasheer.testapplication.model.Image;

/**
 * Created by waleed.basheer on 10/16/2014.
 */
public class ImageInfoActivity extends Activity {

    private Image image;

    NetworkImageView imageThumbnail;
    private TextView imageTitle;
    private TextView imageDescription;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public static final String defaultDescription = "No Description";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.image_info_layout);

        imageThumbnail = (NetworkImageView) findViewById(R.id.detailImageView);
        imageTitle = (TextView) findViewById(R.id.imageTitleTextView);
        imageDescription = (TextView) findViewById(R.id.imageDescriptionTextView);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        image = (Image) bundle.getSerializable("IMAGE");

        if(image == null)
            return;


        // download thumbnail image
        if(image.getName() != null)
            imageThumbnail.setImageUrl(AppController.imageUrlBase + image.getName(), imageLoader);

        if(image.getTitle() != null)
            imageTitle.setText(image.getTitle());

        if(image.getDescription() != null)
            imageDescription.setText(image.getDescription());

    }
}
