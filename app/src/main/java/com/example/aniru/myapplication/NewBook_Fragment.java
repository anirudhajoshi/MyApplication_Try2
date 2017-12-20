package com.example.aniru.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * Created by aniru on 11/21/2017.
 */

public class NewBook_Fragment extends Fragment  {

    @BindView(R.id.imgBookImage)
    ImageView imgBookImage;

    @BindView(R.id.et_Title)
    EditText et_Title;

    @BindView(R.id.tv_BookDueDate)
    TextView tv_BookDueDate;

    @BindView(R.id.tv_BookCheckedOutDate)
    TextView tv_BookCheckedOutDate;

    @BindView(R.id.et_ISBN)
    EditText et_ISBN;

    @BindView(R.id.tv_Authors)
    TextView tv_Authors;

    @BindView(R.id.tv_bookCategories)
    TextView tv_bookCategories;

    @BindView(R.id.et_PageCount)
    EditText et_PageCount;

    @BindView(R.id.btn_CheckOut)
    Button btn_CheckOut;

    @BindView(R.id.btn_SetReminder)
    Button btn_SetReminder;

    String bookImageURL;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference myRef = mDatabase.getDatabase().getReference("checkedoutBook");
    private DatabaseReference mBookDetailDatabaseReference;
    private ValueEventListener mBooksListener;
    private ChildEventListener mChildEventListener;

    public static final int RC_SIGN_IN = 1;
    BookDetails_FB mCheckedOutBook;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_newbook, container, false);
        ButterKnife.bind(this, view);

        if( getActivity().getIntent().hasExtra("NewBook")) {
            mCheckedOutBook = (BookDetails_FB) Parcels.unwrap(getActivity().getIntent().getParcelableExtra("NewBook"));

            mCheckedOutBook.setBookIsCheckedOut(false);

            mCheckedOutBook.setBookIsFavorite(false);

            bookImageURL = mCheckedOutBook.getImageLink();
            et_Title.setText(mCheckedOutBook.getTitle());
            Date dtCheckedOutDate = mCheckedOutBook.getBookCheckedOutDate();
            tv_BookCheckedOutDate.setText(dtCheckedOutDate.toString());

            Date dtDueDate = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(dtCheckedOutDate);
            c.add(Calendar.DATE, getNumberOfDaysBeforeDueDate());
            dtDueDate = c.getTime();
            tv_BookDueDate.setText(dtDueDate.toString());

            et_ISBN.setText(mCheckedOutBook.getIsbn());

            tv_Authors.setText(mCheckedOutBook.getAuthors());

            tv_bookCategories.setText(mCheckedOutBook.getCategories());

            et_PageCount.setText(mCheckedOutBook.getPageCount());

            btn_CheckOut.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    onCheckedOut();
                }
            });

            btn_SetReminder.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    onSetReminder();
                }
            });
        }
        else{
            // TODO
            // Show error message, hide all other controls
        }

        return view;
    }

    private void onSetReminder() {
        Toast.makeText(getContext(), "OnSetReminder clicked", Toast.LENGTH_SHORT).show();
    }

    private void onCheckedOut() {
        // Toast.makeText(getContext(), "OnCheckedOut clicked", Toast.LENGTH_SHORT).show();

        // Check if user is authenticated
        if( !IsUserAuthenticated() ){
            RegisterUser();
        }
    }

    private void RegisterUser() {

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setIsSmartLockEnabled(false)
                        .setAvailableProviders(
                                Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                        new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()) )
                        .build(),
                RC_SIGN_IN);
    }

    private boolean IsUserAuthenticated() {
        // Initialize Firebase Auth
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if (mFirebaseUser != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if( requestCode==RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == RESULT_OK) {
                Toast.makeText(getContext(), "Sign In Ok", Toast.LENGTH_SHORT).show();

            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getContext(), "Sign In Canceled", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getContext(), response.describeContents(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private int getNumberOfDaysBeforeDueDate() {
        return 13;  // hardcoded for now - will need to make this a preference setting
    }
}
