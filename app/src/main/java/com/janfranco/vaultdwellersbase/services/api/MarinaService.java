package com.janfranco.vaultdwellersbase.services.api;

import com.janfranco.vaultdwellersbase.entities.AccessToken;
import com.janfranco.vaultdwellersbase.entities.Dtos.UserLoginDto;
import com.janfranco.vaultdwellersbase.entities.Dtos.UserRegisterDto;
import com.janfranco.vaultdwellersbase.entities.Result;
import com.janfranco.vaultdwellersbase.helpers.RepositoryBase;
import com.janfranco.vaultdwellersbase.helpers.ResultCallback;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MarinaService {

    private static MarinaService instance;

    private final MarinaAPI mMarinaAPI;
    private final RepositoryBase mRepositoryBase;

    private MarinaService() {
        String baseUrl = "http://37.148.209.192:3003/api/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create()).build();
        mMarinaAPI = retrofit.create(MarinaAPI.class);
        mRepositoryBase = new RepositoryBase();
    }

    public static MarinaService getInstance() {
        if (instance == null)
            instance = new MarinaService();

        return instance;
    }

    public void login(UserLoginDto userLoginDto, ResultCallback<AccessToken> resultCallback) {
        Call<Result<AccessToken>> call = mMarinaAPI.login(userLoginDto);
        mRepositoryBase.query(call, resultCallback);
    }

    public void register(UserRegisterDto userRegisterDto, ResultCallback<AccessToken> resultCallback) {
        Call<Result<AccessToken>> call = mMarinaAPI.register(userRegisterDto);
        mRepositoryBase.query(call, resultCallback);
    }

}
