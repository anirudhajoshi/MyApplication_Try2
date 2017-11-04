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
 * Created by aniru on 11/3/2017.
 */

public class History_Fragment extends Fragment {

    public interface ClickListener {
        void onClick(View view, int position);
        void onLongClick(View view, int position);
    }

    @BindView(R.id.rv_History)
    RecyclerView rv_History;

    ArrayList<BookDetails_FB> mHistoryBooks = new ArrayList<BookDetails_FB>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        ButterKnife.bind(this, view);

        mHistoryBooks.clear();
        SetupTestData_History();

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv_History.setLayoutManager(llm);

        BooklistAdapter booklistAdapter = new BooklistAdapter(mHistoryBooks);

        rv_History.setAdapter(booklistAdapter);

        rv_History.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), rv_History, new MainActivity.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                BookDetails_FB selectedBook = mHistoryBooks.get(position);
                Toast.makeText(getActivity(), selectedBook.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));

        return view;
    }

    private void SetupTestData_History() {

        BookDetails_FB b1 = new BookDetails_FB();
        b1.setTitle("A Love Story");
        mHistoryBooks.add(b1);

        BookDetails_FB b2 = new BookDetails_FB();
        b2.setTitle("C");
        mHistoryBooks.add(b2);

        BookDetails_FB b3 = new BookDetails_FB();
        b3.setTitle("Omen");
        mHistoryBooks.add(b3);

        BookDetails_FB b4 = new BookDetails_FB();
        b4.setTitle("Bible");
        mHistoryBooks.add(b4);

        BookDetails_FB b5 = new BookDetails_FB();
        b5.setTitle("Madame Butterfly");
        mHistoryBooks.add(b5);

        BookDetails_FB b6 = new BookDetails_FB();
        b6.setTitle("And Then There Were None");
        mHistoryBooks.add(b6);

        BookDetails_FB b7 = new BookDetails_FB();
        b7.setTitle("World War Z");
        mHistoryBooks.add(b7);

        BookDetails_FB b9 = new BookDetails_FB();
        b9.setTitle("O");
        mHistoryBooks.add(b9);

        BookDetails_FB b10 = new BookDetails_FB();
        b10.setTitle("Three Musketeers");
        mHistoryBooks.add(b10);

        BookDetails_FB b11 = new BookDetails_FB();
        b11.setTitle("Little Soldiers");
        mHistoryBooks.add(b11);

        BookDetails_FB b12 = new BookDetails_FB();
        b12.setTitle("The 5 Extinctions");
        mHistoryBooks.add(b12);

        BookDetails_FB b13 = new BookDetails_FB();
        b13.setTitle("Genesis");
        mHistoryBooks.add(b13);

        BookDetails_FB b14 = new BookDetails_FB();
        b14.setTitle("Ten Little Indians");
        mHistoryBooks.add(b14);

        BookDetails_FB b15 = new BookDetails_FB();
        b15.setTitle("What do you say after you say hello?");
        mHistoryBooks.add(b15);
    }
}
