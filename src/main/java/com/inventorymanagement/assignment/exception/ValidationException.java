package com.inventorymanagement.assignment.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidationException extends RuntimeException{


    public ValidationException(String message) {
        super(message);
    }
}
