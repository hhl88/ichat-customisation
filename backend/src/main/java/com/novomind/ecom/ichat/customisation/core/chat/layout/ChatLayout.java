package com.novomind.ecom.ichat.customisation.core.chat.layout;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.novomind.ecom.ichat.customisation.domain.datatypes.FontStyle;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class ChatLayout implements Serializable {

  private String          id;
  private String          name;
  private String          displayType;
  private String          textInputType;
  private String          buttonType;
  private String          logo;
  private String          backgroundImg;
  private String          backgroundType;
  private String          fontFamily;
  private int             fontSize;
  private List<FontStyle> fontStyles;
  
  public ChatLayout() {}

}
