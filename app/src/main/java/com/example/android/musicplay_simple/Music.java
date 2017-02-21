package com.example.android.musicplay_simple;

/**
 * Created by 재은 on 2017-02-15.
 */

public class Music {
    private String mtitle;
    private String martist;
    private String mgenre;

    private int mAudioResourceId;
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    private static final int NO_IMAGE_PROVIDED = -1;


    public Music(String title, String artist,String genre, int imageResourceId,int audioResourceId) {
        mtitle = title;
        martist = artist;
        mgenre = genre;
        mImageResourceId = imageResourceId;
        mAudioResourceId = audioResourceId;

    }

    public Music(String title,String artist, int imageResourceId) {
        mtitle = title;
        martist = artist;
        mImageResourceId = imageResourceId;
    }

    public String getmtitle() {
        return mtitle;
    }

    public String getmartist() {
        return martist;
    }

    public String getMgenre(){return mgenre;}

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    public int getAudioResourceId(){
        return mAudioResourceId;
    }

    @Override
    public String toString() {
        return "Music{"+"mtitle='"+mtitle+'\''+
                ",martist='"+martist+'\''+
                ",mAudioResourceId="+mAudioResourceId+",mImageResourceId="+mImageResourceId+'}';
    }
}
