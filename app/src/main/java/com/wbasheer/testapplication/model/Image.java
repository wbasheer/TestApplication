package com.wbasheer.testapplication.model;

import java.io.Serializable;

/**
 * Created by waleed.basheer on 10/15/2014.
 */
public class Image implements Serializable {

    public static final String defaultImageName = "unknown_name";
    public static final String defaultImageTitle = "unknown_title";
    public static final String defaultImageDescription = "unknown_description";

    private String name;
    private String title;
    private String description;

    public String getName() {

        return (name == null) ? defaultImageName : name;
    }

    public String getTitle() {

        return (title == null) ? defaultImageTitle : title;
    }

    public String getDescription() {

        return (description == null) ? defaultImageDescription : description;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public void setDescription(String description) {

        this.description = description;
    }

}
