package com.example.aniru.myapplication;

/**
 * Created by aniru on 10/17/2017.
 */
import com.example.aniru.myapplication.BooksAPI.BookDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface GoogleBooksAPI {

    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter
    // Get movies by user picked order - popular or top rated
    @GET("volumes")
    Call<BookDetails> getBookDetails(@Query("q") String isbn);
}