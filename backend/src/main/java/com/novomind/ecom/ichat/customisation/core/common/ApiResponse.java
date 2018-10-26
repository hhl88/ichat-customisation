package com.novomind.ecom.ichat.customisation.core.common;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ApiResponse implements Serializable{
  private boolean success;
  private String msg;

  private ApiResponse() {
  }

  public ApiResponse(boolean success, String msg) {
      this.success = success;
      this.msg = msg;
  }

  public ApiResponse(boolean success) {
      this.success = success;
  }

  public boolean isSuccess() {
      return success;
  }

  public String getMsg() {
      return msg;
  }
}
