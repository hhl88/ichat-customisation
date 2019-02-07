package com.novomind.ecom.ichat.customisation.core.demandInfo;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.novomind.ecom.ichat.customisation.domain.dtos.demandinfo.DemandInfoItemDTO;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DemandInfoItem {
    private String name;
    private String example;

    @JsonProperty
    private boolean required;

    public static DemandInfoItem of(DemandInfoItemDTO dto) {
        return DemandInfoItem.builder()
                .name(dto.getName())
                .example(dto.getExample())
                .required(dto.required())
                .build();
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
}
