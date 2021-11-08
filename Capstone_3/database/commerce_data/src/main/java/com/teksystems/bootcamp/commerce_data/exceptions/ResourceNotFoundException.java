package com.teksystems.bootcamp.commerce_data.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private String resourceName;
    private String fieldName;
    private Object fieldValue;
    private static final long serialVersionUID = 1l;

    public ResourceNotFoundException(String resourcename, String fieldname, Object fieldvalue){
        super(String.format("%s not found with %s : '%s'", resourcename, fieldname,fieldvalue));
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
