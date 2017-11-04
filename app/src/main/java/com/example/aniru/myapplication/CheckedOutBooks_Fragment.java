package com.example.aniru.myapplication;

import android.content.Intent;
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

import static android.provider.AlarmClock.EXTRA_MESSAGE;

/**
 * Created by aniru on 11/3/2017.
 */


public class CheckedOutBooks_Fragment extends Fragment {

    public interface ClickListener {
        void onClick(View view, int position);
        void onLongClick(View view, int position);
    }

    @BindView(R.id.rv_CheckedOutBookList)
    RecyclerView rv_CheckedOutBookList;

    ArrayList<BookDetails_FB> mCheckedOutBooks = new ArrayList<BookDetails_FB>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_checkedoutbooks, container, false);
        ButterKnife.bind(this, view);

        mCheckedOutBooks.clear();
        SetupTestData_CheckedoutBooks();

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv_CheckedOutBookList.setLayoutManager(llm);

        BooklistAdapter booklistAdapter = new BooklistAdapter(mCheckedOutBooks);

        rv_CheckedOutBookList.setAdapter(booklistAdapter);

        rv_CheckedOutBookList.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), rv_CheckedOutBookList, new MainActivity.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                BookDetails_FB selectedBook = mCheckedOutBooks.get(position);
                // Toast.makeText(getActivity(), selectedBook.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getContext(), BookDetailsActivity.class);
                intent.putExtra("SelectedBook", selectedBook.getTitle());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));

        return view;
    }

    private void SetupTestData_CheckedoutBooks() {
        BookDetails_FB b1 = new BookDetails_FB();
        b1.setTitle("A Love Story");
        mCheckedOutBooks.add(b1);

        BookDetails_FB b2 = new BookDetails_FB();
        b2.setTitle("C");
        mCheckedOutBooks.add(b2);

        BookDetails_FB b3 = new BookDetails_FB();
        b3.setTitle("Omen");
        mCheckedOutBooks.add(b3);
    }
}
