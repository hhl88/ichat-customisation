package com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend;

import javax.validation.constraints.NotNull;

import com.novomind.ecom.ichat.customisation.domain.datatypes.Connection;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FrontEndUpdateDTO {
  
  @ApiModelProperty(required = true)
  @NotNull
  private String name;
  
  @ApiModelProperty(required = true)
  @NotNull
  private Connection connectionType;
  
}
