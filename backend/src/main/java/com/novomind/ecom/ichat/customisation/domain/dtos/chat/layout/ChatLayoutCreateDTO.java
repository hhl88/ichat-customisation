package com.novomind.ecom.ichat.customisation.domain.dtos.chat.layout;

import javax.validation.constraints.NotNull;

import com.novomind.ecom.ichat.customisation.domain.datatypes.BackgroundDisplay;
import com.novomind.ecom.ichat.customisation.domain.datatypes.ButtonDisplay;
import com.novomind.ecom.ichat.customisation.domain.datatypes.LayoutDisplay;
import com.novomind.ecom.ichat.customisation.domain.datatypes.TextAreaDisplay;
import com.novomind.ecom.ichat.customisation.domain.dtos.font.FontCreateDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatLayoutCreateDTO {

  @ApiModelProperty(required = true)
  @NotNull
  private String userId;
  
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
  private FontCreateDTO fontCreateDTO;

}
