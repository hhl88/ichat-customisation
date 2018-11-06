package com.novomind.ecom.ichat.customisation.exceptions;


import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Value
@ResponseStatus(value = HttpStatus.NOT_FOUND, code = HttpStatus.NOT_FOUND)
public class IAgentServerNotFoundException extends Exception{

  public IAgentServerNotFoundException(String id) {
    super(String.format("Could not find iagent server with id %d.", id));
  }

  public IAgentServerNotFoundException() {
    super("Could not find iagetn server");
  }
}
