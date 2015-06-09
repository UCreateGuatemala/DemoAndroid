package com.derek.ucreate.ucreate;

/**
 * Created by Derek on 5/27/2015.
 */
public class Catalog {

    private String backgroundColor;
    private String logoResource;
    private int logoSize;
    private int logoPosition;
    private String title;
    private String titlePosition;

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getLogoResource() {
        return logoResource;
    }

    public int getLogoSize(){
        return logoSize;
    }

    public int getLogoPosition(){
        return logoPosition;
    }

    public void setBackgroundColor(String newBackgroundColor) {
        backgroundColor = newBackgroundColor;
    }

    public void setLogoResource(String newLogoResource) {
        logoResource = newLogoResource;
    }

    public void setLogoSize(int newLogoSize){
        logoSize = newLogoSize;
    }

    public void setLogoPosition(int newLogoPosition){
        logoPosition = newLogoPosition;
    }
}
