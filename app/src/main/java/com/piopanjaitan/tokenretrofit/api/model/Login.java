package com.piopanjaitan.tokenretrofit.api.model;

public class Login {
    /*private String user;
    private String password;*/

    private String loginid;
    private String password;
    private String site;


    /*public Login(String user, String password) {
        this.user = user;
        this.password = password;
    }*/

    public Login (String loginid, String password, String site) {
        this.loginid = loginid;
        this.password = password;
        this.site = site;
    }
}
