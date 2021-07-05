package com.dave.inventorymanagement.responses;

public class AuthToken {

    private String access_token;
    private String jwt;

    public AuthToken(String access_token, String jwt) {
        this.access_token = access_token;
        this.jwt = jwt;
    }

    public AuthToken() {
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
