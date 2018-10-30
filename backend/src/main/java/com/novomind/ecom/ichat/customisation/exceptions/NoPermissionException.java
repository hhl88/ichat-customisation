package com.novomind.ecom.ichat.customisation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, code = HttpStatus.UNAUTHORIZED)
public class NoPermissionException extends RuntimeException {
  
    public NoPermissionException() {
        super();
    }

    public NoPermissionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoPermissionException(String message) {
        super(message);
    }

    public NoPermissionException(Throwable cause) {
        super(cause);
    }
    
}
