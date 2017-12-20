package com.example.aniru.myapplication.BooksAPI;

/**
 * Created by aniru on 9/20/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchInfo {

    @SerializedName("textSnippet")
    @Expose
    private String textSnippet;

    public String getTextSnippet() {
        return textSnippet;
    }

    public void setTextSnippet(String textSnippet) {
        this.textSnippet = textSnippet;
    }

}