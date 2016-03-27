package com.grability.android.test.vo;

public class CategoryVO {
    private int ID;
    private String label;

    public int getID() {
        return ID;
    }

    public CategoryVO(int ID, String label) {
        this.ID = ID;
        this.label = label;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLabel() {
        return label;
    }



}
