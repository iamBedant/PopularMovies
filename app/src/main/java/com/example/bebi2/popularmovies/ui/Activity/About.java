package com.example.bebi2.popularmovies.ui.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bebi2.popularmovies.Pojo.Help;
import com.example.bebi2.popularmovies.R;

import java.util.ArrayList;

public class About extends AppCompatActivity {

    ArrayList<Help> helpList = new ArrayList<>();
    Context mContext;
    LinearLayout libs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        mContext = this;

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


        populateData();
        libs = (LinearLayout) findViewById(R.id.libs);

        for(Help help : helpList){
            generateViews(help);
        }


    }

    private void generateViews(final Help help) {
        View view = getLayoutInflater().inflate(R.layout.layout_help_item,null);

        TextView name = (TextView) view.findViewById(R.id.name);
        Button website = (Button) view.findViewById(R.id.url);

        name.setText(help.getName());
        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(help.getUrl()));
                startActivity(i);
            }
        });

        libs.addView(view);

    }

    private void populateData() {
        helpList.add(new Help("Udacity", "https://www.udacity.com"));
        helpList.add(new Help("Shared Element Transition", "https://github.com/codepath/android_guides/wiki/Shared-Element-Activity-Transition"));
        helpList.add(new Help("Volley", "http://www.truiton.com/2015/02/android-volley-example/"));
        helpList.add(new Help("Glide", "https://github.com/bumptech/glide"));
        helpList.add(new Help("Use of Palette", "http://blog.grafixartist.com/toolbar-animation-with-android-design-support-library/"));
        helpList.add(new Help("Stack Overflow", "http://stackoverflow.com/"));
    }

}
