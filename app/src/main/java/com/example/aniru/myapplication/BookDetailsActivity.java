package com.example.aniru.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * Created by aniru on 11/4/2017.
 */

public class BookDetailsActivity extends AppCompatActivity {

    TextView tv_bookTitle;
    TextView tv_bookDescription;

    public BookDetailsActivity() {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookdetails);

        Intent intent = getIntent();
        String bookTitle = intent.getStringExtra("SelectedBook");

        // Create a new Fragment to be placed in the activity layout
        BookDetails_Fragment bookDetailsFragment = new BookDetails_Fragment();

        // In case this activity was started with special instructions from an
        // Intent, pass the Intent's extras to the fragment as arguments
        bookDetailsFragment.setArguments(getIntent().getExtras());

        // Add the fragment to the 'fragment_container' FrameLayout
        getSupportFragmentManager().beginTransaction()
                .add(R.id.bookdetailsfragment_container, bookDetailsFragment).commit();

    }
}
