package com.work.app.exception;

import static java.text.MessageFormat.format;

public class ObjectNotFoundException extends RuntimeException {

    private static final String OBJ_NOT_FOUND_MESSAGE = "{0} not found!";

    public ObjectNotFoundException(String entityName) {
        super(format(OBJ_NOT_FOUND_MESSAGE, entityName));
    }

}
