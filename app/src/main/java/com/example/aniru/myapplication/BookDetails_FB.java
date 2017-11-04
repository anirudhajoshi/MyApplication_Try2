package com.example.aniru.myapplication;

/**
 * Created by aniru on 10/12/2017.
 */



public class BookDetails_FB {
    // Default constructor
    public BookDetails_FB() {
        // Default constructor required for calls to Firebase DataSnapshot.getValue(BookDetails_FB.class)
    }

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



    /*
    // @SerializedName("fbKey")
    // @Expose
    // private String fbKey;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("authors")
    @Expose
    private List<String> authors = null;

    @SerializedName("pageCount")
    @Expose
    private Integer pageCount;
    @SerializedName("categories")
    @Expose
    private List<String> categories = null;

    @SerializedName("ISBN")
    @Expose
    private String isbn;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    // public String getFbKey() {
    // return fbKey;
    // }

    // public void setFbKey(String fbKey) {
    //     this.fbKey = fbKey;
    // }
    */
}
