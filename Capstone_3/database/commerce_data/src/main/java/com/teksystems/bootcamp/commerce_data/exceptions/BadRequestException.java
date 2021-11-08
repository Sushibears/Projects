package com.teksystems.bootcamp.commerce_data.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    private String resourceName;
    private String fieldName;
    private Object fieldValue;
    private static final long serialVersionUID = 2l;

    public BadRequestException(String resourcename, String fieldname, Object fieldvalue){
        super(String.format("Your request to %s is invalid. Details; %s : '%s'", resourcename, fieldname,fieldvalue));
        this.resourceName = resourcename;
        this.fieldName = fieldname;
        this.fieldValue = fieldvalue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}
