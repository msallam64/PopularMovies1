package com.example.mohamedsallam.popularmovies_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String decription = intent.getStringExtra("decription");
        String releaseDate = intent.getStringExtra("release_date");
        String posterUrl = intent.getStringExtra("poster_url");
        double voteAverage = intent.getDoubleExtra("vote_average", 0.0);
        ImageView Image = findViewById(R.id.poster);
        Picasso.with(getApplicationContext()).load(posterUrl).into(Image);
        TextView Title = findViewById(R.id.Title);
        Title.setText(title);
        TextView ReleaseDate = findViewById(R.id.Release_date);
        ReleaseDate.setText(releaseDate);
        TextView voteTextView = findViewById(R.id.Vote_rate);
        voteTextView.setText(String.valueOf(voteAverage));
        TextView plot = findViewById(R.id.Plot);
        plot.setText(decription);

    }
}
