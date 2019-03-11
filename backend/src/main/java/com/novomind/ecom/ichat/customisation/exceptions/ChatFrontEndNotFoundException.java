package com.novomind.ecom.ichat.customisation.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, code = HttpStatus.NOT_FOUND)
public class ChatFrontEndNotFoundException extends Exception{


  public ChatFrontEndNotFoundException(String id) {
    super(String.format("Could not find chat frontend with id %d.", id));
  }

  public ChatFrontEndNotFoundException() {
    super("Could not find chat frontend");
  }

}
