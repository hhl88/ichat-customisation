package com.novomind.ecom.ichat.customisation.domain.dtos.chat.layout;

import com.novomind.ecom.ichat.customisation.domain.datatypes.*;
import com.novomind.ecom.ichat.customisation.domain.dtos.font.FontDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.Map;

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

  @ApiModelProperty(required = true)
  @NotNull
  private BackgroundDisplay backgroundType;

  @ApiModelProperty(required = true)
  @NotNull
  @JsonProperty("font")
  private FontDTO font;

    @ApiModelProperty(required = true)
    @NotNull
    @JsonProperty("bubbleStyle")
    private Map<String, Bubble> bubbleStyle;

}
