package com.janfranco.vaultdwellersbase.services.api;

import com.janfranco.vaultdwellersbase.entities.AccessToken;
import com.janfranco.vaultdwellersbase.entities.Dtos.UserLoginDto;
import com.janfranco.vaultdwellersbase.entities.Dtos.UserRegisterDto;
import com.janfranco.vaultdwellersbase.entities.Result;
import com.janfranco.vaultdwellersbase.entities.User;
import com.janfranco.vaultdwellersbase.entities.UserList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface MarinaAPI {

    @POST("user/login")
    Call<Result<AccessToken>> login(@Body UserLoginDto userLoginDto);

    @POST("user/register")
    Call<Result<AccessToken>> register(@Body UserRegisterDto userRegisterDto);

    @GET("user/users")
    Call<Result<UserList>> getUsers(@Header("Authorization") String accessToken);

}
