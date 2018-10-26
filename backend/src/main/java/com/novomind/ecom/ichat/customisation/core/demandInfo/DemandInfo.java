package com.novomind.ecom.ichat.customisation.core.demandInfo;

public class DemandInfo {
  
  private String name;
  private boolean isRequired;
  
  public DemandInfo() {
    
  }

  public DemandInfo(String name, boolean isRequired) {
    this.name = name;
    this.isRequired = isRequired;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isRequired() {
    return isRequired;
  }

  public void setRequired(boolean isRequired) {
    this.isRequired = isRequired;
  }
  
}
