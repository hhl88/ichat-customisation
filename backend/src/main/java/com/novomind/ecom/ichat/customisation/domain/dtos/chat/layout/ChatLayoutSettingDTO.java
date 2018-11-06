package com.novomind.ecom.ichat.customisation.domain.dtos.chat.layout;

import com.novomind.ecom.ichat.customisation.domain.datatypes.*;
import com.novomind.ecom.ichat.customisation.domain.dtos.font.FontCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.font.FontDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ChatLayoutSettingDTO {
  
  private LayoutDisplay layoutDisplay;
  private TextAreaDisplay textAreaDisplay;
  private ButtonDisplay buttonDisplay;
  private String logoImage;
  private BackgroundDisplay backgroundDisplay;
  private String backgroundImage;
  private Font font;

}
