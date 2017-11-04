package com.example.aniru.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aniru on 11/4/2017.
 */

public class BookDetails_Fragment extends Fragment {

    @BindView(R.id.tv_bookTitle)
    TextView tv_bookTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bookdetails, container, false);

        String bookTitle = getArguments().getString("SelectedBook");

        ButterKnife.bind(this, view);

        tv_bookTitle.setText(bookTitle);

        return view;
    }
}
