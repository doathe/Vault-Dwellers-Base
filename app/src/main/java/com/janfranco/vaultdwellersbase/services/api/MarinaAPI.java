package com.janfranco.vaultdwellersbase.services.api;

import com.janfranco.vaultdwellersbase.entities.AccessToken;
import com.janfranco.vaultdwellersbase.entities.Dtos.UserLoginDto;
import com.janfranco.vaultdwellersbase.entities.Result;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MarinaAPI {

    @POST("user/login")
    Call<Result<AccessToken>> login(@Body UserLoginDto userLoginDto);

}
