package com.example.aniru.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by aniru on 10/25/2017.
 */

public class BooklistAdapter extends RecyclerView.Adapter<BooklistAdapter.MyViewHolder> {

    List<BookDetails_FB> mBookList;

    public BooklistAdapter (List<BookDetails_FB> bookList) {
        mBookList = bookList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_booklist, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        BookDetails_FB book = mBookList.get(position);
        holder.tv_bookTitle.setText(book.getTitle());
    }

    @Override
    public int getItemCount() {
        return mBookList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_bookTitle;

        public MyViewHolder(View view) {
            super(view);
            tv_bookTitle = (TextView) view.findViewById(R.id.tv_bookTitle);
        }
    }
}
