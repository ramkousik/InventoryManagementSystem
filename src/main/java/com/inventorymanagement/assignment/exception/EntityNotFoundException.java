package com.inventorymanagement.assignment.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntityNotFoundException extends RuntimeException {



    public EntityNotFoundException(String message) {
        super(message);
    }
}
