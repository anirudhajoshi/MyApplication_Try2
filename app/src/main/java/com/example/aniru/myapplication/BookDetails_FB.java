package com.example.aniru.myapplication;

//import android.os.Parcel;
//import android.os.Parcelable;

import org.parceler.Parcel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by aniru on 10/12/2017.
 */

@Parcel
public class BookDetails_FB {

    String imageLink;
    String title;

    String bookCheckedOutDate;

    String bookDueDate;

    private boolean setReminder = false;

    String isbn;

    ArrayList<String> authors = new ArrayList<String>();

    ArrayList<String> categories = new ArrayList<String>();

    int pageCount;

    String summary;

    private String bookReturnedDate;

    private int numberOfRenewals = 0;

    String authorslist = "";

    String categorieslist = "";

    // Default constructor required for calls to Firebase DataSnapshot.getValue(BookDetails_FB.class)
    // Default constructor for Parcelable
    public BookDetails_FB() {

    }

    private boolean bBookIsCheckedOut;
    private boolean bBookIsFavorite;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPageCount() {
        return Integer.toString(pageCount);
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getAuthors() {

        return this.authorslist;
    }

    public void setAuthors(ArrayList<String> bookauthors) {

        if( bookauthors.size() > 1)
        {
            for (int i = 0;i<bookauthors.size();i++){
                if( i<bookauthors.size()-1 )
                    authorslist += bookauthors.get(i) + ", ";
                else
                    authorslist += bookauthors.get(i);
            }
        }
        else{
            this.authorslist = bookauthors.get(0);
        }
    }

    public String getCategories() {

        return categorieslist;

    }

    public void setCategories(ArrayList<String> categories) {


        if( categories.size() > 1)
        {
            for (int i = 0;i<categories.size();i++){
                if( i<categories.size()-1 )
                    categorieslist += categories.get(i) + ", ";
                else
                    categorieslist += categories.get(i);
            }
        }
        else{
            categorieslist = categories.get(0);
        }
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getBookCheckedOutDate() {
        /*SimpleDateFormat bookCheckedOutDateformat = new SimpleDateFormat("yyyy-MM-dd");
        String bookCheckedOutDateFormat = bookCheckedOutDateformat.format(this.bookCheckedOutDate);*/
        return this.bookCheckedOutDate;
    }

    public void setBookCheckedOutDate(String bookCheckedOutDate) {
        this.bookCheckedOutDate = bookCheckedOutDate;
    }

    public String getBookReturnedDate() {
      /*  SimpleDateFormat bookReturnedDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String bookReturnedDate = bookReturnedDateFormat.format(this.bookReturnedDate);*/
        return bookReturnedDate;
    }

    public void setBookReturnedDate(String bookReturnedDate) {
        this.bookReturnedDate = bookReturnedDate;
    }

    public String getBookDueDate() {
        /*SimpleDateFormat bookDueDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String bookDueDate = bookDueDateFormat.format(bookReturnedDate);*/
        return bookDueDate;
    }

    public void setBookDueDate(String bookDueDate) {
        this.bookDueDate = bookDueDate;
    }

    public String getNumberOfRenewals() {
        return String.valueOf(numberOfRenewals);
    }

    public void setNumberOfRenewals(int numberOfRenewals) {
        this.numberOfRenewals = numberOfRenewals;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public boolean isSetReminder() {
        return setReminder;
    }

    public void setSetReminder(boolean setReminder) {
        this.setReminder = setReminder;
    }

    public boolean isbBookIsCheckedOut() {
        return bBookIsCheckedOut;
    }

    public void setBookIsCheckedOut(boolean bBookIsCheckedOut) {
        this.bBookIsCheckedOut = bBookIsCheckedOut;
    }

    public boolean isbBookIsFavorite() {
        return bBookIsFavorite;
    }

    public void setBookIsFavorite(boolean bBookIsFavorite) {
        this.bBookIsFavorite = bBookIsFavorite;
    }

}

/*
public class BookDetails_FB implements Parcelable {
    // Default constructor
    public BookDetails_FB() {
        // Default constructor required for calls to Firebase DataSnapshot.getValue(BookDetails_FB.class)
    }

    public BookDetails_FB(Parcel in) {
        // Default constructor required for calls to Firebase DataSnapshot.getValue(BookDetails_FB.class)
    }

    public BookDetails_FB(String title, String isbn, String pageCount) {
        // Default constructor required for calls to Firebase DataSnapshot.getValue(BookDetails_FB.class)
        this.title = title;
        this.isbn = isbn;
        this.pageCount = Integer.parseInt(pageCount);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // dest.createTypedArrayList(BookDetails_FB.CREATOR );
        Bundle extras = new Bundle();
        extras.putString("title", this.title);
        extras.putString("isbn", this.isbn);
        extras.putString("pageCount", String.valueOf(this.pageCount));
        dest.writeBundle(extras);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public BookDetails_FB createFromParcel(Parcel in) {
            return new BookDetails_FB(in);
        }

        public BookDetails_FB[] newArray(int size) {
            return new BookDetails_FB[size];
        }
    };

    private String title;
    private String isbn;
    private int pageCount;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
*/
