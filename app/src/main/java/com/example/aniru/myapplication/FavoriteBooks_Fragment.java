package com.example.aniru.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aniru on 11/4/2017.
 */

public class FavoriteBooks_Fragment extends Fragment {
    public interface ClickListener {
        void onClick(View view, int position);
        void onLongClick(View view, int position);
    }

    @BindView(R.id.rv_FavoriteBookList)
    RecyclerView rv_FavoritesBookList;

    ArrayList<BookDetails_FB> mFavoriteBooks = new ArrayList<BookDetails_FB>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favoritebooks, container, false);
        ButterKnife.bind(this, view);

        mFavoriteBooks.clear();
        SetupTestData_FavoritedBooks();

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv_FavoritesBookList.setLayoutManager(llm);


        BooklistAdapter booklistAdapter = new BooklistAdapter(mFavoriteBooks);

        rv_FavoritesBookList.setAdapter(booklistAdapter);

        rv_FavoritesBookList.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), rv_FavoritesBookList, new MainActivity.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                BookDetails_FB selectedBook = mFavoriteBooks.get(position);
                Toast.makeText(getActivity(), selectedBook.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));

        return view;
    }

    private void SetupTestData_FavoritedBooks() {

        BookDetails_FB b1 = new BookDetails_FB();
        b1.setTitle("A Love Story");
        mFavoriteBooks.add(b1);

        BookDetails_FB b2 = new BookDetails_FB();
        b2.setTitle("O");
        mFavoriteBooks.add(b2);

        BookDetails_FB b3 = new BookDetails_FB();
        b3.setTitle("Genesis");
        mFavoriteBooks.add(b3);

        BookDetails_FB b4 = new BookDetails_FB();
        b4.setTitle("What do you say after you say hello?");
        mFavoriteBooks.add(b4);
    }
}
