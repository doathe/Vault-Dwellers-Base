package com.janfranco.vaultdwellersbase.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserList {

    @SerializedName("users")
    List<User> users;

    public List<User> getUsers() {
        return users;
    }

}
