package com.example.aniru.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.parceler.Parcels;

public class NewBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newbook);


        // Bundle data = getIntent().getExtras();
        // Bundle data = getIntent().getBundleExtra("NewBook");
        // BookDetails_FB bookDetails_fb = (BookDetails_FB) data.getParcelable("NewBook");

        // Create a new Fragment to be placed in the activity layout
        NewBook_Fragment newBookFragment = new NewBook_Fragment();

        // In case this activity was started with special instructions from an
        // Intent, pass the Intent's extras to the fragment as arguments
        newBookFragment.setArguments(getIntent().getExtras());

        // Add the fragment to the 'fragment_container' FrameLayout
        getSupportFragmentManager().beginTransaction()
                .add(R.id.newbookfragment_container, newBookFragment).commit();
    }
}
