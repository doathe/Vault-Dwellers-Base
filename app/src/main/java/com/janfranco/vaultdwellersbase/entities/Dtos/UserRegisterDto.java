package com.janfranco.vaultdwellersbase.entities.Dtos;

public class UserRegisterDto {

    private final String email;
    private final String password;
    private final String name;

    public UserRegisterDto(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

}
