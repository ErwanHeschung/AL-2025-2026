package org.al.form.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class FormNotFoundException extends CustomException {

    public FormNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

    public FormNotFoundException() {
        super("Form not found", HttpStatus.NOT_FOUND);
    }
}
