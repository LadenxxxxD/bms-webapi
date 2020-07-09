package com.springboot.entity;

import java.io.Serializable;

public class UploadedFile implements Serializable {

    private static final long serialVersionUID = -38331060124340967L;
    private String name;
    private String status;
    private String url;
    private String thumbUrl;

    public UploadedFile() {
        super();
    }

    public UploadedFile(String name, String status, String url) {
        super();
        this.name = name;
        this.status = status;
        this.url = url;
    }

    public UploadedFile(String name, String status, String url, String thumbUrl) {
        super();
        this.name = name;
        this.status = status;
        this.url = url;
        this.thumbUrl = thumbUrl;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

}
