package com.janfranco.vaultdwellersbase.helpers;

public interface ResultCallback<T> {

    void onSuccess(T data);
    void onFailure(String message);

}
