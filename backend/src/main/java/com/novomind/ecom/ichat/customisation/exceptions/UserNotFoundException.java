package com.novomind.ecom.ichat.customisation.exceptions;


import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Value
@ResponseStatus(value = HttpStatus.NOT_FOUND, code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends Exception{

  public UserNotFoundException(String id) {
    super(String.format("Could not find user with id %d.", id));
  }

  public UserNotFoundException() {
    super("Could not find user");
  }
}
