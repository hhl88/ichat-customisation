package com.novomind.ecom.ichat.customisation.domain.dtos.font;

import com.novomind.ecom.ichat.customisation.domain.datatypes.FontStyle;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class FontUpdateDTO {
  
  @ApiModelProperty(required = true)
  @NotNull
  private String fontFamily;
  
  @ApiModelProperty(required = true)
  @NotNull
  private String fontSize;
  
  private List<FontStyle> fontStyles;
}
