package com.inventorymanagement.assignment.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductNotFoundException extends RuntimeException{


    public ProductNotFoundException(String message){
        super(message);
    }
}
