package com.novomind.ecom.ichat.customisation.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, code = HttpStatus.NOT_FOUND)
public class ChatLayoutNotFoundException extends Exception{

  public ChatLayoutNotFoundException(String id) {
    super(String.format("Could not find chat layout with id %d.", id));
  }

  public ChatLayoutNotFoundException() {
    super("Could not find chat layout");
  }
}
