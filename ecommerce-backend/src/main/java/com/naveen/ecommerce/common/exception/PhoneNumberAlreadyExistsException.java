package com.naveen.ecommerce.common.exception;

public class PhoneNumberAlreadyExistsException extends RuntimeException{
    public PhoneNumberAlreadyExistsException(String msg){
        super(msg);
    }
}
