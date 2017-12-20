package com.example.aniru.myapplication.BooksAPI;

/**
 * Created by aniru on 9/20/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pdf {

    @SerializedName("isAvailable")
    @Expose
    private Boolean isAvailable;

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

}