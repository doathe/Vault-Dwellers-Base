package com.janfranco.vaultdwellersbase.services.api;

import com.janfranco.vaultdwellersbase.entities.AccessToken;
import com.janfranco.vaultdwellersbase.entities.Dtos.UserLoginDto;
import com.janfranco.vaultdwellersbase.entities.Result;
import com.janfranco.vaultdwellersbase.helpers.ErrorParser;
import com.janfranco.vaultdwellersbase.helpers.ResultCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MarinaService {

    private static MarinaService instance;

    private final MarinaAPI marinaAPI;

    private MarinaService() {
        String baseUrl = "http://37.148.209.192:3003/api/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create()).build();
        marinaAPI = retrofit.create(MarinaAPI.class);
    }

    public static MarinaService getInstance() {
        if (instance == null)
            instance = new MarinaService();

        return instance;
    }

    public void login(UserLoginDto userLoginDto, ResultCallback<AccessToken> resultCallback) {
        Call<Result<AccessToken>> call = marinaAPI.login(userLoginDto);
        call.enqueue(new Callback<Result<AccessToken>>() {
            @Override
            public void onResponse(Call<Result<AccessToken>> call, Response<Result<AccessToken>> response) {
                if (response.isSuccessful())
                    resultCallback.onSuccess(response.body().getData());
                else {
                    try {
                        String errorMessage = new ErrorParser().getMessage(response.errorBody().string());
                        resultCallback.onFailure(errorMessage);
                    } catch (IOException e) {
                        resultCallback.onFailure(e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<Result<AccessToken>> call, Throwable t) {
                resultCallback.onFailure(t.getMessage());
            }
        });
    }

}
