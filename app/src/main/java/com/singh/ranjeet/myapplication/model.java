package com.singh.ranjeet.myapplication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class model {


    @SerializedName("id")
    @Expose
    private Integer id;


    @SerializedName("scheduledDate")
    @Expose
    private long scheduledDate;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("status ")
    @Expose
    private String status;

    public model(Integer id, long scheduledDate, String description, String status) {
        this.id = id;
        this.scheduledDate = scheduledDate;
        this.description = description;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(long scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

