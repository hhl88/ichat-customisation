package com.novomind.ecom.ichat.customisation.domain.dtos.chat.layout;

import javax.validation.constraints.NotNull;

import com.novomind.ecom.ichat.customisation.domain.datatypes.*;
import com.novomind.ecom.ichat.customisation.domain.dtos.font.FontCreateDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ChatLayoutCreateDTO {

  @ApiModelProperty(required = true)
  @NotNull
  private String name;
  
  @ApiModelProperty(required = true)
  @NotNull
  private LayoutDisplay layoutDisplay;
  
  @ApiModelProperty(required = true)
  @NotNull
  private TextAreaDisplay textAreaDisplay;

  @ApiModelProperty(required = true)
  @NotNull
  private ButtonDisplay buttonDisplay;

  private String logoImage;

  @ApiModelProperty(required = true)
  @NotNull
  private BackgroundDisplay backgroundDisplay;

  private String backgroundImage;

  @ApiModelProperty(required = true)
  @NotNull
  private Font font;

}
