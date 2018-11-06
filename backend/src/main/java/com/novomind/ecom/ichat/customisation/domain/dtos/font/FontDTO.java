package com.novomind.ecom.ichat.customisation.domain.dtos.font;

import com.novomind.ecom.ichat.customisation.domain.datatypes.FontStyle;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class FontDTO {

  private String fontFamily;
  private int fontSize;
  private List<FontStyle> fontStyles;
}
