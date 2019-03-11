package com.novomind.ecom.ichat.customisation.domain.dtos.chat.layout;

import com.novomind.ecom.ichat.customisation.domain.datatypes.*;
import com.novomind.ecom.ichat.customisation.domain.dtos.font.FontDTO;
import io.swagger.annotations.ApiModelProperty;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.Map;

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
    private FontDTO font;


    @ApiModelProperty(required = true)
    @NotNull
    private Map<String, Bubble> bubbleStyle;


    public ChatLayoutCreateDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LayoutDisplay getDisplayType() {
        return displayType;
    }

    public void setDisplayType(LayoutDisplay displayType) {
        this.displayType = displayType;
    }

    public TextAreaDisplay getTextInputType() {
        return textInputType;
    }

    public void setTextInputType(TextAreaDisplay textInputType) {
        this.textInputType = textInputType;
    }

    public ButtonDisplay getButtonType() {
        return buttonType;
    }

    public void setButtonType(ButtonDisplay buttonType) {
        this.buttonType = buttonType;
    }

    public BackgroundDisplay getBackgroundType() {
        return backgroundType;
    }

    public void setBackgroundType(BackgroundDisplay backgroundType) {
        this.backgroundType = backgroundType;
    }

    @JsonProperty("font")
    public FontDTO getFont() {
        return font;
    }

    public void setFont(FontDTO font) {
        this.font = font;
    }

    @JsonProperty("bubbleStyle")
    public Map<String, Bubble> getBubbleStyle() {
        return bubbleStyle;
    }

    public void setBubbleStyle(Map<String, Bubble> bubbleStyle) {
        this.bubbleStyle = bubbleStyle;
    }
}
