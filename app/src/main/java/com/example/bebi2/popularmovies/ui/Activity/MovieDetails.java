package com.example.bebi2.popularmovies.ui.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.bebi2.popularmovies.Config.Config;
import com.example.bebi2.popularmovies.Pojo.Movie;
import com.example.bebi2.popularmovies.R;
import com.example.bebi2.popularmovies.ui.Fragments.MovieDetailsFragment;

public class MovieDetails extends AppCompatActivity {

    Context mContext;
    int mutedColor = R.attr.colorPrimary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        mContext = this;
        Movie movie = getIntent().getExtras().getParcelable(Config.BUNDLE_SINGLE_MOVIES);

        setUpToolBar(movie.getBackdrop(), movie.getTitle());





        if (savedInstanceState == null) {
            Fragment detailsFragment = MovieDetailsFragment.newInstance(movie);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, detailsFragment,
                            MovieDetailsFragment.class.getSimpleName())
                    .commit();
        }

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    private void setUpToolBar(String backDrop, String title) {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(title);
        collapsingToolbar.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);


        Glide.with(mContext)
                .load(backDrop)
                .asBitmap()
//                .placeholder(R.drawable.background_material)
//                .error(R.drawable.background_material)
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
                        super.onResourceReady(bitmap, anim);
                        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                            @SuppressWarnings("ResourceType")
                            @Override
                            public void onGenerated(Palette palette) {
                                mutedColor = palette.getMutedColor(R.color.colorPrimary);
                                if (getLuminance(mutedColor) < 240) {
                                    collapsingToolbar.setContentScrimColor(mutedColor);
                                    collapsingToolbar.setStatusBarScrimColor(getDarkerColor(mutedColor));
                                    return;
                                }


                            }
                        });
                    }
                });


        collapsingToolbar.setContentScrimColor(getResources().getColor(R.color.colorPrimary));
        collapsingToolbar.setStatusBarScrimColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    public static int getLuminance(int argb) {
        int lum = (77 * ((argb >> 16) & 255)
                + 150 * ((argb >> 8) & 255)
                + 29 * ((argb) & 255)) >> 8;
        return lum;
    }

    public static int getDarkerColor(int color) {
        int alpha = Color.alpha(color);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, (int) (red * 0.9), (int) (green * 0.9), (int) (blue * 0.9));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_movie_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            supportFinishAfterTransition();
        }

        return super.onOptionsItemSelected(item);
    }
}
