package com.janfranco.vaultdwellersbase.entities;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class AccessToken {

    @SerializedName("access_token")
    private String token;

    @SerializedName("expiration")
    private int expiration;

    public String getToken() {
        return token;
    }

    public int getExpiration() {
        return expiration;
    }

}
