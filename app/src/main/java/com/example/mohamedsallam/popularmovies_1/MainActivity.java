package com.example.mohamedsallam.popularmovies_1;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String POPULAR_MOVIE =
            "http://api.themoviedb.org/3/movie/popular?api_key=e1820d8d7f9b9576fcded771ce590cd4";
    private static final String TOP_RATED_MOVIE =
            "https://api.themoviedb.org/3/movie/top_rated?api_key=e1820d8d7f9b9576fcded771ce590cd4";

    private MovieAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView gridView = findViewById(R.id.gridview);
        mAdapter = new MovieAdapter(this, new ArrayList<Movie>());
        gridView.setAdapter(mAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                String title = mAdapter.getItem(i).getTitle();
                String releaseDate = mAdapter.getItem(i).getReleaseData();
                String decription = mAdapter.getItem(i).getDescrption();
                double voteAverage = mAdapter.getItem(i).getVoteAverage();
                String posterUrl = mAdapter.getItem(i).getPosterUrl();
                intent.putExtra("title", title);
                intent.putExtra("release_date", releaseDate);
                intent.putExtra("decription", decription);
                intent.putExtra("vote_average", voteAverage);
                intent.putExtra("poster_url", posterUrl);
                startActivity(intent);
            }
        });

        MovieAppAsyncTask task = new MovieAppAsyncTask();
        task.execute(POPULAR_MOVIE);
    }

    private class MovieAppAsyncTask extends AsyncTask<String, Void, List<Movie>> {
        @Override
        protected List<Movie> doInBackground(String... strings) {
            if (strings.length < 1 || strings[0] == null) {
                return null;
            }
            return Networking.fetchMovieAppData(strings[0]);
        }

        @Override
        protected void onPostExecute(List<Movie> movieApps) {
            mAdapter.clear();
            if (movieApps != null && !movieApps.isEmpty()) {
                mAdapter.addAll(movieApps);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.top_rated) {
            new MovieAppAsyncTask().execute(TOP_RATED_MOVIE);
            Toast toast = Toast.makeText(getApplicationContext(), "Sort by top rated", Toast.LENGTH_SHORT);
            toast.show();


            return true;
        }
        if (id == R.id.popular) {
            new MovieAppAsyncTask().execute(POPULAR_MOVIE);
            Toast toast = Toast.makeText(getApplicationContext(), "Sort by popular", Toast.LENGTH_SHORT);
            toast.show();

        }
        return super.onOptionsItemSelected(item);
    }
}
