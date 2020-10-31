package com.janfranco.vaultdwellersbase.helpers;

public class UnknownTypeException extends Exception {

    public UnknownTypeException() {
        super("Unknown type");
    }

    public UnknownTypeException(String errorMessage) {
        super(errorMessage);
    }

}
