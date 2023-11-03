package com.lotfi.productservice.exceptions;

public class ResourceNotFound extends RuntimeException {
    public ResourceNotFound(long message){
        super(String.format("%id not exist",message));
    }
}
