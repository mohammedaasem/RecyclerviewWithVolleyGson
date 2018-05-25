package com.aasem.recyclerviewwithvolleyexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    TextView tvTitle,tvURL;
    ImageView ivImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tvTitle=findViewById(R.id.tv_title);
        tvURL=findViewById(R.id.tv_url);
        ivImage=findViewById(R.id.iv_image);
        Intent intent=getIntent();
        String name=intent.getExtras().getString("title");
        String url=intent.getExtras().getString("url");
        String image=intent.getExtras().getString("thumbnailUrl");
        tvTitle.setText(name);
        tvURL.setText(url);
        Glide
                .with(this)
                .load(image)
                .centerCrop()
                .into(ivImage);
    }
}
