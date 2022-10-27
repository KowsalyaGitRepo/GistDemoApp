package com.gist.demo.list.main.data;

import java.io.Serializable;

public class HotGistOwnerModel implements Serializable {

    public String login;

    public String getlogin() {
        return login;
    }

    public void setlogin(String login) {
        this.login = login;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String description;

  }