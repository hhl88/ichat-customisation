package com.novomind.ecom.ichat.customisation.domain.dtos.font;

import java.util.Set;

import javax.validation.constraints.NotNull;

import  com.novomind.ecom.ichat.customisation.domain.datatypes.FontStyle;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FontCreateDTO {
  
  @ApiModelProperty(required = true)
  @NotNull
  private String fontFamily;
  
  @ApiModelProperty(required = true)
  @NotNull
  private int fontSize;
  
  private Set<FontStyle> fontStyles;
}
