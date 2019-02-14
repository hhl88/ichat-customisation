package com.novomind.ecom.ichat.customisation.domain.dtos.chat.layout;

import com.novomind.ecom.ichat.customisation.domain.datatypes.*;
import com.novomind.ecom.ichat.customisation.domain.dtos.font.FontDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString
public class ChatLayoutCreateDTO {

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

//    @ApiModelProperty
//    private MultipartFile logo;

    @ApiModelProperty(required = true)
    @NotNull
    private BackgroundDisplay backgroundType;

//    @ApiModelProperty
//    private MultipartFile backgroundImg;

    @ApiModelProperty(required = true)
    @NotNull
    @JsonProperty("font")
    private FontDTO font;

}
