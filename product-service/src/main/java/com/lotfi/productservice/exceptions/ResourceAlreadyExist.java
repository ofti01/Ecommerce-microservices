package com.lotfi.productservice.exceptions;

public class ResourceAlreadyExist extends RuntimeException {
    public ResourceAlreadyExist(long id) {
        super(String.format("%s already exist",id));
    }
}
