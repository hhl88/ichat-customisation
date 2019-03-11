package com.novomind.ecom.ichat.customisation.core.demandInfo;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.novomind.ecom.ichat.customisation.domain.dtos.demandinfo.DemandInfoItemDTO;


public class DemandInfoItem {
    private String name;
    private String example;

    @JsonProperty
    private boolean required;

    private DemandInfoItem() {
    }

    public static DemandInfoItem of(DemandInfoItemDTO dto) {
        return new DemandInfoItem()
                .setName(dto.getName())
                .setExample(dto.getExample())
                .setRequired(dto.required());
    }

    public String getName() {
        return name;
    }

    public String getExample() {
        return example;
    }

    @JsonProperty
    public boolean required() {
        return required;
    }

    public DemandInfoItem setName(String name) {
        this.name = name;
        return this;
    }

    public DemandInfoItem setExample(String example) {
        this.example = example;
        return this;
    }

    public DemandInfoItem setRequired(boolean required) {
        this.required = required;
        return this;
    }
}
