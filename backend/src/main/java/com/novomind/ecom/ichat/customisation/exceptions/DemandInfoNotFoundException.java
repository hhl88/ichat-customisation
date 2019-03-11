package com.novomind.ecom.ichat.customisation.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, code = HttpStatus.NOT_FOUND)
public class DemandInfoNotFoundException extends Exception{

  public DemandInfoNotFoundException(String id) {
    super(String.format("Could not find demand info with id %d.", id));
  }

  public DemandInfoNotFoundException() {
    super("Could not find demand indo");
  }
}
