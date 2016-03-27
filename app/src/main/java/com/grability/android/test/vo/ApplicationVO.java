package com.grability.android.test.vo;

public class ApplicationVO {
    private int ID;
    private int catID;
    private String name;
    private String imageAURL;
    private String imageBURL;
    private String imageCURL;
    private String summary;
    private String price;
    private String title;
    private String artist;
    private String releaseDate;

    public ApplicationVO(int ID, int catID, String name, String imageAURL, String imageBURL, String imageCURL, String summary, String title, String artist, String releaseDate) {
        this.ID = ID;
        this.catID = catID;
        this.name = name;
        this.imageAURL = imageAURL;
        this.imageBURL = imageBURL;
        this.imageCURL = imageCURL;
        this.summary = summary;
        this.title = title;
        this.artist = artist;
        this.releaseDate = releaseDate;
    }


    public int getID() {
        return ID;
    }

    public int getCatID() {
        return catID;
    }

    public String getName() {
        return name;
    }

    public String getImageAURL() {
        return imageAURL;
    }

    public String getImageBURL() {
        return imageBURL;
    }

    public String getImageCURL() {
        return imageCURL;
    }

    public String getSummary() {
        return summary;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
