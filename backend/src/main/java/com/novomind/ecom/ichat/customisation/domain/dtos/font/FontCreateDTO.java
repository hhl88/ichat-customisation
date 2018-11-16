package com.novomind.ecom.ichat.customisation.domain.dtos.font;

import com.novomind.ecom.ichat.customisation.domain.datatypes.FontStyle;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FontCreateDTO {
  
  @ApiModelProperty(required = true)
  @NotNull
  private String fontFamily;
  
  @ApiModelProperty(required = true)
  @NotNull
  private int fontSize;
  
  private List<FontStyle> fontStyles;
}
