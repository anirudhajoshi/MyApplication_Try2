package com.example.aniru.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.aniru.myapplication.BooksAPI.BookDetails;
import com.example.aniru.myapplication.BooksAPI.Item;
import com.example.aniru.myapplication.GoogleBarcodeStarterCode.BarcodeCaptureActivity;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

import com.google.android.gms.vision.barcode.Barcode;

/**
 * Created by aniru on 11/13/2017.
 */

public class DialogCaptureISBN extends DialogFragment {

    private static final int RC_BARCODE_CAPTURE = 9001;

    public static interface OnCompleteListener {
        public abstract void onComplete(BookDetails_FB bookDetails_fb);
    }

    private OnCompleteListener mListener;

    List<Item> mBooks = new ArrayList<>();
    BookDetails_FB mBookDetails = new BookDetails_FB();
    String mIsbn = "";

    private final Gson gson = new GsonBuilder()
            .create();

    private Retrofit retrofit;

    @BindView(R.id.btn_addManually) Button btn_addManually;
    @BindView(R.id.btn_searchISBN) Button btn_searchISBN;
    @BindView(R.id.ib_Camera) ImageButton ib_Camera;
    @BindView(R.id.edit_ISBN) EditText edit_ISBN;
    @BindView(R.id.indeterminateCyclicBar) ProgressBar indeterminateCyclicBar;

    // make sure the Activity implemented it
    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        Activity activity = getActivity();
        try {
            this.mListener = (OnCompleteListener)activity;
        }
        catch (final ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnCompleteListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_captureisbn, container, false);

        ButterKnife.bind(this, v);

        indeterminateCyclicBar.setVisibility(View.INVISIBLE);

        // Watch for button clicks.
        btn_searchISBN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // When button is clicked, call up to owning activity.
                onSearchISBN();
            }
        });

        btn_addManually.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // When button is clicked, call up to owning activity.
                OnAddManually();
            }
        });

        ib_Camera.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // When button is clicked, call up to owning activity.
                OnCamera();
            }
        });

        return v;
    }

    private void OnCamera() {

        // Toast.makeText(getContext(), "OnCamera", Toast.LENGTH_SHORT).show();
        Intent barcodeCaptureIntent = new Intent(getActivity().getApplicationContext(), BarcodeCaptureActivity.class);

        try {
            startActivityForResult(barcodeCaptureIntent, RC_BARCODE_CAPTURE);
        }catch (Exception e){
            Timber.d(e.getMessage());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_BARCODE_CAPTURE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);
                    // statusMessage.setText(R.string.barcode_success);
                    // barcodeValue.setText(barcode.displayValue);
                    // Timber.d("Barcode read: " + barcode.displayValue);
                    edit_ISBN.setText(barcode.displayValue);

                } else {
                    // statusMessage.setText(R.string.barcode_failure);
                    Timber.d( "No barcode captured, intent data is null");
                    Toast.makeText(getContext(), R.string.noISBNCaptured, Toast.LENGTH_SHORT).show();
                }
            } else {
                // statusMessage.setText(String.format(getString(R.string.barcode_error),
                //        CommonStatusCodes.getStatusCodeString(resultCode)));
                Timber.d(getString(R.string.barcode_error));
                Toast.makeText(getContext(), R.string.barcode_error, Toast.LENGTH_SHORT).show();
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void OnAddManually() {

        Intent intent = new Intent(getActivity(), NewBookActivity.class);
        startActivity(intent);
    }

    private void onSearchISBN() {

        indeterminateCyclicBar.setVisibility(View.VISIBLE);
        // Get the ISBN entered in the edit text and call Google API to see if we can get it
        // Create the retrofit client
        retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.baseURL))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GoogleBooksAPI apiService =
                retrofit.create(GoogleBooksAPI.class);

        getBookDetails(apiService);
    }

    private void getBookDetails(GoogleBooksAPI booksAPI) {

        if( isEmpty(edit_ISBN)){
            Toast.makeText(getContext(), R.string.isbnCannotBeEmpty, Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            mIsbn = edit_ISBN.getText().toString();
        }


        Call<BookDetails> callAPIForBookDetails = booksAPI
                .getBookDetails(  "isbn:" + mIsbn );

        Timber.d("BookDetailsActivity","callAPIForBookDetails.enqueue");

        callAPIForBookDetails.enqueue(new Callback<BookDetails>() {
            @Override
            public void onResponse(Call<BookDetails> call, Response<BookDetails> response) {

                indeterminateCyclicBar.setVisibility(View.INVISIBLE);

                BookDetails bookDetails = response.body();
                if( bookDetails!=null ) {
                    if( bookDetails.getTotalItems()>0 && bookDetails.getItems()!=null ) {
                        mBooks = bookDetails.getItems();
                        for (int i = 0; i < mBooks.size(); i++) {
                            // Get title
                            mBookDetails.setTitle(mBooks.get(i).getVolumeInfo().getTitle());
                            // Get author
                            mBookDetails.setAuthors((ArrayList<String>) mBooks.get(i).getVolumeInfo().getAuthors());
                            // Get book descirption/summary
                            mBookDetails.setSummary(mBooks.get(i).getVolumeInfo().getDescription());
                            // Get book category
                            mBookDetails.setCategories((ArrayList<String>)mBooks.get(i).getVolumeInfo().getCategories());
                            // Get imagelink
                            mBookDetails.setImageLink(mBooks.get(i).getVolumeInfo().getImageLinks().getSmallThumbnail());
                            // Get ISBN
                            mBookDetails.setIsbn(mIsbn);
                            // Get book page count
                            mBookDetails.setPageCount(mBooks.get(i).getVolumeInfo().getPageCount());
                            // Get book checked out (current date)
                            String bookCheckedOutDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
                            String bookCheckedOutDate_f1 = new SimpleDateFormat("E, M dd yyyy").format(Calendar.getInstance().getTime());

                            mBookDetails.setBookCheckedOutDate(bookCheckedOutDate);
                            // Get book renewals (set to 0 first time)
                            int bookRenewals = 0;   // For now
                            // Get book returned date (when user taps and selects checked in)
                            String bookReturnedDate  = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
                            mBookDetails.setBookReturnedDate(bookReturnedDate);
                            // Get book due date (current date + x days (or weeks) as specified in settings
                            java.util.Date dtBookCheckedOutDate = Calendar.getInstance().getTime();
                            int checkOutPeriod = getCheckOutPeriod();
                            java.util.Date dt = new java.util.Date();
                            Calendar c = Calendar.getInstance();
                            c.setTime(dt);
                            c.add(Calendar.DATE, checkOutPeriod);
                            java.util.Date dtBookDueDate = c.getTime();
                            mBookDetails.setBookDueDate(dtBookDueDate.toString());
                            break;
                        }

                        // Pass this over to the activity and dismiss the dialog
                        dismiss();

                        mListener.onComplete(mBookDetails);
                    }
                    else{
                        String msg = getString(R.string.isbnNotFound, mIsbn);
                        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    // Timber.d("vInfo is null");
                    Toast.makeText(getContext(), getString(R.string.BookwithISBN) + mIsbn + getString(R.string.BookNotFound), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<BookDetails> call, Throwable t) {
                indeterminateCyclicBar.setVisibility(View.INVISIBLE);

                Toast.makeText(getContext(), R.string.errorGettingISBN + ". " + mIsbn + " " + R.string.tryAddingIManually, Toast.LENGTH_LONG).show();
            }
         });
    }

    private int getCheckOutPeriod() {
        // TODO
        // Remove hardcoded value and get it from preferences
        return 14;
    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }
}