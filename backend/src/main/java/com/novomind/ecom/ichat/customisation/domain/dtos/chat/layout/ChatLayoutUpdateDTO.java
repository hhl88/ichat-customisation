package com.novomind.ecom.ichat.customisation.domain.dtos.chat.layout;

import com.novomind.ecom.ichat.customisation.domain.datatypes.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ChatLayoutUpdateDTO {

  @ApiModelProperty(required = true)
  @NotNull
  private String name;
  
  @ApiModelProperty(required = true)
  @NotNull
  private LayoutDisplay displayType;
  
  @ApiModelProperty(required = true)
  @NotNull
  private TextAreaDisplay textInputType;

  @ApiModelProperty(required = true)
  @NotNull
  private ButtonDisplay buttonType;

  @ApiModelProperty
  private String logo;

  @ApiModelProperty(required = true)
  @NotNull
  private BackgroundDisplay backgroundType;

  @ApiModelProperty
  private String backgroundImg;

  @ApiModelProperty(required = true)
  @NotNull
  private Font font;

}
