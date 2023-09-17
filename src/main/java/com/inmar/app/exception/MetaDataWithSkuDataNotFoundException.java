package com.inmar.app.exception;

public class MetaDataWithSkuDataNotFoundException extends RuntimeException{
    private String message;

    public MetaDataWithSkuDataNotFoundException() {}

    public MetaDataWithSkuDataNotFoundException(String msg)
    {
        super(msg);
        this.message = msg;
    }
}
