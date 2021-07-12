package com.example.solution2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        TextView searchTv = findViewById(R.id.search_movies);
        TextView releaseTv = findViewById(R.id.release_movies);
        TextView upcomingTv = findViewById(R.id.upcoming_movies);
        TextView popularTv = findViewById(R.id.popular_movies);

        searchTv.setOnClickListener(v -> startActivity(new Intent(this, SearchActivity.class)));
        releaseTv.setOnClickListener(v -> startActivity(new Intent(this, NowPlayingActivity.class)));
        upcomingTv.setOnClickListener(v -> startActivity(new Intent(this, UpcomingActivity.class)));
        popularTv.setOnClickListener(v -> startActivity(new Intent(this, PopularActivity.class)));
    }
}