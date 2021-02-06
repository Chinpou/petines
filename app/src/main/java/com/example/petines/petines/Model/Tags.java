package com.example.petines.petines.Model;

/**
 * Created by petines on 5/19/2018.
 */

public class Tags {

    private int tid;
    private String Tag;


    @Override
    public String toString() {
        return  Tag ;
    }

    public Tags() {
    }

    public Tags(int tid, String tag) {
        this.tid = tid;
        Tag = tag;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTag() {
        return Tag;
    }

    public void setTag(String tag) {
        Tag = tag;
    }

    public String getTags() {
        return Tag;
    }

    public void setTags(String Tag) {
        this.Tag = Tag;
    }
}
