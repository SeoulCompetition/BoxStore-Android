package com.b05studio.boxstore.model;

import java.io.Serializable;

/**
 * Created by young on 2017-10-04.
 */

public class BoxstoreUser implements Serializable {

    private String uId;
    private String email;
    private String photoURL;
    private String name;
    private String userToken;

    public BoxstoreUser() {

    }

    public BoxstoreUser(String uId, String email, String phothURL, String name, String userToken) {
        this.uId = uId;
        this.email = email;
        this.photoURL = phothURL;
        this.name = name;
        this.userToken = userToken;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
