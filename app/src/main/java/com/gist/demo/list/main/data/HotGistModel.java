package com.gist.demo.list.main.data;

import java.io.Serializable;

public class HotGistModel implements Serializable {

    public String id;
    public String url;


    public boolean isSelect;

    public boolean isSelect() {
        return isSelect;
    }

    public int getCountShared() {
        return countShared;
    }

    public void setCountShared(int countShared) {
        this.countShared = countShared;
    }

    public int countShared;

    public void setSelect(boolean select) {
        isSelect = select;
    }


    public HotGistOwnerModel getOwner() {
        return owner;
    }

    public void setOwner(HotGistOwnerModel owner) {
        this.owner = owner;
    }

    public HotGistOwnerModel owner;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String hotGistUrl) {
        this.url = hotGistUrl;
    }
}