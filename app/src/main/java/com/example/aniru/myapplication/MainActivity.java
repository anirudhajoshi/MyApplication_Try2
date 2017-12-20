package com.example.aniru.myapplication;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements DialogCaptureISBN.OnCompleteListener {

    @Override
    public void onComplete(BookDetails_FB bookDetails_fb) {

        // Create a bundle that has the BookDetails_FB object and pass it to the new book activity
        Intent intent = new Intent(this, NewBookActivity.class);

        intent.putExtra("NewBook", Parcels.wrap(bookDetails_fb));

        startActivity(intent);
    }

    public interface ClickListener {
        void onClick(View view, int position);
        void onLongClick(View view, int position);
    }

    @BindView(R.id.rv_CheckedOutBookList)
    RecyclerView rv_BookList;

    ArrayList<BookDetails_FB> mBooks = new ArrayList<BookDetails_FB>();

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private int[] tabIcons = {
            R.drawable.ic_alarm_black_24px,
            R.drawable.ic_favorite_black_24px,
            R.drawable.ic_library_books_black_24px
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // ShowHistory();

            // ShowCheckedOutBooks();

            // ShowFavoriteBooks();
        }
    }
    private void setupTabIcons() {
        // Checkout icon
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);

        // Favorites icon
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);

        // History icon
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CheckedOutBooks_Fragment(), getString(R.string.tab_checkedout));
        adapter.addFragment(new FavoriteBooks_Fragment(), getString(R.string.tab_favorites));
        adapter.addFragment(new History_Fragment(), getString(R.string.tab_history));
        viewPager.setAdapter(adapter);
    }

    private void ShowCheckedOutBooks() {

        // Create a new Fragment to be placed in the activity layout
        CheckedOutBooks_Fragment checkedOutBooksFragment = new CheckedOutBooks_Fragment();

        // In case this activity was started with special instructions from an
        // Intent, pass the Intent's extras to the fragment as arguments
        checkedOutBooksFragment.setArguments(getIntent().getExtras());


        // Add the fragment to the 'fragment_container' FrameLayout
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, checkedOutBooksFragment).commit();
    }

    private void ShowFavoriteBooks() {

        // Create a new Fragment to be placed in the activity layout
        FavoriteBooks_Fragment favoriteBooksFragment = new FavoriteBooks_Fragment();

        // In case this activity was started with special instructions from an
        // Intent, pass the Intent's extras to the fragment as arguments
        favoriteBooksFragment.setArguments(getIntent().getExtras());


        // Add the fragment to the 'fragment_container' FrameLayout
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, favoriteBooksFragment).commit();
    }

    private void ShowHistory() {

        // Create a new Fragment to be placed in the activity layout
        History_Fragment historyFragmnet = new History_Fragment();

        // In case this activity was started with special instructions from an
        // Intent, pass the Intent's extras to the fragment as arguments
        historyFragmnet.setArguments(getIntent().getExtras());

        // Add the fragment to the 'fragment_container' FrameLayout
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, historyFragmnet).commit();
    }
}
